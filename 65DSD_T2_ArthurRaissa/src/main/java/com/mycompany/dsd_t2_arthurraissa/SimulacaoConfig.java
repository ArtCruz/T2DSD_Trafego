/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.dsd_t2_arthurraissa;

import com.mycompany.dsd_t2_arthurraissa.Controller.MalhaController;
import com.mycompany.dsd_t2_arthurraissa.Controller.Observer;
import model.Veiculo;
import com.mycompany.dsd_t2_arthurraissa.Controller.VeiculoController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
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
import javax.swing.table.TableColumnModel;
import model.Celula;
import model.Configuracoes;
import model.Malha;

public class SimulacaoConfig extends javax.swing.JFrame implements Observer{

    private JTable table = null;
    private MalhaController malhaController;
    private JPanel containerPanel;


    public SimulacaoConfig() {
        initComponents();
        
        setTitle("Simulação de Trafego");
        setSize(1200, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loadMalhas();
        
        //btnIniciar();
        //btnEncerrar();
        //Tabela();
        
//        malhaController = new MalhaController();
//        malhaController.anexarObserver(this);
//        malhaController.start();
        super.setVisible(true);
        
    }
    
    
    private void loadMalhas() {
        // Caminho para o diretório que contém os arquivos de malha
        File arquivo = new File("C:/Users/Raissa/Documents/NetBeansProjects/T2DSD_Trafego");

        if (arquivo.exists() && arquivo.isDirectory()) {
            File[] files = arquivo.listFiles();

            for (File file : files) {
                // Adiciona apenas arquivos, ignorando diretórios
                if (file.isFile()) {
                    // Verifica se o arquivo termina com ".txt" para evitar adicionar outros tipos de arquivo
                    if (file.getName().toLowerCase().endsWith(".txt")) {
                        cbCenario.addItem(file.getName());
                    }
                }
            }System.out.println("chegou aqui");
        }
    }   

    
    public void btnIniciar(){
        
       // btnIniciar.addActionListener((ActionEvent e) -> {
            Configuracoes.reset();
            Configuracoes.getInstancia().setMalhaAtual(getCenarioSelecionado())
                    .setMecanismoExclusaoMutua(getSimulacaoSelecionado())
                    .setqtdVeiculos(Integer.parseInt(qtVeiculos.getText()));
            Malha.reset();
            
            this.tabela();
            malhaController = new MalhaController();
            malhaController.anexarObserver(this);
            malhaController.start();
            
            //super.dispose();            
       // });
        
    }
    
    public String getCenarioSelecionado(){
        return (String) cbCenario.getSelectedItem();
    }

    public String getSimulacaoSelecionado(){
        return (String) cbSimulacao.getSelectedItem();
    }
    
    
    public void btnEncerrar() {
        btnEncerrar.addActionListener((ActionEvent e) -> {
            Configuracoes.getInstancia().emExecucao = false;
            Configuracoes.reset();
            super.dispose();
        });
    }
    
    
    public void tabela(){
        table = new JTable();
        table.setModel(new MalhaTableModel());
        table.setRowHeight(32);
        table.setDefaultRenderer(Object.class, new MalhaTableModelCellRenderer());

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setMaxWidth(32);
        }
        
        panelTabela.add(table);
    }

    public JPanel getPanelTabela() {
        return panelTabela;
    }
    
    
    @Override
    public void atuIconeDaCelula(Celula celula) {
        MalhaTableModel malhaTableModel = (MalhaTableModel) table.getModel();
        malhaTableModel.fireTableCellUpdated(celula.getLinha(), celula.getColuna());
        malhaTableModel.fireTableDataChanged();    
    }

    @Override
    public void atuCarrosNaMalha(int qtdCarrosMalha) {
       
    }

    @Override
    public void encerrar() {
        btnEncerrar.doClick();
    }
//    public static void main(String[] args) {
//    SwingUtilities.invokeLater(() -> {
//        TelaPrincipal example = new TelaPrincipal();
//        example.setVisible(true);        
//        // Crie uma instância de VeiculoController
//        VeiculoController veiculoController = new VeiculoController(new Veiculo(1, 0, 0, example.possiveisEntradas));
//
//        // Chame o método mover() do VeiculoController
//        veiculoController.mover();
//        
//        example.mostrarPossiveisEntradas();
//    });
//}


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
        cbCenario = new javax.swing.JComboBox<>();
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

        jLabel2.setText("Exclusão Mutua");

        cbSimulacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semáfaros", "Monitores" }));

        btnIniciar.setBackground(new java.awt.Color(60, 179, 113));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnEncerrar.setBackground(new java.awt.Color(255, 99, 71));
        btnEncerrar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEncerrar.setText("Encerrar");
        btnEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarActionPerformed(evt);
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
                        .addComponent(qtVeiculos, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                    .addGroup(panelBotoesLayout.createSequentialGroup()
                        .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCenario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSimulacao, 0, 231, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelBotoesLayout.setVerticalGroup(
            panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qtVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQTveiculos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCenario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGap(0, 262, Short.MAX_VALUE)
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

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
           btnIniciar();
           System.out.println("click iniciar");
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarActionPerformed
        btnEncerrar();
        System.out.println("click encerrar");
    }//GEN-LAST:event_btnEncerrarActionPerformed

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JComboBox<String> cbCenario;
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
