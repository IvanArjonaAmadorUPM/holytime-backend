package com.example.holytime.backend.ecosystem;

import com.example.holytime.backend.ant.Ant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EcosystemService {
        Ecosystem ecosystem;
        public EcosystemService() {
            this.ecosystem = new Ecosystem();
        }
        public void createNewRoute(Ant ant) {

            System.out.println("Petición realizada");
            System.out.println(ant.toString());
            initEcosystem(ant);
    }

    public void initEcosystem(Ant ant) {
        System.out.println("El ecosistema se está inicializando");

    }

}
