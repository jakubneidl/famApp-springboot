package cz.neidl.farmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
