/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Classe.Cargo;
import Classe.Limite_Digitos;
import DAO.DaoCargo;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Wesley Morais Santos
 */

public class TelaCadCargo extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadCargo
     */
    public TelaCadCargo(){
        initComponents();
        cadCargoSiglaTxt.setDocument(new Limite_Digitos(2));
        
        try {
            listarTabela();
        } catch (Exception e) {
            System.out.println(e);
        }
        consultarId();       
        
    }
    //Método para trazer o último id para inserção de novos dados
    public void consultarId(){
        DaoCargo daoCargo = new DaoCargo();
        try {
            cadCargoIdTxt.setText(daoCargo.ConsultarId().toString());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Limpeza de campos utilizados em salvar e limpar
    public void limparCampos(){
        cadCargoSiglaTxt.setText("");
        cadCargoDescrTxt.setText("");
        cadCargoSalarioTxt.setText("");
        try {
            listarTabela();
        } catch (Exception e) {
            System.out.println(e);
        }
        consultarId();
    }
    //Lista os dados existentes no banco na tabela da interface de amostragem
    public void listarTabela() throws SQLException{
        DefaultTableModel table = (DefaultTableModel) cadCargoTabelaCargos.getModel();
        table.getDataVector().removeAllElements();
        DaoCargo daoCargo = new DaoCargo();
        
        List<Cargo> listaCargos = new ArrayList<Cargo>();
        
        listaCargos = daoCargo.listarCargos();
             
        int i =0;
        while(listaCargos.size()>i){
            
            table.addRow(new Object[]{listaCargos.get(i).getId(), listaCargos.get(i).getSigla(), listaCargos.get(i).getDescricao(), listaCargos.get(i).getSalario()});
            i++;
        }      
        
    }
    //Armazena o Cargo na Base de dados
    public void salvarCargo(){
        if((cadCargoSiglaTxt.getText().equals("")) || (cadCargoDescrTxt.getText().equals("")) ||
          (cadCargoSalarioTxt.getText().toString().equals(""))){
            JOptionPane.showMessageDialog(null, "Pro favor preencha todos os campos!");
        } else{
        Cargo cargo = new Cargo();
        cargo.setId(Integer.parseInt(cadCargoIdTxt.getText()));
        cargo.setSigla(cadCargoSiglaTxt.getText().toString().toUpperCase());
        cargo.setDescricao(cadCargoDescrTxt.getText().toString().toUpperCase());
        cargo.setSalario(Float.parseFloat(cadCargoSalarioTxt.getText().replace(",", ".")));
        
        DaoCargo daoCargo = new DaoCargo();
        daoCargo.salvar(cargo);
        try {
            listarTabela();
        } catch (Exception e) {
            System.out.println(e);
        }
        limparCampos();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cadCargoDescrTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cadCargoSalarioTxt = new javax.swing.JFormattedTextField();
        cadCargoBtnSalvar = new javax.swing.JButton();
        cadCargoBtnEditar = new javax.swing.JButton();
        cadCargoBtnCancelar = new javax.swing.JButton();
        cadCargoBtnLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cadCargoTabelaCargos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        cadCargoIdTxt = new javax.swing.JFormattedTextField();
        cadCargoSiglaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de Cargos");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sigla:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descrição:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Salário:");

        cadCargoSalarioTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        cadCargoBtnSalvar.setText("Salvar");
        cadCargoBtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCargoBtnSalvarActionPerformed(evt);
            }
        });

        cadCargoBtnEditar.setText("Editar");
        cadCargoBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCargoBtnEditarActionPerformed(evt);
            }
        });

        cadCargoBtnCancelar.setText("Cancelar");
        cadCargoBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCargoBtnCancelarActionPerformed(evt);
            }
        });

        cadCargoBtnLimpar.setText("Limpar");
        cadCargoBtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCargoBtnLimparActionPerformed(evt);
            }
        });

        cadCargoTabelaCargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Sigla", "Descrição", "Salario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cadCargoTabelaCargos);
        if (cadCargoTabelaCargos.getColumnModel().getColumnCount() > 0) {
            cadCargoTabelaCargos.getColumnModel().getColumn(0).setPreferredWidth(50);
            cadCargoTabelaCargos.getColumnModel().getColumn(1).setPreferredWidth(50);
            cadCargoTabelaCargos.getColumnModel().getColumn(2).setPreferredWidth(200);
            cadCargoTabelaCargos.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID:");

        cadCargoIdTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        cadCargoIdTxt.setEnabled(false);
        cadCargoIdTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cadCargoIdTxtFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(474, 474, 474)
                            .addComponent(cadCargoBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cadCargoBtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(cadCargoBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cadCargoBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(31, 31, 31)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cadCargoSalarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cadCargoDescrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))
                            .addGap(31, 31, 31)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cadCargoIdTxt)
                                .addComponent(cadCargoSiglaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))))
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(246, 246, 246))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cadCargoIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cadCargoSiglaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadCargoDescrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadCargoSalarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadCargoBtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadCargoBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadCargoBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadCargoBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(654, 516));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadCargoBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCargoBtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cadCargoBtnCancelarActionPerformed

    private void cadCargoIdTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cadCargoIdTxtFocusGained
        
    }//GEN-LAST:event_cadCargoIdTxtFocusGained

    private void cadCargoBtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCargoBtnSalvarActionPerformed
        salvarCargo();
    }//GEN-LAST:event_cadCargoBtnSalvarActionPerformed

    private void cadCargoBtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCargoBtnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_cadCargoBtnLimparActionPerformed

    private void cadCargoBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCargoBtnEditarActionPerformed
        try{
            int linha = cadCargoTabelaCargos.getSelectedRow();
            cadCargoIdTxt.setText(cadCargoTabelaCargos.getValueAt(linha, 0).toString());
            cadCargoSiglaTxt.setText(cadCargoTabelaCargos.getValueAt(linha, 1).toString());
            cadCargoDescrTxt.setText(cadCargoTabelaCargos.getValueAt(linha, 2).toString());
            cadCargoSalarioTxt.setText(cadCargoTabelaCargos.getValueAt(linha, 3).toString()); 
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Selecione uma dos registros da tabela!");
        }
    }//GEN-LAST:event_cadCargoBtnEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadCargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadCargo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cadCargoBtnCancelar;
    private javax.swing.JButton cadCargoBtnEditar;
    private javax.swing.JButton cadCargoBtnLimpar;
    private javax.swing.JButton cadCargoBtnSalvar;
    private javax.swing.JTextField cadCargoDescrTxt;
    private javax.swing.JFormattedTextField cadCargoIdTxt;
    private javax.swing.JFormattedTextField cadCargoSalarioTxt;
    private javax.swing.JTextField cadCargoSiglaTxt;
    private javax.swing.JTable cadCargoTabelaCargos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
