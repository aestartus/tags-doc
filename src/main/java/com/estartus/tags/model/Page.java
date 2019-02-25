package com.estartus.tags.model;

import java.util.List;

/**
 * @author aestartus
 * @since 2/23/19.
 */
public class Page {

    byte[] page;
    int number;
    List<String> tags;

    public byte[] getPage() {
        return page;
    }

    public void setPage(byte[] page) {
        this.page = page;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
