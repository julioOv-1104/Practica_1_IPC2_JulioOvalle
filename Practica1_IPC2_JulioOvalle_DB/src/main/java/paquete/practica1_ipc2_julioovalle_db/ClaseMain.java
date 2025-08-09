
package paquete.practica1_ipc2_julioovalle_db;

import PaqueteDAOs.*;
import PaqueteEntidades.*;
import java.sql.*;
import java.util.Scanner;



public class ClaseMain {
    
    

    public static void main(String[] args) {
        
        Connection conn = ConexionBaseDeDatos.conectarConBaseDeDatos();//Se conecta con la base de datos
        
         Scanner escaner = new Scanner(System.in);

        System.out.println("Ingrese nombre");
        String nombre = escaner.nextLine();

        System.out.println("Ingrese Instituto");
        String instit = escaner.nextLine();

        System.out.println("Ingrese eMail");
        String eMail = escaner.nextLine();

        
        Participante nuevoPart = new Participante(nombre,instit,eMail,TipoParticipantes.ESTUDIANTE);
        
        ParticipanteDAO participanteDAO = new ParticipanteDAO(conn);
        participanteDAO.registrarParticipante(nuevoPart);
        
        
    }
}
