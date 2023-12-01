package vistas;

import controladores.Herramientas;

public class PrincipalJFrame extends javax.swing.JFrame {

    public PrincipalJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonFamilia = new javax.swing.JButton();
        jButtonCliente = new javax.swing.JButton();
        jButtonFactura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica de Hibernate - Carlos Milena Quesada");
        setMinimumSize(new java.awt.Dimension(280, 240));
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(280, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 240));

        jToolBar1.setRollover(true);

        jButtonFamilia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonFamilia.setText("Familias");
        jButtonFamilia.setFocusable(false);
        jButtonFamilia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFamilia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFamiliaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonFamilia);

        jButtonCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonCliente.setText("Clientes");
        jButtonCliente.setFocusable(false);
        jButtonCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonCliente);

        jButtonFactura.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonFactura.setText("Facturas");
        jButtonFactura.setFocusable(false);
        jButtonFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFactura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFacturaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonFactura);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(335, 335, 335))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClienteActionPerformed
        ClienteJDialog clienteJDialog = new ClienteJDialog(this, true);
        clienteJDialog.setBounds(Herramientas.bondsDeDialogs(this, clienteJDialog));
        clienteJDialog.setVisible(true);
    }//GEN-LAST:event_jButtonClienteActionPerformed

    private void jButtonFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFacturaActionPerformed
        FacturaJDialog facturaJDialog = new FacturaJDialog(this, true);
        facturaJDialog.setBounds(Herramientas.bondsDeDialogs(this, facturaJDialog));
        facturaJDialog.setVisible(true);
    }//GEN-LAST:event_jButtonFacturaActionPerformed

    private void jButtonFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFamiliaActionPerformed
        FamiliaJDialog familiaJDialog = new FamiliaJDialog(this, true);
        familiaJDialog.setBounds(Herramientas.bondsDeDialogs(this, familiaJDialog));
        familiaJDialog.setVisible(true);
    }//GEN-LAST:event_jButtonFamiliaActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCliente;
    private javax.swing.JButton jButtonFactura;
    private javax.swing.JButton jButtonFamilia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
