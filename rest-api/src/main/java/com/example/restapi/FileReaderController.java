package com.example.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/calculate/")
public class FileReaderController {
    @GetMapping
    public String Add(@RequestParam(required = false) boolean zip, @RequestParam(value="encrypt", required = false) List<String> decryptionKeys) {
        return String.valueOf(zip) + decryptionKeys;
    }
}
