/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projeto;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import projeto.controller.ClienteJpaController;
import projeto.controller.FornecedorJpaController;
import projeto.controller.ProdutoJpaController;
import projeto.controller.VendaJpaController;
import projeto.entities.Cliente;
import projeto.entities.Fornecedor;
import projeto.entities.Produto;
import projeto.entities.Venda;


public class Relatorio extends javax.swing.JFrame {
    EntityManagerFactory emf;
    FornecedorJpaController fpc;
    ClienteJpaController cpc;
    VendaJpaController vpc;
    ProdutoJpaController ppc;
    
    public Relatorio() {
        initComponents();
        setLocationRelativeTo( null );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emf = Persistence.createEntityManagerFactory("jpaPU");
        fpc = new FornecedorJpaController(emf);
        cpc = new ClienteJpaController(emf);
        vpc = new VendaJpaController(emf);
        ppc = new ProdutoJpaController(emf);
     
    }
    private String buscarVenda(Integer idVenda){
       List <Venda> vendas = vpc.findVendaEntities();
            List <Cliente> clientes = cpc.findClienteEntities();
            for(Venda venda: vendas){
             for (Cliente cliente: clientes){
                    if(venda.getIDcliente().toString().equals(cliente.getIDcliente().toString())){
                        if(idVenda.equals(venda.getIDvenda())){
                            return cliente.getNome();
                            }
                        }
                    }
                }
            return "0";
   } 
    private String buscarNomeProduto(Integer iDVenda){
                 StoredProcedureQuery store = emf.createEntityManager().createStoredProcedureQuery("RetornoNomeProduto");
                 store.registerStoredProcedureParameter("p_IDVenda", Integer.class, ParameterMode.IN);
                 store.registerStoredProcedureParameter("NomeProduto", String.class, ParameterMode.IN);
                 store.setParameter("p_IDVenda", iDVenda);
                 store.execute();
                 
                 return (String) store.getOutputParameterValue("NomeProduto");
                 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jButton1.setText("Estoque");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cliente/ Fornecedor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Venda");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Gestão Inteligente para Master Freios");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(jLabel2)
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Relatórios");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(501, 501, 501)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fornecedor", "Cliente", "Venda", "Produto" }));

        jButton4.setText("Procurar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(348, 348, 348)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CadastroEstoque tela = new CadastroEstoque();
        tela.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CadastroClienteFornecedor tela = new CadastroClienteFornecedor ();
        tela.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CadastroVendas tela = new CadastroVendas ();
        tela.show();
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            DefaultTableModel modelo = new DefaultTableModel();
            if(jComboBox1.getSelectedIndex()==0){
                List <Fornecedor> fornecedor = fpc.findFornecedorEntities();
                modelo.addColumn("Id");
                modelo.addColumn("CNPJ");
                modelo.addColumn("Nome");
                modelo.addColumn("Email");
                modelo.addColumn("Telefone");
                modelo.addColumn("Endereço");
                modelo.addColumn("Cidade");
                modelo.addColumn("Estado");
                
                for(Fornecedor fornecedorT: fornecedor){
                    modelo.addRow(new Object[]{
                        fornecedorT.getIDfornecedor(),
                        fornecedorT.getCnpj(),
                        fornecedorT.getNome(),
                        fornecedorT.getEmail(),
                        fornecedorT.getTelefone(),
                        fornecedorT.getEndereco(),
                        fornecedorT.getCidade(),
                        fornecedorT.getEstado(),
                       });
                }
                  jTable1.setModel(modelo);
            }
         
            
        if(jComboBox1.getSelectedIndex()==1){
                List <Cliente> cliente = cpc.findClienteEntities();
                modelo.addColumn("Id");
                modelo.addColumn("CPF/CNPJ");
                modelo.addColumn("RG");
                modelo.addColumn("Nome");
                modelo.addColumn("Email");
                modelo.addColumn("Telefone");
                modelo.addColumn("Endereço");
                modelo.addColumn("Cidade");
                modelo.addColumn("Estado");
                for(Cliente clienteT: cliente){
                    modelo.addRow(new Object[]{
                        clienteT.getIDcliente(),
                        clienteT.getCpfCnpj(),
                        clienteT.getRg(),
                        clienteT.getNome(),
                        clienteT.getEmail(),
                        clienteT.getTelefone(),
                        clienteT.getEndereco(),
                        clienteT.getCidade(),
                        clienteT.getEstado(),
                       });
                }
                  jTable1.setModel(modelo);
        }
        
        
        
        if(jComboBox1.getSelectedIndex()==2){
             List <Venda> venda = vpc.findVendaEntities();
                modelo.addColumn("Id");
                modelo.addColumn("Nome Produto");
                modelo.addColumn("Nome Cliente");
                modelo.addColumn("Tipo Pagamento");
                modelo.addColumn("Estado da Venda");
                modelo.addColumn("Data da Venda");
                modelo.addColumn("Chave Nfe");
                modelo.addColumn("Valor Total");
                 for(Venda vendaT: venda){
                    modelo.addRow(new Object[]{
                        vendaT.getIDvenda(),
                        buscarNomeProduto(vendaT.getIDvenda()),//Nome Produto Procedure 
                        buscarVenda(vendaT.getIDvenda()),
                        vendaT.getTipoPagamento(),
                        vendaT.getEstadoVenda(),
                        vendaT.getDataDaVenda(),
                        vendaT.getCodVenda(),
                        vendaT.getValorTotal(),
                        
                        
                       });
                }
                  jTable1.setModel(modelo);
        }
        if(jComboBox1.getSelectedIndex()==3){
              List <Produto> produto = ppc.findProdutoEntities();
                 modelo.addColumn("Id");
                 modelo.addColumn("Nome");
                 modelo.addColumn("Categoria");
                 modelo.addColumn("Valor Pago");
                 modelo.addColumn("Valor Venda");
                 modelo.addColumn("Quantidade Comprada");
                 modelo.addColumn("Quantidade Vendida");
                 
                 for(Produto produtoT: produto){
                    modelo.addRow(new Object[]{
                     produtoT.getIDproduto(),
                     produtoT.getNome(),
                     produtoT.getCategoria(),
                     produtoT.getValorPago(),
                     produtoT.getValorVenda(),
                     produtoT.getQuantidadeComprada(),
                     produtoT.getQuantidadeVendida(),  
                       });
                }
                  jTable1.setModel(modelo);
        
        }
         
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Relatorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
