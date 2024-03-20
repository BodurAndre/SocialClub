package org.example.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.models.FriendNotification;
import org.example.server.models.Friends;
import org.example.server.models.User;
import org.example.server.repositories.UserRepository;
import org.example.server.service.FriendNotificationService;
import org.example.server.service.FriendService;
import org.example.server.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final FriendService friendService;
    private final FriendNotificationService friendNotificationService;

    @PostMapping("/editAccount")
    public String AccountEdit(@RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "lastName") String lastName,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "gender") String gender,
                              @RequestParam(name = "profile") String profile
    ){
        String userName = AuthenticationUser();
        User user = userRepository.findUserByEmail(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(gender);
        user.setProfile(profile);
        userRepository.save(user);
        return "redirect:/settings";
    }

    @GetMapping("/account/{id}")
    public String userInfo(@PathVariable Long id, Model model){
        String userName = AuthenticationUser();
        User userLogin = userService.getUserByName(userName);
        User user = userService.getUserById(id);
        FriendNotification friend =  friendNotificationService.friendshipExists(userLogin.getId(), id);
        model.addAttribute("user", user);
        model.addAttribute("friend", friend);
        return "Users/user-info";
    }

    @GetMapping("/friendAdd/{id}")
    public String friendAdd(@PathVariable Long id){
        String userName = AuthenticationUser();
        User user = userService.getUserByName(userName);
        Friends friends = new Friends();
        friends.setUserId(user.getId());
        friends.setFriendId(id);
        friendService.createFriends(friends);
        return "redirect:/account/"+id;
    }

    @GetMapping("/friendNotification/{id}")
    public String friendNotification(@PathVariable Long id){
        String userName = AuthenticationUser();
        User user = userService.getUserByName(userName);
        FriendNotification friends = new FriendNotification();
        friends.setUserId(user.getId());
        friends.setFriendId(id);
        friends.setStatus("expectation");
        friendNotificationService.creatNotification(friends);
        return "redirect:/account/"+id;
    }

    @GetMapping("/friendDelete/{id}")
    public String friendDelete(@PathVariable Long id){
        String userName = AuthenticationUser();
        User user = userService.getUserByName(userName);
        friendService.deleteFriends(user.getId(), id);
        friendNotificationService.deleteFriends(user.getId(), id);;
        return "redirect:/account/"+id;
    }

    private String AuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
