/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import model.Veiculo;
import com.mycompany.dsd_t2_arthurraissa.Controller.VeiculoController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

public class TelaPrincipal extends javax.swing.JFrame {

    private JTable table = null;
    public ArrayList possiveisEntradas;
    public int tipoCenario;

    public TelaPrincipal() {
        
        //inicializações
        initComponents();
        setTitle("Matriz a partir de Arquivo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 1400);
        possiveisEntradas = new ArrayList();
        
        
    }
    

    public void carregarCenario(int tipo){
        
        if (table != null) {
            panelTabela.remove(table);
        }
        possiveisEntradas.clear();
        table = null;
        
        
        String[][] data = null; //new String[DO_NOTHING_ON_CLOSE][DO_NOTHING_ON_CLOSE];
        this.tipoCenario = tipo;
        switch (tipo){
            case 1: 
                data = readDataFromFile("C:/Users/Raissa/Documents/NetBeansProjects/T2DSD_Trafego/malha-exemplo-1.txt");
                break; 
            case 2: 
                data = readDataFromFile("C:/Users/Raissa/Documents/NetBeansProjects/T2DSD_Trafego/malha-exemplo-2.txt");
                break;
            case 3: 
                data = readDataFromFile("C:/Users/Raissa/Documents/NetBeansProjects/T2DSD_Trafego/malha-exemplo-3.txt");
                break;
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

            JScrollPane scrollPane = new JScrollPane(table);
            panelTabela.removeAll();
            panelTabela.setLayout(new BorderLayout());
            panelTabela.add(scrollPane, BorderLayout.CENTER);
            panelTabela.revalidate();
            panelTabela.repaint();
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

    public String[][] readDataFromFile(String filename) {
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
        // Crie uma instância de VeiculoController
        VeiculoController veiculoController = new VeiculoController(new Veiculo(1, 0, 0, example.possiveisEntradas));

        // Chame o método mover() do VeiculoController
        veiculoController.mover();
        
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

        jLabel3 = new javax.swing.JLabel();
        panelBotoes = new javax.swing.JPanel();
        lbQTveiculos = new javax.swing.JLabel();
        qtVeiculos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbSimulacao = new javax.swing.JComboBox<>();
        btnIniciar = new javax.swing.JButton();
        btnEncerrar = new javax.swing.JButton();
        btnCenario1 = new javax.swing.JButton();
        btnCenario2 = new javax.swing.JButton();
        btnCenario3 = new javax.swing.JButton();
        panelTabela = new javax.swing.JPanel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbQTveiculos.setBackground(new java.awt.Color(242, 190, 242));
        lbQTveiculos.setText("Quantidade máxima de veículos:");
        lbQTveiculos.setToolTipText("");
        lbQTveiculos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        qtVeiculos.setText("1");
        qtVeiculos.setToolTipText("");

        jLabel1.setText("Cenário ");

        jLabel2.setText("Tipo de simulação");

        cbSimulacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semáfaros", "Monitores" }));

        btnIniciar.setBackground(new java.awt.Color(60, 179, 113));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnIniciar.setText("Iniciar");

        btnEncerrar.setBackground(new java.awt.Color(255, 99, 71));
        btnEncerrar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEncerrar.setText("Encerrar");

        btnCenario1.setText("Simples");
        btnCenario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCenario1ActionPerformed(evt);
            }
        });

        btnCenario2.setText("Médio");
        btnCenario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCenario2ActionPerformed(evt);
            }
        });

        btnCenario3.setText("Difícil");
        btnCenario3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCenario3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotoesLayout = new javax.swing.GroupLayout(panelBotoes);
        panelBotoes.setLayout(panelBotoesLayout);
        panelBotoesLayout.setHorizontalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBotoesLayout.createSequentialGroup()
                        .addComponent(lbQTveiculos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qtVeiculos))
                    .addGroup(panelBotoesLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBotoesLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCenario1)
                        .addGap(12, 12, 12)
                        .addComponent(btnCenario2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCenario3)))
                .addGap(45, 45, 45)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBotoesLayout.setVerticalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qtVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQTveiculos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCenario1)
                    .addComponent(btnCenario2)
                    .addComponent(btnCenario3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelTabela.setBackground(new java.awt.Color(142, 242, 242));

        javax.swing.GroupLayout panelTabelaLayout = new javax.swing.GroupLayout(panelTabela);
        panelTabela.setLayout(panelTabelaLayout);
        panelTabelaLayout.setHorizontalGroup(
            panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTabelaLayout.setVerticalGroup(
            panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCenario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCenario1ActionPerformed
            carregarCenario(1);
    }//GEN-LAST:event_btnCenario1ActionPerformed

    private void btnCenario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCenario2ActionPerformed
            carregarCenario(2);
    }//GEN-LAST:event_btnCenario2ActionPerformed

    private void btnCenario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCenario3ActionPerformed
            carregarCenario(3);
    }//GEN-LAST:event_btnCenario3ActionPerformed

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCenario1;
    private javax.swing.JButton btnCenario2;
    private javax.swing.JButton btnCenario3;
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JComboBox<String> cbSimulacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbQTveiculos;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelTabela;
    private javax.swing.JTextField qtVeiculos;
    // End of variables declaration//GEN-END:variables
}
