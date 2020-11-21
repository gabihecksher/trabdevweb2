package Model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Aplicacao.Usuario;
import Model.Conexao;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() {
        try {
            // Executa a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } catch (Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }

    public List<Usuario> listarTodosUsuariosDAO() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;

        try {
            connection = new Conexao().criaConexao();
            stmt = connection.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();

            while(rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setPapel(rs.getInt("papel"));
                usuario.setCadastroAprovado(rs.getString("cadastro_aprovado"));
                
                usuarios.add(usuario);
            }        	
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar todos os usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public Usuario getUsuarioPorID(int id) {
        Usuario usuario = new Usuario();
        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPapel(rs.getInt("papel"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }
    
    public Usuario getUsuarioPorLoginSenha(String cpf, String senha) {
        Usuario Contato = new Usuario();
        try {
            Contato.setId(0);
            
            String sql = "SELECT * FROM usuario WHERE cpf = ? and senha = ? limit 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("CPF:" + cpf);
            System.out.println("Senha: " + senha);
            
            if (rs.next()) {
                System.out.println(rs.getInt("id"));
                Contato.setId(rs.getInt("id"));
                Contato.setNome(rs.getString("nome"));
                Contato.setCpf(rs.getString("cpf"));
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Contato;
    }

    public boolean gravar(Usuario usuario) {
        try {
            String sql;
            if (usuario.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO usuario (nome, cpf, tipo, senha) VALUES (?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE contato SET nome=?, cpf=?,  tipo=? senha=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(4, usuario.getSenha());

            if (usuario.getId() > 0) {
                ps.setInt(5, usuario.getId());
            }

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

}
