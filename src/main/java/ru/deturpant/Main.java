package ru.deturpant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;


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
            //CREATE
/*
            session = factory.getCurrentSession();
            Catalog newCat = new Catalog("Firefox14");
            session.beginTransaction();
            session.save(newCat);
            session.getTransaction().commit();
*/
            session = factory.getCurrentSession();
            Song song = new Song("Ride the lightning");
            Artist artist = new Artist("Metallica");
            HashSet<Song> songs_by_artists = new HashSet<Song>();
            songs_by_artists.add(song);
            artist.setSongs(songs_by_artists);
            session.beginTransaction();
            session.save(song);
            session.save(artist);
            session.getTransaction().commit();

            //READ
/*            session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog = session.get(Catalog.class, 2);
            session.getTransaction().commit();
            System.out.println(catalog);*/
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