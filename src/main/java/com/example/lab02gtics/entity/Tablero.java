package com.example.lab02gtics.entity;




public class Tablero {

    private int filas;
    private int columnas;
    private int intentos;
    private int bombas;

    private int [][] campo = new int[filas][columnas];

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getBombas() {
        return bombas;
    }

    public void setBombas(int bombas) {
        this.bombas = bombas;
    }


    public int[][] getCampo() {
        return campo;
    }

    public void setCampo(int[][] campo) {
        this.campo = campo;
    }
}
