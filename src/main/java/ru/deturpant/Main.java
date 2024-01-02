package ru.deturpant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Catalog.class)
                .buildSessionFactory();
        Session session = null;
        try {
            //CREATE
/*            session = factory.getCurrentSession();
            Catalog newCat = new Catalog("Firefox14");
            session.beginTransaction();
            session.save(newCat);
            session.getTransaction().commit();*/

            //READ
            session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog = session.get(Catalog.class, 2);
            session.getTransaction().commit();
            System.out.println(catalog);
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