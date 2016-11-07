package TransactionScripts;

import DatabaseHelpers.DatabaseConnector;
import Entities.Mobilia;
import Exceptions.ComodoComMobiliaException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juane on 02/11/2016.
 */
public class MobiliaTransactions {
    public static Mobilia CreateMobilia(String description, int deliveryTime, float cost, Collection<Integer> comodos) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        int id = (int)db.ExecuteInsertStatement("INSERT INTO mobilia (Description, DeliveryTime, Cost) VALUES ('"
                + description + "'," + deliveryTime + "," + cost + ")");
        for (Integer comodo : comodos){
            db.ExecuteStatement("INSERT INTO comodo_mobilia VALUES (" + comodo + "," + id + ")");
        }
        db.CloseConnection();
        return new Mobilia(id,description,deliveryTime,cost);
    }

    public static void UpdateMobilia(int id, String description, int deliveryTime, float cost, Collection<Integer> comodos) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        db.ExecuteStatement("UPDATE mobilia SET Description = '" + description + "', DeliveryTime = " + deliveryTime +
        ", Cost = " + cost + " WHERE Id = " + id);
        db.ExecuteStatement("DELETE FROM comodo_mobilia WHERE mobiliaId = " + id);
        for (Integer comodo : comodos){
            db.ExecuteStatement("INSERT INTO comodo_mobilia VALUES (" + comodo + "," + id + ")");
        }
        db.CloseConnection();
    }

    public static Mobilia GetMobilia(Integer id) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        Mobilia mobilia = null;
        ResultSet result = db.ExecuteSelectStatement("SELECT * FROM mobilia WHERE Id = " + id + " LIMIT 1");

        if(result.next()){
            mobilia = new Mobilia(result.getInt("Id"), result.getString("Description"), result.getInt("deliveryTime"), result.getFloat("Cost"));
        }

        return mobilia;
    }

    public static void DeleteMobilia(Integer id) throws Exception{
        DatabaseConnector db = new DatabaseConnector();

        //ResultSet set = db.ExecuteSelectStatement("SELECT * FROM comodo_mobilia WHERE mobiliaId = " + id);

        db.ExecuteStatement("DELETE FROM comodo_mobilia WHERE mobiliaId = " + id);
        db.ExecuteStatement("DELETE FROM mobilia WHERE id = " + id);

        db.CloseConnection();
    }

    public static Collection<Mobilia> GetMobilias() throws Exception{
        DatabaseConnector  db = new DatabaseConnector();
        Collection<Mobilia> mobilias = new ArrayList<>();
        ResultSet result = db.ExecuteSelectStatement("SELECT * FROM mobilia");
        while(result.next()){
            Mobilia m = new Mobilia(result.getInt("Id"), result.getString("Description"), result.getInt("deliveryTime"), result.getFloat("Cost"));
            mobilias.add(m);
        }
        db.CloseConnection();
        return mobilias;
    }
}
