/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mycompany.dsd_t2_arthurraissa.Controller.MalhaController;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Raissa
 */
public class Carro extends Thread {
    private int tempoEspera; 
    private MalhaController malhaController;
    private Celula celulaAtual;
    private boolean finalizado = false;
    private final Random random = new Random();

    public Carro(MalhaController malhaController, Celula celulaAtual){
        this.malhaController = malhaController;
        this.celulaAtual = celulaAtual;
        this.tempoEspera = (random.nextInt(10)*100) + 200; // sorteio de 0,2 segundos a 1,2 segundos de espera
        celulaAtual.setCarroAtual(this);
    }

    public Celula getCelulaAtual() {
        return celulaAtual;
    }

    @Override
    public void run() {
        while (Configuracoes.getInstancia().emExecucao && !this.finalizado){
            Celula proximaCelula = Malha.getInstance().getProximaCelula(celulaAtual);

            
            if (proximaCelula == null)
                sairDaMalha();

            
            else if (proximaCelula.getClassificacao().equals(ClassCelula.CRUZAMENTO))
                this.locomoverRegiaoCritica(proximaCelula);

            
            else
                locomoverEstradaComum(proximaCelula);
        }
        this.finalizar();
    }

    private void sairDaMalha(){
        aguardar();
        this.finalizado = true;
    }

    private void locomoverEstradaComum(Celula proximaCelula){
        boolean locomoveu = false;
        while (!locomoveu){
            if (proximaCelula.tentarReservar()){
                locomover(proximaCelula);
                locomoveu = true;
                proximaCelula.liberar();
            }else
                try {
                    sleep(100 + random.nextInt(400));
                }catch (Exception e){
                    System.out.println(e);
                    System.out.println(e.getMessage());
                }
        }
    }

    private void locomoverRegiaoCritica(Celula proximaCelula){

        LinkedList<Celula> rotaCruzamento = this.getRotaCruzamento(proximaCelula);
        boolean reservou = false;

        while (!reservou){
            LinkedList<Celula> celulasReservadas = new LinkedList<>();
            for (Celula celula : rotaCruzamento){
                if (!celula.tentarReservar()) {
                    liberarCelulas(celulasReservadas);
                    try {
                        sleep(100 + random.nextInt(1000));
                    }catch (Exception e){
                        System.out.println(e);
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                celulasReservadas.add(celula);
                reservou = celulasReservadas.size() == rotaCruzamento.size();
            }
        }
        andarNoCruzamento(rotaCruzamento);
    }

    private LinkedList<Celula> getRotaCruzamento(Celula proximaCelula){
        LinkedList<Celula> rota = new LinkedList<>();
        rota.add(proximaCelula);
        while (rota.getLast().getClassificacao().equals(ClassCelula.CRUZAMENTO)) {
            if (rota.size() >=3)
                proximaCelula = Malha.getInstance().getCelulaSaidaMaisProxima(proximaCelula);
            else
                proximaCelula = Malha.getInstance().getProximaCelula(proximaCelula);
            rota.add(proximaCelula);
        }
        return rota;
    }

    private void andarNoCruzamento(List<Celula> rota) {
        for (Celula celula : rota) {
            this.locomover(celula);
            celula.liberar();
        }
    }

    private synchronized void liberarCelulas(List<Celula> celulas){
        for (Celula celula : celulas){
            celula.liberar();
        }
    }

    public void aguardar() {
        try {
            Thread.sleep(tempoEspera);
        } catch (InterruptedException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void locomover(Celula celulaDestino){
        this.aguardar();

        this.celulaAtual.setCarroAtual(null);
        this.malhaController.atualizarIconeDaCelula(celulaAtual);

        celulaDestino.setCarroAtual(this);
        this.celulaAtual = celulaDestino;
        this.malhaController.atualizarIconeDaCelula(celulaDestino);
    }

    public void finalizar(){
        this.malhaController.removerCarroDaMalha(this);
        this.interrupt();
    }


    public void printInformacoes(){
        System.out.println(
                "Adicionado novo carro. [linha/coluna]"+this.celulaAtual.getLinha()+"/"+this.celulaAtual.getColuna()+". "+
                        this.celulaAtual.getClassificacao()+". "+this.tempoEspera+
                        ". Total de carros: "+this.malhaController.getQtdCarrosCirculacao()
        );
    }

    public synchronized void printCruzamentos(List<Celula> regiaoCritia){
        for (Celula c: regiaoCritia){
            System.out.print(c.getLinha()+","+c.getColuna()+"  ");
        }
        System.out.println("  ");
    }
}
