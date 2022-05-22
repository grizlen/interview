package work5.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Optional;

public class Sessions {
    private static Session session = null;

    private static Session open() {
        SessionFactory sessionFactory = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build())
                .buildMetadata()
                .buildSessionFactory();

            return (session = sessionFactory.openSession());
    }

    public static Session get() {
        return session == null ? open() : session;
    }

    public static void beginTransaction() {
        get().beginTransaction();
    }

    public static void commit() {
        get().getTransaction().commit();
    }

    public static void close() {
        if (session != null) {
            session.close();
        }
    }

    public static void save(Object object) {
        get().beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
    }

    public static <T> void saveList(List<T> list) {
        get().beginTransaction();
        list.forEach(obj -> session.saveOrUpdate(obj));
        session.getTransaction().commit();
    }

    public static <T> Optional<T> getById(Long id, Class<T> tClass) {
        get().beginTransaction();
        T result = session.get(tClass, id);
        session.getTransaction().commit();
        return Optional.ofNullable(result);
    }

    public static <T> List<T> getAll(Class<T> tClass) {
        get().beginTransaction();
        List<T> result = session.createQuery("from " + tClass.getSimpleName(), tClass).list();
        session.getTransaction().commit();
        return result;
    }

    public static void delete(Object object) {
        session.delete(object);
    }
}
