package com.coursework.controllers;

import com.coursework.controllers.ReservationController;
import com.coursework.models.Reservation;
import com.coursework.models.modelsResponse.ReservationResponse;
import com.coursework.services.ReservationService;
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

import java.time.LocalDateTime;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReservationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    void createReservation() throws Exception {
        // Prepare data for the request
        ReservationResponse request = new ReservationResponse(
                1L,
                1L, // userId
                2L, // institutionId
                3L, // tableId
                LocalDateTime.now(), // startTime
                LocalDateTime.now().plusHours(2) // endTime
        );

        // Mock the service behavior
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(mapToReservation(request));

        // Perform the POST request
        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        // Verify that the service method was called with the correct argument
        //verify(reservationService, times(1)).createReservation(any(Reservation.class));
    }

    // Helper method to map ReservationResponse to Reservation
    private Reservation mapToReservation(ReservationResponse response) {
        Reservation reservation = new Reservation();
        reservation.setId(response.getId());
        // Map other fields accordingly
        return reservation;
    }
}
