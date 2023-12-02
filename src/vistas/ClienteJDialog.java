package vistas;

import controladores.ClientesJpaController;
import controladores.Herramientas;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Clientes;

public class ClienteJDialog extends javax.swing.JDialog {

    private ClientesJpaController ctrlClientes;
    private DefaultTableModel dtmCliente;
    private List<Clientes> listaClientes;

    public ClienteJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initConfiguracion();
    }

    private void initConfiguracion() {
        this.ctrlClientes = new ClientesJpaController(Herramientas.EMF);
        this.dtmCliente = (DefaultTableModel) jtCliente.getModel();
        this.jtCliente.setCellSelectionEnabled(false);
        this.jtCliente.setRowSelectionAllowed(true);
        this.jtCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        actualizarTabla();

        this.jtCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    actualizarInputsTexto();
                }
            }
        });
    }

    private void actualizarInputsTexto() {
        Clientes clienteSeleccionado = (Clientes) jtCliente.getValueAt(jtCliente.getSelectedRow(), 0);
        jtfCodigo.setText(clienteSeleccionado.getCodcliente());
        jtfNombre.setText(clienteSeleccionado.getNomcliente());
    }

    private void actualizarTabla() {
        listaClientes = ctrlClientes.findClientesEntities();
        dtmCliente.setRowCount(0);
        for (Clientes c : this.listaClientes) {
            dtmCliente.addRow(new Object[]{c});
        }
        jtfCodigo.setText("");
        jtfNombre.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbActualizarTabla = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jbCrear = new javax.swing.JButton();
        jbModificar = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jspCliente = new javax.swing.JScrollPane();
        jtCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de clientes");
        setResizable(false);

        jbActualizarTabla.setText("Actualizar tabla");
        jbActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarTablaActionPerformed(evt);
            }
        });

        jLabel1.setText("Código");

        jLabel2.setText("Nombre");

        jbCrear.setText("Crear cliente");
        jbCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearActionPerformed(evt);
            }
        });

        jbModificar.setText("Modificar Cliente");
        jbModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModificarActionPerformed(evt);
            }
        });

        jbBorrar.setText("Eliminar Cliente");
        jbBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarActionPerformed(evt);
            }
        });

        jtCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCliente.setColumnSelectionAllowed(true);
        jtCliente.getTableHeader().setReorderingAllowed(false);
        jspCliente.setViewportView(jtCliente);
        jtCliente.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbCrear)
                        .addGap(18, 18, 18)
                        .addComponent(jbModificar)
                        .addGap(18, 18, 18)
                        .addComponent(jbBorrar))
                    .addComponent(jspCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCodigo)
                    .addComponent(jtfNombre)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jbActualizarTabla))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarTablaActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_jbActualizarTablaActionPerformed

    private void jbCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearActionPerformed
        //Compruebo que código de cliente que pretendo crear no esté vacío
        if (jtfCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[26], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        try {
            //Inicio la creación del nuevo cliente
            ctrlClientes.create(new Clientes(jtfCodigo.getText(), jtfNombre.getText()));
        } catch (PreexistingEntityException pe) {
            //Si el cliente ya existe, se informará y se detendrá el proceso.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[28] + " " + pe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (Exception ex) {
            //Cualquier otro error, se informará y detendrá el proceso de creación.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[28] + " " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si todo ha ido bien, se actualiza la tabla de la vista
        actualizarTabla();


    }//GEN-LAST:event_jbCrearActionPerformed

    private void jbModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarActionPerformed
        //Compruebo que código de cliente que pretendo modificar no esté vacío
        if (jtfCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[26], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        try {
            //Inicio la modificación del cliente
            Clientes cliente = ctrlClientes.findClientes(jtfCodigo.getText());
            //Si el código de cliente proporcionado no corresponde con ningún cliente, 'findClientes' devuelve null, así que informamos y detenemos le proceso
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, Herramientas.mensajes[3], "Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            cliente.setNomcliente(jtfNombre.getText());
            ctrlClientes.edit(cliente);
        } catch (NonexistentEntityException ne) {
            //Si el cliente que se pretende modificar no existe, se informará y se detendrá el proceso.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[40] + " " + ne.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (Exception ex) {
            //Cualquier otro error, se informará y detendrá el proceso de modificación.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[40] + " " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        //Si todo ha ido bien, se actualiza la tabla de la vista
        actualizarTabla();


    }//GEN-LAST:event_jbModificarActionPerformed

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed
        //Compruebo que código de cliente que pretendo borrar no esté vacío
        if (jtfCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[26], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Pregunto al usuario si realmente desea borrar el cliente e informo de las cosecuencias (se borrarán las facturas asociadas al cliente y lineas de factura), antes de continuar con el proceso
        int opcion = JOptionPane.showOptionDialog(this, Herramientas.mensajes[31], Herramientas.mensajes[30],
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"NO BORRAR NADA", "BORRAR CLIENTE, FACTURAS ASOCIADAS Y LÍNEAS DE FACTURA ", "CANCELAR"}, "NO BORRAR NADA");
        if (opcion != 1) {
            return;
        }
        try {
            //Inicio el borrado del cliente
            ctrlClientes.destroy(jtfCodigo.getText());
        } catch (IllegalOrphanException io) {
            //Si el cliente que se va a borrar está presente en alguna factura en un campo no nulleable, se informará y se detendrá el proceso.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[28] + " " + io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);

            return;
        } catch (NonexistentEntityException ne) {
            //Si el cliente que se pretende borrar no existe, se informará y se detendrá el proceso.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[28] + " " + ne.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (Exception ex) {
            //Cualquier otro error, se informará y detendrá el proceso de modificación.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[28] + " " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        //Si "error" está vacío, es que todo ha ido bien, así que actualizo las tablas de la vista
        actualizarTabla();
    }//GEN-LAST:event_jbBorrarActionPerformed

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
            java.util.logging.Logger.getLogger(ClienteJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteJDialog dialog = new ClienteJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jbActualizarTabla;
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbCrear;
    private javax.swing.JButton jbModificar;
    private javax.swing.JScrollPane jspCliente;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables
}
