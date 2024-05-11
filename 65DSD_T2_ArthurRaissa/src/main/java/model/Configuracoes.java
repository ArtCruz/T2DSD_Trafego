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
    private static Configuracoes instance;
    private String malhaAtual;
    private int qtdVeiculos;
    private double intervaloInsercao;
    private String mecanismoExclusaoMutua;

    private boolean spawnarNovosCarros = true;

    public static final String ICONS_PATH = "icons/";
    public static final String MALHA_PATH = "arquivos/";

    public boolean emExecucao = false;

    public int getQtdVeiculos(){
        return qtdVeiculos;
    }


    private Configuracoes() {
    }

    public static synchronized Configuracoes getInstance(){
        if (instance == null)
            reset();
        return instance;
    }

    public static synchronized void reset(){
        instance =  new Configuracoes();
    }

    public static void setInstance(Configuracoes instance) {
        Configuracoes.instance = instance;
    }

    public String getMalhaAtual() {
        return MALHA_PATH + malhaAtual;
    }

    public Configuracoes setMalhaAtual(String malhaAtual) {

        this.malhaAtual = malhaAtual;
        return instance;
    }

    public Configuracoes setqtdVeiculos(int qtdVeiculos) {
        this.qtdVeiculos = qtdVeiculos;
        return instance;
    }

    public Configuracoes setIntervaloInsercao(double intervaloInsercao) {
        this.intervaloInsercao = intervaloInsercao;
        return instance;
    }

    public boolean getSpawnarNovosCarros(){
        return this.spawnarNovosCarros;
    }

    public void setSpawnarNovosCarros(boolean spawnarNovosCarros) {
        this.spawnarNovosCarros = spawnarNovosCarros;
    }

    public String getMecanismoExclusaoMutua() {
        return mecanismoExclusaoMutua;
    }

    public Configuracoes setMecanismoExclusaoMutua(String mecanismoExclusaoMutua) {
        this.mecanismoExclusaoMutua = mecanismoExclusaoMutua;
        return instance;
    }
}
