package com.coursework.services;

import com.coursework.models.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);

    List<Reservation> getReservationsByUserId(Long userId);


    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);
}
