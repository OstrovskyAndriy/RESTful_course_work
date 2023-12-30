package com.coursework.models.modelsResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationResponse {
    private Long id;
    private Long userId;
    private Long institutionId;
    private Long tableId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReservationResponse(Long id, Long userId, Long institutionId, Long tableId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.userId = userId;
        this.institutionId = institutionId;
        this.tableId = tableId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

