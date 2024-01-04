package ru.deturpant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Artist.class)
                .addAnnotatedClass(Song.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            //CREATE
            session.beginTransaction();
            Query<Song> query1 = session.createQuery("from Song u where u.title=:title", Song.class);
            query1.setParameter("title", "Test metallica song");
            Song findedsong = query1.uniqueResult();
            System.out.println(findedsong);
            Query<Artist> query = session.createQuery("from Artist u where u.name=:name", Artist.class);
            query.setParameter("name", "Metallica");
            Artist findedArtist = query.uniqueResult();
            System.out.println(findedArtist);
            findedsong.getArtists().add(findedArtist);
            findedArtist.getSongs().add(findedsong);
            session.getTransaction().commit();

            //READ
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Artist> artist_from_db = new ArrayList<Artist>();
            artist_from_db = session.createQuery("from Artist", Artist.class).getResultList();
            //session.getTransaction().commit();
            for (Artist i : artist_from_db) {
                System.out.println(i);
            }
            //UPDATE
/*            session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog = session.get(Catalog.class, 2);
            catalog.setTitle("sex");
            session.getTransaction().commit();*/
            //DELETE
            /*session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog = session.get(Catalog.class, 2);
            session.remove(catalog);
            session.detach(catalog); // отсоединить объект от контекста хибернейта
            session.persist(catalog); // присоединить объект к контексту
            session.refresh(catalog); // обновить данные с БД в объект
            */

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            factory.close();
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}