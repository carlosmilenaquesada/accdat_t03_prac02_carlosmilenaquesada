package vistas;

import controladores.Crud;
import controladores.Herramientas;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import modelos.Articulos;
import modelos.Clientes;
import modelos.Facturas;

public class FacturaJDialog extends javax.swing.JDialog {

    private Crud crud;

    private DefaultTableModel dtmFactura;
    private List<Facturas> listaFacturas;
    private Facturas facturaEnFoco;

    private DefaultTableModel dtmLineaFactura;

    public FacturaJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.crud = new Crud();
        initComponents();
        initConfiguracion();
    }

    private void initConfiguracion() {
        this.dtmFactura = (DefaultTableModel) jtFactura.getModel();
        this.dtmLineaFactura = (DefaultTableModel) jtLineaFactura.getModel();
        this.jtFactura.setCellSelectionEnabled(false);
        this.jtLineaFactura.setCellSelectionEnabled(false);
        this.jtFactura.setRowSelectionAllowed(true);
        this.jtLineaFactura.setRowSelectionAllowed(true);
        this.jtFactura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jtLineaFactura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.facturaEnFoco = null;
        actualizarTablas();
        this.jtFactura.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {                
                if (e.getValueIsAdjusting()) {
                    facturaEnFoco = listaFacturas.get(jtFactura.getSelectedRow());
                    actualizarInputsFacturas();
                    actualizarTablaLineaFacturas();
                }
            }
        });

        this.jtLineaFactura.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    actualizarInputsLineaFacturas();
                }
            }
        });
    }

    private void actualizarInputsFacturas() {
        jtfNumeroFactura.setText(facturaEnFoco.getNumfactura().toString());
        jtfCodigoCliente.setText(facturaEnFoco.getClientes().getCodcliente());
        jtfFechaFactura.setText(Herramientas.dateAStringFormateado(facturaEnFoco.getFechafactura()));
    }

    private void actualizarInputsLineaFacturas() {
        jtfNumeroFacturaLinea.setText(jtLineaFactura.getValueAt(jtLineaFactura.getSelectedRow(), 0).toString());
        jtfCodigoArticuloLinea.setText((String) jtLineaFactura.getValueAt(jtLineaFactura.getSelectedRow(), 1));

    }

    private void actualizarTablas() {
        Herramientas.limpiarTabla(dtmFactura);
        this.listaFacturas = crud.readAllHQL("from Facturas f");
        for (Facturas f : this.listaFacturas) {
            dtmFactura.addRow(new Object[]{f.getNumfactura(), f.getClientes().getCodcliente(), Herramientas.dateAStringFormateado(f.getFechafactura())});
        }
        facturaEnFoco = null;
        jtfNumeroFactura.setText("");
        jtfCodigoCliente.setText("");
        jtfFechaFactura.setText("");
        actualizarTablaLineaFacturas();
    }

    private void actualizarTablaLineaFacturas() {
        Herramientas.limpiarTabla(dtmLineaFactura);
        if (facturaEnFoco != null) {            
            for (Articulos a : (Set<Articulos>) facturaEnFoco.getArticuloses()) {
                dtmLineaFactura.addRow(
                        new Object[]{facturaEnFoco.getNumfactura(), a.getCodarticulo()});
            }
        }
        jtfNumeroFacturaLinea.setText("");
        jtfCodigoArticuloLinea.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFactura = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNumeroFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfCodigoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfFechaFactura = new javax.swing.JTextField();
        jbCrearFactura = new javax.swing.JButton();
        jbModificarFactura = new javax.swing.JButton();
        jbBorrarFactura = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();
        jspFactura = new javax.swing.JScrollPane();
        jtFactura = new javax.swing.JTable();
        jpLineaFactura = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfNumeroFacturaLinea = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfCodigoArticuloLinea = new javax.swing.JTextField();
        jbCrearLineaFactura = new javax.swing.JButton();
        jbBorrarLineaFactura = new javax.swing.JButton();
        jspLineaFactura = new javax.swing.JScrollPane();
        jtLineaFactura = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de facturas");
        setMaximumSize(new java.awt.Dimension(684, 664));
        setMinimumSize(new java.awt.Dimension(684, 664));
        setResizable(false);

        jpFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText("Número factura (número entero)");

        jLabel2.setText("Código cliente");

        jLabel3.setText("Fecha factura (dd-mm-aaaa)");

        jbCrearFactura.setText("Crear factura");
        jbCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearFacturaActionPerformed(evt);
            }
        });

        jbModificarFactura.setText("Modificar factura");
        jbModificarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModificarFacturaActionPerformed(evt);
            }
        });

        jbBorrarFactura.setText("Eliminar factura");
        jbBorrarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarFacturaActionPerformed(evt);
            }
        });

        jbActualizar.setText("Actualizar");
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
            }
        });

        jtFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número factura", "Código cliente", "Fecha factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFactura.setColumnSelectionAllowed(true);
        jtFactura.getTableHeader().setReorderingAllowed(false);
        jspFactura.setViewportView(jtFactura);
        jtFactura.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jpFacturaLayout = new javax.swing.GroupLayout(jpFactura);
        jpFactura.setLayout(jpFacturaLayout);
        jpFacturaLayout.setHorizontalGroup(
            jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpFacturaLayout.createSequentialGroup()
                        .addComponent(jbCrearFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbModificarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBorrarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfNumeroFactura)
                    .addComponent(jtfCodigoCliente)
                    .addComponent(jtfFechaFactura)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFacturaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpFacturaLayout.setVerticalGroup(
            jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFacturaLayout.createSequentialGroup()
                .addGroup(jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbCrearFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbModificarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbBorrarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpFacturaLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpLineaFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Líneas de la factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel4.setText("Número factura (número entero)");

        jLabel5.setText("Código artículo");

        jbCrearLineaFactura.setText("Crear línea");
        jbCrearLineaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearLineaFacturaActionPerformed(evt);
            }
        });

        jbBorrarLineaFactura.setText("Eliminar línea");
        jbBorrarLineaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarLineaFacturaActionPerformed(evt);
            }
        });

        jtLineaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número factura", "Código artículo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLineaFactura.setColumnSelectionAllowed(true);
        jtLineaFactura.getTableHeader().setReorderingAllowed(false);
        jspLineaFactura.setViewportView(jtLineaFactura);
        jtLineaFactura.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jpLineaFacturaLayout = new javax.swing.GroupLayout(jpLineaFactura);
        jpLineaFactura.setLayout(jpLineaFacturaLayout);
        jpLineaFacturaLayout.setHorizontalGroup(
            jpLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLineaFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLineaFacturaLayout.createSequentialGroup()
                        .addComponent(jspLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNumeroFacturaLinea)
                            .addComponent(jtfCodigoArticuloLinea)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jpLineaFacturaLayout.createSequentialGroup()
                        .addComponent(jbCrearLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBorrarLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpLineaFacturaLayout.setVerticalGroup(
            jpLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLineaFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCrearLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrarLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpLineaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLineaFacturaLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNumeroFacturaLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoArticuloLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpLineaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jpLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(308, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearFacturaActionPerformed
        //Compruebo que código de factura que pretendo crear no esté vacío
        if (jtfNumeroFactura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[0], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Creo la factura, a la que le asigno el número de factura
        Facturas factura;
        try {
            factura = new Facturas(Herramientas.stringABigDecimalEntero(jtfNumeroFactura.getText()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Compruebo que no existiera previamente esa factura, para detener o no el proceso.
        if (listaFacturas.contains(factura)) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[2], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Añado el resto de atributos
        Clientes cliente = (Clientes) crud.read(Clientes.class, jtfCodigoCliente.getText());
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[3], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        factura.setClientes(cliente);
        try {
            factura.setFechafactura(Herramientas.stringADateFormateado(jtfFechaFactura.getText()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[4], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Los artículos de la factura, en este momento están vacíos
        factura.setArticuloses(new HashSet(0));
        //Inicio la creación
        String error = crud.create(factura);
        //Si "error" no está vacío, es que ha ocurrido un error al crear.
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[5] + " " + error, "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si "error" está vacío, es que todo ha ido bien, así que actualizo las tablas de la vista
        actualizarTablas();
    }//GEN-LAST:event_jbCrearFacturaActionPerformed

    private void jbModificarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarFacturaActionPerformed
        //Compruebo que número de factura que pretendo modificar no esté vacío
        if (jtfNumeroFactura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[0], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Compruebo que el código de factura proporcionado corresponda a una factura que ya exista
        int indexFactura = -1;
        try {
            indexFactura = listaFacturas.indexOf(new Facturas(Herramientas.stringABigDecimalEntero(jtfNumeroFactura.getText())));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        if (indexFactura == -1) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[6], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        ///Si la factura existe, la tomo en una variable de tránsito para trabajar más comodamente
        Facturas factura = listaFacturas.get(indexFactura);

        //Le modifico los campos oportunos (excepto la colección de artículos 'articuloses', eso no cambiará)
        Clientes cliente = (Clientes) crud.read(Clientes.class, jtfCodigoCliente.getText());
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[3], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        factura.setClientes(cliente);
        try {
            factura.setFechafactura(Herramientas.stringADateFormateado(jtfFechaFactura.getText()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[4], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Actualizo la factura
        String error = crud.update(factura);
        //Si "error" no está vacío, es que ha ocurrido un error al modificar.
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[7] + " " + error, "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si "error" está vacío, es que todo ha ido bien, así que actualizo las tablas de la vista
        actualizarTablas();

    }//GEN-LAST:event_jbModificarFacturaActionPerformed

    private void jbBorrarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFacturaActionPerformed
        //Compruebo que número de factura que pretendo borrar no esté vacío
        if (jtfNumeroFactura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[0], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Pregunto al usuario si realmente desea borrar la factura, antes de continuar con el proceso
        int opcion = JOptionPane.showOptionDialog(this, Herramientas.mensajes[33], Herramientas.mensajes[32],
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"NO BORRAR NADA", "BORRAR FACTURA Y LÍNEAS DE FACTURA", "CANCELAR"}, "NO BORRAR NADA");
        if (opcion != 1) {
            return;
        }

        String error = "";
        try {
            //Intento borrar la factura con el código proporcionado
            error = crud.delete(new Facturas((Herramientas.stringABigDecimalEntero(jtfNumeroFactura.getText()))));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si "error" no está vacío, es que ha ocurrido un error al borrar (probablemente la familia no exista)
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[8] + " " + error, "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si "error" está vacío, es que todo ha ido bien, así que actualizo las tablas de la vista
        actualizarTablas();
    }//GEN-LAST:event_jbBorrarFacturaActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        actualizarTablas();
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbCrearLineaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearLineaFacturaActionPerformed
        //Compruebo que el código de artículo y número de factura proporcionados no estén vacíos
        if (jtfCodigoArticuloLinea.getText().isEmpty() || jtfNumeroFacturaLinea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[9], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Obtengo el artículo que usaré en la pk compuesta de la fila insertada
        Articulos articulo = (Articulos) crud.read(Articulos.class, jtfCodigoArticuloLinea.getText());
        if (articulo == null) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[10], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Obtengo la factura que usaré en la pk compuesta de la fila insertada
        Facturas factura;
        try {
            factura = (Facturas) crud.read(Facturas.class, Herramientas.stringABigDecimalEntero(jtfNumeroFacturaLinea.getText()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        if (factura == null) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[6], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        if (articulo.getFacturases().contains(factura)) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[11], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Añado el artículo a la factura, usando el méotodo 'add' personalizado, que también añade la factura al artículo.
        factura.addArticulo(articulo);

        //Actualizo el master(artículo)
        String error = crud.update(articulo);
        //Si "error" no está vacío, es que ha ocurrido un error al borrar (probablemente la familia no exista)
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[12] + " " + error, "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si "error" está vacío, es que todo ha ido bien, así que actualizo las tablas de la vista
        actualizarTablas();

    }//GEN-LAST:event_jbCrearLineaFacturaActionPerformed

    private void jbBorrarLineaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarLineaFacturaActionPerformed
        //Compruebo que el código de artículo y número de factura proporcionados no estén vacíos
        if (jtfCodigoArticuloLinea.getText().isEmpty() || jtfNumeroFacturaLinea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[9], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Pregunto al usuario si realmente desea borrar la línea, antes de continuar con el proceso
        int opcion = JOptionPane.showOptionDialog(this, Herramientas.mensajes[35], Herramientas.mensajes[34],
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"NO BORRAR NADA", "BORRAR LÍNEA DE FACTURA", "CANCELAR"}, "NO BORRAR NADA");
        if (opcion != 1) {
            return;
        }
        //Obtengo el artículo que usaré en la pk compuesta de la fila insertada
        Articulos articulo = (Articulos) crud.read(Articulos.class, jtfCodigoArticuloLinea.getText());
        if (articulo == null) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[10], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Obtengo la factura que usaré en la pk compuesta de la fila insertada
        Facturas factura;
        try {
            factura = (Facturas) crud.read(Facturas.class, Herramientas.stringABigDecimalEntero(jtfNumeroFacturaLinea.getText()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        if (factura == null) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[6], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        if (!articulo.getFacturases().contains(factura)) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[13], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Borro el artículo a la factura, usando el méotodo 'remove' personalizado, que también borra la factura del artículo.
        factura.removeArticulo(articulo);
        //En este caso, para que funcione, hay que actualizar el slave(factura)
        String error = crud.update(factura);
        //Si "error" no está vacío, es que ha ocurrido un error al borrar (probablemente la familia no exista)
        if (!error.isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[13] + " " + error, "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si "error" está vacío, es que todo ha ido bien, así que actualizo las tablas de la vista
        actualizarTablas();
    }//GEN-LAST:event_jbBorrarLineaFacturaActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FacturaJDialog dialog = new FacturaJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbBorrarFactura;
    private javax.swing.JButton jbBorrarLineaFactura;
    private javax.swing.JButton jbCrearFactura;
    private javax.swing.JButton jbCrearLineaFactura;
    private javax.swing.JButton jbModificarFactura;
    private javax.swing.JPanel jpFactura;
    private javax.swing.JPanel jpLineaFactura;
    private javax.swing.JScrollPane jspFactura;
    private javax.swing.JScrollPane jspLineaFactura;
    private javax.swing.JTable jtFactura;
    private javax.swing.JTable jtLineaFactura;
    private javax.swing.JTextField jtfCodigoArticuloLinea;
    private javax.swing.JTextField jtfCodigoCliente;
    private javax.swing.JTextField jtfFechaFactura;
    private javax.swing.JTextField jtfNumeroFactura;
    private javax.swing.JTextField jtfNumeroFacturaLinea;
    // End of variables declaration//GEN-END:variables
}
