package com.example.lab02gtics.controller;

import com.example.lab02gtics.entity.Tablero;
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

    @PostMapping("/buscaminas/conf")

    public String guardarConf(@RequestParam("filas") String filas,
                              @RequestParam("columnas") String columnas,
                              @RequestParam("intentos") String intentos,
                              @RequestParam("bombas") String bombas,
                              @RequestParam("posiciones") String posiciones
                              ){

        tablero.setFilas(Integer.parseInt(filas));
        tablero.setColumnas(Integer.parseInt(columnas));
        tablero.setIntentos(Integer.parseInt(intentos));
        tablero.setBombas(Integer.parseInt(bombas));

        int campo [][]= new int[tablero.getFilas()][tablero.getColumnas()];

        tablero.setCampo(campo);

        return "jugar";
    }

    @GetMapping("/jugar")
    public String jugar(Model model){
        model.addAttribute("tablero",tablero);
        return "minar";
    }

}
