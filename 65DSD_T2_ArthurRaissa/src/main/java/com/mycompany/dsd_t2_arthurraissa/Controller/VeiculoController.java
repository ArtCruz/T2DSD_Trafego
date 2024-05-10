/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa.Controller;

import model.Veiculo;

/**
 *
 * @author warley
 */
public class VeiculoController {
    private Veiculo veiculo;

    public VeiculoController(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void mover() {
        String posicaoAtual = this.veiculo.getPosicao();
        System.out.println(posicaoAtual);
        
        String sentidoMovimento = pegarSentidoVia(posicaoAtual);
        switch (sentidoMovimento) {
            case "1":
                System.out.println("Mover para Norte");
                break;
            case "2":
                System.out.println("Mover para Leste");
                break;
            case "3":
                System.out.println("Mover para Sul");
                break;
            case "4":
                 System.out.println("Mover para Oeste");
                break;
            default:
                throw new AssertionError();
        }        
    }
    
        private String pegarSentidoVia(String cellText) {
        String digito = ""; 
        for(int i = 0; i < cellText.length(); i++){
            if(cellText.charAt(i) == '-') {
                return digito;
            }
            else {
                digito += cellText.charAt(i);
            }
        }
        return "";
    }
    
    
}
