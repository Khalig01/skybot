package skypro_bot.skybot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import skypro_bot.skybot.model.Pet;
import skypro_bot.skybot.model.User;
import skypro_bot.skybot.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private final UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("Timur");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User addedUser = userService.addUser(user);

        assertEquals("Timur", addedUser.getName());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindUserByChatId() {
        User user = new User();
        user.setChatId(1L);
        user.setName("Timur");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserById(1L);

        assertEquals("Timur", foundUser.get().getName());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testEditUser() {
        User user = new User();
        user.setChatId(1L);
        user.setName("Timur");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User editedUser = userService.editUser(user);

        assertEquals("Timur", editedUser.getName());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}