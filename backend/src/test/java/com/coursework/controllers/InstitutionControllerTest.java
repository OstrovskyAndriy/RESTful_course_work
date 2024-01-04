package com.coursework.controllers;


import com.coursework.models.Institution;
import com.coursework.models.modelsResponse.InstitutionResponse;
import com.coursework.services.InstitutionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InstitutionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class InstitutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstitutionService institutionService;

    @InjectMocks
    private InstitutionController institutionController;

    @Test
    void createInstitution() throws Exception {
        // Prepare data for the request
        Institution institutionRequest = new Institution();
        institutionRequest.setName("Test Institution");
        institutionRequest.setType(1); // Replace with your type logic
        institutionRequest.setAddress("Test Address");
        // Add other necessary fields...

        // Mock the service behavior
        when(institutionService.createInstitution(any(Institution.class))).thenReturn(institutionRequest);

        // Perform the POST request
        mockMvc.perform(post("/api/institutions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(institutionRequest)))
                .andExpect(status().isOk());

        // You can add additional assertions based on the expected behavior
    }
}
