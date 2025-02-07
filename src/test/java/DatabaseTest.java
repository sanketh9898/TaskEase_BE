package com.example.taskease;

import com.example.taskease.model.User;
import com.example.taskease.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest // Use @DataJpaTest for testing JPA repositories
@ActiveProfiles("test") // Activate the test profile
public class DatabaseTest {

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    @Test
    void testUserEntityPersistence() {
        // Create a User object
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password"); // This will be hashed by the service
        // Don't set ssoProvider/ssoId for this basic test

        // Save the user using the repository
        User savedUser = userRepository.save(user);

        // Assert that the user was saved and has an ID
        assertNotNull(savedUser.getUserId(), "User ID should not be null after saving");

        // Retrieve user from the database
        User retrievedUser = userRepository.findById(savedUser.getUserId()).orElse(null);
        assertNotNull(retrievedUser);
        assertTrue(userRepository.existsByUsername("testuser"));
        assertTrue(userRepository.existsByEmail("test@example.com"));

    }
}