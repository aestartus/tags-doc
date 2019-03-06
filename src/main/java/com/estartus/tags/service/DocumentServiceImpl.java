package com.estartus.tags.service;

import com.estartus.tags.model.Document;
import com.estartus.tags.repository.DocumentRepository;
import com.estartus.tags.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author aestartus
 * @since 12/29/18
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void save(Document document) {
        document.setValueOnCreation(Crypto.sha256(document.getFile()));
        document.setCreationDate(new Date());
        document.setModificationDate(new Date());
        documentRepository.save(document);
    }

    @Override
    public void remove(String id){
        documentRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> findDocumentsByOwner(String owner) {
        return documentRepository.findDocumentsByOwner(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public Document findDocumentsById(String id) {
        return documentRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}
