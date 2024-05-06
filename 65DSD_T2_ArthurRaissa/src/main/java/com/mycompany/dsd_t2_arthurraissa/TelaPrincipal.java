/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

public class TelaPrincipal extends javax.swing.JFrame {

    private JTable table = null;
    public ArrayList possiveisEntradas;
    public int tipoCenario;

    public TelaPrincipal() {
        setTitle("Matriz a partir de Arquivo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 1400);
        
        possiveisEntradas = new ArrayList();
        String[][] data = new String[DO_NOTHING_ON_CLOSE][DO_NOTHING_ON_CLOSE];
        
        tipoCenario = 3;
        if(tipoCenario == 1) {
            data = readDataFromFile("/home/warley/Desktop/Arthur/1_2024/65DSD/malha-exemplo-1.txt");
        }else if (tipoCenario == 2) {
            data = readDataFromFile("/home/warley/Desktop/Arthur/1_2024/65DSD/malha-exemplo-2.txt");
        } else if (tipoCenario == 3) {
            data = readDataFromFile("/home/warley/Desktop/Arthur/1_2024/65DSD/malha-exemplo-3.txt");
        }

        // Criando a tabela
        if (data != null) {
            table = new JTable(data.length, data[0].length);
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if(possivelEntrada(data[i][j], i, data.length, j, data[0].length)) {
                        possiveisEntradas.add(data[i][j]+"-"+i+":"+j);
                    }
                    table.setValueAt(data[i][j]+"-"+i+":"+j, i, j);
                }
            }

            // Definindo um renderizador de célula personalizado para a tabela
            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String cellText = (value == null) ? "" : value.toString();

//                    System.out.println(cellComponent.toString());
                    // Define o fundo da célula na linha 2 e coluna 2 como amarelo
                    switch (pegarDigitoPraCor(cellText)){
                        case "0":
                            cellComponent.setBackground(Color.WHITE);
                            break;
                        case "1":
                            cellComponent.setBackground(Color.BLUE);
                            break;
                        case "2":
                            cellComponent.setBackground(Color.GREEN);
                            break;
                        case "3":
                            cellComponent.setBackground(Color.LIGHT_GRAY);
                            break;
                        case "4":
                            cellComponent.setBackground(Color.RED);
                            break;
                        default:
                            cellComponent.setBackground(Color.DARK_GRAY);
                            break;
                    }
                    return cellComponent; // Retorna o componente da célula

                }
            });

            // Adicionando a tabela em um JScrollPane e colocando-o no JFrame
            JScrollPane scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        } else {
            System.out.println("Não foi possível ler os dados do arquivo.");
        }
    }
    
    private boolean possivelEntrada(String dado, int posI, int extremoI, int posJ, int extremoJ) {        
        if(dado.equals("2") && posJ == 0){
            return true;
        } else if(dado.equals("4") && posJ == (extremoJ-1)) {
            return true;
        } else if(dado.equals("3") && posI == 0) {
            return true;
        } else if(dado.equals("1") && posI == (extremoI-1)) {
            return true;
        }
        return false;
    }
    
    private String pegarDigitoPraCor(String cellText) {
        String digito = ""; 
        for(int i = 0; i < cellText.length(); i++){
            if(cellText.charAt(i) == '-') {
                return digito;
            }
            else {
                digito += cellText.charAt(i);
            }
        }
        return "";
    }

    private String[][] readDataFromFile(String filename) {
        String[][] data = null;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            scanner.nextLine(); // Para consumir a quebra de linha após a segunda linha de metadados
            data = new String[rows][cols];

            for (int i = 0; i < rows; i++) {
                String[] line = scanner.nextLine().trim().split("\\s+"); // Dividir a linha em espaços em branco
                for (int j = 0; j < cols; j++) {
                    data[i][j] = line[j];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public void mostrarPossiveisEntradas() {
        if (possiveisEntradas != null) {
            possiveisEntradas.forEach(System.out::println);
        } else {
            System.out.println("Não há possíveis entradas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal example = new TelaPrincipal();
            example.setVisible(true);
            
            example.mostrarPossiveisEntradas();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}