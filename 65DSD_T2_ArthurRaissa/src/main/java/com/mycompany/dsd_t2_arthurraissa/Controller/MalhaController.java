/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Carro;
import model.Celula;
import model.ClassCelula;
import model.Configuracoes;
import model.Malha;

/**
 *
 * @author Raissa
 */
public class MalhaController extends Thread{
 
    private List<Carro> carrosEmCirculacao;
    private List<Observer> observers;

    public MalhaController() {
        this.carrosEmCirculacao = new ArrayList<>();
        this.observers= new ArrayList<>();
    }

    @Override
    public void run() {
        inicializar();
    }

    private void inicializar() {
        Configuracoes.getInstance().emExecucao = true;
        while (Configuracoes.getInstance().emExecucao){
            while (Configuracoes.getInstance().getSpawnarNovosCarros() && Configuracoes.getInstance().emExecucao){
                for (int linha = 0; linha < Malha.getInstance().getQtdLinhas(); linha++) {
                    for (int coluna = 0; coluna < Malha.getInstance().getQtdColunas(); coluna++) {
                        this.AtualizarCelula(linha,coluna);
                    }
                }
            }
            if (this.getQtdCarrosCirculacao() == 0)
                Configuracoes.getInstance().emExecucao = false;
        }
        encerrarSimulacao();
    }

    private void AtualizarCelula(int linha, int coluna){
        Celula celulaAtual = Malha.getInstance().getMatrizMalha()[linha][coluna];
        if (!celulaAtual.getClassificacao().equals(ClassCelula.ENTRADA)) 
            return;
        if (!celulaAtual.celulaEstaVazia()) // Tem de estar vazia
            return;
        if (this.getQtdCarrosCirculacao() == Configuracoes.getInstance().getQtdVeiculos()) 
            return;
        try{
            Thread.sleep(1000);
            adicionarNovoCarroAMalha(celulaAtual);
        } catch (Exception e){
            System.out.println(e.getMessage()+"   -   "+ Arrays.toString(e.getStackTrace()));
        }
    }

    private void adicionarNovoCarroAMalha(Celula celulaInicial){
        Carro carro = new Carro(this, celulaInicial);

        carrosEmCirculacao.add(carro);
        this.atualizarQuantidadeDeCarrosDaMalha();
        this.atualizarIconeDaCelula(celulaInicial);
        carro.printInformacoes();
        carro.start();
    }

    public void removerCarroDaMalha(Carro carro){
        this.carrosEmCirculacao.remove(carro);
        Celula celula = carro.getCelulaAtual();
        celula.setCarroAtual(null);
        this.atualizarIconeDaCelula(celula);
        this.atualizarQuantidadeDeCarrosDaMalha();
    }

    public void anexarObserver(Observer observer){
        this.observers.add(observer);
    }

    public int getQtdCarrosCirculacao(){
        return this.carrosEmCirculacao.size();
    }

    public void atualizarIconeDaCelula(Celula celula) {
        for (Observer obs: observers){
            obs.atuIconeDaCelula(celula);
        }
    }

    public void atualizarQuantidadeDeCarrosDaMalha(){
        for (Observer obs: observers){
            obs.atuCarrosNaMalha(this.getQtdCarrosCirculacao());
        }
    }

    public void encerrarSimulacao(){
        for (Observer obs: observers){
            obs.encerrar();
        }
    }

}
