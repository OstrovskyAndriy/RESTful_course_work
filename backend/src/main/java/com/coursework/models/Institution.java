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
    private int typeId;  // Змінено тип на int

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<InstitutionTables> tables;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<Photo> photos;
}
