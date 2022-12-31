package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.ant.AntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/getRoute")
public class EcosystemControler {
    @Autowired
   EcosystemService ecosystemService;

    @GetMapping()
    public String createNewAnt(@RequestBody Ant ant) {
        return ecosystemService.createNewRoute(ant);
    }
}
