package com.coursework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

//    @ManyToOne
//    @JoinColumn(name = "institution_id")
//    private Institution institution;
@JsonBackReference
@ManyToOne
@JoinColumn(name = "institution_id")
private Institution institution;
}

