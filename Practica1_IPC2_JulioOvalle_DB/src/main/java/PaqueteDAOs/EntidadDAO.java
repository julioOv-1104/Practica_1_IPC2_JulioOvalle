
package PaqueteDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class EntidadDAO {
    
    private Connection conn;
    
    public boolean buscarPorParametros(String busqueda, String columna, String entidad, Connection conn) {
        //Hace una busqueda en la BD, recibe que busca, en que entidad y en que columna
        String sql = "SELECT "+columna+" FROM "+entidad+" WHERE "+columna+" = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, busqueda);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal con la busqueda");
        }

        return false;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
