/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Raissa
 */
public class MalhaTableModelCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        setIcon((ImageIcon) value);
        return this;
    }
}
