package com.raj.Exchange.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class LogTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name; //jest kolumna

}
