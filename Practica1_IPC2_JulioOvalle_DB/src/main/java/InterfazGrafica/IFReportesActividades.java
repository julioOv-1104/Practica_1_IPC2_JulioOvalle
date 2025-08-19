package InterfazGrafica;

import GestionArchivo.GeneradorDeReportes;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class IFReportesActividades extends javax.swing.JInternalFrame {

    private Connection conn;
    private String ruta;
    private GeneradorDeReportes generador;

    public IFReportesActividades(Connection conn, String ruta) {
        this.conn = conn;
        this.ruta = ruta;
        this.generador = new GeneradorDeReportes(conn, ruta);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtEvento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jComboBoxTipoActividad = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Codigo Evento");

        jLabel2.setText("Correo Encargado ");

        jComboBoxTipoActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin especificar", "CHARLA", "TALLER", "DEBATE", "OTRA" }));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Actividad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(btnFiltrar)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(txtEvento, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(txtCorreo))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnFiltrar)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        if (ruta == null) {
            JOptionPane.showMessageDialog(null, "No hay ninguna ruta para los reportes", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            filtrarReportes();
            JOptionPane.showMessageDialog(null, "Reportes filtrtados", "Listo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    
    private void filtrarReportes() {

        //Se asegura de saber cuales son los filtros que quiere el usuario
        if (txtEvento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo obligatorio para filtrar los reportes", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (txtCorreo.getText().isEmpty() && (jComboBoxTipoActividad.getSelectedIndex() != 0)) {
            //El usuario ingreso el codigo y el tipo         
            codigoTipo();

        } else if (!(txtCorreo.getText().isEmpty()) && jComboBoxTipoActividad.getSelectedIndex() == 0) {
            //El usuario ingreso el codigo y el correo
            codigoCorreo();

        } else if (txtCorreo.getText().isEmpty() && (jComboBoxTipoActividad.getSelectedIndex() == 0)) {
            //El usuario ingresó todos los filtros
            soloCodigo();

        } else {
            //El usuario solo ingreso el codigo
            todosLosFiltros();
        }

    }
    
    private void codigoTipo() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' "
                + "AND participante.tipo_participante = '%s';";

        String tipo = (String) jComboBoxTipoActividad.getSelectedItem();
        String completo = String.format(sql, txtEvento.getText(), tipo);

        pedirReportes(completo);
    }

    private void codigoCorreo() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' "
                + "AND participante.institucion = '%s';";
        String completo = String.format(sql, txtEvento.getText(), txtCorreo.getText());

        pedirReportes(completo);
    }

    private void todosLosFiltros() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' "
                + "AND participante.tipo_participante = '%s' AND participante.institucion = '%s';";

        String tipo = (String) jComboBoxTipoActividad.getSelectedItem();
        String completo = String.format(sql, txtEvento.getText(), tipo, txtCorreo.getText());

        pedirReportes(completo);
    }

    private void soloCodigo() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' ;";

        String completo = String.format(sql, txtEvento.getText());

        pedirReportes(completo);
    }

    private void pedirReportes(String completo) {

        generador.generarActividades(generador.obtenerActividades(completo), ruta);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JComboBox<String> jComboBoxTipoActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEvento;
    // End of variables declaration//GEN-END:variables
}
