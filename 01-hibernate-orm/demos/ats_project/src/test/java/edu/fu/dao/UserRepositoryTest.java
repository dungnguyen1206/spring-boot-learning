package edu.fu.dao;

import edu.fu.entities.Users;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    private UserRepository userRepository;
    private Users sampleUser;

    @BeforeEach
    public void setUp() {
        EntityManager em = DbContext.getEntityManager();
        userRepository = new UserRepository(em);
    }

    @Test
    public void saveUser_HappyCase() {
        Users sampleUser = createSampleUser();
        Users acturalResult = userRepository.save(sampleUser);

       Assertions.assertEquals(sampleUser.getName(), acturalResult.getName());
    }

    @Test
    public void updateUser() {
        Users sampleUser = userRepository.findById(1L);
        sampleUser.setName("Nguyen Van B");
        Users  acturalUser =  userRepository.update(sampleUser);
        Assertions.assertEquals("Nguyen Van B", acturalUser.getName());
    }

    @Test
    public void deleteUser_HappyCase() {
        userRepository.delete(1L);
        Users acturalUser =  userRepository.findById(1L);
        Assertions.assertNull(acturalUser);
    }

    private Users createSampleUser() {
        return new Users(
                "Nguyen Van A",
                "nguyenvana.test@example.com",
                "123456",
                "0912345678",
                "CANDIDATE",
                "ACTIVE"
        );
    }

}
