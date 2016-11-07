package Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by juane on 05/09/2016.
 */
public class ComodoComposto extends Comodo {
    public ComodoComposto(){}
    private Collection<Comodo> Composition = new LinkedHashSet<>();

    public void AddComodoToComposition(Comodo c){
        if(ContainsComodo(c) == false){
            Composition.add(c);
        }
    }

    public boolean ContainsComodo(Comodo comodo){
        for(Comodo c : Composition){
            if(c.getId() == comodo.getId())
                return true;
        }

        return false;
    }

    public <T extends Comodo> void AddRange(Collection<T> collection){
        Composition.addAll(collection);
    }

    public void RemoveComposition(int id){
        Composition.removeIf(x -> x.getId() == id);
    }

    public void RemoveAllComposition(){
        Composition.clear();
    }
}
