package com.coursework.models;

import com.coursework.enums.InstitutionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "institutions")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_id")  // Змінено назву стовпця на type_id
    //@Enumerated(EnumType.ORDINAL)
    private int type;  // Змінено тип на InstitutionType

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InstitutionTables> tables;


    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photo> photos;
}