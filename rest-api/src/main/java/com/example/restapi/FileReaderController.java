package com.example.restapi;

import archivers.ArchivationFileManager;
import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import enums.FileExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/file-reader/")
public class FileReaderController {
    private boolean isValidDecryptionKeys(List<String> decryptionKeys) {
        if (decryptionKeys == null) {
            return true;
        }
        for (var key : decryptionKeys) {
            if (key.length() != 16) {
                return false;
            }
        }
        return true;
    }

    // http://localhost:8080/api/file-reader/calculate/?inputfile=double_encrypted.zip&outputfile=qwe.txt&iszipped=true&decryptionkeys=qwsdcvbgfthyrdfw,asdfghjkqewrtyto
    @GetMapping("calculate/")
    public ResponseEntity<String> Calculate(@RequestParam(value= "inputfile") String inputFilename,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(value = "iszipped", required = false) boolean isZipped,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys) {

        if (!isValidDecryptionKeys(decryptionKeys)) {
            return new ResponseEntity<>("Key must be 16 length, when decrypting with padded cipher", HttpStatus.BAD_REQUEST);
        }

        try {
            var readerBuilder = new FileReaderBuilder(inputFilename, outputFilename);
            readerBuilder.setFileExtension(FileExtension.Txt);
            if (decryptionKeys != null) {
                for (var key : decryptionKeys) {
                    readerBuilder.setEncrypting(key);
                }
            }
            if (isZipped) {
                readerBuilder.setZipping();
            }
            var reader = readerBuilder.getResult();
            reader.WriteCalculated();
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(inputFilename + " " + outputFilename + " " + isZipped + " " + decryptionKeys, HttpStatus.OK);
    }

    // http://localhost:8080/api/file-reader/encrypt/?inputfile=input.txt&outputfile=input3.txt&key=1234567812345678
    @GetMapping("encrypt/")
    public ResponseEntity<String> Encrypt(@RequestParam(value= "inputfile") String inputFilename,
                                            @RequestParam(value = "outputfile") String outputFilename,
                                            @RequestParam(value="key", required = false) String key) {
        try {
            CryptoUtils.Encrypt(key, inputFilename, outputFilename);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(inputFilename + " " + outputFilename + " " + key, HttpStatus.OK);
    }

    // http://localhost:8080/api/file-reader/zip/?inputfile=input.txt
    @GetMapping("zip/")
    public ResponseEntity<String> Zip(@RequestParam(value= "inputfile") String inputFilename) {
        try {
            ArchivationFileManager.ZipFile(inputFilename);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(inputFilename, HttpStatus.OK);
    }
}