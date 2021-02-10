package com.galvanize.discuss;

import com.galvanize.discuss.users.User;
import com.galvanize.discuss.users.UserNotFoundException;
import com.galvanize.discuss.users.UserRepository;
import com.galvanize.discuss.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
public class UserServiceTest {
    private UserService service;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        service = new UserService(repository);
    }

    @Test
    void createNewUser_withUsername() {
        User user = new User("Charon");
        assertThat(service.save(user)).isNotNull();
        assertThat(user.getId()).isNotNull();
    }

    @Test
    void getUserById_exists() throws UserNotFoundException {
        User user = new User("Charon");
        service.save(user);
        assertThat(service.getById(user.getId())).isEqualTo(user);
    }

    @Test
    void getUserById_notExists() {
        assertThatThrownBy(() -> service.getById(789L))
                .isInstanceOf(UserNotFoundException.class);
    }
}
