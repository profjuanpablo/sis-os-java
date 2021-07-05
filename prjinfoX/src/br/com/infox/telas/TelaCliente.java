/*
 -- BY OTÁVIO HENRIQUE --
 Email para contato: otavio-henrique10@hotmail.com 
 linkedin link do perfil: https://www.linkedin.com/in/otávio-henrique-filgueiras-2746a120a/ 
 Gibhub link do perfil: https://github.com/ResoluteJax 
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //metodo para adicionar clientes 
    private void adicionar() {
        String sql = "insert into tbclientes(nomeCli,endCli,fone,email)values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            //retornar aos "?" para adicionar ao DB
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());

            if (((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha Todos os campos Obrigatórios");

            } else {

                //atualizar a tabela de usuario com os dados do form
                //confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, " Usuário Cadastrado com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro da TelaCliente: Adicionar");
        }
    }

    //metodo para pesquisar cientes no banco de dados pelo nome com filtros
    private void pesquisar_cliente() {
        String sql = "select * from tbclientes where nomeCli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //pasando conteudo do input pesquisar para o "?"
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela 
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro da TelaCliente: Pesquisar_cliente");

        }
    }

    //metodo para setar os campos do formulário com o conteudo da tabela
    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        
        //a linha abaixo desabilita o botão adicionar
        btnAdicionar.setEnabled(false);

    }

    //metodo Update
    private void alterar() {
        String sql = "update tbclientes set nomeCli=?,endCli=?,fone=?,email=? where idCli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());

            if (((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha Todos os campos Obrigatórios");

            } else {

                //confirmar a alteração dos dados
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Alterado com sucesso");

                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                    //a linha abaixo habilita o botão adicionar
                      btnAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro da TelaCliente: Alterar");
        }
    }

     //Remover usuario
    private void deletar(){
        //confirma a remoção
        int confirma = JOptionPane.showConfirmDialog(null,"Você está prestes a DESTRUIR os dados de cadastro do Cliente","Deseja remover o Cliente?",JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idCli=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1,txtCliId.getText());
                int apagado = pst.executeUpdate();
                if(apagado > 0){
                JOptionPane.showMessageDialog(null,"Cliente Deletado com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);
                    txtCliEmail.setText(null);
                }
                
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro da TelaUsurio: Deletar");
                    
            }
        } else {
        }
        
    }
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastrar Clientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel2.setText("Telefone:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel3.setText("Endereço:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel4.setText("Email:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));
        getContentPane().add(txtCliNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 206, -1));
        getContentPane().add(txtCliFone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 206, -1));
        getContentPane().add(txtCliEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 206, -1));
        getContentPane().add(txtCliEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 206, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Cadastro de Clientes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar Cadastro");
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Atualizar Cadastro");
        btnAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, -1, -1));

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Deletar Cadastro");
        btnRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("*");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 20, 20));

        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("*");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 30, -1));

        jInternalFrame1.setTitle("Cadastrar Clientes");
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Nome:");
        jInternalFrame1.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 75, -1, -1));

        jLabel11.setText("Telefone:");
        jInternalFrame1.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 107, -1, -1));

        jLabel12.setText("Endereço:");
        jInternalFrame1.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 139, -1, -1));

        jLabel13.setText("Email:");
        jInternalFrame1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 177, -1, -1));
        jInternalFrame1.getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 69, 206, -1));
        jInternalFrame1.getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 101, 206, -1));
        jInternalFrame1.getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 133, 206, -1));
        jInternalFrame1.getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 171, 206, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Cadastro de Clientes");
        jInternalFrame1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 20, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        jButton4.setToolTipText("Adicionar Cadastro");
        jButton4.setPreferredSize(new java.awt.Dimension(80, 80));
        jInternalFrame1.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 244, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        jButton5.setToolTipText("Atualizar Cadastro");
        jButton5.setPreferredSize(new java.awt.Dimension(80, 80));
        jInternalFrame1.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 244, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        jButton6.setToolTipText("Deletar Cadastro");
        jButton6.setPreferredSize(new java.awt.Dimension(80, 80));
        jInternalFrame1.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 244, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 51));
        jLabel15.setText("* Campos Obrigatórios");
        jInternalFrame1.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 0, 51));
        jLabel16.setText("*");
        jInternalFrame1.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 10, 10));

        jLabel17.setForeground(new java.awt.Color(255, 0, 51));
        jLabel17.setText("*");
        jInternalFrame1.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 74, 10, 10));

        jLabel18.setForeground(new java.awt.Color(255, 0, 51));
        jLabel18.setText("*");
        jInternalFrame1.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 10, 10));

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtCliPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 260, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 340, 90));

        jLabel7.setText("Id Cliente:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        txtCliId.setEnabled(false);
        getContentPane().add(txtCliId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 30, -1));

        jInternalFrame2.setTitle("Cadastrar Clientes");
        jInternalFrame2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("Nome:");
        jInternalFrame2.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 75, -1, -1));

        jLabel22.setText("Telefone:");
        jInternalFrame2.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 107, -1, -1));

        jLabel23.setText("Endereço:");
        jInternalFrame2.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 139, -1, -1));

        jLabel24.setText("Email:");
        jInternalFrame2.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 177, -1, -1));
        jInternalFrame2.getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 69, 206, -1));
        jInternalFrame2.getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 101, 206, -1));
        jInternalFrame2.getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 133, 206, -1));
        jInternalFrame2.getContentPane().add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 171, 206, -1));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Cadastro de Clientes");
        jInternalFrame2.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 20, -1, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        jButton7.setToolTipText("Adicionar Cadastro");
        jButton7.setPreferredSize(new java.awt.Dimension(80, 80));
        jInternalFrame2.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 244, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        jButton8.setToolTipText("Atualizar Cadastro");
        jButton8.setPreferredSize(new java.awt.Dimension(80, 80));
        jInternalFrame2.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 244, -1, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        jButton9.setToolTipText("Deletar Cadastro");
        jButton9.setPreferredSize(new java.awt.Dimension(80, 80));
        jInternalFrame2.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 244, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 51));
        jLabel26.setText("* Campos Obrigatórios");
        jInternalFrame2.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jLabel27.setForeground(new java.awt.Color(255, 0, 51));
        jLabel27.setText("*");
        jInternalFrame2.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 10, 10));

        jLabel28.setForeground(new java.awt.Color(255, 0, 51));
        jLabel28.setText("*");
        jInternalFrame2.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 74, 10, 10));

        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setText("*");
        jInternalFrame2.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 10, 10));

        getContentPane().add(jInternalFrame2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        setBounds(0, 0, 461, 443);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Chamar o metodo Adicionar Clientes
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed
    // o evento abaixo é do tipo " enquanto for digitado "
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        //chamar o metodo pesquisar clientes
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    //o evento que será usado para setar os campos da tabela (clicando com o mouse)
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // chamando o metodo para sertar os campos
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // Chama o metodo alterar
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // Chamar o método Deletar
        deletar();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
