package Entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by juane on 05/09/2016.
 */
@Entity @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comodo {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int Id;
    private String Description;

    public String getDescription() {
        return Description;
    }
    public int getId(){return Id;}

    public void setDescription(String _description) {
        this.Description = _description;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="comodo_mobilia", joinColumns=@JoinColumn(name="comodoId"), inverseJoinColumns=@JoinColumn(name="mobiliaId"))
    protected Set<Mobilia> ListaMobilias = new HashSet<>();

    public Set<Mobilia> getListaMobilias() {
        return ListaMobilias;
    }

    public void setListaMobilias(Set<Mobilia> listaMobilias) {
        ListaMobilias = listaMobilias;
    }

    public void AddMobilia(Mobilia mobilia){
        this.ListaMobilias.add(mobilia);
    }
}
