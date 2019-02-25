package com.estartus.tags.functions;

import com.estartus.tags.model.Page;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aestartus
 * @since 2/23/19.
 */
@Service
public class DocumentFunctionsImpl implements DocumentFunctions{

    public List<Page> extractPages(byte[] pdf) throws IOException {

        PDDocument document = PDDocument.load(pdf);
        List<Page> pages = new ArrayList<>();

        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            ImageIO.write(bim,"png",baos);
            baos.flush();

            Page pageDoc = new Page();
            pageDoc.setNumber(page+1);
            pageDoc.setPage(baos.toByteArray());

            baos.close();

            pages.add(pageDoc);
        }
        document.close();

        return pages;
    }
}
