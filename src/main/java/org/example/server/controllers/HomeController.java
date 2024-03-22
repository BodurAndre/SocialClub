package org.example.server.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.models.User;

import org.example.server.repositories.UserRepository;
import org.example.server.service.FriendNotificationService;
import org.example.server.service.FriendService;
import org.example.server.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.Notification;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final FriendService friendService;
    private final FriendNotificationService friendNotificationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "Home/home";
    }

    @RequestMapping(value = "/Message", method = RequestMethod.GET)
    public String message(){
        return "Home/Message";
    }

    @RequestMapping("/MyProfile")
    public String MyProfile(Model model){
        String userName = AuthenticationUser();
        User user = userRepository.findUserByEmail(userName);
        model.addAttribute("user", user);
        return "Users/MyProfile";
    }

    @RequestMapping("/settings")
    public String Settings(Model model){
        String userName = AuthenticationUser();
        User user = userRepository.findUserByEmail(userName);
        model.addAttribute("user", user);
        return "Home/Settings";
    }

    @GetMapping("/Notification")
    public String Notification(Model model){
        String userName = AuthenticationUser();
        User userLogin = userService.getUserByName(userName);
        List<Long> users = friendNotificationService.listNotificationById(userLogin.getId());
        List<User> userList = new ArrayList<>();
        if (users != null) {
            for(Long userId: users){
                User user = userService.getUserById(userId);
                userList.add(user);
            }
            model.addAttribute("users", userList);
            return "Home/Notification";
        } else {
            model.addAttribute("message", "No users found");
        }
        return "Home/Notification";
    }

    @GetMapping("/search")
    public String users(@RequestParam(name = "title", required = false) String title, Model model) {
        List<User> users = userService.listUser(title);
        String userName = AuthenticationUser();
        User userLogin = userService.getUserByName(userName);
        String Friend = "friend";

        for (User user : users) {
            if (friendService.friendshipExists(userLogin.getId(),user.getId())) {
                model.addAttribute("friend", Friend);
            }
        }
        if (users != null) {
            model.addAttribute("users", users);
        } else {
            model.addAttribute("message", "No users found");
        }
        return "Users/Users";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/settings";
        }

        try {
            String userName = AuthenticationUser();
            String uploadDir = "/home/andrei/Documents/Projekt/Work/SocialClub/target/classes/static/img/" + userName;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            User user = userRepository.findUserByEmail(userName);
            user.setProfilePhotoUrl("true");
            if (Files.exists(filePath)) {
                user.setFileName(fileName);
                userRepository.save(user);
                return "redirect:/settings";
            }

            Files.copy(file.getInputStream(), filePath);
            user.setFileName(fileName);
            userRepository.save(user);
            return "redirect:/settings";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error-page";
        }
    }

    private String AuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
