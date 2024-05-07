/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import com.mycompany.dsd_t2_arthurraissa.Controller.VeiculoController;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author warley
 */
public class Veiculo { 
    private String posicao;
    private String proximaPosicao;
    private int id;
    private int dataHoraVeiculoChegaIntersecao;
    private boolean existencia;
    private int velocidade;
    private ArrayList<String> possiveisEntradas;
    
    private VeiculoController controller;

    public Veiculo(int id, int dataHoraVeiculoChegaIntersecao, int dataHoraVeiculoSaiIntersecao, ArrayList<String> possiveisEntradas) { // Modifique esta linha
        this.id = id;
        this.dataHoraVeiculoChegaIntersecao = dataHoraVeiculoChegaIntersecao;
        this.possiveisEntradas = possiveisEntradas; // Modifique esta linha
        posicao = ramdomOrigem(possiveisEntradas);
//        proximaPosicao = 
        
        System.out.println("Posição de Origem: " + posicao);
        controller = new VeiculoController(this);
    }

    public Veiculo() {
        
    }
    
    
    
    private String ramdomOrigem(ArrayList<String> possiveisEntradas) {       
        Collections.shuffle(possiveisEntradas);
        return possiveisEntradas.get(0);
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataHoraVeiculoChegaIntersecao() {
        return dataHoraVeiculoChegaIntersecao;
    }

    public void setDataHoraVeiculoChegaIntersecao(int dataHoraVeiculoChegaIntersecao) {
        this.dataHoraVeiculoChegaIntersecao = dataHoraVeiculoChegaIntersecao;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    
    
    
}

