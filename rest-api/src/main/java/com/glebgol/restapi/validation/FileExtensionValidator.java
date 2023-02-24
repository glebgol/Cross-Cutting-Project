package com.glebgol.restapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, String>  {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("Txt|txt|Json|json|Xml|xml");
    }
}
