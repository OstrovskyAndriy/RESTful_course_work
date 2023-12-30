package com.coursework.services.serviceImpl;

import com.coursework.models.Reservation;
import com.coursework.repository.ReservationRepository;
import com.coursework.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Cacheable(value = "reservationsCache")
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Cacheable(value = "reservationsCache", key = "#id")
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @CachePut(value = "reservationsCache", key = "#result.id")
    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @CachePut(value = "reservationsCache", key = "#id")
    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        if (reservationRepository.existsById(id)) {
            reservation.setId(id);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @CacheEvict(value = "reservationsCache", key = "#id")
    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
