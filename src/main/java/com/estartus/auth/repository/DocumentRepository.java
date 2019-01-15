package com.estartus.auth.repository;

import com.estartus.auth.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author aestartus
 * @since 12/29/18
 */
public interface DocumentRepository extends MongoRepository<Document, Long>{

    List<Document> findDocumentsByOwner(String owner);
}
