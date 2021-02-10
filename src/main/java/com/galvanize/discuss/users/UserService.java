package com.galvanize.discuss.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User getById(Long userId) throws UserNotFoundException {
        try {
            return repository.findById(userId).get();
        } catch (NoSuchElementException exc) {
            throw new UserNotFoundException("User id does not exist.");
        }
    }
}
