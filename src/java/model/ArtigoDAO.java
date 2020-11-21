package Model;

import Aplicacao.Artigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
