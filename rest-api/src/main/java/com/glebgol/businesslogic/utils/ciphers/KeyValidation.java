package com.glebgol.businesslogic.utils.ciphers;

import java.util.List;

public class KeyValidation {
    public static boolean isValidDecryptionKeys(List<String> decryptionKeys) {
        for (var key : decryptionKeys) {
            if (key.length() != 16) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidForCalculation(List<String> decryptionKeys) {
        return decryptionKeys == null || KeyValidation.isValidDecryptionKeys(decryptionKeys);
    }

    public static boolean isValidDecryptionKey(String decryptionKey) {
        return decryptionKey.length() == 16;
    }
}
