package cz.neidl.farmapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crop extends AbstractEntity {
    @NotNull
    private String name;
    @NotNull
    private String info;
    @NotNull
    private String spacing;
    @NotNull
    private String idealSoilHumidity;

    @ManyToOne
    @JoinColumn(name = "seeder_id")
    private Seeder seeder;
    public Crop(String name) {
        this.name = name;
    }
}
