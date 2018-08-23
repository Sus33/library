package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
    private int id;
    @Column
    private String title;
    @Column
    private String text;
    @ManyToOne
    private User user;
}
