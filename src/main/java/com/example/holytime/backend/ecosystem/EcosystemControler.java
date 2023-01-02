package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import com.example.holytime.backend.ant.AntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/getRoute")
public class EcosystemControler {
    @Autowired
   EcosystemService ecosystemService;

    @PostMapping()
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
    public String createNewAnt(@RequestBody Ant ant) {
        //print the request
        return ecosystemService.createNewRoute(ant);
    }
}
