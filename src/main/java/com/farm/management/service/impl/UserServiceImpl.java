package com.farm.management.service.impl;

import com.farm.management.model.User;
import com.farm.management.repository.UserRepository;
import com.farm.management.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        if(user.getPassword() != null) {
        	existingUser.setPassword(user.getPassword());
        }
        existingUser.setUsername(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setDoc(user.getDoc());
        existingUser.setPhone(user.getPhone());
        existingUser.setZipcode(user.getZipcode());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> findByCreated_by(Long created_by){
        return userRepository.findByCreated_by(created_by);
    }
}
