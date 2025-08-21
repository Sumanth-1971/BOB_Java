package com.bob.masterdata.Service;

import com.bob.db.entity.User;
import com.bob.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        try {
            user.setCreated_date(LocalDateTime.now());
            user.setUpdated_date(LocalDateTime.now());
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getAllUsers() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch user");
        }
    }

    public User updateUser(int id,User user) {
        try {
            User user1=user;
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isPresent()) {
                user.setUserid(id);
                user.setUpdated_date(LocalDateTime.now());
                userRepository.save(user);
                return user;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public User deleteUser(int id) {
        try {
            if (userRepository.existsById(id)) {
                User user=userRepository.findById(id).get();
                userRepository.deleteById(id);
                return user;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
