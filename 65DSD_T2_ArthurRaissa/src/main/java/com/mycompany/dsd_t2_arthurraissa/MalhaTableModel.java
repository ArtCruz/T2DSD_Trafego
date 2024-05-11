/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import model.Malha;

/**
 *
 * @author Raissa
 */
public class MalhaTableModel extends AbstractTableModel {

    @Override
    public int getRowCount() {
        return Malha.getInstance().getQtdLinhas();
    }

    @Override
    public int getColumnCount() {
        return Malha.getInstance().getQtdColunas();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return new ImageIcon(Malha.getInstance().getMatrizMalha()[rowIndex][columnIndex].getIcon());
    }
    
}
