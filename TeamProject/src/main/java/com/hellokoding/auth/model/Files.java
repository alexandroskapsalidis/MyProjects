/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellokoding.auth.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ioanna
 */
@Entity
@Table(name = "files")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Files.findAll", query = "SELECT f FROM Files f"),
    @NamedQuery(name = "Files.findByFileId", query = "SELECT f FROM Files f WHERE f.fileId = :fileId"),
    @NamedQuery(name = "Files.findByFileName", query = "SELECT f FROM Files f WHERE f.fileName = :fileName")})
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_id")
    private Integer fileId;
    @Size(max = 45)
    @Column(name = "file_name")
    private String fileName;
    @Lob
    @Column(name = "file_context")
    private byte[] fileContext;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne
    private User id;

    public Files() {
    }

    public Files(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContext() {
        return fileContext;
    }

    public void setFileContext(byte[] fileContext) {
        this.fileContext = fileContext;
    }

    public User getId() {
        return id;
    }

    public void setId(User id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Files)) {
            return false;
        }
        Files other = (Files) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.myTeamProject.entities.Files[ fileId=" + fileId + " ]";
    }
    
}
