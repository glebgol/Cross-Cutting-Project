package com.example.restapi.controllers;

import archivers.ArchivingFileManager;
import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import ciphers.KeyValidation;
import com.example.restapi.responses.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/file-reader/")
public class FileReaderController {
    @GetMapping("calculate/")
    public ResponseEntity<CalculateResponse> calculate(@RequestParam(value= "inputfile") String inputFilename,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(value = "iszipped", required = false) boolean isZipped,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys,
                                            @RequestParam(value = "extension") String extension) {

        if (decryptionKeys != null && !KeyValidation.isValidDecryptionKeys(decryptionKeys)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            var readerBuilder = new FileReaderBuilder(extension, inputFilename);
            readerBuilder.setEncrypting(decryptionKeys);
            readerBuilder.setZipping(isZipped);

            var reader = readerBuilder.getFileReader();
            reader.getResult(outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(new CalculateResponse(inputFilename, outputFilename));
    }

    @GetMapping("encrypt/")
    public ResponseEntity<EncryptResponse> encrypt(@RequestParam(value= "inputfile") String inputFilename,
                                                   @RequestParam(value = "outputfile") String outputFilename,
                                                   @RequestParam(value="key") String key) {
        if (!KeyValidation.isValidDecryptionKey(key)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CryptoUtils.encrypt(key, inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new EncryptResponse(inputFilename, outputFilename));
    }

    @GetMapping("decrypt/")
    public ResponseEntity<DecryptResponse> decrypt(@RequestParam(value= "inputfile") String inputFilename,
                                                   @RequestParam(value = "outputfile") String outputFilename,
                                                   @RequestParam(value="key") String key) {
        if (!KeyValidation.isValidDecryptionKey(key)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CryptoUtils.decrypt(key, inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new DecryptResponse(inputFilename, outputFilename));
    }

    @GetMapping("zip/")
    public ResponseEntity<ZipResponse> zip(@RequestParam(value= "inputfile") String inputFilename) {
        try {
            ArchivingFileManager.zipFile(inputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ZipResponse(inputFilename));
    }

    @GetMapping("unzip/")
    public ResponseEntity<UnzipResponse> unZip(@RequestParam(value= "inputfile") String inputFilename, @RequestParam(value= "outputfile") String outputFilename) {
        try {
            ArchivingFileManager.unZipFile(inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new UnzipResponse(inputFilename, outputFilename));
    }
}
