/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa.Controller;

import com.mycompany.dsd_t2_arthurraissa.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Raissa
 */
public class TelaPrincipalController {
    
    private TelaPrincipal view;

    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;

        // Adicione os ouvintes de eventos aos botões na view
        view.getBtnCenario1().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               view.carregarCenario(1);
            }
        });

        view.getBtnCenario2().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.carregarCenario(2);
            }
        });

        view.getBtnCenario3().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.carregarCenario(3);
            }
        });
    }
    
    
}
