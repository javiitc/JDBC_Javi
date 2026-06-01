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
            Producto p = dao.buscarPorId(1);
            p.setNombre("Monster Blanco");
            p.setPrecio(1.40);
            dao.update(p);
            System.out.println(dao.buscarPorId(1));
        } catch (SQLException error) {
            System.out.println("No se puedo conectar a la base de datos");
        }
    }
}
