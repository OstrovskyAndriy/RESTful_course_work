package com.coursework.models.modelsResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionTablesResponse {
    private Long id;
    private int tableNumber;
    private int countOfChairs;
    private Long institutionId;

    public InstitutionTablesResponse(Long id, int tableNumber, int countOfChairs, Long institutionId) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.countOfChairs = countOfChairs;
        this.institutionId = institutionId;
    }


}
