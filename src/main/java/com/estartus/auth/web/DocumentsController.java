package com.estartus.auth.web;

import com.estartus.auth.model.Document;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = {"/documents"}, method = RequestMethod.GET)
    public ModelAndView requestDocumentPage(ModelAndView modelAndView){
        modelAndView.addObject("documents", getDocuments());
        modelAndView.setViewName("documents");
        return modelAndView;
    }

    private List getDocuments(){

        Document document = new Document("aestartus@gmail.com");
        document.setId(1L);
        document.setCreationDate(new Date());
        document.setModificationDate(new Date());
        document.setValueOnCreation(UUID.randomUUID().toString());
        document.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        Document document1 = new Document("aestartus@gmail.com");
        document1.setId(2L);
        document1.setCreationDate(new Date());
        document1.setModificationDate(new Date());
        document1.setValueOnCreation(UUID.randomUUID().toString());
        document1.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        Document document2 = new Document("aestartus@gmail.com");
        document2.setId(3L);
        document2.setCreationDate(new Date());
        document2.setModificationDate(new Date());
        document2.setValueOnCreation(UUID.randomUUID().toString());
        document2.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        Document document3 = new Document("aestartus@gmail.com");
        document3.setId(4L);
        document3.setCreationDate(new Date());
        document3.setModificationDate(new Date());
        document3.setValueOnCreation(UUID.randomUUID().toString());
        document3.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        return Arrays.asList(document,document1,document2,document3);
    }

}
