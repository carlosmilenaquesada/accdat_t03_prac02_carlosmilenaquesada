package vistas;

import controladores.ClientesJpaController;
import controladores.FacturasJpaController;
import controladores.Herramientas;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import modelos.Articulos;
import modelos.Clientes;
import modelos.Facturas;

public class FacturaJDialog extends javax.swing.JDialog {

    private FacturasJpaController ctrlFacturas;
    private ClientesJpaController ctrlClientes;

    private DefaultTableModel dtmFactura;
    private List<Facturas> listaFacturas;
    private Facturas facturaEnFoco;

    private DefaultTableModel dtmLineaFactura;

    public FacturaJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.ctrlFacturas = new FacturasJpaController(Herramientas.EMF);
        this.ctrlClientes = new ClientesJpaController(Herramientas.EMF);
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
        jtfFechaFactura.setText(Herramientas.dateAStringFormateado(facturaEnFoco.getFechafactura()));
        jtfCodigoCliente.setText(facturaEnFoco.getCodcliente().getCodcliente());
    }

    private void actualizarInputsLineaFacturas() {
        int selectedRow = jtLineaFactura.getSelectedRow();
        jtfNumeroFacturaLinea.setText(jtLineaFactura.getValueAt(selectedRow, 0).toString());
        jtfCodigoArticuloLinea.setText(jtLineaFactura.getValueAt(selectedRow, 1).toString());
    }

    private void actualizarTablas() {
        dtmFactura.setRowCount(0);
        this.listaFacturas = ctrlFacturas.findFacturasEntities();
        for (Facturas f : this.listaFacturas) {
            dtmFactura.addRow(new Object[]{f});
        }
        facturaEnFoco = null;
        jtfNumeroFactura.setText("");
        jtfCodigoCliente.setText("");
        jtfFechaFactura.setText("");
        actualizarTablaLineaFacturas();
    }

    private void actualizarTablaLineaFacturas() {
        dtmLineaFactura.setRowCount(0);
        if (facturaEnFoco != null) {
            for (Articulos a : (Collection<Articulos>) facturaEnFoco.getArticulosCollection()) {
                dtmLineaFactura.addRow(
                        new Object[]{facturaEnFoco, a});
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
                "Factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
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
        //Compruebo que el código de cliente porporcionado para asociarlo a la factura no esté vacío (ya que es un atributo no nulleable)
        if (jtfCodigoCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[26], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        try {
            //Inicio la creación de la nueva factura, a la que le asigno el número de factura y el resto de atributos
            Facturas factura = new Facturas(Long.valueOf(jtfNumeroFactura.getText()));
            factura.setFechafactura(Herramientas.stringADateFormateado(jtfFechaFactura.getText()));
            factura.setArticulosCollection(null);
            factura.setCodcliente(ctrlClientes.findClientes(jtfCodigoCliente.getText()));
            //Si el código de cliente proporcionado no corresponde con ningún cliente, 'findClientes' devuelve null, así que informamos y detenemos le proceso (el campo cliente de la factura no puede ser null)
            if (factura.getCodcliente() == null) {
                JOptionPane.showMessageDialog(null, Herramientas.mensajes[3], "Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            //Por último, creamos la factura
            ctrlFacturas.create(factura);
        } catch (NumberFormatException ex) {
            //Si el número de factura proporcionado tiene un formato no válido, se informa y se detiene el proceso
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (ParseException pae) {
            //Si la fecha de la factura no tiene un formato válido, se informa y se detiene el proceso
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[4], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (PreexistingEntityException pe) {
            //Si ya existe una factura con el número de factura proporicionado, se informa y se detiene el proceso
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[5] + " " + pe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (Exception ex) {
            //Cualquier otro error, se informará y detendrá el proceso de creación.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[5] + " " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Si todo ha ido bien, se actualizan las tablas de la vista
        actualizarTablas();
    }//GEN-LAST:event_jbCrearFacturaActionPerformed

    private void jbModificarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarFacturaActionPerformed
        //Compruebo que número de factura que pretendo modificar no esté vacío
        if (jtfNumeroFactura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[0], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Compruebo que el código de cliente porporcionado para asociarlo a la factura que se va a modificar no esté vacío (ya que es un atributo no nulleable)
        if (jtfCodigoCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[26], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        //Inicio la modificación de la factura
        try {
            Facturas factura = ctrlFacturas.findFacturas(Long.valueOf(jtfNumeroFactura.getText()));
            //Si el código de factura proporcionado no corresponde con ninguna factura, 'findFacturas' devuelve null, así que informamos y detenemos le proceso
            if (factura == null) {
                JOptionPane.showMessageDialog(null, Herramientas.mensajes[6], "Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            //Añadimos el resto de atributos modificados
            factura.setFechafactura(Herramientas.stringADateFormateado(jtfFechaFactura.getText()));
            factura.setCodcliente(ctrlClientes.findClientes(jtfCodigoCliente.getText()));
            //Si el código de cliente proporcionado en la modificación no corresponde con ningún cliente, 'findClientes' devuelve null, así que informamos y detenemos le proceso (el campo cliente de la factura no puede quedar null)
            if (factura.getCodcliente() == null) {
                JOptionPane.showMessageDialog(null, Herramientas.mensajes[3], "Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            //Por último, actualizamos los campos modificados en la factura            
            System.out.println(factura.toStringCompleto());
            ctrlFacturas.edit(factura);

        } catch (NumberFormatException nf) {
            //Si el número de factura proporcionado tiene un formato no válido, se informa y se detiene el proceso
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[1], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (ParseException ex) {
            //Si la fecha de la factura no tiene un formato válido, se informa y se detiene el proceso
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[4], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (NonexistentEntityException nee) {
            //Aunque en el 'findFacturas' usado en la modificación ya se comprobó si la factura existía (para continuar o no con el resto del proceso),
            //no podemos garantizar que siga existiendo cuando se ejecuta el 'edit(factura)', por eso estoy tratando también esta excepción.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[6], "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } catch (Exception ex) {
            //Cualquier otro error, se informará y detendrá el proceso de creación.
            JOptionPane.showMessageDialog(null, Herramientas.mensajes[7] + " " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        //Si todo ha ido bien, se actualizan las tablas de la vista
        actualizarTablas();

    }//GEN-LAST:event_jbModificarFacturaActionPerformed

    private void jbBorrarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFacturaActionPerformed
        /*//Compruebo que número de factura que pretendo borrar no esté vacío
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
        actualizarTablas();*/
    }//GEN-LAST:event_jbBorrarFacturaActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        actualizarTablas();
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbCrearLineaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearLineaFacturaActionPerformed
        /*//Compruebo que el código de artículo y número de factura proporcionados no estén vacíos
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
        actualizarTablas();*/

    }//GEN-LAST:event_jbCrearLineaFacturaActionPerformed

    private void jbBorrarLineaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarLineaFacturaActionPerformed
        /*//Compruebo que el código de artículo y número de factura proporcionados no estén vacíos
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
        actualizarTablas();*/
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
