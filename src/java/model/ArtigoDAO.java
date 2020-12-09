package Model;

import Aplicacao.Artigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ArtigoDAO {
    
    public List<Artigo> listarTodosArtigosDAO() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Artigo> artigos = new ArrayList<>();
        Artigo artigo = null;

        try {
            connection = new Conexao().criaConexao();
            stmt = connection.prepareStatement("SELECT * FROM artigo");
            rs = stmt.executeQuery();

            while(rs.next()) {
                artigo = new Artigo();
                artigo.setId(rs.getInt("id"));
                artigo.setTitulo(rs.getString("titulo"));
                artigo.setConteudo(rs.getString("conteudo"));
                artigo.setLiberar(rs.getString("liberar"));
                artigo.setAprovado(rs.getString("aprovado"));
                artigo.setUsuario(rs.getInt("id_usuario"));
                artigo.setCategoria(rs.getInt("id_categoria"));

                artigos.add(artigo);
            }        	
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar todos os artigos: " + e.getMessage());
        }

        return artigos;
    }
    

    public boolean salvarArtigo(Artigo artigo) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = new Conexao().criaConexao();
            String sql;
            if (artigo.getId() == 0) {
                sql = "INSERT INTO artigo (id_usuario, id_categoria, titulo, conteudo, liberar, aprovado) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE artigo SET id_usuario=?, id_categoria=?,  titulo=?, conteudo=?, liberar=?, aprovado=? WHERE id=?";
            }

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, artigo.getUsuario().getId());
            ps.setInt(2, artigo.getCategoria().getId());
            ps.setString(3, artigo.getTitulo());
            ps.setString(4, artigo.getConteudo());
            ps.setString(5, artigo.getLiberar());
            ps.setString(6, artigo.getAprovado());

            if (artigo.getId() > 0) {
                ps.setInt(7, artigo.getId());
            }

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public List<Artigo> listarArtigosPendentesDAO() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Artigo> artigos = new ArrayList<>();
        Artigo artigo = null;

        try {
            connection = new Conexao().criaConexao();
            stmt = connection.prepareStatement("SELECT * FROM artigo WHERE aprovado='N' and liberar='S'");
            rs = stmt.executeQuery();

            while(rs.next()) {
                artigo = new Artigo();
                artigo.setId(rs.getInt("id"));
                artigo.setTitulo(rs.getString("titulo"));
                artigo.setConteudo(rs.getString("conteudo"));
                artigo.setLiberar(rs.getString("liberar"));
                artigo.setAprovado(rs.getString("aprovado"));
                artigo.setUsuario(rs.getInt("id_usuario"));
                artigo.setCategoria(rs.getInt("id_categoria"));

                artigos.add(artigo);
            }        	
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar artigos pendentes: " + e.getMessage());
        }

        return artigos;
    }
    
    
    public List<Artigo> listarArtigosPublicadosDAO() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Artigo> artigos = new ArrayList<>();
        Artigo artigo = null;

        try {
            connection = new Conexao().criaConexao();
            stmt = connection.prepareStatement("SELECT * FROM artigo WHERE aprovado='S' and liberar='S'");
            rs = stmt.executeQuery();

            while(rs.next()) {
                artigo = new Artigo();
                artigo.setId(rs.getInt("id"));
                artigo.setTitulo(rs.getString("titulo"));
                artigo.setConteudo(rs.getString("conteudo"));
                artigo.setLiberar(rs.getString("liberar"));
                artigo.setAprovado(rs.getString("aprovado"));
                artigo.setUsuario(rs.getInt("id_usuario"));
                artigo.setCategoria(rs.getInt("id_categoria"));

                artigos.add(artigo);
            }        	
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar artigos publicados: " + e.getMessage());
        }

        return artigos;
    }
    
    public boolean aprovaArtigo(int artigo_id, String aprovado) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = new Conexao().criaConexao();
            String sql;
            
            sql = "UPDATE artigo SET aprovado=? WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aprovado);
            ps.setInt(2, artigo_id);

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean liberaArtigo(int artigo_id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = new Conexao().criaConexao();
            String sql;
            
            sql = "UPDATE artigo SET liberar='S' WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, artigo_id);

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public List<Artigo> listarArtigosPorUsuarioDAO(int usuario_id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Artigo> artigos = new ArrayList<>();
        Artigo artigo = null;

        try {
            connection = new Conexao().criaConexao();
            stmt = connection.prepareStatement("SELECT * FROM artigo WHERE id_usuario=?");
            stmt.setInt(1, usuario_id);
            rs = stmt.executeQuery();

            while(rs.next()) {
                artigo = new Artigo();
                artigo.setId(rs.getInt("id"));
                artigo.setTitulo(rs.getString("titulo"));
                artigo.setConteudo(rs.getString("conteudo"));
                artigo.setLiberar(rs.getString("liberar"));
                artigo.setAprovado(rs.getString("aprovado"));
                artigo.setUsuario(rs.getInt("id_usuario"));
                artigo.setCategoria(rs.getInt("id_categoria"));

                artigos.add(artigo);
            }        	
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar artigos pendentes: " + e.getMessage());
        }

        return artigos;
    }
    
    public Artigo getArtigoPorID(int id) {
        Connection connection = null;
        Artigo artigo = new Artigo();
        System.out.println(id);
        try {
            connection = new Conexao().criaConexao();
            System.out.println(id);
            String sql = "SELECT * FROM artigo WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ACHOU ARTIGO");
                artigo.setId(rs.getInt("id"));
                artigo.setUsuario(rs.getInt("id_usuario"));
                artigo.setCategoria(rs.getInt("id_categoria"));
                artigo.setTitulo(rs.getString("titulo"));
                artigo.setConteudo(rs.getString("conteudo"));
                artigo.setLiberar(rs.getString("liberar"));
                artigo.setAprovado(rs.getString("aprovado"));
            }

        } catch (SQLException e) {
            System.out.println("NÃO ACHOU ARTIGO");
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return artigo;
    }
    
    public boolean excluirArtigo(int artigo_id) {
        Connection connection = null;
        try {
            connection = new Conexao().criaConexao();

            String sql = "DELETE FROM artigo WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, artigo_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    
}
