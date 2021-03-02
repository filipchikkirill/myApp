package com.example.home.myApp.domain.dirsAndFiles;

import javax.persistence.*;

@Entity
public class Dir {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "base_dir_id")
    private BaseDir baseDir;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseDir getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(BaseDir baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public String toString() {
        return name;
    }
}
