package Entities;

import javax.persistence.Entity;

/**
 * Created by juane on 05/09/2016.
 */

public class Sala extends Comodo {
    public Sala(int id, String description){
        setId(id);
        setDescription(description);
    }

    public Sala(){}
}
