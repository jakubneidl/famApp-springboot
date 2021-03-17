package com.example.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Seeder extends AbstractEntity {
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private SeederPosition seederPosition;
}
