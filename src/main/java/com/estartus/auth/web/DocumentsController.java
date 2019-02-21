package com.estartus.auth.web;

import com.estartus.auth.model.Document;
import com.estartus.auth.service.DocumentService;
import com.estartus.auth.service.SecurityService;
import com.estartus.auth.validator.DocumentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;


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

    @RequestMapping(value = {"/documents"}, method = RequestMethod.GET)
    public ModelAndView requestDocumentPage(ModelAndView modelAndView){
        modelAndView.addObject("documents", getDocuments());
        modelAndView.setViewName("documents");
        return modelAndView;
    }

    @RequestMapping(value = {"/documents"}, method = RequestMethod.POST)
    public String saveDocument(@ModelAttribute("documentForm") Document document, BindingResult bindingResult, Model model){
        documentValidator.validate(document,bindingResult);
        if(bindingResult.hasErrors()){
            return "documents";
        }
        logger.info("usuario activo: {} grabando objeto: {}",document.getOwner(),document.getNameOfFile());
        documentService.save(document);
        logger.info("usuario activo: {} objeto almacenado: {}",document.getOwner(),document.getNameOfFile());
        return "documents";
    }

    private List getDocuments(){
        return documentService.findAll();
    }

}
