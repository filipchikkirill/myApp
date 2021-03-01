package com.example.home.myApp.domain.dirsAndFiles;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "base_dir")
public class BaseDir {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;
    private Date dateAdd;
    private Long size;

    @OneToMany(mappedBy = "baseDir",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dir> dirs;

    @OneToMany(mappedBy = "baseDir",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileCustom> files;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<Dir> getDirs() {
        return dirs;
    }

    public void setDirs(List<Dir> dirs) {
        this.dirs = dirs;
    }

    public List<FileCustom> getFiles() {
        return files;
    }

    public void setFiles(List<FileCustom> files) {
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
