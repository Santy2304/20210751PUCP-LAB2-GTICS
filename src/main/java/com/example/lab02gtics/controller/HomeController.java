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
    int vidas;
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

        vidas =  tablero.getIntentos();


        int[][] arreglo = new int[tablero.getFilas()][tablero.getColumnas()];

        tablero.setCampo(arreglo);

        String [] coordenadas = posiciones.split(" ");


        for (int i =0; i<coordenadas.length; i++){
            coordenadas[i] = coordenadas[i].replace("(", "").replace(")", "");

        }

        int[][] coordenadasDivididas = new int[coordenadas.length][2];
        for (int i = 0; i < coordenadas.length; i++) {
            String[] partes = coordenadas[i].split(",");
            coordenadasDivididas[i][0] = Integer.parseInt(partes[0]);
            coordenadasDivididas[i][1] = Integer.parseInt(partes[1]);
        }

        for (int k =0; k<coordenadas.length;k++){
            tablero.getCampo()[coordenadasDivididas[k][0]][coordenadasDivididas[k][1]] = -50;
        }

        model.addAttribute("tablero",tablero);
        return "jugar";
    }

    @PostMapping("/minar")
    public String minar(Model model, @RequestParam(name = "coordenadaExplotar") String coordenadaExplotar){

        String [] coordenadaExp = coordenadaExplotar.split(" ");

        contarMinasTablero(tablero);

        if (tablero.getCampo()[Integer.parseInt(coordenadaExp[0])][Integer.parseInt(coordenadaExp[1])] == -50){
            vidas = vidas -1;

            if (vidas == 0){
                String msg = "Usted a perdido el juego!";
                model.addAttribute("msg");

                return "jugar";
            }

        }else{


            return "jugar";
        }
        model.addAttribute("tablero",tablero);
        return "jugar";
    }


    public void contarMinasTablero(Tablero tablero) {

        for (int i = 0; i<tablero.getFilas(); i++){
            for (int j=0; j<tablero.getColumnas(); j++){

                if (tablero.getCampo()[i][j]==-50){

                    if (i==tablero.getFilas()-1 && j != tablero.getColumnas()-1){
                        for (int m = i-1; m<=i; m++){
                            for (int k = j-1; k<=j+1;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                    if (i!=tablero.getFilas()-1 && j == tablero.getColumnas()-1){
                        for (int m = i-1; m<=i+1; m++){
                            for (int k = j-1; k<=j;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                    if (i== tablero.getFilas()-1 && j == tablero.getColumnas()-1){
                        for (int m = i-1; m<=i; m++){
                            for (int k = j-1; k<=j;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                    if (i!= tablero.getFilas()-1 && j != tablero.getColumnas()-1){
                        for (int m = i-1; m<=i+1; m++){
                            for (int k = j-1; k<=j+1;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                    if (i==0 && j != tablero.getColumnas()-1){
                        for (int m = i; m<=i+1; m++){
                            for (int k = j-1; k<=j+1;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                    if (i!=0 && j ==0){
                        for (int m = i-1; m<=i+1; m++){
                            for (int k = j; k<=j+1;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                    if (i==0 && j ==0){
                        for (int m = i; m<=i+1; m++){
                            for (int k = j; k<=j+1;k++){
                                tablero.getCampo()[m][k] = tablero.getCampo()[m][k] +1;
                            }
                        }
                    }

                }
            }
        }
    }
}
