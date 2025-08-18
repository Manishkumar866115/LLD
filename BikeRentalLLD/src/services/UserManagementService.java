package services;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManagementService {
    private static UserManagementService instance;
    private List<User> usersList;

    private UserManagementService() {
        this.usersList = new ArrayList<>();
    }

    public static UserManagementService getInstance() {
        if (instance == null) instance = new UserManagementService();
        return instance;
    }

    public boolean addUser(User user) {
        return usersList.add(user);
    }

    public boolean deductBalance(UUID userId, Double amount) {
        for (User u : usersList) {
            if (u.getUserId().equals(userId)) {
                u.deductBalance(amount);
                return true;
            }
        }
        return false;
    }

    public User getUserById(UUID userId) {
        return usersList.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
