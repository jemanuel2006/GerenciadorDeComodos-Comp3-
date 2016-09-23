package HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.List;

/**
 * Created by juane on 18/09/2016.
 */
public class SessionFactoryBuilder {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //TODO: Jogar em outra classe
    public static void SaveObject(Object obj){
        SessionFactory factory = SessionFactoryBuilder.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(obj);
        session.getTransaction().commit();
        session.close();
    }

    public static Object GetObjectById(Class objType, int id){
        SessionFactory factory = SessionFactoryBuilder.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Object obj = session.get(objType, id);
        session.close();
        return obj;
    }

    public static List<Object> GetObjects(Class objType){
        SessionFactory factory = SessionFactoryBuilder.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List objs = session.createQuery("FROM " + objType.getName()).list();

        return objs;
    }

    public static void DeleteObjectById(Class classType, int id){
        SessionFactory factory = SessionFactoryBuilder.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Object obj = session.get(classType, id);
        session.delete(obj);
        session.getTransaction().commit();
        session.close();
    }
}
