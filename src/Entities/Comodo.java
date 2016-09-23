package Entities;

import javax.persistence.*;

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
}
