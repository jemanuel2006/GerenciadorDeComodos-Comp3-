package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by juane on 23/09/2016.
 */
@Entity
public class Mobilia {
    @Id
    private int Id;
    private float Cost;
    private String Description;
    private int DeliveryTime;

    public int getId() {
        return Id;
    }

    public float getCost() {
        return Cost;
    }

    public int getDeliveryTime() {
        return DeliveryTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setCost(float cost) {
        Cost = cost;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.DeliveryTime = deliveryTime;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
