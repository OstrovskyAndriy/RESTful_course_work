package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.models.InstitutionTables;
import com.coursework.models.Reservation;
import com.coursework.models.User;
import com.coursework.models.modelsResponse.InstitutionResponse;
import com.coursework.models.modelsResponse.InstitutionTablesResponse;
import com.coursework.models.modelsResponse.ReservationResponse;
import com.coursework.services.InstitutionService;
import com.coursework.services.InstitutionTablesService;
import com.coursework.services.ReservationService;
import com.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final InstitutionService institutionService;
    private final InstitutionTablesService institutionTablesService;

    @Autowired
    public ReservationController(
            ReservationService reservationService,
            UserService userService,
            InstitutionService institutionService,
            InstitutionTablesService institutionTablesService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.institutionService = institutionService;
        this.institutionTablesService = institutionTablesService;
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        return reservations.stream()
                .map(reservation -> new ReservationResponse(
                        reservation.getId(),
                        reservation.getUser().getId(),
                        reservation.getInstitution().getId(),
                        reservation.getTable().getId(),
                        reservation.getStartTime(),
                        reservation.getEndTime()
                ))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ReservationResponse getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);

        // Create and return a ReservationDTO with the necessary information
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getInstitution().getId(),
                reservation.getTable().getId(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }


    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationResponse request) {
        Long userId = request.getUserId();
        Long institutionId = request.getInstitutionId();
        Long tableId = request.getTableId();
        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();

        User user = userService.getUserById(userId);

        if (user == null) {
            return null;
        }

        Institution institution = institutionService.getInstitutionById(institutionId);
        InstitutionTables table = institutionTablesService.getTableById(tableId);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setInstitution(institution);
        reservation.setTable(table);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        try {
            Reservation createdReservation = reservationService.createReservation(reservation);

            ReservationResponse response = new ReservationResponse(
                    createdReservation.getId(),
                    createdReservation.getUser().getId(),
                    createdReservation.getInstitution().getId(),
                    createdReservation.getTable().getId(),
                    createdReservation.getStartTime(),
                    createdReservation.getEndTime()
            );

            // успішно
            return response;
        } catch (Exception e) {
            // помикла
            return null;
        }
    }


    @PutMapping("/{id}")
    public ReservationResponse updateReservation(@PathVariable Long id, @RequestBody ReservationResponse request) {
            Reservation existingReservation = reservationService.getReservationById(id);

            if (existingReservation != null) {
                Long userId = request.getUserId();
                Long institutionId = request.getInstitutionId();
                Long tableId = request.getTableId();

                User user = userService.getUserById(userId);
                Institution institution = institutionService.getInstitutionById(institutionId);
                InstitutionTables table = institutionTablesService.getTableById(tableId);

                existingReservation.setUser(user);
                existingReservation.setInstitution(institution);
                existingReservation.setTable(table);
                existingReservation.setStartTime(request.getStartTime());
                existingReservation.setEndTime(request.getEndTime());

                Reservation updatedReservation = reservationService.updateReservation(id, existingReservation);

                // Перетворіть оновлене бронювання в ReservationResponse
                return new ReservationResponse(
                        updatedReservation.getId(),
                        updatedReservation.getUser().getId(),
                        updatedReservation.getInstitution().getId(),
                        updatedReservation.getTable().getId(),
                        updatedReservation.getStartTime(),
                        updatedReservation.getEndTime()
                );
            }
            else {
                return null;
            }

    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
