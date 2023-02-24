package com.glebgol.restapi.validation;

import com.glebgol.businesslogic.utils.ciphers.KeyValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DecryptionKeysForCalculationValidator implements ConstraintValidator<DecryptionKeysForCalculation, List<String>> {
    @Override
    public boolean isValid(List<String> keys, ConstraintValidatorContext constraintValidatorContext) {
        return KeyValidation.isValidForCalculation(keys);
    }
}
