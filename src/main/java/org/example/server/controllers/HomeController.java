package org.example.server.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.models.User;

import org.example.server.repositories.UserRepository;
import org.example.server.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final UserService userService;

    private String mode = "false";
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findUserByEmail(userName);
        model.addAttribute("user", user);
        if(user.getProfilePhotoUrl() != null)  mode = "true";
        model.addAttribute("mode", mode);
        return "Users/MyProfile";
    }

    @RequestMapping("/settings")
    public String Settings(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findUserByEmail(userName);
        model.addAttribute("user", user);
        if(user.getProfilePhotoUrl() != null)  mode = "true";
        model.addAttribute("mode", mode);
        return "Home/Settings";
    }

    @PostMapping("/editAccount")
    public String AccountEdit(@RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "lastName") String lastName,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "gender") String gender,
                              @RequestParam(name = "profile") String profile
                              ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findUserByEmail(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(gender);
        user.setProfile(profile);
        userRepository.save(user);
        return "redirect:/settings";
    }

    @RequestMapping(value = "/Notification", method = RequestMethod.GET)
    public String Notification(){
        return "Home/Notification";
    }

    @GetMapping("/search")
    public String users(@RequestParam(name = "title", required = false) String title, Model model) {
        List<User> users = userService.listUser(title);
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
            String uploadDir = "/home/andrei/Documents/Projekt/Work/SocialClub/target/classes/static/img/Screen";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            String userName = authentication.getName();
            User user = userRepository.findUserByEmail(userName);

            if (Files.exists(filePath)) {
                user.setFileName(fileName);
                userRepository.save(user);
                return "redirect:/settings";
            }

            Files.copy(file.getInputStream(), filePath);
            user.setProfilePhotoUrl(filePath.toString());
            user.setFileName(fileName);
            userRepository.save(user);
            return "redirect:/settings";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error-page";
        }
    }

    @GetMapping("/account/{id}")
    public String supportDelete(@PathVariable Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "Users/user-info";
    }
}
