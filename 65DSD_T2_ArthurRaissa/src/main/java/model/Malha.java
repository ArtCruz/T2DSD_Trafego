/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Raissa
 */
public class Malha {
    private Celula matrizMalha[][];
    private int qtdLinhas;
    private int qtdColunas;
    private Scanner matrizScanner;
    private static Malha instance;

    private Random random = new Random();

    private Malha() {
        this.inicializarVariaveis();
        this.inicializarMalha();
        this.printMatriz();
    }

    public synchronized static Malha getInstance() {
        if (instance == null){
            reset();
        }
        return instance;
    }

    public void setCell(Celula celula){
        matrizMalha[celula.getLinha()][celula.getColuna()] = celula;
    }

    public synchronized static void reset(){
        instance = new Malha();
    }

    private void inicializarVariaveis(){
        File arquivoMalha = new File(Configuracoes.getInstance().getMalhaAtual());
        try{
            matrizScanner = new Scanner(arquivoMalha);
            this.qtdLinhas = matrizScanner.nextInt();
            this.qtdColunas = matrizScanner.nextInt();
            this.matrizMalha = new Celula[qtdLinhas][qtdColunas];
        }catch (Exception e){
            System.out.println(e.getMessage()+" - "+ Arrays.toString(e.getStackTrace()));
        }
    }

    private void inicializarMalha(){
        while (matrizScanner.hasNextInt()){
            for (int linha = 0; linha < this.qtdLinhas; linha++) {
                for (int coluna = 0; coluna < this.qtdColunas; coluna++) {
                    int tipo = matrizScanner.nextInt();
                    Celula celulaAtual = new Celula(coluna, linha, tipo, qtdLinhas, qtdColunas);
                    this.matrizMalha[linha][coluna] = celulaAtual;
                }
            }
        }
        matrizScanner.close();
    }
    private void printMatriz(){
        for (int linha = 0; linha < this.qtdLinhas; linha++) {
            for (int coluna = 0; coluna < this.qtdColunas; coluna++) {
                System.out.printf(this.matrizMalha[linha][coluna].toString()+" ");
            }
            System.out.println("");
        }
    }

    public Celula[][] getMatrizMalha() {
        return matrizMalha;
    }

    public int getQtdLinhas() {
        return qtdLinhas;
    }

    public int getQtdColunas() {
        return qtdColunas;
    }

    public Celula getProximaCelula(Celula celulaAtual) {
            if (celulaAtual.getClassificacao().equals(ClassCelula.SAIDA))
                return null;
            switch (celulaAtual.getTipo()){

                case TipoCelula.ESTRADA_CIMA:
                case TipoCelula.CRUZAMENTO_CIMA:
                    return getCelulaACima(celulaAtual);

                case TipoCelula.ESTRADA_DIREITA:
                case TipoCelula.CRUZAMENTO_DIREITA:
                    return getCelulaADireita(celulaAtual);

                case TipoCelula.ESTRADA_BAIXO:
                case TipoCelula.CRUZAMENTO_BAIXO:
                    return getCelulaABaixo(celulaAtual);

                case TipoCelula.ESTRADA_ESQUERDA:
                case TipoCelula.CRUZAMENTO_ESQUERDA:
                    return getCelulaAEsquerda(celulaAtual);


                case TipoCelula.CRUZAMENTO_CIMA_E_DIREITA:
                    if (random.nextInt(2) == 0)
                        return getCelulaACima(celulaAtual);
                    else
                        return getCelulaADireita(celulaAtual);

                case TipoCelula.CRUZAMENTO_CIMA_E_ESQUERDA:
                    if (random.nextInt(2) == 0)
                        return getCelulaACima(celulaAtual);
                    else
                        return getCelulaAEsquerda(celulaAtual);

                case TipoCelula.CRUZAMENTO_DIREITA_E_BAIXO:
                    if (random.nextInt(2) == 0)
                        return getCelulaADireita(celulaAtual);
                    else
                        return getCelulaABaixo(celulaAtual);

                case TipoCelula.CRUZAMENTO_BAIXO_E_ESQUERDA:
                    if (random.nextInt(2) == 0)
                        return getCelulaABaixo(celulaAtual);
                    else
                        return getCelulaAEsquerda(celulaAtual);
                default:
                    return null;
        }
    }

    public Celula getCelulaSaidaMaisProxima(Celula celula) {
        switch (celula.getTipo()){
            case TipoCelula.CRUZAMENTO_CIMA_E_DIREITA:
            case TipoCelula.CRUZAMENTO_DIREITA:
            case TipoCelula.ESTRADA_CIMA:
                return getCelulaADireita(celula);

            case TipoCelula.CRUZAMENTO_CIMA_E_ESQUERDA:
            case TipoCelula.CRUZAMENTO_CIMA:
            case TipoCelula.ESTRADA_DIREITA:
                return getCelulaACima(celula);

            case TipoCelula.CRUZAMENTO_DIREITA_E_BAIXO:
            case TipoCelula.CRUZAMENTO_BAIXO:
            case TipoCelula.ESTRADA_BAIXO:
                return getCelulaABaixo(celula);

            case TipoCelula.CRUZAMENTO_BAIXO_E_ESQUERDA:
            case TipoCelula.CRUZAMENTO_ESQUERDA:
            case TipoCelula.ESTRADA_ESQUERDA:
                return getCelulaAEsquerda(celula);
            default:
                return null;
        }
    }

    private Celula getCelulaACima(Celula celula){
        return matrizMalha[celula.getLinha()-1][celula.getColuna()];
    }
    private Celula getCelulaADireita(Celula celula){
        return matrizMalha[celula.getLinha()][celula.getColuna()+1];
    }
    private Celula getCelulaABaixo(Celula celula){
        return matrizMalha[celula.getLinha()+1][celula.getColuna()];
    }
    private Celula getCelulaAEsquerda(Celula celula){
        return matrizMalha[celula.getLinha()][celula.getColuna()-1];
    }
    private Celula getCelulaADiagonalEsquerdaCima(Celula celula){
        return matrizMalha[celula.getLinha()-1][celula.getColuna()-1];
    }
    private Celula getCelulaADiagonalEsquerdaBaixo(Celula celula){
        return matrizMalha[celula.getLinha()+1][celula.getColuna()-1];
    }
    private Celula getCelulaADiagonalDireitaCima(Celula celula){
        return matrizMalha[celula.getLinha()-1][celula.getColuna()+1];
    }
    private Celula getCelulaADiagonalDireitaBaixo(Celula celula){
        return matrizMalha[celula.getLinha()+1][celula.getColuna()+1];
    }

    public ArrayList<Celula> getRegiaoCritica(Celula proximaCelula) {

        ArrayList<Celula> regiaoCritica = new ArrayList<>();

        regiaoCritica.add(proximaCelula);

        switch (proximaCelula.getTipo()){
            case TipoCelula.CRUZAMENTO_CIMA:
                regiaoCritica.add(this.getCelulaACima(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalEsquerdaCima(proximaCelula));
                regiaoCritica.add(this.getCelulaAEsquerda(proximaCelula));
                break;
            case TipoCelula.CRUZAMENTO_DIREITA:
                regiaoCritica.add(this.getCelulaADireita(proximaCelula));
                regiaoCritica.add(this.getCelulaACima(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalDireitaCima(proximaCelula));
                break;
            case TipoCelula.CRUZAMENTO_BAIXO:
                regiaoCritica.add(this.getCelulaABaixo(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalDireitaBaixo(proximaCelula));
                regiaoCritica.add(this.getCelulaADireita(proximaCelula));
                break;
            case TipoCelula.CRUZAMENTO_ESQUERDA:
            case TipoCelula.CRUZAMENTO_CIMA_E_ESQUERDA:
                regiaoCritica.add(this.getCelulaAEsquerda(proximaCelula));
                regiaoCritica.add(this.getCelulaABaixo(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalEsquerdaBaixo(proximaCelula));
                break;
            case TipoCelula.CRUZAMENTO_CIMA_E_DIREITA:
                regiaoCritica.add(this.getCelulaAEsquerda(proximaCelula));
                regiaoCritica.add(this.getCelulaACima(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalEsquerdaCima(proximaCelula));
                break;
            case TipoCelula.CRUZAMENTO_DIREITA_E_BAIXO:
                regiaoCritica.add(this.getCelulaACima(proximaCelula));
                regiaoCritica.add(this.getCelulaADireita(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalDireitaCima(proximaCelula));
                break;
            case TipoCelula.CRUZAMENTO_BAIXO_E_ESQUERDA:
                regiaoCritica.add(this.getCelulaABaixo(proximaCelula));
                regiaoCritica.add(this.getCelulaADireita(proximaCelula));
                regiaoCritica.add(this.getCelulaADiagonalDireitaBaixo(proximaCelula));
                break;
        }

        return regiaoCritica;
    }
}
