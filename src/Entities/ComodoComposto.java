package Entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juane on 05/09/2016.
 */
@Entity
public class ComodoComposto extends Comodo {
    @OneToMany
    private Collection<Comodo> Composition = new ArrayList<>();
}
