package InterfazGrafica;

import PaqueteDAOs.EventoDAO;
import PaqueteDAOs.InscripcionDAO;
import PaqueteDAOs.ParticipanteDAO;
import PaqueteEntidades.Inscripcion;
import PaqueteEntidades.TipoInscripciones;
import java.sql.*;
import javax.swing.JOptionPane;

public class IFInscripcion extends javax.swing.JInternalFrame {

    private Connection conn;
    private InscripcionDAO inscripcionDAO;



    public IFInscripcion(Connection conn) {
        initComponents();
        setTitle("Inscribir Participante");
        this.conn = conn;
        this.inscripcionDAO = new InscripcionDAO(conn);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnInscribir = new javax.swing.JButton();

        jLabel1.setText("E-Mail del participante");

        jLabel2.setText("Codigo del evento");

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASISTENTE", "CONFERENCISTA", "TALLERISTA", "OTRO" }));

        jLabel3.setText("Tipo de inscripcion");

        btnInscribir.setText("Inscribir");
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
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
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInscribir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInscribir)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed
        validarCampos();
    }//GEN-LAST:event_btnInscribirActionPerformed

    public void validarCampos() {

        try {

            if (txtCodigo.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
                throw new Exception();//Revisa que no estén vacios
            }

            String correo = txtCorreo.getText();
            String codigo = txtCodigo.getText();

            prepararDatos(correo, codigo);

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al inscribir");
            JOptionPane.showMessageDialog(null, "Campo o campos no validos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void prepararDatos(String correo, String codigo) {

        TipoInscripciones tipo = TipoInscripciones.ASISTENTE;

        switch (jComboBoxTipo.getSelectedIndex()) {
            case 1:
                tipo = TipoInscripciones.CONFERENCISTA;
                break;
            case 2:
                tipo = TipoInscripciones.TALLERISTA;
                break;
            case 3:
                tipo = TipoInscripciones.OTRO;
                break;
        }

        Inscripcion nuevaInscripcion = new Inscripcion(correo, codigo, tipo);//Crea la inscripcion
        inscripcionDAO.comprobarExistencia(correo, codigo, nuevaInscripcion);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInscribir;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
