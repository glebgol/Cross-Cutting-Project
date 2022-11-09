package com.example.restapi;

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
    @GetMapping("calculate/")
    public ResponseEntity<String> Calculate(@RequestParam(value= "inputfile") String inputFilename,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(required = false) boolean zip,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys) {

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
