package org.example.server.service;

import org.example.server.models.User;
import org.example.server.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }

    public List<User> listUser(String title) {
        Set<User> users = new HashSet<>();

        if (title != null) {
            List<User> usersByFirstName = userRepository.findUsersByPartialName(title);
            List<User> usersByLastName = userRepository.findUsersByPartialLast(title);

            if (usersByFirstName != null) {
                users.addAll(usersByFirstName);
            }
            if (usersByLastName != null) {
                users.addAll(usersByLastName);
            }
        } else {
            users.addAll(userRepository.findAll());
        }

        // Получаем имя текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        // Удаляем текущего пользователя из списка
        users.removeIf(user -> user.getEmail().equals(currentUserName));

        return new ArrayList<>(users);
    }

    public List<User> listUserById(Long id) {
        List<User> usersById= userRepository.findUsersById(id);
        return new ArrayList<>(usersById);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String name) {
        return userRepository.findUserByEmail(name);
    }


    public boolean findUser(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }


}
