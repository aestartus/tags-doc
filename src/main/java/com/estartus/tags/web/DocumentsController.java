package com.estartus.tags.web;

import com.estartus.tags.functions.DocumentFunctionsImpl;
import com.estartus.tags.model.Document;
import com.estartus.tags.service.DocumentService;
import com.estartus.tags.service.SecurityService;
import com.estartus.tags.validator.DocumentValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


/**
 * @author aestartus
 * @since 1/15/19.
 */

@Controller
public class DocumentsController {

    Logger logger = LoggerFactory.getLogger(DocumentsController.class);
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentValidator documentValidator;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private DocumentFunctionsImpl documentFunctions;

    @RequestMapping(value = {"/documents"}, method = RequestMethod.GET)
    public ModelAndView requestDocumentPage(ModelAndView modelAndView){
        modelAndView.addObject("documents", getDocuments());
        modelAndView.setViewName("documents");
        return modelAndView;
    }

    @RequestMapping(value = {"/documents/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<byte[]> requestDocumentById(@PathVariable(value = "id") String id, ModelAndView modelAndView) throws JsonProcessingException {

        Document document = documentService.findDocumentsById(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = document.getNameOfFile()+".pdf";

        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(document.getFile(), headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = {"/documents/see/{id}"}, method = RequestMethod.GET)
    public ModelAndView getDocument(@PathVariable(value = "id") String id, ModelAndView modelAndView){

        Document document = documentService.findDocumentsById(id);
        modelAndView.addObject("pages",document.getPages());
        modelAndView.addObject("document", document);
        modelAndView.setViewName("seeDocument");

        return modelAndView;
    }

    @RequestMapping(value = {"/documents/delete/{id}"}, method = RequestMethod.GET)
    public String deleteDocumentById(@PathVariable(value = "id") String id, ModelAndView modelAndView) throws JsonProcessingException {

        documentService.remove(id);

        return "redirect:/index";
    }

    @RequestMapping(value = {"/documents"}, method = RequestMethod.POST)
    public String saveDocument( @RequestParam("file") MultipartFile file,
                                @RequestParam("nameOfFile") String nameOfFile,
                                @RequestParam("metaData") String metaData,
                                RedirectAttributes redirectAttributes) throws IOException {


        Document document = new Document(securityService.findLoggedInUsername());
        document.setNameOfFile(nameOfFile);
        document.setFile(file.getBytes());
        document.setPages(documentFunctions.extractPages(document.getFile()));

        //documentValidator.validate(document,bindingResult);

        logger.info("usuario activo: {} grabando objeto: {}",document.getOwner(),document.getNameOfFile());

        documentService.save(document);
        logger.info("usuario activo: {} objeto almacenado: {}",document.getOwner(),document.getNameOfFile());
        return "documents";
    }

    private List getDocuments(){
        return documentService.findAll();
    }

}
