package com.example.loopie.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;

import com.example.loopie.Users.service.UserService;
import com.example.loopie.Users.controller.UserController;
import com.example.loopie.Users.model.User;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService servicio;

    @Test
    public void testGetAllUsers()throws Exception {
        User user1 = new User(1, "michi", "michi@loopie.cl", "michi123",  "Michelle", "Melo", "Admin", "Mirasol", true);
        User user2 = new User(2, "miki", "miki@loopie.cl", "miki123",  "Javiera", "Sanchez", "Admin", "Mirasol", true);

        when(servicio.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("michi@loopie.cl"))
                .andExpect(jsonPath("$[1].email").value("miki@loopie.cl"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1, "michi", "michi@loopie.cl", "michi123",  "Michelle", "Melo", "Admin", "Mirasol", true);

        when(servicio.getUserById(1)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("michi@loopie.cl"));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(1, "michi", "michi@loopie.cl", "michi123",  "Michelle", "Melo", "Admin", "Mirasol", true);

        when(servicio.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                .contentType("application/json")
                .content("{\"id\":1,\"username\":\"michi\",\"email\":\"michi@loopie.cl\",\"password\":\"michi123\",\"nombre\":\"Michelle\",\"apellido\":\"Melo\",\"rol\":\"Admin\",\"direccion\":\"Mirasol\",\"estado\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("michi@loopie.cl"));    
    }    

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User(1, "michi", "michi@loopie.cl", "michi123",  "Michelle", "Melo", "Admin", "Mirasol", true);

        when(servicio.updateUser(anyInt(), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/v1/users/{id}", 1)
                .contentType("application/json")
                .content("{\"id\":1,\"username\":\"michi\",\"email\":\"michi@loopie.cl\",\"password\":\"michi123\",\"nombre\":\"Michelle\",\"apellido\":\"Melo\",\"rol\":\"Admin\",\"direccion\":\"Mirasol\",\"estado\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("michi@loopie.cl"));
    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User(1, "michi", "michi@loopie.cl", "michi123",  "Michelle", "Melo", "Admin", "Mirasol", true);

        when(servicio.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users/add")
                .contentType("application/json")
                .content("{\"id\":1,\"username\":\"michi\",\"email\":\"michi@loopie.cl\",\"password\":\"michi123\",\"nombre\":\"Michelle\",\"apellido\":\"Melo\",\"rol\":\"Admin\",\"direccion\":\"Mirasol\",\"estado\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("michi@loopie.cl"));
    }

}