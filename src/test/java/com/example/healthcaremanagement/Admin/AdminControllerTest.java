package com.example.healthcaremanagement.Admin;

import com.example.healthcaremanagement.User.User;
import com.example.healthcaremanagement.User.UserNotFoundException;
import com.example.healthcaremanagement.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdminController.class)
@ContextConfiguration(classes = AdminController.class)
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static final User user = new User(1,
            123456789,
            1000,
            false,
            "12/12/1912",
            "Joe",
            "Doe",
            "johndoe@example.com",
            "johndoe@example.com",
            "password",
            new ArrayList<>(),
            new ArrayList<>());

    private static final String LOGIN_ADMIN_URL = "/admin/login";

    @MockBean
    UserRepository userRepository;

    @Test
    @WithMockUser(authorities = "USER")
    void whenLoginAdminAsUser_thenReturnHttpStatus403() throws Exception {

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        assertThatThrownBy(() -> mockMvc.perform(get(LOGIN_ADMIN_URL)
                                    .param("id", "1")
                                    .param("password", "password"))
        ).isInstanceOf(NestedServletException.class);
    }
}
