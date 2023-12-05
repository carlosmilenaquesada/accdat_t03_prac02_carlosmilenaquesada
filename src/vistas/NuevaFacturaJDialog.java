package vistas;

import controladores.ClientesJpaController;
import controladores.FacturasJpaController;
import controladores.GestorErrores;
import controladores.Herramientas;
import controladores.exceptions.PreexistingEntityException;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import modelos.Clientes;
import modelos.Facturas;

public class NuevaFacturaJDialog extends javax.swing.JDialog {

    DefaultComboBoxModel dcbmClienteFactura;
    ClientesJpaController ctrlClientes;
    FacturasJpaController ctrlFacturas;

    public NuevaFacturaJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ctrlClientes = new ClientesJpaController(Herramientas.EMF);
        ctrlFacturas = new FacturasJpaController(Herramientas.EMF);
        initComponents();
        initConfiguracion();
    }

    private void initConfiguracion() {
        dcbmClienteFactura = (DefaultComboBoxModel) jcbClienteFactura.getModel();

        rellenarJComboBox();
        jtfNumeroFactura.setEditable(false);
        jtfNumeroFactura.setBackground(Color.LIGHT_GRAY);
        jtfNumeroFactura.setText(generarNumeroFacturaAutoincremental().toString());

    }

    private Long generarNumeroFacturaAutoincremental() {
        Long siguienteNumero = (Long) ctrlFacturas.getEntityManager().createNamedQuery("Facturas.findMaxNumfactura").getSingleResult();
        if (siguienteNumero == null) {
            siguienteNumero = 0l;
        }
        return siguienteNumero + 1;
    }

    private void rellenarJComboBox() {
        for (Clientes c : ctrlClientes.findClientesEntities()) {
            dcbmClienteFactura.addElement(c);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNumeroFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbClienteFactura = new javax.swing.JComboBox<>();
        jbCrearFactura = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jsdFechaFactura = new com.toedter.calendar.JSpinnerDateEditor();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear nueva factura");
        setMaximumSize(new java.awt.Dimension(240, 164));
        setMinimumSize(new java.awt.Dimension(240, 164));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(240, 164));
        jPanel1.setMinimumSize(new java.awt.Dimension(240, 164));

        jLabel1.setText("Número de factura");

        jLabel2.setText("Fecha de factura");

        jLabel3.setText("Cliente de factura");

        jbCrearFactura.setText("Crear factura");
        jbCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearFacturaActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jsdFechaFactura.setModel(new javax.swing.SpinnerDateModel());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbCrearFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jcbClienteFactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jsdFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jsdFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbClienteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCrearFactura)
                    .addComponent(jbCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearFacturaActionPerformed
        //Validación de los input
        ArrayList<String> errores = new ArrayList<>();
        if (jcbClienteFactura.getSelectedItem() == null) {
            errores.add(GestorErrores.mensajes[5]);
        }
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar        
        if (errores.isEmpty()) {
            //Comprobamos que el número de factura y la fecha de factura tengan un formato válido (compruebo de manera independiente
            //cada uno para informar de los dos errores por separado) y también que el cliente porporcionado exista.

            Clientes cliente = ctrlClientes.findClientes(jcbClienteFactura.getSelectedItem().toString());

            if (cliente == null) {
                //Si el código de cliente proporcionado no corresponde a ningún cliente (a podido ser borrado de la base de datos mientras se elegía), se genera error
                errores.add(GestorErrores.mensajes[3]);
            }
            if (errores.isEmpty()) {
                //Si no a ocurrido ningún error hasta ahora, 'errores' estará vacío y se continuará
                try {
                    //Inicio la creación de la factura
                    ctrlFacturas.create(new Facturas(Long.valueOf(jtfNumeroFactura.getText()), jsdFechaFactura.getDate(), cliente));
                } catch (PreexistingEntityException pe) {
                    //Si la factura ya existe (alguien ha podido crear una factura con ese número de id en la base de datos mientras nosotros estábamos creándola), se recoge el error.
                    errores.add(pe.getMessage());
                } catch (Exception ex) {
                    //Cualquier otro error, será recogido
                    errores.add(ex.getMessage());
                }
            }
        }

        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la creación, se crea la factura, y se vuelve hacia atrás
            this.dispose();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbCrearFacturaActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(NuevaFacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaFacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaFacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaFacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevaFacturaJDialog dialog = new NuevaFacturaJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbCrearFactura;
    private javax.swing.JComboBox<String> jcbClienteFactura;
    private com.toedter.calendar.JSpinnerDateEditor jsdFechaFactura;
    private javax.swing.JTextField jtfNumeroFactura;
    // End of variables declaration//GEN-END:variables
}
