package com.example.lab02gtics.controller;

import com.example.lab02gtics.entity.Tablero;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


    Tablero tablero = new Tablero();

    @RequestMapping(value = "/buscaminas",method = RequestMethod.GET)

    public String buscaminas(){

        return "buscaminas";
    }

    @PostMapping("/jugar")
    public String guardarConf(@RequestParam("filas") String filas,
                              @RequestParam("columnas") String columnas,
                              @RequestParam("intentos") String intentos,
                              @RequestParam("bombas") String bombas,
                              @RequestParam("posiciones") String posiciones,
                              Model model
                              ){

        tablero.setFilas(Integer.parseInt(filas));
        tablero.setColumnas(Integer.parseInt(columnas));
        tablero.setIntentos(Integer.parseInt(intentos));
        tablero.setBombas(Integer.parseInt(bombas));

        int[][] arreglo = new int[tablero.getFilas()][tablero.getColumnas()];

        tablero.setCampo(arreglo);


        model.addAttribute("tablero",tablero);
        return "jugar";
    }

    @PostMapping("/minar")
    public String jugar(Model model){
        model.addAttribute("tablero",tablero);
        return "jugar";
    }

}
