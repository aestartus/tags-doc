package com.estartus.tags.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @author aestartus
 * @since 12/29/18
 */
public class Document {

    @Id
    private String id;
    private String nameOfFile;
    private List<String> metaData;
    private List<String> tags;
    private byte[] file;
    private Date creationDate;
    private String valueOnCreation;
    private Date modificationDate;
    private String owner;
    private List<Page> pages;

    private Document(){}

    public Document(String owner){
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public List<String> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<String> metaData) {
        this.metaData = metaData;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getValueOnCreation() {
        return valueOnCreation;
    }

    public void setValueOnCreation(String valueOnCreation) {
        this.valueOnCreation = valueOnCreation;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
