package com.coursework.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "institution_tables")
public class InstitutionTables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_number", unique = true)
    private int tableNumber;

    @Column(name = "count_of_chairs", nullable = false)
    private int countOfChairs;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}


