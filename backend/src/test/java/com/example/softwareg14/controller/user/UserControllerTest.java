package com.example.softwareg14.controller.user;

import com.example.softwareg14.controller.Endpoint;
import com.example.softwareg14.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    protected MockMvc mvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void getUserShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.USER_BY_ID, 1))
                .andExpect(status().isOk());
    }

    @Test
    void getUserShouldReturnStatusIsBadRequest() throws Exception {
        mvc.perform(get(Endpoint.USER_BY_ID, "badString"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserByIdShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.USER_BY_ID, 1))
                .andExpect(status().isOk());
    }

    @Test
    void registerShouldReturnStatusIsCreated() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.name = "test";
        userRequest.username = "test";
        userRequest.password = "test";
        userRequest.email = "test";
        String inputJson = mapToJson(userRequest);
        mvc.perform(post(Endpoint.USER_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isCreated());
    }

    @Test
    void registerShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(get(Endpoint.USER_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturnStatusIsOk() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.name = "test";
        userRequest.username = "test";
        userRequest.password = "test";
        userRequest.email = "test";
        String inputJson = mapToJson(userRequest);
        mvc.perform(put(Endpoint.USER_UPDATE, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());
    }

    @Test
    void updateShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(put(Endpoint.USER_UPDATE, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.USER_DELETE, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(get(Endpoint.USER_DELETE, badString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}