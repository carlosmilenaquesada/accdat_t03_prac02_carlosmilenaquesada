package vistas;

import controladores.ArticulosJpaController;
import controladores.FamiliasJpaController;
import controladores.Herramientas;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import modelos.Articulos;
import modelos.Facturas;
import modelos.Familias;

public class FamiliaJDialog extends javax.swing.JDialog {

    private FamiliasJpaController ctrlFamilias;
    private ArticulosJpaController ctrlArticulos;

    private DefaultTableModel dtmFamilia;
    private List<Familias> listaFamilias;
    private Familias familiaEnFoco;

    private DefaultTableModel dtmArticulo;

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
        jtfFamiliaArticulo.setText(articulo.getCodfamilia().toString());
        jtfNombreArticulo.setText(articulo.getNomarticulo());
    }

    private void actualizarTablas() {
        dtmFamilia.setRowCount(0);
        this.listaFamilias = ctrlFamilias.findFamiliasEntities();
        for (Familias f : this.listaFamilias) {
            dtmFamilia.addRow(new Object[]{f});
        }
        familiaEnFoco = null;
        jtfCodigoFamilia.setText("");
        jtfNombreFamilia.setText("");
        actualizarTablaArticulos();
    }

    private void actualizarTablaArticulos() {
        dtmArticulo.setRowCount(0);
        if (familiaEnFoco != null) {
            for (Articulos a : (Collection<Articulos>) familiaEnFoco.getArticulosCollection()) {
                dtmArticulo.addRow(
                        new Object[]{a}
                );
            }
        }
        jtfCodigoArticulo.setText("");
        jtfFamiliaArticulo.setText("");
        jtfNombreArticulo.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFamilia = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigoFamilia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfNombreFamilia = new javax.swing.JTextField();
        jbCrearFamilia = new javax.swing.JButton();
        jbModificarFamilia = new javax.swing.JButton();
        jbBorrarFamilia = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();
        jspFamilia = new javax.swing.JScrollPane();
        jtFamilia = new javax.swing.JTable();
        jpArticulo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jtfFamiliaArticulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfCodigoArticulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfNombreArticulo = new javax.swing.JTextField();
        jbCrearArticulo = new javax.swing.JButton();
        jbModificarArticulo = new javax.swing.JButton();
        jbBorrarArticulo = new javax.swing.JButton();
        jspArticulo = new javax.swing.JScrollPane();
        jtArticulo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de familias");
        setMaximumSize(new java.awt.Dimension(684, 664));
        setMinimumSize(new java.awt.Dimension(684, 664));
        setResizable(false);

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

        jbActualizar.setText("Actualizar");
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
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
                        .addComponent(jbModificarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBorrarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCodigoFamilia)
                    .addComponent(jtfNombreFamilia)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFamiliaLayout.createSequentialGroup()
                        .addGap(0, 46, Short.MAX_VALUE)
                        .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFamiliaLayout.createSequentialGroup()
                        .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpFamiliaLayout.setVerticalGroup(
            jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFamiliaLayout.createSequentialGroup()
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFamiliaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbCrearFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbModificarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbBorrarFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFamiliaLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpArticulo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Articulos de la familia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel6.setText("Familia");

        jLabel4.setText("Código");

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
                    .addComponent(jspArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpArticuloLayout.createSequentialGroup()
                        .addComponent(jbCrearArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbModificarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBorrarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfFamiliaArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jtfNombreArticulo)
                    .addGroup(jpArticuloLayout.createSequentialGroup()
                        .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtfCodigoArticulo))
                .addContainerGap())
        );
        jpArticuloLayout.setVerticalGroup(
            jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCrearArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbModificarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrarArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpArticuloLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFamiliaArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpFamilia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jpArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(354, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCrearFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearFamiliaActionPerformed

    }//GEN-LAST:event_jbCrearFamiliaActionPerformed

    private void jbModificarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarFamiliaActionPerformed

    }//GEN-LAST:event_jbModificarFamiliaActionPerformed

    private void jbBorrarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFamiliaActionPerformed

    }//GEN-LAST:event_jbBorrarFamiliaActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        actualizarTablas();
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbCrearArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearArticuloActionPerformed

    }//GEN-LAST:event_jbCrearArticuloActionPerformed

    private void jbModificarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificarArticuloActionPerformed

    }//GEN-LAST:event_jbModificarArticuloActionPerformed

    private void jbBorrarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarArticuloActionPerformed

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
    private javax.swing.JPanel jpArticulo;
    private javax.swing.JPanel jpFamilia;
    private javax.swing.JScrollPane jspArticulo;
    private javax.swing.JScrollPane jspFamilia;
    private javax.swing.JTable jtArticulo;
    private javax.swing.JTable jtFamilia;
    private javax.swing.JTextField jtfCodigoArticulo;
    private javax.swing.JTextField jtfCodigoFamilia;
    private javax.swing.JTextField jtfFamiliaArticulo;
    private javax.swing.JTextField jtfNombreArticulo;
    private javax.swing.JTextField jtfNombreFamilia;
    // End of variables declaration//GEN-END:variables
}
