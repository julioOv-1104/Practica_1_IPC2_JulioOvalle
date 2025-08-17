package InterfazGrafica;

import java.sql.*;
import javax.swing.*;

public class MenuPrincipal extends javax.swing.JFrame {

    Connection conn;

    public MenuPrincipal(Connection conn) {
        initComponents();
        this.conn = conn;
        setResizable(true);
        setTitle("Menu Eventos Hyrule");
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        vistaEscritorio = new javax.swing.JDesktopPane();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuRegistrar = new javax.swing.JMenu();
        menuRegistrarEvento = new javax.swing.JMenuItem();
        menuRegistrarActividad = new javax.swing.JMenuItem();
        menuRegistrarParticipante = new javax.swing.JMenuItem();
        menuInscribir = new javax.swing.JMenu();
        menuInscribirParticipante = new javax.swing.JMenuItem();
        menuPago = new javax.swing.JMenu();
        menuPagoInscripcion = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuAsisitencia = new javax.swing.JMenu();
        menuRegistrarAsistencia = new javax.swing.JMenuItem();
        menuCargarArchivo = new javax.swing.JMenu();
        menuCargar = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        txtArea.setText("inicio...");
        jScrollPane1.setViewportView(txtArea);

        vistaEscritorio.setLayer(btnSalir, javax.swing.JLayeredPane.DEFAULT_LAYER);
        vistaEscritorio.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout vistaEscritorioLayout = new javax.swing.GroupLayout(vistaEscritorio);
        vistaEscritorio.setLayout(vistaEscritorioLayout);
        vistaEscritorioLayout.setHorizontalGroup(
            vistaEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaEscritorioLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(vistaEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaEscritorioLayout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaEscritorioLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        vistaEscritorioLayout.setVerticalGroup(
            vistaEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vistaEscritorioLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaEscritorio)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaEscritorio)
        );

        MenuRegistrar.setText("Registrar ");

        menuRegistrarEvento.setText("Evento");
        menuRegistrarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistrarEventoActionPerformed(evt);
            }
        });
        MenuRegistrar.add(menuRegistrarEvento);

        menuRegistrarActividad.setText("Actividad");
        menuRegistrarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistrarActividadActionPerformed(evt);
            }
        });
        MenuRegistrar.add(menuRegistrarActividad);

        menuRegistrarParticipante.setText("Participante");
        menuRegistrarParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistrarParticipanteActionPerformed(evt);
            }
        });
        MenuRegistrar.add(menuRegistrarParticipante);

        jMenuBar1.add(MenuRegistrar);

        menuInscribir.setText("Inscribir a Evento");
        menuInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInscribirActionPerformed(evt);
            }
        });

        menuInscribirParticipante.setText("Inscribir");
        menuInscribirParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInscribirParticipanteActionPerformed(evt);
            }
        });
        menuInscribir.add(menuInscribirParticipante);

        jMenuBar1.add(menuInscribir);

        menuPago.setText("Pago");
        menuPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPagoActionPerformed(evt);
            }
        });

        menuPagoInscripcion.setText("Pagar");
        menuPagoInscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPagoInscripcionActionPerformed(evt);
            }
        });
        menuPago.add(menuPagoInscripcion);

        jMenuBar1.add(menuPago);

        jMenu1.setText("Validar");

        jMenuItem1.setText("Validar inscripcion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        menuAsisitencia.setText("Asistencia");

        menuRegistrarAsistencia.setText("Registrar Asistecia");
        menuRegistrarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegistrarAsistenciaActionPerformed(evt);
            }
        });
        menuAsisitencia.add(menuRegistrarAsistencia);

        jMenuBar1.add(menuAsisitencia);

        menuCargarArchivo.setText("Cargar Archivo");

        menuCargar.setText("Cargar Archivo");
        menuCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCargarActionPerformed(evt);
            }
        });
        menuCargarArchivo.add(menuCargar);

        jMenuBar1.add(menuCargarArchivo);

        menuReportes.setText("Reportes");
        jMenuBar1.add(menuReportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuRegistrarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistrarActividadActionPerformed

        limpiarPantalla();

        IFRegistrarActividad regActi = new IFRegistrarActividad(conn);
        vistaEscritorio.add(regActi);
        regActi.show();
    }//GEN-LAST:event_menuRegistrarActividadActionPerformed

    private void menuRegistrarParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistrarParticipanteActionPerformed

        limpiarPantalla();

        IFRegistrarParticipante partici = new IFRegistrarParticipante(conn);
        vistaEscritorio.add(partici);
        partici.show();


    }//GEN-LAST:event_menuRegistrarParticipanteActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void menuRegistrarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistrarEventoActionPerformed

        limpiarPantalla();

        IFRegistrarEvento event = new IFRegistrarEvento(conn);
        vistaEscritorio.add(event);

        event.show();
    }//GEN-LAST:event_menuRegistrarEventoActionPerformed

    private void menuInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInscribirActionPerformed

    }//GEN-LAST:event_menuInscribirActionPerformed

    private void menuPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPagoActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuPagoActionPerformed

    private void menuInscribirParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInscribirParticipanteActionPerformed

        limpiarPantalla();
        IFInscripcion insc = new IFInscripcion(conn);
        vistaEscritorio.add(insc);

        insc.show();

    }//GEN-LAST:event_menuInscribirParticipanteActionPerformed

    private void menuPagoInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPagoInscripcionActionPerformed
        limpiarPantalla();
        IFPagar pagar = new IFPagar(conn);
        vistaEscritorio.add(pagar);

        pagar.show();
    }//GEN-LAST:event_menuPagoInscripcionActionPerformed

    private void menuRegistrarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegistrarAsistenciaActionPerformed
        limpiarPantalla();
        IFRegistrarAsistencias asist = new IFRegistrarAsistencias(conn);
        vistaEscritorio.add(asist);

        asist.show();
    }//GEN-LAST:event_menuRegistrarAsistenciaActionPerformed

    private void menuCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCargarActionPerformed
        limpiarPantalla();
        IFCargaArchivo cargar = new IFCargaArchivo(this);
        vistaEscritorio.add(cargar);

        cargar.show();
    }//GEN-LAST:event_menuCargarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        limpiarPantalla();
        IFValidacion validar = new IFValidacion(conn);
        vistaEscritorio.add(validar);

        validar.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void limpiarPantalla() {

        for (JInternalFrame frame : vistaEscritorio.getAllFrames()) {
            frame.setVisible(false);//Cierra todas las ventanas que estubieran abiertas
        }

    }

    public JTextArea getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(JTextArea txtArea) {
        this.txtArea = txtArea;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAsisitencia;
    private javax.swing.JMenuItem menuCargar;
    private javax.swing.JMenu menuCargarArchivo;
    private javax.swing.JMenu menuInscribir;
    private javax.swing.JMenuItem menuInscribirParticipante;
    private javax.swing.JMenu menuPago;
    private javax.swing.JMenuItem menuPagoInscripcion;
    private javax.swing.JMenuItem menuRegistrarActividad;
    private javax.swing.JMenuItem menuRegistrarAsistencia;
    private javax.swing.JMenuItem menuRegistrarEvento;
    private javax.swing.JMenuItem menuRegistrarParticipante;
    private javax.swing.JMenu menuReportes;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JDesktopPane vistaEscritorio;
    // End of variables declaration//GEN-END:variables
}
