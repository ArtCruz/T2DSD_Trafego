/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa.Controller;

import com.mycompany.dsd_t2_arthurraissa.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author Raissa
 */
public class CenarioController implements ActionListener{

    private JComboBox<String> cbCenario;
    private TelaPrincipal telaPrincipal;

    public CenarioController(JComboBox<String> cbCenario, TelaPrincipal telaPrincipal) {
        this.cbCenario = cbCenario;
        this.telaPrincipal = telaPrincipal;
        this.cbCenario.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cenario = (String) cbCenario.getSelectedItem();
        switch (cenario){
            case "1 - Simples": 
                telaPrincipal.carregarCenario(1);
                break; 
            case "2 - Médio": 
                telaPrincipal.carregarCenario(2);
                break;
            case "3 - Complexo": 
                telaPrincipal.carregarCenario(3);
                break;
        }    }
    
}
