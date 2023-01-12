package helpers;

public class TestValues {
    public static final long TIME_TO_CALCULATION = 3500;
    public static final long TIME_TO_ENC_DEC = 3500;
    public static final long TIME_TO_DOWNLOAD = 3500;
    private static final String PATH = System.getProperty("user.dir") + "\\";
    public static final String KEY = "qwsdcvbgfthyrdfw";
    public static final String ENCRYPTED_AND_ZIPPED_FILE = PATH + "selenium-ui-tests\\src\\test\\resources\\encrypted.zip";
    public static final String XML_FILE = PATH + "selenium-ui-tests\\src\\test\\resources\\input.xml";
    public static final String JSON_FILE = PATH + "selenium-ui-tests\\src\\test\\resources\\input.json";
    public static final String FILE_TO_ENCRYPT = PATH + "selenium-ui-tests\\src\\test\\resources\\fileForEnc.txt";
    public static final String DOWNLOADED_ENCRYPTED_FILE = PATH + TestValues.ENCRYPTED_FILE_NAME;
    public static final String DOWNLOADED_DECRYPTED_FILE = PATH + TestValues.DECRYPTED_FILE_NAME;
    public static final String ENCRYPTED_FILE_NAME = "encrypted.txt";
    public static final String DECRYPTED_FILE_NAME = "decrypted.txt";


    public static final String SUCCESSFULLY_CALCULATED = "Successfully calculated!";
    public static final String NOT_VALID_FORM = "Not valid form!";
    public static final String DISABLED_DOWNLOAD_BUTTON = "Disabled download button!";


    public static String ErrorMessageForNonExistentFile(String fileName) {
        return String.format("File \"%s\" did not downloaded", fileName);
    }

    public static String ErrorMessageNotEqualFiles(String file1, String file2) {
        return String.format("Bytes \"%s\" are not equal to  \"%s\" bytes", file1, file2);
    }
}
