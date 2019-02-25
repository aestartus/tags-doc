package com.estartus.tags.functions;

import com.estartus.tags.model.Page;

import java.io.IOException;
import java.util.List;

/**
 * Created by aestartus on 2/23/19.
 */
public interface DocumentFunctions {

    List<Page> extractPages(byte[] pdf) throws IOException;
}
