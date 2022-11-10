package com.example.restapi;

import builder.FileReaderBuilder;
import enums.FileExtension;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/")
public class FileReaderController {
    private boolean isValidDecryptionKeys(List<String> decryptionKeys) {
        for (var key : decryptionKeys) {
            if (key.length() != 16) {
                return false;
            }
        }
        return true;
    }

    @GetMapping("calculate/")
    public ResponseEntity<String> Calculate(@RequestParam(value= "inputfile") String inputFilename,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(required = false) boolean zip,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys) {

        if (decryptionKeys != null && !isValidDecryptionKeys(decryptionKeys)) {
            return new ResponseEntity<>("Key must be 16 length, when decrypting with padded cipher", HttpStatus.BAD_REQUEST);
        }

        try {
            var readerBuilder = new FileReaderBuilder(inputFilename, outputFilename);
            readerBuilder.setFileExtension(FileExtension.Txt);
            if (zip) {
                readerBuilder.setZipping();
            }
            var reader = readerBuilder.getResult();
            reader.WriteCalculated();

        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(inputFilename + " " + outputFilename + " " + String.valueOf(zip) + " " + decryptionKeys, HttpStatus.OK);
    }

    @GetMapping("encrypt/")
    public ResponseEntity<String> Encrypt(@RequestParam(value= "inputfile") String inputFilename,
                                            @RequestParam(value = "outputfile") String outputFilename,
                                            @RequestParam(value="key", required = false) String key) {

        return new ResponseEntity<>(inputFilename + " " + outputFilename + " " + key, HttpStatus.OK);
    }

    @GetMapping("zip/")
    public ResponseEntity<String> Zip(@RequestParam(value= "inputfile") String inputFilename,
                                          @RequestParam(value = "outputfile") String outputFilename) {

        return new ResponseEntity<>(inputFilename + " " + outputFilename, HttpStatus.OK);
    }
}
