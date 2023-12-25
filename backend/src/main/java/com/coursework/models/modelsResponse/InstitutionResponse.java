package com.coursework.models.modelsResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InstitutionResponse {
    Long id;
    private String name;
    private int type;
    private String address;
    private String phone;
    private String mail;
    private String description;
    private List<InstitutionTablesResponse> tables;
    private List<PhotoResponse> photos;

    public InstitutionResponse(Long id, String name, int type, String address, String phone, String mail, String description, List<InstitutionTablesResponse> tables, List<PhotoResponse> photos) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.description = description;
        this.tables = tables;
        this.photos = photos;
    }
}