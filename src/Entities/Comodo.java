package Entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by juane on 05/09/2016.
 */
public abstract class Comodo {
    private int Id;
    private String Description;

    public String getDescription() {
        return Description;
    }
    public int getId(){return Id;}

    public void setId(int id) {
        Id = id;
    }

    public void setDescription(String _description) {
        this.Description = _description;
    }

    protected Set<Mobilia> ListaMobilias = new HashSet<>();

    public Set<Mobilia> getListaMobilias() {
        return ListaMobilias;
    }

    public void setListaMobilias(Set<Mobilia> listaMobilias) {
        ListaMobilias = listaMobilias;
    }

    public void AddMobilia(Mobilia mobilia){
        if(ContainsMobilia(mobilia.getId()) == true)
            return;

        this.ListaMobilias.add(mobilia);
    }

    public void RemoveMobilia(Mobilia mobilia){
        ListaMobilias.removeIf(m -> m.getId() == mobilia.getId());
    }

    public boolean ContainsMobilia(int id){
        for (Mobilia mobilia : ListaMobilias) {
            if(mobilia.getId() == id)
                return true;
        }

        return false;
    }

    public Mobilia GetMobiliaById(int id){
        for (Mobilia mobilia : ListaMobilias) {
            if(mobilia.getId() == id)
                return mobilia;
        }

        return null;
    }

    public boolean HasMobilia(){
        return ListaMobilias.size() > 0;
    }
}
