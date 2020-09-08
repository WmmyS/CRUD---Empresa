/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Classe.Cargo;
import Classe.Funcionario;
import DAO.DaoCargo;

import DAO.DaoFuncionario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wesley Morais Santos
 */
public class TelaCadFuncionario extends javax.swing.JFrame {

    
    public TelaCadFuncionario() throws SQLException {
        initComponents();
        TelaCadFuncionarioIdTxt.enable(false);
        listarTabela();
        consultarId();
        listarCargoCombo();
        
    }
    public void LimparCampos() throws SQLException{
        TelaCadFuncionarioNomeTxt.setText("");
        TelaCadFuncionarioSobrenomeTxt.setText("");
        TelaCadFuncionarioCPFTxt.setText("");
        TelaCadFuncionarioRGTxt.setText("");
        TelaCadFuncionarioCargoCB.setSelectedIndex(0);
        listarTabela();
    }
    public void listarCargoCombo(){
        DaoCargo daoCargo = new DaoCargo();
        TelaCadFuncionarioCargoCB.removeAllItems();
        TelaCadFuncionarioCargoCB.addItem("");
        for(Cargo c:daoCargo.listarCargos()){
           TelaCadFuncionarioCargoCB.addItem(c.getDescricao());
        }
    }
    //Salva os funcionários na Base de Dados
    public void salvarFuncionario() throws SQLException{
        //Métodos para evitar do usuário deixar capos em branco
        if(TelaCadFuncionarioNomeTxt.getText().equals("") || TelaCadFuncionarioSobrenomeTxt.getText().equals("")||TelaCadFuncionarioCPFTxt.getText().equals("")||TelaCadFuncionarioRGTxt.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos!");
        }else{
            if(TelaCadFuncionarioCargoCB.getSelectedItem().toString().equals("")){
                JOptionPane.showMessageDialog(null, "Por favor selecione um cargo!");
            }else{
                //Tratamentos nas inserções de dados
                Cargo cargo = new Cargo();
                Funcionario funcionario = new Funcionario();
                funcionario.setId(Integer.parseInt(TelaCadFuncionarioIdTxt.getText()));
                funcionario.setNome(TelaCadFuncionarioNomeTxt.getText().toString().toUpperCase());
                funcionario.setSobrenome(TelaCadFuncionarioSobrenomeTxt.getText().toString().toUpperCase());
                funcionario.setCpf(TelaCadFuncionarioCPFTxt.getText().toString().replace(".","").replace("-", ""));
                funcionario.setRg(TelaCadFuncionarioRGTxt.getText().toString().replace(".","").replace("-", ""));
                cargo.setDescricao(TelaCadFuncionarioCargoCB.getSelectedItem().toString());
                //Conversão de classes para inserção de dados no Banco
                DaoCargo daoCargo = new DaoCargo();
                daoCargo.RetornarCargo(cargo);
                funcionario.setCargo(cargo);
                DaoFuncionario daoFuncionario = new DaoFuncionario();
                daoFuncionario.salvar(funcionario);
                //Limpeza de campos após a inserção de dados
                try{
                    listarTabela();
                    LimparCampos();
                } catch (Exception ex){
                    System.out.println("Inserção de funcionários deu errado!\n"+ex);
                }
            }
        }
    }
    //Método criado para Trazer o último id para inserção de um novo dado
    public void consultarId(){
        DaoFuncionario daoFuncionario = new DaoFuncionario();
        try {
            TelaCadFuncionarioIdTxt.setText(daoFuncionario.ConsultarId().toString());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Método criado para listar os dados na tabela de amostragem
    public void listarTabela() throws SQLException{
        DefaultTableModel table = (DefaultTableModel) CadFuncTabelaFunc.getModel();
        table.getDataVector().removeAllElements();
        DaoFuncionario daoFuncionario = new DaoFuncionario();
        
        List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
        
        listaFuncionarios = daoFuncionario.listarFuncionarios();      
        
        int i =0;
        while(listaFuncionarios.size()>i){
            
            table.addRow(new Object[]{listaFuncionarios.get(i).getId(), listaFuncionarios.get(i).getNome(), listaFuncionarios.get(i).getSobrenome(), listaFuncionarios.get(i).getCpf(), listaFuncionarios.get(i).getRg(),listaFuncionarios.get(i).getCargo().getDescricao()});
            i++;
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        TelaCadFuncionarioIdTxt = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        TelaCadFuncionarioNomeTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TelaCadFuncionarioSobrenomeTxt = new javax.swing.JTextField();
        TelaCadFuncionarioCPFTxt = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TelaCadFuncionarioRGTxt = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        TelaCadFuncionarioCargoCB = new javax.swing.JComboBox<>();
        cadFuncBtnSalvar = new javax.swing.JButton();
        cadFuncBtnEditar = new javax.swing.JButton();
        cadFuncBtnLimpar = new javax.swing.JButton();
        cadFuncBtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CadFuncTabelaFunc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de Funcionários");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID:");

        TelaCadFuncionarioIdTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TelaCadFuncionarioIdTxtFocusGained(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sobrenome:");

        try {
            TelaCadFuncionarioCPFTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CPF:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("RG:");

        try {
            TelaCadFuncionarioRGTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cargo:");

        TelaCadFuncionarioCargoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cadFuncBtnSalvar.setText("Salvar");
        cadFuncBtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFuncBtnSalvarActionPerformed(evt);
            }
        });

        cadFuncBtnEditar.setText("Editar");

        cadFuncBtnLimpar.setText("Limpar");
        cadFuncBtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFuncBtnLimparActionPerformed(evt);
            }
        });

        cadFuncBtnCancelar.setText("Cancelar");
        cadFuncBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFuncBtnCancelarActionPerformed(evt);
            }
        });

        CadFuncTabelaFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Sobrenone", "CPF", "RG", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CadFuncTabelaFunc);
        if (CadFuncTabelaFunc.getColumnModel().getColumnCount() > 0) {
            CadFuncTabelaFunc.getColumnModel().getColumn(0).setResizable(false);
            CadFuncTabelaFunc.getColumnModel().getColumn(0).setPreferredWidth(1);
            CadFuncTabelaFunc.getColumnModel().getColumn(1).setResizable(false);
            CadFuncTabelaFunc.getColumnModel().getColumn(1).setPreferredWidth(30);
            CadFuncTabelaFunc.getColumnModel().getColumn(2).setResizable(false);
            CadFuncTabelaFunc.getColumnModel().getColumn(2).setPreferredWidth(30);
            CadFuncTabelaFunc.getColumnModel().getColumn(3).setResizable(false);
            CadFuncTabelaFunc.getColumnModel().getColumn(3).setPreferredWidth(12);
            CadFuncTabelaFunc.getColumnModel().getColumn(4).setResizable(false);
            CadFuncTabelaFunc.getColumnModel().getColumn(4).setPreferredWidth(12);
            CadFuncTabelaFunc.getColumnModel().getColumn(5).setResizable(false);
            CadFuncTabelaFunc.getColumnModel().getColumn(5).setPreferredWidth(12);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TelaCadFuncionarioCargoCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TelaCadFuncionarioIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TelaCadFuncionarioNomeTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TelaCadFuncionarioSobrenomeTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TelaCadFuncionarioRGTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TelaCadFuncionarioCPFTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(cadFuncBtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(cadFuncBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cadFuncBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(cadFuncBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TelaCadFuncionarioIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TelaCadFuncionarioNomeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(TelaCadFuncionarioCPFTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TelaCadFuncionarioSobrenomeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(TelaCadFuncionarioRGTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TelaCadFuncionarioCargoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadFuncBtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadFuncBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadFuncBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cadFuncBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(512, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(632, 516));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadFuncBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFuncBtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cadFuncBtnCancelarActionPerformed

    private void TelaCadFuncionarioIdTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelaCadFuncionarioIdTxtFocusGained
        
    }//GEN-LAST:event_TelaCadFuncionarioIdTxtFocusGained

    private void cadFuncBtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFuncBtnSalvarActionPerformed
        try {
            salvarFuncionario();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cadFuncBtnSalvarActionPerformed

    private void cadFuncBtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFuncBtnLimparActionPerformed
        try {
            LimparCampos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cadFuncBtnLimparActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaCadFuncionario().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CadFuncTabelaFunc;
    private javax.swing.JFormattedTextField TelaCadFuncionarioCPFTxt;
    private javax.swing.JComboBox<String> TelaCadFuncionarioCargoCB;
    private javax.swing.JFormattedTextField TelaCadFuncionarioIdTxt;
    private javax.swing.JTextField TelaCadFuncionarioNomeTxt;
    private javax.swing.JFormattedTextField TelaCadFuncionarioRGTxt;
    private javax.swing.JTextField TelaCadFuncionarioSobrenomeTxt;
    private javax.swing.JButton cadFuncBtnCancelar;
    private javax.swing.JButton cadFuncBtnEditar;
    private javax.swing.JButton cadFuncBtnLimpar;
    private javax.swing.JButton cadFuncBtnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
