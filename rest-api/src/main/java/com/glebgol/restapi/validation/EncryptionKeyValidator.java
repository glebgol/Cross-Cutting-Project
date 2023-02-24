package com.glebgol.restapi.validation;

import com.glebgol.businesslogic.utils.ciphers.KeyValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EncryptionKeyValidator implements ConstraintValidator<EncryptionKey, String> {
    @Override
    public boolean isValid(String key, ConstraintValidatorContext constraintValidatorContext) {
        return KeyValidation.isValidDecryptionKey(key);
    }
}
