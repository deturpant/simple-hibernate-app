package ru.deturpant;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Song")
public class Song
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_song")
    private Integer id;
    @Column(name="title")
    private String title;

    public Song(String title) {
        this.title = title;
    }
    @ManyToMany(mappedBy = "songs")
    private Set<Artist> artists = new HashSet<Artist>();
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Song() {
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
