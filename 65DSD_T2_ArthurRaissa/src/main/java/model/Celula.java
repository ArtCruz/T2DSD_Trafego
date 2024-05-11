/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 *
 * @author Raissa
 */
public class Celula {
    
    private int coluna;
    private int linha;
    private int tipo; 
    private String classificacao; 
    private Carro carroAtual = null;
    private int ultimaLinhaDaMalha;
    private int ultimaColunaDaMalha;
    private boolean celulaDisponivel = true;
    private Lock lock;
    private Semaphore semaforo;

    String mecanismoExclusaoMutua;


    public Celula(int coluna, int linha, int tipo, int qtdTotalLinhas, int qtdTotalColunas) {
        this.mecanismoExclusaoMutua = Configuracoes.getInstance().getMecanismoExclusaoMutua();
        this.lock = new ReentrantLock();
        this.semaforo = new Semaphore(1);
        this.coluna = coluna;
        this.linha = linha;
        this.tipo = tipo;
        this.ultimaLinhaDaMalha = qtdTotalLinhas - 1;
        this.ultimaColunaDaMalha = qtdTotalColunas - 1;
        this.setClassificacao();
    }

    public int getTipo() {
        return tipo;
    }

    public boolean estaDisponivel(){
        return this.carroAtual == null && this.celulaDisponivel;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    private void setClassificacao() {

        switch (this.tipo){
            case TipoCelula.VAZIO:
                this.classificacao = ClassCelula.VAZIO;
                break;
            case TipoCelula.ESTRADA_BAIXO:
                if (this.linha == ultimaLinhaDaMalha)
                    this.classificacao = ClassCelula.SAIDA;
                else if (this.linha == 0)
                    this.classificacao = ClassCelula.ENTRADA;
                else
                    this.classificacao = ClassCelula.ESTRADA;
                break;
            case TipoCelula.ESTRADA_CIMA:
                if (this.linha == ultimaLinhaDaMalha)
                    this.classificacao = ClassCelula.ENTRADA;
                else if (this.linha == 0)
                    this.classificacao = ClassCelula.SAIDA;
                else
                    this.classificacao = ClassCelula.ESTRADA;
                break;
            case TipoCelula.ESTRADA_DIREITA:
                if (this.coluna == ultimaColunaDaMalha)
                    this.classificacao = ClassCelula.SAIDA;
                else if (this.coluna == 0)
                    this.classificacao = ClassCelula.ENTRADA;
                else
                    this.classificacao = ClassCelula.ESTRADA;
                break;
            case TipoCelula.ESTRADA_ESQUERDA:
                if (this.coluna == ultimaColunaDaMalha)
                    this.classificacao = ClassCelula.ENTRADA;
                else if (this.coluna == 0)
                    this.classificacao = ClassCelula.SAIDA;
                else
                    this.classificacao = ClassCelula.ESTRADA;
                break;
            default:
                this.classificacao = ClassCelula.CRUZAMENTO;
        }
    }

    public boolean celulaEstaVazia(){
        return this.carroAtual == null;
    }

    public Carro getCarroAtual() {
        return carroAtual;
    }

    public void setCarroAtual(Carro carroAtual) {
        this.carroAtual = carroAtual;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getIcon(){
        if (this.carroAtual != null) {
            return Configuracoes.ICONS_PATH + "icon-carro.png";
        }
        else {
            return Configuracoes.ICONS_PATH + "icon" + this.tipo + ".png";
        }
    }

    @Override
    public String toString() {
        return ""+this.tipo;
    }
    
    public boolean tentarReservarMonitor(){
        try{
            return this.lock.tryLock(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    public boolean tentarReservarSemaforo(){
        try{
            return this.semaforo.tryAcquire(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    public boolean tentarReservar(){
        if (this.carroAtual != null)
            return false;
        if (this.mecanismoExclusaoMutua.equals("Semaforo"))
            return this.tentarReservarSemaforo();
        else
            return tentarReservarMonitor();
    }

    public void liberar(){
        if (this.mecanismoExclusaoMutua.equals("Semaforo"))
            this.liberarSemaforo();
        else
            this.liberarMonitor();
    }

    public void liberarSemaforo(){
        try{
            this.semaforo.release();
        }catch (Exception e){}
    }

    public void liberarMonitor(){
        try{
            this.lock.unlock();
        }catch (Exception e){}
    }

}
