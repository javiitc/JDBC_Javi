package org.hipermercado;

import modelo.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import dao.ProductoDAO;

public class Main {
    static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        try {
            Connection con = ConexionDB.getConnection();
            System.out.println("Conectado a la BD");
            Producto p = new Producto(0, "Tussi", "Si lo compras, caerá una llamadita al FBI", 1, 1, 4.99, 50, "1234567890123");
            dao.nuevoProducto(p);
        } catch (SQLException error) {
            System.out.println("No se puedo conectar a la base de datos");
        }
    }
}
