package com.example.home.myApp.domain.audioAndPlayList;

import com.example.home.myApp.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date dateAdd;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "audio_playList",
            joinColumns = @JoinColumn(name = "playList_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id"))
    private Set<Audio> audio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Audio> getAudio() {
        return audio;
    }

    public void addAudio(Audio audio) {
        getAudio().add(audio);
    }

    public void setAudio(Set<Audio> audio) {
        this.audio = audio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return id.equals(playList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
