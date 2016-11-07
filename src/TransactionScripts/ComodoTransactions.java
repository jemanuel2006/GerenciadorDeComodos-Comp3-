package TransactionScripts;

import DatabaseHelpers.DatabaseConnector;
import Entities.*;
import Exceptions.ComodoComMobiliaException;
import Exceptions.ComodoPertenceAComodoCompostoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juane on 24/10/2016.
 */
public class ComodoTransactions {
    public static <T extends Comodo> Collection<T> GetComodos(Class<T> c) throws Exception{
        ArrayList<T> list = new ArrayList();
        DatabaseConnector db = new DatabaseConnector();
        ResultSet result = db.ExecuteSelectStatement("SELECT * FROM " + c.getSimpleName());

        while(result.next()){
            T s = c.newInstance();
            s.setDescription(result.getString("Description"));
            s.setId(result.getInt("Id"));

            ResultSet mobilias = db.ExecuteSelectStatement("SELECT * FROM comodo_mobilia a INNER JOIN mobilia b " +
                    "on a.mobiliaId = b.Id WHERE a.comodoId = " + s.getId());

            while (mobilias.next()) {
                Mobilia m = new Mobilia(mobilias.getInt("Id"), mobilias.getString("Description"),
                        mobilias.getInt("DeliveryTime"), mobilias.getFloat("Cost"));

                s.AddMobilia(m);
            }

            list.add(s);
        }
        db.CloseConnection();

        return list;
    }

    public static <T extends Comodo> T GetComodo(Class<T> c,Integer id) throws Exception{
        T s = null;
        DatabaseConnector db = new DatabaseConnector();
        ResultSet comodo = db.ExecuteSelectStatement("SELECT a.Id, b.Description FROM comodo a INNER JOIN " + c.getSimpleName() + " b " +
                "ON a.Id = b.Id WHERE a.id = " + id.toString() + " LIMIT 1");
        if(comodo.next()) {
            s = c.newInstance();
            s.setId(comodo.getInt("Id"));
            s.setDescription(comodo.getString("Description"));

            ResultSet mobilias = db.ExecuteSelectStatement("SELECT * FROM comodo_mobilia a INNER JOIN mobilia b " +
                    "on a.mobiliaId = b.Id WHERE a.comodoId = " + id.toString());

            while (mobilias.next()) {
                Mobilia m = new Mobilia(mobilias.getInt("Id"), mobilias.getString("Description"),
                        mobilias.getInt("DeliveryTime"), mobilias.getFloat("Cost"));

                s.AddMobilia(m);
            }
        }

        db.CloseConnection();
        return s;
    }

    public static <T extends Comodo> T CreateComodo(Class<T> c,String description) throws Exception{
        T s = null;
        DatabaseConnector db = new DatabaseConnector();
        int id = (int) db.ExecuteInsertStatement("INSERT INTO comodo VALUES ()");
        db.ExecuteStatement("INSERT INTO " + c.getSimpleName() + " VALUES (" + id + ",'" + description + "')");
        s = c.newInstance();
        s.setId(id);
        s.setDescription(description);
        db.CloseConnection();
        return s;
    }

    public static <T extends Comodo> void UpdateComodo(Class<T> c, Integer id, String description) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        db.ExecuteStatement("UPDATE " + c.getSimpleName() + " SET Description = '" + description + "' WHERE Id = " + id.toString());
        db.CloseConnection();
    }

    public static <T extends Comodo> void DeleteComodo(Class<T> c,Integer id) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        T s = GetComodo(c, id);

        if(s.HasMobilia()){
            throw new ComodoComMobiliaException();
        }

        ResultSet result = db.ExecuteSelectStatement("SELECT * FROM comodocomposto_comodo WHERE Composition_Id = " + id.toString() + " LIMIT 1");

        if(result.next() == true){
            throw new ComodoPertenceAComodoCompostoException();
        }

        db.ExecuteStatement("DELETE FROM " + c.getSimpleName() + " WHERE Id = " + id.toString());
        db.ExecuteStatement("DELETE FROM comodo WHERE Id = " + id.toString());
        db.CloseConnection();
    }

    public static ComodoComposto CreateComodoComposto(String description, Collection<Integer> composition) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        ComodoComposto c = CreateComodo(ComodoComposto.class, description);

        for (Integer comp : composition) {
            db.ExecuteStatement("INSERT INTO comodocomposto_comodo VALUES (" + c.getId() + "," + comp +")");
        }

        AddCompositionsToComodo(c.getId(), c);
        db.CloseConnection();

        return c;
    }

    public static ComodoComposto GetComodoComposto(Integer id) throws Exception{
        ComodoComposto c = ComodoTransactions.GetComodo(ComodoComposto.class, id);
        AddCompositionsToComodo(id, c);
        return c;
    }

    private static void AddCompositionsToComodo(Integer id, ComodoComposto c) throws Exception {
        c.AddRange(GetCompositionComodos(Cozinha.class, id));
        c.AddRange(GetCompositionComodos(Quarto.class, id));
        c.AddRange(GetCompositionComodos(Sala.class, id));
        c.AddRange(GetCompositionComodos(ComodoComposto.class, id));
    }

    private static <T extends Comodo> Collection<T> GetCompositionComodos(Class<T> c,Integer id) throws Exception {
        DatabaseConnector db = new DatabaseConnector();
        ResultSet result = db.ExecuteSelectStatement("SELECT * FROM comodocomposto_comodo WHERE ComodoComposto_Id = " + id.toString());
        ArrayList<T> collection = new ArrayList<T>();
        while(result.next()){
            T comodo = GetComodo(c, result.getInt("Composition_Id"));
            if (comodo != null) {
                collection.add(comodo);
            }
        }
        return collection;
    }

    public static void UpdateComodoComposto(Integer id,  String description, Collection<Integer> selectedComodos) throws Exception{
        DatabaseConnector db = new DatabaseConnector();
        db.ExecuteStatement("UPDATE comodocomposto SET Description = '" + description + "' WHERE Id = " + id.toString());
        db.ExecuteStatement("DELETE FROM comodocomposto_comodo WHERE ComodoComposto_Id = " + id.toString());
        for (Integer i : selectedComodos) {
            db.ExecuteStatement("INSERT INTO comodocomposto_comodo VALUES (" + id + "," + i + ")");
        }
    }

    public static void DeleteComodoComposto(Integer id)  throws Exception{
        ComodoComposto c = GetComodo(ComodoComposto.class, id);
        if(c.HasMobilia()){
            throw new ComodoComMobiliaException();
        }

        DatabaseConnector db = new DatabaseConnector();
        db.ExecuteStatement("DELETE FROM comodocomposto_comodo WHERE ComodoComposto_Id = " + id.toString());
        db.ExecuteStatement("DELETE FROM comodocomposto WHERE Id = " + id.toString());
        db.ExecuteStatement("DELETE FROM comodo WHERE Id = " + id.toString());
        db.CloseConnection();
    }

    public static Collection<Comodo> GetAllComodos() throws Exception{
        Collection<Comodo> comodos = new ArrayList<>();
        comodos.addAll(GetComodos(Cozinha.class));
        comodos.addAll(GetComodos(Sala.class));
        comodos.addAll(GetComodos(Quarto.class));
        comodos.addAll(GetComodos(ComodoComposto.class));

        return comodos;
    }
}
