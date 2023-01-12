package reactuitest;

public class TestValues {
    public static final String KEY = "qwsdcvbgfthyrdfw";
    public static final String ENCRYPTED_AND_ZIPPED_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\encrypted.zip";
    public static final String XML_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\input.xml";
    public static final String JSON_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\input.json";
    public static final String FILE_TO_ENCRYPT = System.getProperty("user.dir") + "\\src\\test\\resources\\fileForEnc.txt";
    public static final String ENCRYPTED_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\encrypted.txt";
    public static final String ENCRYPTED_FILE_NAME = "encrypted.txt";

    public static final String SUCCESSFULLY_CALCULATED = "Successfully calculated!";
    public static final String NOT_VALID_FORM = "Not valid form!";
    public static final String DISABLED_DOWNLOAD_BUTTON = "Disabled download button!";


    public static String ErrorMessageForNonExistentFile(String fileName) {
        return String.format("File \"%s\" did not downloaded", fileName);
    }

}
