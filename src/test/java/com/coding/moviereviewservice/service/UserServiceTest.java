package com.coding.moviereviewservice.service;

import com.coding.moviereviewservice.enums.Genre;
import com.coding.moviereviewservice.enums.Role;
import com.coding.moviereviewservice.model.Movie;
import com.coding.moviereviewservice.model.User;
import com.coding.moviereviewservice.repository.UserRepository;
import com.coding.moviereviewservice.util.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static java.util.Optional.ofNullable;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    public User user;

    @BeforeEach
    public void init() {

        Movie movie5 = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
        user = new User(1L, "Twinkle", Role.VIEWER, new ArrayList<>(Arrays.asList(movie5)));

    }

    @Test
    public void createUserTest() {
        Assertions.assertEquals(userService.createUser(user).getName(), user.getName());
    }

    @Test
    public void getUserById() {

        Mockito.when(userRepository.getData(Mockito.any())).thenReturn(ofNullable(user));
        Assertions.assertEquals(userService.getUserById(1L).getName(), "Twinkle");
    }

    @Test
    public void getUserByIdNotFound() {

        Mockito.when(userRepository.getData(Mockito.any())).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void getRoleViewer() {
        Mockito.when(userRepository.getData(Mockito.any())).thenReturn(ofNullable(user));
        Assertions.assertEquals(userService.getUserRole(1L), Role.VIEWER);

    }

    @Test
    public void getRoleCritic() {
        Movie movie1 = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
        Movie movie2 = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
        Movie movie3 = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
        Movie movie4 = new Movie(1L, "DDLJ", new Date(), Genre.ROMANTIC, new ArrayList<>());
        List<Movie> movies = user.getMovies();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        user.setMovies(movies);
        Mockito.when(userRepository.getData(Mockito.any())).thenReturn(ofNullable(user));
        Assertions.assertEquals(userService.getUserRole(1L), Role.CRITIC);

    }

    @Test
    public void getUsers() {
        Mockito.when(userRepository.getAllData()).thenReturn(Collections.singletonList(user));
        Assertions.assertEquals(userService.getUsers().get(0).getName(), "Twinkle");
    }


}
