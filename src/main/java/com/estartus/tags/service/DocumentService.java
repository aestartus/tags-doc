package com.estartus.tags.service;

import com.estartus.tags.model.Document;

import java.util.List;


/**
 * @author aestartus
 * @since 12/29/18
 */
public interface DocumentService {

    void save(Document document);

    List<Document> findDocumentsByOwner(String owner);

    Document findDocumentsById(String id);

    List<Document> findAll();

    void remove(String id);
}
