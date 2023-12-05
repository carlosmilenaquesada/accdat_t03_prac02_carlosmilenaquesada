package vistas;

import controladores.ArticulosJpaController;
import controladores.ClientesJpaController;
import controladores.FacturasJpaController;
import controladores.GestorErrores;
import static controladores.GestorInformacion.panelBorradoFacturas;

import controladores.Herramientas;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
    private ArticulosJpaController ctrlArticulos;

    private DefaultTableModel dtmFactura;
    private List<Facturas> listaFacturas;
    private Facturas facturaEnFoco;
    private JTextField[] inputsFactura;

    private DefaultTableModel dtmLineaFactura;
    private JTextField[] inputsLineaFactura;

    public FacturaJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.ctrlFacturas = new FacturasJpaController(Herramientas.EMF);
        this.ctrlClientes = new ClientesJpaController(Herramientas.EMF);
        this.ctrlArticulos = new ArticulosJpaController(Herramientas.EMF);
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
        this.inputsFactura = new JTextField[]{jtfNumeroFactura, jtfFechaFactura, jtfCodigoCliente};
        this.inputsLineaFactura = new JTextField[]{jtfNumeroFacturaLinea, jtfCodigoArticuloLinea};
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
        jtfFechaFactura.setText("");
        jtfCodigoCliente.setText("");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFacturaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfFechaFactura)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
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
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsFactura, GestorErrores.mensajesInputsVaciosFactura);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar        
        if (errores.isEmpty()) {
            //Comprobamos que el número de factura y la fecha de factura tengan un formato válido (compruebo de manera independiente
            //cada uno para informar de los dos errores por separado) y también que el cliente porporcionado exista.
            Long numeroFactura = null;
            Date fechaFactura = null;
            Clientes cliente = null;
            try {
                numeroFactura = Long.valueOf(jtfNumeroFactura.getText());
            } catch (NumberFormatException nf) {
                //Si el número de factura no tiene un formato válido, se genera error
                errores.add(GestorErrores.mensajes[1]);
                GestorErrores.cambiarABordeError(jtfNumeroFactura);
            }
            try {
                fechaFactura = Herramientas.stringADateFormateado(jtfFechaFactura.getText());
            } catch (ParseException pe) {
                //Si la fecha de factura no tiene un formato válido, se genera error
                errores.add(GestorErrores.mensajes[4]);
                GestorErrores.cambiarABordeError(jtfFechaFactura);
            }
            //Obtenemos la referencia al cliente que se va a asociar con la factura
            cliente = ctrlClientes.findClientes(jtfCodigoCliente.getText());

            if (cliente == null) {
                //Si el código de cliente proporcionado no corresponde a ningún cliente, se genera error
                errores.add(GestorErrores.mensajes[27]);
                GestorErrores.cambiarABordeError(jtfCodigoCliente);
            }
            if (errores.isEmpty()) {
                //Si no a ocurrido ningún error hasta ahora, 'errores' estará vacío y se continuará
                try {
                    //Inicio la creación de la factura
                    ctrlFacturas.create(new Facturas(numeroFactura, fechaFactura, cliente));
                } catch (PreexistingEntityException pe) {
                    //Si la factura ya existe, se recoge el error.
                    errores.add(pe.getMessage());
                    GestorErrores.cambiarABordeError(jtfNumeroFactura);
                } catch (Exception ex) {
                    //Cualquier otro error, será recogido
                    errores.add(ex.getMessage());
                }
            }
        }

        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la creación, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbCrearFacturaActionPerformed

    private void jbModificarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarFacturaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsFactura, GestorErrores.mensajesInputsVaciosFactura);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            //Obtengo los nuevos datos de la modificación (numeroFactura es la pk y no se va a modificar, simplemente lo obtengo para 
            //saber cual es la factura que voy a modificar)
            Long numeroFactura = null;
            Date fechaFactura = null;
            Clientes cliente = null;
            try {
                numeroFactura = Long.valueOf(jtfNumeroFactura.getText());
            } catch (NumberFormatException nf) {
                //Si el número de factura no tiene un formato válido, se genera error
                errores.add(GestorErrores.mensajes[1]);
                GestorErrores.cambiarABordeError(jtfNumeroFactura);
            }
            try {
                fechaFactura = Herramientas.stringADateFormateado(jtfFechaFactura.getText());
            } catch (ParseException pe) {
                //Si la fecha de factura no tiene un formato válido, se genera error
                errores.add(GestorErrores.mensajes[4]);
                GestorErrores.cambiarABordeError(jtfFechaFactura);
            }
            //Obtenemos la referencia al nuevo cliente que se va a asociar con la factura
            cliente = ctrlClientes.findClientes(jtfCodigoCliente.getText());

            if (cliente == null) {
                //Si el código de cliente proporcionado no corresponde a ningún cliente, se genera error
                errores.add(GestorErrores.mensajes[27]);
                GestorErrores.cambiarABordeError(jtfCodigoCliente);
            }

            if (errores.isEmpty()) {
                //Si no a ocurrido ningún error en la recopilación de datos, podemos continuar                           
                try {
                    //Obtenemos la referencia a la factura. Podríamos hacer un new factura en este punto, pero eso produce un problemar al actualizar la factura porque esa
                    //new factura carecería de contenido en su colección interna de artículos, y eso borraría todas las líneas de factura asociadas al actualizar.
                    Facturas factura = ctrlFacturas.findFacturas(numeroFactura);
                    //Se asignan los nuevos valores a la factura
                    factura.setFechafactura(fechaFactura);
                    factura.setCodcliente(cliente);
                    //inicio la actualización del artículo sobre la base de datos
                    ctrlFacturas.edit(factura);
                } catch (NonexistentEntityException ne) {
                    //Si la factura que se pretende actualizar no existe (puede haber sido borrado por otro usuario en la base de datos mientras se editaba), se genera el error.
                    errores.add(ne.getMessage());
                } catch (Exception ex) {
                    //Cualquier otro error, se informará y detendrá el proceso de modificación.
                    errores.add(ex.getMessage());
                }
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la modificación, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }

    }//GEN-LAST:event_jbModificarFacturaActionPerformed

    private void jbBorrarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFacturaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(new JTextField[]{jtfNumeroFactura}, GestorErrores.mensajesInputsVaciosFactura);
        Long numeroFactura = null;
        try {
            numeroFactura = Long.valueOf(jtfNumeroFactura.getText());
        } catch (NumberFormatException nf) {
            //Si el número de factura proporcionado por el usuario no tiene un formato válido, se informa y se emite el error
            errores.add(GestorErrores.mensajes[1]);
            GestorErrores.cambiarABordeError(jtfNumeroFactura);
        }
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            //Si la factura que se va a borrar tiene alguna línea de factura asociada, se preguntará al usuario si desea borrar la factura y las líneas
            //de factura asociadas a la factura
            int opcion = JOptionPane.showOptionDialog(this, panelBorradoFacturas[0], panelBorradoFacturas[1],
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    new Object[]{panelBorradoFacturas[2], panelBorradoFacturas[3], panelBorradoFacturas[4]}, panelBorradoFacturas[2]);
            if (opcion == 1) {
                try {
                    //Si el usuario acepta, procedo con el borrado de la factura que
                    //borrará también las líneas de factura donde dicha factura. Este borrado en cascada se
                    //lleva a cabo de manera implícita, ya que el destroy de factura viene programado así por defecto.
                    ctrlFacturas.destroy(numeroFactura);
                } catch (NonexistentEntityException ne) {
                    //Aunque acabo de comprobar en el paso anterior si la factura existía,
                    //debo veriricarlo también en este punto, ya que el usuario debe aceptar el
                    //borrado mediante un JOptionPane y eso puede demorar un tiempo en el cual otro usuario de la base de datos
                    //podría haber borrado la factura
                    errores.add(ne.getMessage());
                    GestorErrores.cambiarABordeError(jtfNumeroFactura);
                } catch (Exception ex) {
                    //Cualquier otro error, se informará y detendrá el proceso de modificación.
                    errores.add(ex.getMessage());
                }
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras el borrado, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbBorrarFacturaActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        actualizarTablas();
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbCrearLineaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearLineaFacturaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsLineaFactura, GestorErrores.mensajesInputsVaciosLineasFactura);
        Long numeroFactura = null;
        try {
            numeroFactura = Long.valueOf(jtfNumeroFacturaLinea.getText());
        } catch (NumberFormatException nf) {
            //Si el número de factura proporcionado por el usuario no tiene un formato válido, se informa y se emite el error
            errores.add(GestorErrores.mensajes[1]);
            GestorErrores.cambiarABordeError(jtfNumeroFacturaLinea);
        }
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            //Obtenemos la factura y el artículo involucrados en la línea de factura
            Facturas factura = ctrlFacturas.findFacturas(numeroFactura);
            Articulos articulo = ctrlArticulos.findArticulos(jtfCodigoArticuloLinea.getText());

            //Si la factura es null, es que no existe tal factura, así que se informa
            if (factura == null) {
                errores.add(GestorErrores.mensajes[6]);
                GestorErrores.cambiarABordeError(jtfNumeroFacturaLinea);
            }
            //Si el artículo es null, es que no existe tal artículo, así que se informa
            if (articulo == null) {
                errores.add(GestorErrores.mensajes[41]);
                GestorErrores.cambiarABordeError(jtfCodigoArticuloLinea);
            }
            if (errores.isEmpty()) {
                //Si la factura y el artículo existen, agrego el artículo a la colección de artículos de la factura, y la actualizo (aunque es una relación
                //de muchos-muchos, solo hay que agregar en una dirección, no es necesario agregar la factura a la colección de facturas del artículo, 
                //ya que este proceso se realiza en el controlador de facturas o en el de artículos de manera implícita, según quien inicie el proceso de agregar)
                if (!factura.getArticulosCollection().contains(articulo)) {
                    factura.getArticulosCollection().add(articulo);
                }
                try {
                    //Actualizo la factura, en la que se ha incluido el artículo, como consecuencia, se crea la línea de factura cuya pk es numerofactura+codigoarticulo
                    ctrlFacturas.edit(factura);
                } catch (NonexistentEntityException ne) {
                    //Si la factura dejó de existir durante este proceso de creación de línea, se emite mensaje de factura no existente.
                    //Por el contrario, si el artículo dejó de existir durante el proceso, simplemente no se generará la línea nueva.
                    errores.add(GestorErrores.mensajes[6]);
                    GestorErrores.cambiarABordeError(jtfNumeroFacturaLinea);
                } catch (Exception ex) {
                    //Cualquier otro error, se informará y detendrá el proceso de modificación.
                    errores.add(ex.getMessage());
                }
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras el borrado, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbCrearLineaFacturaActionPerformed

    private void jbBorrarLineaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarLineaFacturaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsLineaFactura, GestorErrores.mensajesInputsVaciosLineasFactura);
        Long numeroFactura = null;
        try {
            numeroFactura = Long.valueOf(jtfNumeroFacturaLinea.getText());
        } catch (NumberFormatException nf) {
            //Si el número de factura proporcionado por el usuario no tiene un formato válido, se informa y se emite el error
            errores.add(GestorErrores.mensajes[1]);
            GestorErrores.cambiarABordeError(jtfNumeroFacturaLinea);
        }
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            //Obtenemos la factura y el artículo involucrados en la línea de factura
            Facturas factura = ctrlFacturas.findFacturas(numeroFactura);
            Articulos articulo = ctrlArticulos.findArticulos(jtfCodigoArticuloLinea.getText());

            //Si la factura es null, es que no existe tal factura, así que se informa
            if (factura == null) {
                errores.add(GestorErrores.mensajes[6]);
                GestorErrores.cambiarABordeError(jtfNumeroFacturaLinea);
            }
            //Si el artículo es null, es que no existe tal artículo, así que se informa
            if (articulo == null) {
                errores.add(GestorErrores.mensajes[41]);
                GestorErrores.cambiarABordeError(jtfCodigoArticuloLinea);
            }
            if (errores.isEmpty()) {
                //Si la factura y el artículo existen, elimino el artículo de la colección de artículos de la factura, y la actualizo (aunque es una relación
                //de muchos-muchos, solo hay que eliminar en una dirección, no es necesario eliminar la factura de la colección de facturas del artículo, 
                //ya que este proceso se realiza en el controlador de facturas o en el de artículos de manera implícita, según quien inicie el proceso de agregar)
                if (factura.getArticulosCollection().contains(articulo)) {
                    factura.getArticulosCollection().remove(articulo);
                }
                try {
                    //Actualizo la factura, en la que se ha eliminado el artículo, como consecuencia, se elimina línea de factura cuya pk es numerofactura+codigoarticulo
                    ctrlFacturas.edit(factura);
                } catch (NonexistentEntityException ne) {
                    //Si la factura dejó de existir durante este proceso de eliminación de línea, se emite mensaje de factura no existente.
                    errores.add(GestorErrores.mensajes[6]);
                    GestorErrores.cambiarABordeError(jtfNumeroFacturaLinea);
                } catch (Exception ex) {
                    //Cualquier otro error, se informará y detendrá el proceso de modificación.
                    errores.add(ex.getMessage());
                }
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras el borrado, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
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
