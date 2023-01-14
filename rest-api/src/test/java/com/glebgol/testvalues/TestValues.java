package com.glebgol.testvalues;

public class TestValues {
    private static final String path = System.getProperty("user.dir");
    public static final String DEFAULT_ZIP = path +"\\src\\test\\resources\\default.zip";
    public static final String ENCRYPTED_ZIP = path +"\\src\\test\\resources\\encrypted.zip";
    public static final String TWICE_ENCRYPTED_ZIP = path + "\\src\\test\\resources\\double_encrypted.zip";
    public static final String TWICE_ENCRYPTED = path + "\\src\\test\\resources\\double_encrypted.txt";
    public static final String XML_FILE = path + "\\src\\test\\resources\\input.xml";
    public static final String JSON_FILE = path + "\\src\\test\\resources\\input.json";
    public static final String ENCRYPTED_TXT = path + "\\src\\test\\resources\\encrypted.txt";
    public static final String INPUT_FILE_TXT = path + "\\src\\test\\resources\\input-file.txt";
    public static final String ENC_XML = path + "\\src\\test\\resources\\enc_xml.txt";
    public static final String ENC_JSON = path + "\\src\\test\\resources\\enc_json.txt";
    public static final String OUTPUT_TXT = "output.txt";
    public static final String OUTPUT_XML = "output.xml";
    public static final String OUTPUT_JSON = "output.json";
    public static final String FIRST_KEY = "qwsdcvbgfthyrdfw";
    public static final String SECOND_KEY = "asdfghjkqewrtyto";
    public static final String KEYS = "qwsdcvbgfthyrdfw,asdfghjkqewrtyto";

    public static String MESSAGE_FOR_NOT_UPLOADED_FILE(String fileName) {
        return String.format("File \"%s\" did not uploaded.", fileName);
    }

}
