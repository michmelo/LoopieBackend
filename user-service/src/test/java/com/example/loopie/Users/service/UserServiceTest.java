package com.example.loopie.Users.service;

import com.example.loopie.Users.model.User;
import com.example.loopie.Users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdUsuario(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setNombre("Test");
        user.setApellido("User");
        user.setRol("USER");
        user.setDireccion("Test Address");
        user.setEstado(true);
    }

    @Test
    void getAllUsers() {
        when(repository.findAll()).thenReturn(Arrays.asList(user));
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals(user.getIdUsuario(), foundUser.getIdUsuario());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getUserById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void createUser() {
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(repository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("encodedPassword", user.getPassword());
        verify(passwordEncoder, times(1)).encode(any(String.class));
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(any(User.class))).thenReturn(user);

        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("newPassword");
        updatedUser.setDireccion("New Address");

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("updatedUser", result.getUsername());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("newPassword", result.getPassword());
        assertEquals("New Address", result.getDireccion());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser() {
        doNothing().when(repository).deleteById(1L);
        userService.deleteUser(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
