package com.ipv404.services;

import com.ipv404.models.User;
import com.ipv404.repositories.UserRepository;
import com.ipv404.exceptions.UserNotFoundException;
import com.ipv404.utils.UserSession;
import java.util.List;
import com.ipv404.models.UserRole;

public class UserService {
    private final UserRepository userRepository;
    private final UserSession userSession;

    public UserService() {
        this.userRepository = new UserRepository();
        this.userSession = UserSession.getInstance();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.delete(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid email or password");
        }
        userSession.setCurrentUser(user);
        return user;
    }

    public void logout() {
        userSession.clearSession();
    }

    public User getCurrentUser() {
        return userSession.getCurrentUser();
    }

    public boolean isLoggedIn() {
        return userSession.isLoggedIn();
    }

    public boolean isAdmin() {
        return userSession.isAdmin();
    }
} 