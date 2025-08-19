package InterfazGrafica;

import GestionArchivo.LecturaArchivo;
import java.io.File;
import java.sql.Connection;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class IFCargaArchivo extends javax.swing.JInternalFrame {

    private MenuPrincipal menu;
    private Connection conn;

    public IFCargaArchivo(MenuPrincipal menu, Connection conn) {
        this.menu = menu;
        this.conn = conn;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPathEntrada = new javax.swing.JTextField();
        btnBuscarEntrada = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtVelocidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPathSalida = new javax.swing.JTextField();
        btnBuscarSalida = new javax.swing.JButton();

        btnBuscarEntrada.setText("Buscar");
        btnBuscarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEntradaActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel1.setText("Velocidad de prpcesamiento (milisegundos)");

        jLabel2.setText("Archivo de entrada");

        jLabel3.setText("Archivo de salida");

        btnBuscarSalida.setText("Buscar");
        btnBuscarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSalidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarSalida)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarEntrada)
                                .addComponent(txtPathEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtVelocidad, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(txtPathSalida)))
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addComponent(txtPathEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarEntrada)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(txtVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPathSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarSalida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEntradaActionPerformed

        try {

            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(null);

            File file = jfc.getSelectedFile();
            txtPathEntrada.setText(file.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("No selecciono ningun archivo");
        }

    }//GEN-LAST:event_btnBuscarEntradaActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        empezarLectura();
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnBuscarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSalidaActionPerformed
        try {

            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showOpenDialog(null);

            File carpeta = jfc.getSelectedFile();
            txtPathSalida.setText(carpeta.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("No selecciono ningun archivo");
        }
    }//GEN-LAST:event_btnBuscarSalidaActionPerformed

    public void empezarLectura() {

        int n = 0;

        try {

            if (txtPathEntrada.getText().isEmpty() || txtVelocidad.getText().isEmpty() || txtPathSalida.getText().isEmpty()) {
                System.out.println("No hay ningun archivo");
                JOptionPane.showMessageDialog(null, "Tiene que especificar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);

            } else {
                //inicializa la lectura del archivo de entrada y guarda el archivo de salida para reportes
                n = Integer.parseInt(txtVelocidad.getText());
                LecturaArchivo leer = new LecturaArchivo(txtPathEntrada.getText(), n, menu, conn);
                Thread iniciar = new Thread(leer);
                iniciar.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarEntrada;
    private javax.swing.JButton btnBuscarSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtPathEntrada;
    private javax.swing.JTextField txtPathSalida;
    private javax.swing.JTextField txtVelocidad;
    // End of variables declaration//GEN-END:variables
}
