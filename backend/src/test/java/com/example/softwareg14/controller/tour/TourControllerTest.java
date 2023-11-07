package com.example.softwareg14.controller.tour;

import com.example.softwareg14.controller.Endpoint;
import com.example.softwareg14.model.Organization;
import com.example.softwareg14.model.Tour;
import com.example.softwareg14.service.TourService;
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

@WebMvcTest(TourController.class)
class TourControllerTest {

    @MockBean
    private TourService tourService;

    @Autowired
    protected MockMvc mvc;

    private final Tour content = Tour.builder()
            .name("test1")
            .description("test2")
            .price(1)
            .durationHours(1)
            .image("test3")
            .location("test4")
            .organization(Organization.builder().id(2).build())
            .build();

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void createTourShouldReturnStatusIsCreated() throws Exception {
        String inputJson = mapToJson(content);
        mvc.perform(post(Endpoint.CREATE_TOUR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());
    }

    @Test
    void createTourShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(post(Endpoint.CREATE_TOUR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTourShouldReturnStatusIsOk() throws Exception {
        String inputJson = mapToJson(content);
        mvc.perform(put(Endpoint.TOUR_UPDATE, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());
    }

    @Test
    void updateTourShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(put(Endpoint.TOUR_UPDATE, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTourShouldReturnStatusIsOk() throws Exception {
        mvc.perform(delete(Endpoint.DELETE_TOUR, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTourShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(delete(Endpoint.DELETE_TOUR, badString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTourByIdShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.TOUR_BY_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllToursShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.TOURS_ALL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getToursByUserIdShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.TOURS_BY_USER_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getToursByOrganizationIdShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.TOURS_BY_ORGANIZATION_ID, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addUserToTourShouldReturnStatusIsOk() throws Exception {
        String inputJson = mapToJson(content);
        mvc.perform(post(Endpoint.ADD_USER_TO_TOUR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());
    }

    @Test
    void addUserToTourShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(post(Endpoint.ADD_USER_TO_TOUR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void removeUserFromTourShouldReturnStatusIsOk() throws Exception {
        TourRequest tourRequest = new TourRequest();
        tourRequest.tourId = 1;
        tourRequest.userId = 1;

        mvc.perform(post(Endpoint.REMOVE_USER_FROM_TOUR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(tourRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void removeUserFromTourShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(post(Endpoint.REMOVE_USER_FROM_TOUR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

}