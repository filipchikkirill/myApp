package com.example.home.myApp.domain.dirsAndFiles;

import javax.persistence.*;

@Entity
public class FileCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long size;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
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
