package InterfazGrafica;

import PaqueteDAOs.ActividadDAO;
import PaqueteDAOs.EventoDAO;
import PaqueteDAOs.ParticipanteDAO;
import PaqueteEntidades.Actividad;
import PaqueteEntidades.TipoActividades;
import java.sql.*;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class IFRegistrarActividad extends javax.swing.JInternalFrame {

    private Connection conn;
    private ActividadDAO actividadDAO;


    public IFRegistrarActividad(Connection conn) {
        initComponents();
        setTitle("Registrar Actividad");
        this.conn = conn;
        this.actividadDAO = new ActividadDAO(conn);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        codigoA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        codigoE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        eMail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txthoraIni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txthoraFin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttitulo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cupoMax = new javax.swing.JTextField();
        jComboBoxTipoActividad = new javax.swing.JComboBox<>();
        btnRegistrarActividad = new javax.swing.JButton();

        jLabel1.setText("Codigo de la Actividad");

        jLabel2.setText("Codigo del Evento al que pertenece");

        jLabel3.setText("E-Mail del encargado");

        eMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eMailActionPerformed(evt);
            }
        });

        jLabel4.setText("Hora inicio (HH:mm)");

        jLabel5.setText("Hora fin (HH:mm)");

        jLabel6.setText("Titulo");

        jLabel7.setText("Cupo Maximo");

        jComboBoxTipoActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHARLA", "TALLER", "DEBATE", "OTRA" }));

        btnRegistrarActividad.setText("Registrar");
        btnRegistrarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActividadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(codigoA, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(jLabel2)
                                                .addGap(37, 37, 37))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(codigoE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(97, 97, 97)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(eMail)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txthoraIni))
                                        .addGap(80, 80, 80)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txthoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabel6))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cupoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(81, 81, 81)
                                .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegistrarActividad)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthoraIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoActividad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cupoMax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(btnRegistrarActividad)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eMailActionPerformed

    private void btnRegistrarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActividadActionPerformed
        validarCampos();
    }//GEN-LAST:event_btnRegistrarActividadActionPerformed

    public void validarCampos() {

        try {
            //Por si algun campo está vacio
            if (codigoA.getText().isEmpty() || codigoE.getText().isEmpty() || eMail.getText().isEmpty()
                    || txthoraIni.getText().isEmpty() || txthoraFin.getText().isEmpty() || txttitulo.getText().isEmpty() || cupoMax.getText().isEmpty()) {
                throw new Exception();
            }

            String codigoActividad = codigoA.getText();
            String codigoEvento = codigoE.getText();
            String correo = eMail.getText();
            String titulo = txttitulo.getText();
            int cupoMaximo = Integer.parseInt(cupoMax.getText());
            LocalTime horaInicio = LocalTime.parse(txthoraIni.getText());
            LocalTime horaFin = LocalTime.parse(txthoraFin.getText());

            prepararDatos(codigoActividad, codigoEvento, correo, horaInicio, horaFin, titulo, cupoMaximo);

        } catch (Exception e) {
            System.out.println("Error al registrar Actividad");
            JOptionPane.showMessageDialog(null, "Campo o campos no validos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void prepararDatos(String codA, String codE, String correo, LocalTime horaIn,
            LocalTime horaFin, String titulo, int cupo) {

        TipoActividades tipoA = TipoActividades.CHARLA;//Por defecto

        switch (jComboBoxTipoActividad.getSelectedIndex()) {
            case 1:
                tipoA = TipoActividades.TALLER;
                break;
            case 2:
                tipoA = TipoActividades.DEBATE;
                break;
            case 3:
                tipoA = TipoActividades.OTRA;
                break;
        }

        Actividad nuevaActividad = new Actividad(codA, codE, correo, titulo, tipoA, cupo, horaIn, horaFin);
        actividadDAO.comprobarExistencia(nuevaActividad);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarActividad;
    private javax.swing.JTextField codigoA;
    private javax.swing.JTextField codigoE;
    private javax.swing.JTextField cupoMax;
    private javax.swing.JTextField eMail;
    private javax.swing.JComboBox<String> jComboBoxTipoActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txthoraFin;
    private javax.swing.JTextField txthoraIni;
    private javax.swing.JTextField txttitulo;
    // End of variables declaration//GEN-END:variables
}
