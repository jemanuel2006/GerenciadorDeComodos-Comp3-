package Entities;

import javax.persistence.*;

/**
 * Created by juane on 05/09/2016.
 */
@Entity @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comodo {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int Id;
    private String _description;

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
