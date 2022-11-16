package ciphers;

import java.util.List;

public class KeyValidation {
    public static boolean IsValidDecryptionKeys(List<String> decryptionKeys) {
        for (var key : decryptionKeys) {
            if (key.length() != 16) {
                return false;
            }
        }
        return true;
    }

    public static boolean IsValidDecryptionKey(String decryptionKey) {
        return decryptionKey.length() == 16;
    }
}
