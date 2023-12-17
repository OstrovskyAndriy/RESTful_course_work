package com.coursework.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "tables")
public class InstitutionTables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tableNumber", unique = true)
    private int tableNumber;

    @Column(name = "countOfChairs", nullable = false)
    private int countOfChairs;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}


