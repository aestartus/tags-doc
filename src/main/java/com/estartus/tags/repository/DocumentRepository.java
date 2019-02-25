package com.estartus.tags.repository;

import com.estartus.tags.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author aestartus
 * @since 12/29/18
 */
public interface DocumentRepository extends MongoRepository<Document, String>{

    List<Document> findDocumentsByOwner(String owner);
}
