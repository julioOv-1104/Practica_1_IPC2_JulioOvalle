package InterfazGrafica;

import PaqueteDAOs.EventoDAO;
import PaqueteDAOs.PagoDAO;
import PaqueteDAOs.ParticipanteDAO;
import PaqueteEntidades.MetodoDePago;
import PaqueteEntidades.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class IFPagar extends javax.swing.JInternalFrame {
    
    private Connection conn;
    private PagoDAO pagoDAO;
    private ParticipanteDAO participanteDAO;
    private EventoDAO eventoDAO;
    
    private final String ENTIDAD_PARTICIPANTE = "participante";
    private final String ATRIBUTO_EVENTO = "codigo_evento";
    private final String ENTIDAD_EVENTO = "evento";
    
    public IFPagar(Connection conn) {
        initComponents();
        setTitle("Pagar");
        this.conn = conn;
        this.pagoDAO = new PagoDAO(conn);
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
        jComboBoxMetodo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        txtMonto = new javax.swing.JTextField();

        jLabel1.setText("E-Mail participante");

        jLabel2.setText("Codigo del evento");

        jComboBoxMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TRANSEFERENCIA", "TARGETA" }));

        jLabel3.setText("Metodo de pago");

        lblMonto.setText("Monto: ");

        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMonto)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxMetodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMonto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(btnPagar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMonto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        validarCampos();
    }//GEN-LAST:event_btnPagarActionPerformed
    
    private void validarCampos() {
        
        try {
            
            if (txtCodigo.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
                throw new Exception();//Por si están vacios los campos               
            }
            
            String codigo = txtCodigo.getText();
            String correo = txtCorreo.getText();
            double monto = Double.parseDouble(txtMonto.getText());

            //Verifica que el participante y el evento existan
            if (participanteDAO.buscarPorParametros(correo, "email", ENTIDAD_PARTICIPANTE, conn)
                    && eventoDAO.buscarPorParametros(codigo, ATRIBUTO_EVENTO, ENTIDAD_EVENTO, conn)) {
                //ambos existen
                System.out.println("Ambos existen, correo y evento");
                buscarInscripcion(correo, codigo, monto);//Busca inscripcion existente
                
            } else {
                JOptionPane.showMessageDialog(null, "Hay un error en uno o ambos datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al pagar");
            JOptionPane.showMessageDialog(null, "Campo o campos no validos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void pagarYValidar(String correo, String codigo, double monto) {
        
        MetodoDePago metodo = MetodoDePago.EFECTIVO;
        
        switch (jComboBoxMetodo.getSelectedIndex()) {
            case 1:
                metodo = MetodoDePago.TRANSFERENCIA;
                break;
            case 2:
                metodo = MetodoDePago.TARGETA;
                break;
        }
        
        Pago nuevopago = new Pago(correo, codigo, metodo, monto);
        pagoDAO.verificaPagoSuficiente(nuevopago,monto);//antes de terminar verifica si el monto es suficiente
        
    }
    
    private void buscarInscripcion(String correo, String codigo, double monto) {
        
        String sql = "SELECT 1 FROM inscripcion WHERE " + ATRIBUTO_EVENTO + " = ?"
                + " AND email_participante = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, correo);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                //la inscripcion existe
                pagarYValidar(correo, codigo,monto);//Ya que existe paga 
            } else {
                //no está inscrito
                JOptionPane.showMessageDialog(null, "No existe la inscripcion", "ERROR", JOptionPane.ERROR_MESSAGE);
                throw new SQLException();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda");
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> jComboBoxMetodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
