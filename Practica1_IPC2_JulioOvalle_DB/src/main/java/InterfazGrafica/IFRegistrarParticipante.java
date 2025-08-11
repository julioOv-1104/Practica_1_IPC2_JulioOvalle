package InterfazGrafica;

import PaqueteDAOs.ParticipanteDAO;
import PaqueteEntidades.Participante;
import PaqueteEntidades.TipoParticipantes;
import java.sql.*;
import javax.swing.JOptionPane;

public class IFRegistrarParticipante extends javax.swing.JInternalFrame {

    private Connection conn;
    private ParticipanteDAO participanteDAO;
    private final String ATRIBUTO = "email";
    private final String ENTIDAD = "participante";

    public IFRegistrarParticipante(Connection conn) {
        initComponents();
        setTitle("Registrar Participante");
        this.conn = conn;
        this.participanteDAO = new ParticipanteDAO(conn);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtInstitucion = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblInstitucion = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtInstitucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInstitucionActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre Completo");

        lblCorreo.setText("Correo Electronico");

        lblInstitucion.setText("Institucion de Procedencia");

        lblTipo.setText("Tipo de participante");

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante ", "Profesional", "Invitado" }));

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
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo)
                    .addComponent(txtInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInstitucion)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombre)
                .addGap(12, 12, 12)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCorreo)
                .addGap(13, 13, 13)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInstitucion)
                .addGap(18, 18, 18)
                .addComponent(txtInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrar)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtInstitucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInstitucionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInstitucionActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        validarCampos();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void validarCampos() {

        try {

            //Si cuaquiera de los tres está vacio
            if (txtCorreo.getText().isEmpty() || txtInstitucion.getText().isEmpty() || txtNombre.getText().isEmpty()) {

                throw new Exception();
            }

            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String insti = txtInstitucion.getText();

            //Verifica que no exista el participante
            if (participanteDAO.buscarPorParametros(correo, ATRIBUTO, ENTIDAD, conn)) {
                System.out.println("Ya existe este participante");
                JOptionPane.showMessageDialog(null, "Ya exite este participante", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

            } else {
                crearParticipante(nombre, insti, correo);
            }

            System.out.println("Todo bien");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo o campos no validos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("Algo salio mal en validar campos Participante");
            //e.printStackTrace();
        }
    }

    //Si esntra a este metodo es porque llenó todos los campos correctamente
    public void crearParticipante(String nombre, String institucion, String eMail) {

        TipoParticipantes tipo = TipoParticipantes.ESTUDIANTE;

        switch (jComboBoxTipo.getSelectedIndex()) {//Revisa que tipo escogio el usuario

            case 1:
                tipo = TipoParticipantes.PROFESIONAL;
                break;
            case 2:
                tipo = TipoParticipantes.INVITADO;
                break;
        }

        Participante nuevo = new Participante(nombre, institucion, eMail, tipo);//Crea un nuevo participante

        participanteDAO.registrarParticipante(nuevo);//Registra el nuevo participante en la BD
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblInstitucion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtInstitucion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
