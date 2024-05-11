/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa.Controller;

import model.Celula;

/**
 *
 * @author Raissa
 */
public interface Observer {
    void atuIconeDaCelula(Celula celula);
    void atuCarrosNaMalha(int qtdCarrosMalha);
    void encerrar();
}
