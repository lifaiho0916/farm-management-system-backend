package com.farm.management.service;

import com.farm.management.model.User;

import java.util.List;

public interface  UserService {

    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    List<User> findByCreated_by(Long created_by);

    User updateUser(User user);

    void deleteUser(Long userId);
}
