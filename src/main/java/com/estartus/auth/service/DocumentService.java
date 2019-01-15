package com.estartus.auth.service;

import com.estartus.auth.model.Document;

import java.util.List;


/**
 * @author aestartus
 * @since 12/29/18
 */
public interface DocumentService {

    void save(Document document);

    List<Document> findDocumentsByOwner(String owner);

    List<Document> findAll();
}
