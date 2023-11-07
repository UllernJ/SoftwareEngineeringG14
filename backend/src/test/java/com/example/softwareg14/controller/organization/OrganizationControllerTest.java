package com.example.softwareg14.controller.organization;

import com.example.softwareg14.controller.Endpoint;
import com.example.softwareg14.model.Organization;
import com.example.softwareg14.service.OrganizationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private OrganizationService organizationService;

    private final Organization toDB = Organization.builder()
            .name("test1")
            .address("test2")
            .phone("test3")
            .email("test4")
            .website("test5")
            .description("test6")
            .password("test7")
            .build();


    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void registerOrganizationShouldReturnStatusIsCreated() throws Exception {
        String inputJson = mapToJson(toDB);

        mvc.perform(post(Endpoint.ORGANIZATION_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isCreated());
    }

    @Test
    void registerOrganizationShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(post(Endpoint.ORGANIZATION_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerOrganizationMissingFieldsShouldReturnErrors() throws Exception {
        String inputJson = mapToJson(Organization.builder().build());

        MvcResult mvcResult = mvc.perform(post(Endpoint.ORGANIZATION_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andReturn();
        String expected = "[MISSING_NAME, MISSING_ADDRESS, MISSING_PHONE, MISSING_EMAIL, MISSING_WEBSITE, MISSING_DESCRIPTION, MISSING_PASSWORD]";
        assertEquals(mvcResult.getResponse().getContentAsString(), expected);
    }

    @Test
    void deleteOrganizationShouldReturnStatusIsBadRequest() throws Exception {
        String badString = "badString";
        mvc.perform(delete(Endpoint.ORGANIZATION_DELETE, badString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteOrganizationShouldReturnOkStatus() throws Exception {
        mvc.perform(delete(Endpoint.ORGANIZATION_DELETE, 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAllOrganizationsShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.ORGANIZATIONS_ALL))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrganizationShouldReturnStatusIsOk() throws Exception {
        String inputJson = mapToJson(toDB);

        mvc.perform(put(Endpoint.ORGANIZATION_UPDATE, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());
    }

    @Test
    void getOrganizationByIdShouldReturnStatusIsOk() throws Exception {
        mvc.perform(get(Endpoint.ORGANIZATION_BY_ID, 1))
                .andExpect(status().isOk());
    }

    @Test
    void loginShouldReturnStatusIsOk() throws Exception {
        OrganizationRequest request = new OrganizationRequest();
        request.email = "test4";
        request.password = "test7";
        String inputJson = mapToJson(request);

        mvc.perform(post(Endpoint.ORGANIZATION_LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());
    }


}