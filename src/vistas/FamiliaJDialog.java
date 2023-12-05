package vistas;

import controladores.ArticulosJpaController;
import controladores.FamiliasJpaController;
import controladores.GestorErrores;
import static controladores.GestorInformacion.panelBorradoArticulo;

import controladores.Herramientas;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static controladores.GestorInformacion.panelBorradoFamilia;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelos.Articulos;
import modelos.Familias;

public class FamiliaJDialog extends javax.swing.JDialog {

    private FamiliasJpaController ctrlFamilias;
    private ArticulosJpaController ctrlArticulos;

    private DefaultTableModel dtmFamilia;
    private List<Familias> listaFamilias;
    private Familias familiaEnFoco;
    private JTextField[] inputsFamilia;

    private DefaultTableModel dtmArticulo;
    private JTextField[] inputsArticulo;

    private DefaultComboBoxModel dcbmFamilias;

    public FamiliaJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ctrlFamilias = new FamiliasJpaController(Herramientas.EMF);
        ctrlArticulos = new ArticulosJpaController(Herramientas.EMF);
        initComponents();
        initConfiguracion();
    }

    private void initConfiguracion() {
        this.dtmFamilia = (DefaultTableModel) jtFamilia.getModel();
        this.dtmArticulo = (DefaultTableModel) jtArticulo.getModel();
        this.jtFamilia.setCellSelectionEnabled(false);
        this.jtArticulo.setCellSelectionEnabled(false);
        this.jtFamilia.setRowSelectionAllowed(true);
        this.jtArticulo.setRowSelectionAllowed(true);
        this.jtFamilia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jtArticulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.familiaEnFoco = null;
        this.inputsFamilia = new JTextField[]{jtfCodigoFamilia, jtfNombreFamilia};
        this.inputsArticulo = new JTextField[]{jtfCodigoArticulo, jtfNombreArticulo};
        this.dcbmFamilias = (DefaultComboBoxModel) jcbFamilia.getModel();
        actualizarTablas();
        this.jtFamilia.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    familiaEnFoco = listaFamilias.get(jtFamilia.getSelectedRow());
                    actualizarInputsFamilias();
                    actualizarTablaArticulos();
                }
            }
        });

        this.jtArticulo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    actualizarInputsArticulos();
                }
            }
        });
    }

    private void actualizarInputsFamilias() {
        jtfCodigoFamilia.setText(familiaEnFoco.getCodfamilia());
        jtfNombreFamilia.setText(familiaEnFoco.getNomfamilia());
    }

    private void actualizarInputsArticulos() {
        Articulos articulo = (Articulos) jtArticulo.getValueAt(jtArticulo.getSelectedRow(), 0);
        jtfCodigoArticulo.setText(articulo.getCodarticulo());
        jcbFamilia.setSelectedItem(articulo.getCodfamilia());
        jtfNombreArticulo.setText(articulo.getNomarticulo());
    }

    private void actualizarTablas() {
        dtmFamilia.setRowCount(0);
        this.listaFamilias = ctrlFamilias.getEntityManager().createNamedQuery("Familias.findOrderByCodfamilia").getResultList();
        for (Familias f : this.listaFamilias) {
            dtmFamilia.addRow(new Object[]{f});
            dcbmFamilias.addElement(f);
        }
        familiaEnFoco = null;
        jtfCodigoFamilia.setText("");
        jtfNombreFamilia.setText("");

        actualizarTablaArticulos();
    }

    private void actualizarTablaArticulos() {
        dtmArticulo.setRowCount(0);
        jtfCodigoArticulo.setText("");
        jcbFamilia.setSelectedItem(null);
        jtfNombreArticulo.setText("");
        if (familiaEnFoco != null) {
            for (Articulos a : (Collection<Articulos>) familiaEnFoco.getArticulosCollection()) {
                dtmArticulo.addRow(
                        new Object[]{a}
                );
            }
            jcbFamilia.setSelectedItem(familiaEnFoco);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbActualizar = new javax.swing.JButton();
        jpFamilia = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigoFamilia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfNombreFamilia = new javax.swing.JTextField();
        jbCrearFamilia = new javax.swing.JButton();
        jbModificarFamilia = new javax.swing.JButton();
        jbBorrarFamilia = new javax.swing.JButton();
        jspFamilia = new javax.swing.JScrollPane();
        jtFamilia = new javax.swing.JTable();
        jpArticulo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfCodigoArticulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcbFamilia = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jtfNombreArticulo = new javax.swing.JTextField();
        jbCrearArticulo = new javax.swing.JButton();
        jbModificarArticulo = new javax.swing.JButton();
        jbBorrarArticulo = new javax.swing.JButton();
        jspArticulo = new javax.swing.JScrollPane();
        jtArticulo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de familias");
        setMaximumSize(new java.awt.Dimension(425, 582));
        setMinimumSize(new java.awt.Dimension(425, 582));
        setResizable(false);

        jbActualizar.setText("Actualizar");
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
            }
        });

        jpFamilia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Familia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText("Código");

        jLabel2.setText("Nombre");

        jbCrearFamilia.setText("Crear familia");
        jbCrearFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearFamiliaActionPerformed(evt);
            }
        });

        jbModificarFamilia.setText("Modificar familia");
        jbModificarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModificarFamiliaActionPerformed(evt);
            }
        });

        jbBorrarFamilia.setText("Eliminar familia");
        jbBorrarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarFamiliaActionPerformed(evt);
            }
        });

        jtFamilia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Familia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFamilia.setColumnSelectionAllowed(true);
        jtFamilia.getTableHeader().setReorderingAllowed(false);
        jspFamilia.setViewportView(jtFamilia);
        jtFamilia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jpFamiliaLayout = new javax.swing.GroupLayout(jpFamilia);
        jpFamilia.setLayout(jpFamiliaLayout);
        jpFamiliaLayout.setHorizontalGroup(
            jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFamiliaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpFamiliaLayout.createSequentialGroup()
                        .addComponent(jbCrearFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbModificarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbBorrarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpFamiliaLayout.setVerticalGroup(
            jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFamiliaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCrearFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbModificarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpFamiliaLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jpArticulo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Articulos de la familia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel4.setText("Código");

        jLabel6.setText("Familia");

        jLabel5.setText("Nombre");

        jbCrearArticulo.setText("Crear artículo");
        jbCrearArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearArticuloActionPerformed(evt);
            }
        });

        jbModificarArticulo.setText("Modificar artículo");
        jbModificarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModificarArticuloActionPerformed(evt);
            }
        });

        jbBorrarArticulo.setText("Eliminar artículo");
        jbBorrarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarArticuloActionPerformed(evt);
            }
        });

        jtArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Articulos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtArticulo.setColumnSelectionAllowed(true);
        jtArticulo.getTableHeader().setReorderingAllowed(false);
        jspArticulo.setViewportView(jtArticulo);
        jtArticulo.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jpArticuloLayout = new javax.swing.GroupLayout(jpArticulo);
        jpArticulo.setLayout(jpArticuloLayout);
        jpArticuloLayout.setHorizontalGroup(
            jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpArticuloLayout.createSequentialGroup()
                        .addComponent(jbCrearArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbModificarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBorrarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpArticuloLayout.createSequentialGroup()
                        .addComponent(jspArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        jpArticuloLayout.setVerticalGroup(
            jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCrearArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbModificarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpArticuloLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(jpArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(jpFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(314, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCrearFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearFamiliaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsFamilia, GestorErrores.mensajesInputsVaciosFamilia);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            try {
                //Inicio la creación de la nueva familia
                ctrlFamilias.create(new Familias(jtfCodigoFamilia.getText(), jtfNombreFamilia.getText()));
            } catch (PreexistingEntityException pe) {
                //Si la familia ya existe, se recoge el error.
                errores.add(pe.getMessage());
                GestorErrores.cambiarABordeError(jtfCodigoFamilia);
            } catch (Exception ex) {
                //Cualquier otro error, será recogido
                errores.add(ex.getMessage());
            }
        }

        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la creación, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbCrearFamiliaActionPerformed

    private void jbModificarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarFamiliaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsFamilia, GestorErrores.mensajesInputsVaciosFamilia);
        //Inicio la modificación de la familia trayendo de la base de datos un objeto familia con el identificador aportado por el usuario
        Familias familia = ctrlFamilias.findFamilias(jtfCodigoFamilia.getText());
        //Si la familia exite, se modificarán los atributos, de lo contrario, se generará error.
        if (familia != null) {
            familia.setNomfamilia(jtfNombreFamilia.getText());
        } else {
            errores.add(GestorErrores.mensajes[2]);
        }
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            try {
                //inicio la actualización de la familia sobre la base de datos
                ctrlFamilias.edit(familia);
            } catch (IllegalOrphanException io) {
                //Si la familia que voy a modificar no contiene al menos los mismos artículos que la familia que se encuentra en la base de datos,
                //no se podrá actualizar, ya que los artículos que no estarían contenidos en la familia que estoy editando quedarían sin familia, y familia
                //es un atributo not null. Aunque en este punto yo no estoy cambiando ningún artículo, es posible que otro usuario de la base de 
                //datos sí lo haya hecho mientras yo manipulo la familia, y esto podría disparar esta excepción.
                errores.add(String.join("\n", io.getMessages()));
            } catch (NonexistentEntityException ne) {
                //Si la familia que se pretende actualizar no existe, se genera el error.
                errores.add(ne.getMessage());
            } catch (Exception ex) {
                //Cualquier otro error, se informará y detendrá el proceso de modificación.
                errores.add(ex.getMessage());
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la modificación, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbModificarFamiliaActionPerformed

    private void jbBorrarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFamiliaActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(new JTextField[]{jtfCodigoFamilia}, GestorErrores.mensajesInputsVaciosFamilia);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            try {
                //Inicio el borrado de la familia. Si no hay artículos asociados a la familia, se borra directamente.
                ctrlFamilias.destroy(jtfCodigoFamilia.getText());
            } catch (IllegalOrphanException io) {
                //Si la familia que se va a borrar es la familia de algún artículo, no podrá ser borrada de manera implícita con el destroy, produciendo esta excepción.
                //En tal caso, le pregunto al usuario si realmente quiere borrar la familia con borrado en cascada de artículos y lineas de facturas donde apareza el artículo.               
                int opcion = JOptionPane.showOptionDialog(this, panelBorradoFamilia[0], panelBorradoFamilia[1],
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        new Object[]{panelBorradoFamilia[2], panelBorradoFamilia[3], panelBorradoFamilia[4]}, panelBorradoFamilia[2]);
                if (opcion == 1) {
                    try {
                        //Si el usuario acepta, procedo con el borrado en cascada, el cual he implementado a través de una función
                        //creada por mí llamada destroyEnCascada().
                        ctrlFamilias.destroyEnCascada(jtfCodigoFamilia.getText());
                    } catch (NonexistentEntityException nec) {
                        //Aunque acabo de comprobar en el paso anterior si la familia existía,
                        //debo veriricarlo también en este punto, ya que el usuario debe aceptar el
                        //borrado mediante un JOptionPane y eso puede demorar un tiempo en el cual otro usuario de la base de datos
                        //podría haber borrado la familia.                    
                        errores.add(nec.getMessage());
                    }
                }
            } catch (NonexistentEntityException ne) {
                //Si la familia que se pretende borrar no existe, se informará y se detendrá el proceso.
                errores.add(ne.getMessage());
            } catch (Exception ex) {
                //Cualquier otro error, se informará y detendrá el proceso de modificación.
                errores.add(ex.getMessage());
            }
        }
        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras el borrado, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbBorrarFamiliaActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        actualizarTablas();
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbCrearArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearArticuloActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsArticulo, GestorErrores.mensajesInputsVaciosArticulos);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (jcbFamilia.getSelectedItem() == null) {
            errores.add(GestorErrores.mensajes[10]);
        }
        if (errores.isEmpty()) {
            //Obtenemos la referencia a la familia que se va a asociar con el artículo
            Familias familia = ctrlFamilias.findFamilias(jcbFamilia.getSelectedItem().toString());
            if (familia != null) {
                //Si el código de familia aportado por el usuario existe, se asociará al artículo, de lo contrario, se recoge  de error y no se creará el artículo
                try {
                    //Inicio la creación del artículo
                    ctrlArticulos.create(new Articulos(jtfCodigoArticulo.getText(), jtfNombreArticulo.getText(), familia));
                } catch (PreexistingEntityException pe) {
                    //Si el artículo ya existe, se recoge el error.
                    errores.add(pe.getMessage());
                    GestorErrores.cambiarABordeError(jtfCodigoArticulo);
                } catch (Exception ex) {
                    //Cualquier otro error, será recogido
                    errores.add(ex.getMessage());
                }
            } else {
                errores.add(GestorErrores.mensajes[2]);
            }
        }

        if (errores.isEmpty()) {
            //Si 'errores' sigue vacío tras la creación, se actualiza la tabla de la vista
            actualizarTablas();
        } else {
            //Si hay errores, los muestro y finalizo
            GestorErrores.mostrarErrores(errores);
        }
    }//GEN-LAST:event_jbCrearArticuloActionPerformed

    private void jbModificarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarArticuloActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(inputsArticulo, GestorErrores.mensajesInputsVaciosArticulos);
        if (jcbFamilia.getSelectedItem() == null) {
            errores.add(GestorErrores.mensajes[10]);
        }
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar

        if (errores.isEmpty()) {
            //Inicio la modificación del artículo trayendo de la base de datos el artículo que tiene el identificador aportado por el usuario
            Articulos articulo = ctrlArticulos.findArticulos(jtfCodigoArticulo.getText());
            //También traigo una la referencia a la nueva familia aprotada por el usuario, la cual se va a asociar con el artículo
            Familias familia = ctrlFamilias.findFamilias(jcbFamilia.getSelectedItem().toString());
            //Si el artículo y la familia aportadas por el usuario existen, se continúa el proceso
            //Si el artículo no existe o la nueva familia aportada por el usuario no existe, se genera el código de error por cada elemento inexistente.
            if (articulo == null) {
                errores.add(GestorErrores.mensajes[4]);
                GestorErrores.cambiarABordeError(jtfCodigoArticulo);
            }
            if (familia == null) {
                errores.add(GestorErrores.mensajes[2]);
            }
            if (errores.isEmpty()) {
                try {
                    //Se asignan los nuevos valores al artículo.
                    articulo.setCodfamilia(familia);
                    articulo.setNomarticulo(jtfNombreArticulo.getText());
                    //inicio la actualización del artículo sobre la base de datos
                    ctrlArticulos.edit(articulo);
                } catch (NonexistentEntityException ne) {
                    //Si el artículo que se pretende actualizar no existe (puede haber sido borrado por otro usuario en la base de datos mientras se editaba), se genera el error.
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
    }//GEN-LAST:event_jbModificarArticuloActionPerformed

    private void jbBorrarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarArticuloActionPerformed
        //Validación de los input
        ArrayList<String> errores = GestorErrores.validarInput(new JTextField[]{jtfCodigoArticulo}, GestorErrores.mensajesInputsVaciosArticulos);
        //Si no ocurrieron errores en la recopilación de datos, 'errores' estará vacío y puedo continuar
        if (errores.isEmpty()) {
            //Si el artículo que se va a borrar está presente en alguna línea de factura, se preguntará al usuario si desea borrar el artículo y las líneas
            //de factura asociadas al artículo

            int opcion = JOptionPane.showOptionDialog(this, panelBorradoArticulo[0], panelBorradoArticulo[1],
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    new Object[]{panelBorradoArticulo[2], panelBorradoArticulo[3], panelBorradoArticulo[4]}, panelBorradoArticulo[2]);
            if (opcion == 1) {
                try {
                    //Si el usuario acepta, procedo con el borrado del artículo que
                    //borrará también las líneas de factura donde aparezca el artículo. Este borrado en cascada se
                    //lleva a cabo de manera implícita, ya que el destroy de artículo viene programado así por defecto.
                    ctrlArticulos.destroy(jtfCodigoArticulo.getText());
                } catch (NonexistentEntityException ne) {
                    //Aunque acabo de comprobar en el paso anterior si el artículo existía,
                    //debo veriricarlo también en este punto, ya que el usuario debe aceptar el
                    //borrado mediante un JOptionPane y eso puede demorar un tiempo en el cual otro usuario de la base de datos
                    //podría haber borrado el artículo.
                    errores.add(ne.getMessage());
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
    }//GEN-LAST:event_jbBorrarArticuloActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FamiliaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FamiliaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FamiliaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FamiliaJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FamiliaJDialog dialog = new FamiliaJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbBorrarArticulo;
    private javax.swing.JButton jbBorrarFamilia;
    private javax.swing.JButton jbCrearArticulo;
    private javax.swing.JButton jbCrearFamilia;
    private javax.swing.JButton jbModificarArticulo;
    private javax.swing.JButton jbModificarFamilia;
    private javax.swing.JComboBox<String> jcbFamilia;
    private javax.swing.JPanel jpArticulo;
    private javax.swing.JPanel jpFamilia;
    private javax.swing.JScrollPane jspArticulo;
    private javax.swing.JScrollPane jspFamilia;
    private javax.swing.JTable jtArticulo;
    private javax.swing.JTable jtFamilia;
    private javax.swing.JTextField jtfCodigoArticulo;
    private javax.swing.JTextField jtfCodigoFamilia;
    private javax.swing.JTextField jtfNombreArticulo;
    private javax.swing.JTextField jtfNombreFamilia;
    // End of variables declaration//GEN-END:variables
}
