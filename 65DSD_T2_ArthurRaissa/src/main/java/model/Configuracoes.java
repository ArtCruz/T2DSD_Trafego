/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Raissa
 */
public class Configuracoes {
    private static Configuracoes instancia;
    private String malhaAtual;
    private int qtdVeiculos;
    private double intervaloInsercao;
    private String mecanismoExclusaoMutua;

    private boolean gerarNovosCarros = true;

    public static final String ICONS_PATH = "C:/Users/Raissa/Documents/NetBeansProjects/T2DSD_Trafego/icones/";
    public static final String MALHA_PATH = "C:/Users/Raissa/Documents/NetBeansProjects/T2DSD_Trafego/";

    public boolean emExecucao = false;

    public int getQtdVeiculos(){
        return qtdVeiculos;
    }


    private Configuracoes() {
    }

    public static synchronized Configuracoes getInstancia(){
        if (instancia == null)
            reset();
        return instancia;
    }

    public static synchronized void reset(){
        instancia =  new Configuracoes();
    }

    public static void setInstancia(Configuracoes instancia) {
        Configuracoes.instancia = instancia;
    }

    public String getMalhaAtual() {
        System.out.println(MALHA_PATH + malhaAtual);
        return MALHA_PATH + malhaAtual;
    }

    public Configuracoes setMalhaAtual(String malhaAtual) {

        this.malhaAtual = malhaAtual;
        return instancia;
    }

    public Configuracoes setqtdVeiculos(int qtdVeiculos) {
        this.qtdVeiculos = qtdVeiculos;
        return instancia;
    }

    public Configuracoes setIntervaloInsercao(double intervaloInsercao) {
        this.intervaloInsercao = intervaloInsercao;
        return instancia;
    }

    public boolean getGerarNovosCarros(){
        return this.gerarNovosCarros;
    }

    public void setGerarNovosCarros(boolean gerarNovosCarros) {
        this.gerarNovosCarros = gerarNovosCarros;
    }

    public String getMecanismoExclusaoMutua() {
        return mecanismoExclusaoMutua;
    }

    public Configuracoes setMecanismoExclusaoMutua(String mecanismoExclusaoMutua) {
        this.mecanismoExclusaoMutua = mecanismoExclusaoMutua;
        return instancia;
    }
}
