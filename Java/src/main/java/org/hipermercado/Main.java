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
            List<Producto> bebidas = dao.filtrarPorSeccion(1);
            for (Producto p : bebidas) {
                System.out.println(p);
            }
        } catch (SQLException error) {
            System.out.println("No se puedo conectar a la base de datos");
        }
    }
}
