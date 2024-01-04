package ru.deturpant;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Artist")
public class Artist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_artist")
    private Integer id;
    @Column(name="name")
    private String name;

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="artist_song",
    joinColumns = @JoinColumn(name="id_artist", referencedColumnName = "id_artist"),
    inverseJoinColumns = @JoinColumn(name = "id_song", referencedColumnName = "id_song"))
    private Set<Song> songs = new HashSet<Song>();

    public Artist(String title) {
        this.name = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAvailable songs:\n");
        for (Song i : songs) {
            sb.append(i);
        }
        sb.append("\n");
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + sb;
    }

    public Artist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
