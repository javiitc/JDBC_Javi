package dao;
import org.hipermercado.ConexionDB;
import modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public Producto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_seccion"),
                        rs.getInt("id_marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("codigo_barras")
                );
            }
        }
        return null;
    }

    public void nuevoProducto(Producto p) throws SQLException {
        String sql = "INSERT INTO productos (nombre, descripcion, id_seccion, id_marca, precio, stock, codigo_barras) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getIdSeccion());
            ps.setInt(4, p.getIdMarca());
            ps.setDouble(5, p.getPrecio());
            ps.setInt(6, p.getStock());
            ps.setString(7, p.getCodigoBarras());

            int filas = ps.executeUpdate();
            if  (filas > 0) {
                System.out.println("Producto añadido correctamente");
            } else {
                System.out.println("Error al añadir el producto");
            }
        }
    }

    public List<Producto> filtrarPorSeccion(int idSeccion) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id_seccion = ?";
        List<Producto> productos = new ArrayList<>();

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSeccion);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_seccion"),
                        rs.getInt("id_marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("codigo_barras")
                ));
            }
        }
        return productos;
    }

    public void eliminarProducto(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto eliminado correctamente");
            } else {
                System.out.println("No se encontró ningún producto");
            }
        }
    }

    public List<Producto> filtrarPorMarca(int idMarca) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id_marca = ?";
        List<Producto> productos = new ArrayList<>();

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMarca);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_seccion"),
                        rs.getInt("id_marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("codigo_barras")
                ));
            }
        }
        return productos;
    }

    public void update(Producto p) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=? " +
                "WHERE id_producto=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdProducto());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto actualizado correctamente");
            } else {
                System.out.println("No se encontró ningún producto");
            }
        }
    }

    public List<Producto> obtenerProdcutos() throws SQLException {
        String sql = "SELECT * FROM productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_seccion"),
                        rs.getInt("id_marca"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("codigo_barras")
                ));
            }
        }
        return productos;
    }
}
