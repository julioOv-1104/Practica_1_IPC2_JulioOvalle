package InterfazGrafica;

import GestionArchivo.GeneradorDeReportes;
import java.sql.*;
import javax.swing.JOptionPane;

public class IFReportesParticipantes extends javax.swing.JInternalFrame {

    private Connection conn;
    private String ruta;
    private GeneradorDeReportes generador;

    public IFReportesParticipantes(Connection conn, String ruta) {
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
        txtInstitucion = new javax.swing.JTextField();
        jComboTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Codigo de Evento");

        jLabel2.setText("Institucion de Procedencia");

        jComboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin especificar", "ESTUDIANTE ", "PROFESIONAL", "INVITADO" }));

        jLabel3.setText("Tipo de Participante");

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(txtEvento, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                        .addComponent(txtInstitucion)))
                .addContainerGap(170, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Crea los reportes segun los filtros
        if (ruta == null) {
            JOptionPane.showMessageDialog(null, "No hay ninguna ruta para los reportes", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            filtrarReportes();
            JOptionPane.showMessageDialog(null, "Reportes filtrtados", "Listo", JOptionPane.PLAIN_MESSAGE);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void filtrarReportes() {

        //Se asegura de saber cuales son los filtros que quiere el usuario
        if (txtEvento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo obligatorio para filtrar los reportes", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (txtInstitucion.getText().isEmpty() && (jComboTipo.getSelectedIndex() != 0)) {
            //El usuario ingreso el codigo y el tipo         
            codigoTipo();

        } else if (!(txtInstitucion.getText().isEmpty()) && jComboTipo.getSelectedIndex() == 0) {
            //El usuario ingreso el codigo y la institucion
            codigoInstitucion();

        } else if (txtInstitucion.getText().isEmpty() && (jComboTipo.getSelectedIndex() == 0)) {
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

        String tipo = (String) jComboTipo.getSelectedItem();
        String completo = String.format(sql, txtEvento.getText(), tipo);

        pedirReportes(completo);
    }

    private void codigoInstitucion() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' "
                + "AND participante.institucion = '%s';";
        String completo = String.format(sql, txtEvento.getText(), txtInstitucion.getText());

        pedirReportes(completo);
    }

    private void todosLosFiltros() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' "
                + "AND participante.tipo_participante = '%s' AND participante.institucion = '%s';";

        String tipo = (String) jComboTipo.getSelectedItem();
        String completo = String.format(sql, txtEvento.getText(), tipo, txtInstitucion.getText());

        pedirReportes(completo);
    }

    private void soloCodigo() {

        String sql = "SELECT email,tipo_participante,nombre,institucion,es_valida FROM participante INNER JOIN inscripcion"
                + " ON participante.email = inscripcion.email_participante AND inscripcion.codigo_evento = '%s' ;";

        String completo = String.format(sql, txtEvento.getText());

        pedirReportes(completo);
    }

    private void pedirReportes(String completo) {

        generador.generarParticipantes(generador.obtenerParticipantes(completo), ruta);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtEvento;
    private javax.swing.JTextField txtInstitucion;
    // End of variables declaration//GEN-END:variables
}
