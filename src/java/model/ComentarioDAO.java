/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Aplicacao.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class ComentarioDAO {
    private Connection conexao;

    public List<Comentario> listarTodosComentariosDAO(int id_artigo) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Comentario> comentarios = new ArrayList<>();
        Comentario comentario = null;

        try {
            connection = new Model.Conexao().criaConexao();
            stmt = connection.prepareStatement("SELECT * FROM comentario WHERE id_artigo = " + id_artigo);
            rs = stmt.executeQuery();

            while(rs.next()) {
                comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setUsuario(rs.getInt("id_usuario"));
                comentario.setArtigo(rs.getInt("id_artigo"));
                
                comentarios.add(comentario);
            }        	
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar todos os artigos: " + e.getMessage());
        }

        return comentarios;
    }
    
    public List<Comentario> listarComentarioPorArtigos(List<Integer> id_artigos) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Comentario> comentarios = new ArrayList<>();
        if(id_artigos.size() == 0){
            return comentarios;
        }
        Comentario comentario = null;

        try {
            connection = new Model.Conexao().criaConexao();
            StringBuilder string_id_artigos  = new StringBuilder();
            Iterator<Integer> iter = id_artigos.iterator();
            while(iter.hasNext()) {
                string_id_artigos.append(iter.next());
                if(iter.hasNext()){
                    string_id_artigos.append(",");
                }
            }
            string_id_artigos.toString();
            stmt = connection.prepareStatement("SELECT * FROM comentario WHERE id_artigo in (" + string_id_artigos + ")");
            rs = stmt.executeQuery();

            while(rs.next()) {
                comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setUsuario(rs.getInt("id_usuario"));
                comentario.setArtigo(rs.getInt("id_artigo"));
                
                comentarios.add(comentario);
            }
            System.out.println(string_id_artigos);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar todos os artigos: " + e.getMessage());
        }

        return comentarios;
    }
    
    
    public Comentario getComentarioPorID(int id) {
        Comentario comentario = new Comentario();
        try {
            Connection connection = new Model.Conexao().criaConexao();
            String sql = "SELECT * FROM comentario WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println(ps);
                
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ACHEEEI");
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setArtigo(rs.getInt("id_artigo"));
                comentario.setUsuario(rs.getInt("id_usuario"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return comentario;
    }
    
    public boolean salvarComentario(Comentario comentario) {
        Connection connection = null;
        try {
            connection = new Conexao().criaConexao();
            String sql;
            if (comentario.getId() == 0) {
                sql = "INSERT INTO comentario (id_usuario, id_artigo, comentario) VALUES (?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE comentario SET id_usuario=?, id_artigo=?,  comentario=? WHERE id=?";
            }

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, comentario.getUsuario().getId());
            ps.setInt(2, comentario.getArtigo().getId());
            ps.setString(3, comentario.getComentario());

            if (comentario.getId() > 0) {
                ps.setInt(4, comentario.getId());
            }

            System.out.println(ps);
            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int id) {
        try {
            Connection connection = new Conexao().criaConexao();
            System.out.println(id);
            String sql = "DELETE FROM comentario WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
}
