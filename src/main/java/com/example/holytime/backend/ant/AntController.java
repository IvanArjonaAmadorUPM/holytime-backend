package com.example.holytime.backend.ant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AntController {
    private final AntService antService;

    @Autowired
    public AntController(AntService antService) {
        this.antService = antService;
    }
}
