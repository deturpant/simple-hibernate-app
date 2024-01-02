package ru.deturpant;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@Entity
@Table(name="catalogs")
public class Catalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="title")
    private String title;

    public Catalog(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Catalog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
