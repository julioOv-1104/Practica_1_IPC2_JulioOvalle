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
    private ParticipanteDAO participanteDAO;
    private EventoDAO eventoDAO;

    private final String ATRIBUTO_PARTICIPANTE = "email_participante";
    private final String ENTIDAD_PARTICIPANTE = "participante";
    private final String ATRIBUTO_EVENTO = "codigo_evento";
    private final String ENTIDAD_EVENTO = "evento";
    private final String ENTIDAD = "inscripcion";

    public IFInscripcion(Connection conn) {
        initComponents();
        setTitle("Inscribir Participante");
        this.conn = conn;
        this.inscripcionDAO = new InscripcionDAO(conn);
        this.participanteDAO = new ParticipanteDAO(conn);
        this.eventoDAO = new EventoDAO(conn);
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
                throw new Exception();
            }

            String correo = txtCorreo.getText();
            String codigo = txtCodigo.getText();

            //Verifica que el participante y el evento existan
            if (participanteDAO.buscarPorParametros(correo, "email", ENTIDAD_PARTICIPANTE, conn)
                    && eventoDAO.buscarPorParametros(codigo, ATRIBUTO_EVENTO, ENTIDAD_EVENTO, conn)) {
                //ambos existen
                System.out.println("Ambos existen, correo y evento");
                comprobarCuposRestantes(codigo, correo);

            } else {
                JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al inscribir");
            JOptionPane.showMessageDialog(null, "Campo o campos no validos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comprobarCuposRestantes(String codigo, String correo) {

        int cupoMaximo = 0;
        int cupoUsado = 0;

        String cupoOcupado = "SELECT COUNT(*) FROM inscripcion WHERE codigo_evento = ?";
        String cupoDisponible = "SELECT cupo_maximo FROM evento WHERE codigo_evento = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(cupoOcupado);
            ps.setString(1, codigo);

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Obtiene cuantas personas ya están inscritas en el evento
                cupoUsado = Integer.parseInt(rs.getString("COUNT(*)"));
                System.out.println("CUPO USADO " + cupoUsado);

            }

            PreparedStatement ps2 = conn.prepareStatement(cupoDisponible);
            ps2.setString(1, codigo);

            System.out.println(cupoDisponible);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                //Obtiene cuantos espacios hay en total
                cupoMaximo = Integer.parseInt(rs2.getString("cupo_maximo"));
                System.out.println("CUPO MAXIMO " + cupoMaximo);

            }

            if (cupoUsado < cupoMaximo) {
                //Si aun hay espacios
                buscarInscripcionDuplicada(correo, codigo);
            } else {
                //Si no hay espacio no hace nada
                JOptionPane.showMessageDialog(null, "Ya no hay cupos para este evento", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda de cupos");
        }

    }

    private void buscarInscripcionDuplicada(String correo, String codigo) {

        String sql = "SELECT 1 FROM " + ENTIDAD + " WHERE " + ATRIBUTO_EVENTO + " = ?"
                + " AND " + ATRIBUTO_PARTICIPANTE + " = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, correo);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //la inscripcion está duplicada
                JOptionPane.showMessageDialog(null, "Ya existe esta inscrpcion", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            } else {
                //no está duplicada
                inscribir(correo, codigo);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda");
        }

    }

    public void inscribir(String correo, String codigo) {

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

        Inscripcion nuevaInscripcion = new Inscripcion(correo, codigo, tipo);
        inscripcionDAO.inscribirParticipante(nuevaInscripcion);
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
