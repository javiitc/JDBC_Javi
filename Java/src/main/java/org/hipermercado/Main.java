package org.hipermercado;

import modelo.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProductoDAO;

public class Main {
    static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        try {
            Connection con = ConexionDB.getConnection();
            System.out.println("Conectado a la BD");
            List<Producto> monster = dao.filtrarPorMarca(1);
            for (Producto producto : monster) {
                System.out.println(producto);
            }
        } catch (SQLException error) {
            System.out.println("No se puedo conectar a la base de datos");
        }
    }
}
