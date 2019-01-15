package com.estartus.auth.service;

import com.estartus.auth.model.Document;
import com.estartus.auth.model.User;
import com.estartus.auth.repository.DocumentRepository;
import com.estartus.auth.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Transactional(readOnly = true)
    public List<Document> findDocumentsByOwner(String owner) {
        return documentRepository.findDocumentsByOwner(owner);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}
