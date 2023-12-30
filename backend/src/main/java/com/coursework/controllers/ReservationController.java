package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.models.InstitutionTables;
import com.coursework.models.Reservation;
import com.coursework.models.User;
import com.coursework.models.modelsResponse.InstitutionResponse;
import com.coursework.models.modelsResponse.InstitutionTablesResponse;
import com.coursework.models.modelsResponse.ReservationResponse;
import com.coursework.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private UserController userService;
    private InstitutionController institutionService;
    private InstitutionTablesController institutionTablesService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationResponse request) {
        // Отримайте інформацію з запиту
        Long userId = request.getUserId();
        Long institutionId = request.getInstitutionId();
        Long tableId = request.getTableId();
        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();

        User user = userService.getUserById(userId);
        InstitutionResponse institution = institutionService.getInstitutionById(institutionId);
        InstitutionTablesResponse table = institutionTablesService.getTableById(tableId);

        // Створіть об'єкт Reservation і встановіть всі необхідні значення
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setInstitution(institution);
        reservation.setTable(table);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        // Виклик сервісу для створення бронювання
        Reservation createdReservation = reservationService.createReservation(reservation);

        // Перетворіть створене бронювання в ReservationResponse
        ReservationResponse response = new ReservationResponse(
                createdReservation.getId(),
                createdReservation.getUser().getId(),
                createdReservation.getInstitution().getId(),
                createdReservation.getTable().getId(),
                createdReservation.getStartTime(),
                createdReservation.getEndTime()
        );

        return response;
    }




    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
