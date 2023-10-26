//package com.example.softwareg14.controller.organization;
//
//import com.example.softwareg14.controller.Endpoint;
//import com.example.softwareg14.model.Organization;
//import com.example.softwareg14.service.OrganizationService;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = OrganizationController.class
//)
//@AutoConfigureMockMvc
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class OrganizationControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private OrganizationService organizationService;
//    private Organization fromDB;
//    private Organization toDB;
//
//    @BeforeAll
//    public void setUp() {
//        toDB = Organization.builder()
//                .name("test1")
//                .address("test2")
//                .phone("test3")
//                .email("test4")
//                .website("test5")
//                .description("test6")
//                .password("test7")
//                .build();
//    }
//
//    @Test
//    void organization() throws Exception {
//        mvc.perform(post(Endpoint.ORGANIZATION_REGISTER)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toDB.toString()))
//                .andExpect(status().isOk());
//        fromDB = organizationService.getOrganizationByEmail(toDB.getEmail());
//        assertEquals(toDB.getName(), fromDB.getName());
//    }
//
//    @AfterAll
//    public void tearDown() {
//        organizationService.deleteOrganizationById(fromDB.getId());
//    }
//}