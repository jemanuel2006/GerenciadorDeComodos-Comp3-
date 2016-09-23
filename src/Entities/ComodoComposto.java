package Entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juane on 05/09/2016.
 */
@Entity
public class ComodoComposto extends Comodo {
    @ManyToMany
    private Collection<Comodo> Composition = new ArrayList<>();
}
