package com.estartus.tags.web;

import com.estartus.tags.functions.DocumentFunctionsImpl;
import com.estartus.tags.model.Document;
import com.estartus.tags.service.DocumentService;
import com.estartus.tags.service.SecurityService;
import com.estartus.tags.validator.DocumentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
