package InterfazGrafica;

import PaqueteDAOs.AsistenciaYCertificadoDAO;
import PaqueteEntidades.AsistenciaYCertificado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class IFRegistrarAsistencias extends javax.swing.JInternalFrame {

    Connection conn;
    private AsistenciaYCertificadoDAO asistenciaDAO;

    public IFRegistrarAsistencias(Connection conn) {
        initComponents();
        this.conn = conn;
        this.asistenciaDAO = new AsistenciaYCertificadoDAO(conn);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();

        jLabel1.setText("E-Mail del participante");

        jLabel2.setText("Codigo de la actividad");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCorreo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(243, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        validarCampos();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void validarCampos() {

        try {

            if (txtCodigo.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
                throw new Exception();//Revisa que no esté vacio
            }

            String correo = txtCorreo.getText();
            String codigoActividad = txtCodigo.getText();

            //revisa que existam los datos
            if (asistenciaDAO.buscarPorParametros(correo, "email", "participante", conn)
                    && asistenciaDAO.buscarPorParametros(codigoActividad, "codigo_actividad", "actividad", conn)) {

                System.out.println("Ambos existen: participante y actividad");
                buscarAsistenciaDuplicada(correo, codigoActividad);

            } else {
                JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Error en campos de asistencia");
            JOptionPane.showMessageDialog(null, "Campo o campos no validos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void buscarAsistenciaDuplicada(String correo, String codigo) {

        String sql = "SELECT 1 FROM asistencia WHERE email_participante = ?"
                + " AND codigo_actividad = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, codigo);

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //la asistencia está duplicada
                JOptionPane.showMessageDialog(null, "Ya existe esta asistencia", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            } else {
                //no está duplicada
                crearAsistencia(correo, codigo);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda");
        }
    }

    private void crearAsistencia(String correo, String codigo) {

        AsistenciaYCertificado nuevaAsistencia = new AsistenciaYCertificado(correo, codigo);
        asistenciaDAO.buscarCodigoEvento(nuevaAsistencia);
        //Crea una nueva asistencia y si es necesario un nuevo certificado
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
