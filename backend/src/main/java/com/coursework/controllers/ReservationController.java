package com.coursework.controllers;

import com.coursework.models.*;
import com.coursework.models.modelsResponse.PhotoResponse;
import com.coursework.models.modelsResponse.ReservationResponse;
import com.coursework.services.*;
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
    private final PhotoService photoService;


    @Autowired
    public ReservationController(
            ReservationService reservationService,
            UserService userService,
            InstitutionService institutionService,
            InstitutionTablesService institutionTablesService,
            PhotoService photoService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.institutionService = institutionService;
        this.institutionTablesService = institutionTablesService;
        this.photoService = photoService;
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        return reservations.stream()
                .map(this::mapToReservationResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return (reservation != null) ? mapToReservationResponse(reservation) : null;
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
            return mapToReservationResponse(createdReservation);
        } catch (Exception e) {
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

            return mapToReservationResponse(updatedReservation);
        } else {
            return null;
        }

    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    private ReservationResponse mapToReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getInstitution().getId(),
                reservation.getTable().getId(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }
}
