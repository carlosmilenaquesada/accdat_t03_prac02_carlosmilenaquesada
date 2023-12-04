package vistas;

import controladores.ClientesJpaController;
import controladores.GestorErrores;
import static controladores.GestorInformacion.panelBorradoCliente;
import controladores.Herramientas;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Clientes;

public class ClienteJDialog extends javax.swing.JDialog {

    private ClientesJpaController ctrlClientes;
    private DefaultTableModel dtmCliente;
    private List<Clientes> listaClientes;
    private JTextField[] inputsCliente;

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
        this.inputsCliente = new JTextField[]{jtfCodigo, jtfNombre};
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
        GestorErrores.cambiarABordeDefecto(inputsCliente);
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
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsCliente, GestorErrores.mensajesInputsVaciosCliente);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            try {
                //Inicio la creación del nuevo cliente
                ctrlClientes.create(new Clientes(jtfCodigo.getText(), jtfNombre.getText()));
            } catch (PreexistingEntityException pe) {
                //Si el cliente ya existe, se recoge el error.
                errores.add(pe.getMessage());
                GestorErrores.cambiarABordeError(jtfCodigo);
            } catch (Exception ex) {
                //Cualquier otro error, será recogido
                errores.add(ex.getMessage());
            }
        }

        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la creación, se actualiza la tabla de la vista
            actualizarTabla();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbCrearActionPerformed

    private void jbModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsCliente, GestorErrores.mensajesInputsVaciosCliente);
        //Inicio la modificación del cliente
        Clientes cliente = ctrlClientes.findClientes(jtfCodigo.getText());
        //Si el cliente exite, se modificarán los atributos, de lo contrario, se generará error.
        if (cliente != null) {
            cliente.setNomcliente(jtfNombre.getText());
        } else {
            errores.add(GestorErrores.mensajes[3]);
        }

        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            try {
                ctrlClientes.edit(cliente);
            } catch (IllegalOrphanException io) {
                //Si el cliente que voy a modificar no contiene al menos las mismas facturas que el cliente que se encuentra en la base de datos,
                //no se podrá actualizar, ya que las facturas que no estarían contenidas en el cliente que estoy editando quedarían sin cliente, y cliente
                //es un atributo not null. Aunque en este punto yo no estoy modificando ninguna factura, es posible que otro usuario de la base de 
                //datos sí lo haya hecho mientras yo manipulo al cliente, y esto podría disparar esta excepción.
                errores.add(String.join("\n", io.getMessages()));
            } catch (NonexistentEntityException ne) {
                //Si el cliente que se pretende modificar no existe, se genera el error.
                errores.add(ne.getMessage());
            } catch (Exception ex) {
                //Cualquier otro error, se informará y detendrá el proceso de modificación.
                errores.add(ex.getMessage());
            }
        }

        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la modificación, se actualiza la tabla de la vista
            actualizarTabla();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }


    }//GEN-LAST:event_jbModificarActionPerformed

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(new JTextField[]{jtfCodigo}, GestorErrores.mensajesInputsVaciosCliente);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            try {
                //Inicio el borrado del cliente. Si no hay facturas asociadas al cliente, se borra directamente
                ctrlClientes.destroy(jtfCodigo.getText());
            } catch (IllegalOrphanException io) {
                //Si el cliente que se va a borrar está presente en alguna factura, no podrá ser borrado de manera implícita con el destroy, produciendo esta excepción.
                //En tal caso, le pregunto al usuario si realmente quiere borrar cliente con borrado en cascada de facturas y lineas de facturas                
                int opcion = JOptionPane.showOptionDialog(this, panelBorradoCliente[0], panelBorradoCliente[1],
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{panelBorradoCliente[2], panelBorradoCliente[3], panelBorradoCliente[4]}, panelBorradoCliente[2]);
                if (opcion == 1) {
                    try {
                        //Si el usuario acepta, procedo con el borrado en cascada, el cual he implementado a través de una función
                        //creada por mí llamada destroyEnCascada().
                        ctrlClientes.destroyEnCascada(jtfCodigo.getText());
                    } catch (NonexistentEntityException nec) {
                        //Aunque acabo de comprobar en el paso anterior si el cliente existía,
                        //debo veriricarlo también en este punto, ya que el usuario debe aceptar el
                        //borrado mediante un JOptionPane y eso puede demorar un tiempo en el cual otro usuario de la base de datos
                        //podría haber borrado el cliente.                
                        errores.add(nec.getMessage());
                    }
                }
            } catch (NonexistentEntityException ne) {
                //Si el cliente que se pretende borrar no existe, se informará y se detendrá el proceso.
                errores.add(ne.getMessage());
            } catch (Exception ex) {
                //Cualquier otro error, se informará y detendrá el proceso de modificación.
                errores.add(ex.getMessage());
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras el borrado, se actualiza la tabla de la vista
            actualizarTabla();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
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
