package Aplicacao;

import Model.ArtigoDAO;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Artigo {
    private int id;
    private int usuario_id;
    private int categoria_id;
    private String titulo;
    private String conteudo;
    private String liberar;
    private String aprovado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario_id;
    }

    public void setUsuario(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getCategoria() {
        return categoria_id;
    }

    public void setCategoria(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getLiberar() {
        return liberar;
    }

    public void setLiberar(String liberar) {
        this.liberar = liberar;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(String aprovado) {
        this.aprovado = aprovado;
    }
    
    public List<Artigo> listaTodosArtigos() {
            return new ArtigoDAO().listarTodosArtigosDAO();
    }
}
