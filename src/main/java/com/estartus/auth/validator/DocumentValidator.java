package com.estartus.auth.validator;

import com.estartus.auth.model.Document;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author aestartus
 * @since 2/16/19.
 */

@Component
public class DocumentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Document.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Document document = (Document) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfFile", "NotEmpty");
        if (document.getNameOfFile().length() < 6 || document.getNameOfFile().length() > 32) {
            errors.rejectValue("nameOfFile", "Size.userForm.nameOfFile");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "NotEmpty");

    }
}
