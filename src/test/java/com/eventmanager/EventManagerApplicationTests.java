package com.eventmanager;

import com.eventmanager.model.User;
import com.eventmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EventManagerApplicationTests {

    @Test
    void contextLoads() {
    }

    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Rollback(false)
    public static class UserRepositoryTests {
        @Autowired
        private UserRepository repo;
        @Autowired
        private TestEntityManager entityManager;

        @Test
        public void TestToCreateNewUser() {
            User user = new User();
            user.setEmail("Kelvin@gmail.com");
            user.setPassword("4567");
            user.setFirstName("Kelvin");
            user.setLastName("Clein");

            User savedUser = repo.save(user);

            User existUser = entityManager.find(User.class,savedUser.getId());
            assertThat( existUser.getEmail() ).isEqualTo(user.getEmail());

        }


    }
}
