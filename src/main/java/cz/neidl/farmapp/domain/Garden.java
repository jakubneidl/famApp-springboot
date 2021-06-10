package cz.neidl.farmapp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Garden extends AbstractEntity{
    private String name;
    private String info;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "garden_id")
    private List<Harvest> harvests;
}
