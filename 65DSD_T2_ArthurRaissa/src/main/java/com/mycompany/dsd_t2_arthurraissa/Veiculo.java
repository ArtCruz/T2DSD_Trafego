/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author warley
 */
public class Veiculo { 
    private String posicao;
    private int id;
    private int dataHoraVeiculoChegaIntersecao;
    private boolean existencia;
    private int velocidade;
    private ArrayList<String> possiveisEntradas; // Adicione esta linha

    public Veiculo(int id, int dataHoraVeiculoChegaIntersecao, int dataHoraVeiculoSaiIntersecao, ArrayList<String> possiveisEntradas) { // Modifique esta linha
        this.id = id;
        this.dataHoraVeiculoChegaIntersecao = dataHoraVeiculoChegaIntersecao;
        this.possiveisEntradas = possiveisEntradas; // Modifique esta linha
        posicao = ramdomOrigem(possiveisEntradas);
        
        System.out.println("Posição de Origem: " + posicao);
        //destino = ramdomDestino();
    }
    
    private String ramdomOrigem(ArrayList<String> possiveisEntradas) {       
        // Embaralhar a lista
        Collections.shuffle(possiveisEntradas);
        
        // Pegar o primeiro elemento embaralhado
        return possiveisEntradas.get(0);
    }
    
}

