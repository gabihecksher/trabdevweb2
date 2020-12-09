package Aplicacao;

import Model.ArtigoDAO;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Artigo {
    private int id;
    private Usuario usuario;
    private Categoria categoria;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario_id) {
        Usuario usuario = new Usuario();
        this.usuario = usuario.getUsuario(usuario_id);
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(int categoria_id) {
        Categoria categoria = new Categoria();
        this.categoria = categoria.getCategoria(categoria_id);
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
    
    public List<Artigo> listaArtigosPendentes() {
        return new ArtigoDAO().listarArtigosPendentesDAO();
    }
    
    public List<Artigo> listaArtigosPublicados() {
        return new ArtigoDAO().listarArtigosPublicadosDAO();
    }
    
    public List<Artigo> listaArtigosPorUsuario(int usuario_id) {
        return new ArtigoDAO().listarArtigosPorUsuarioDAO(usuario_id);
    }
    
    
    public boolean salvaArtigo(){
        return new ArtigoDAO().salvarArtigo(this);
    }
    
    public boolean aprovaArtigo(int artigo_id, String aprovado){
        return new ArtigoDAO().aprovaArtigo(artigo_id, aprovado);
    }
    
    public boolean liberaArtigo(int artigo_id){
        return new ArtigoDAO().liberaArtigo(artigo_id);
    }
    
    public Artigo getArtigo(int artigo_id){
        System.out.println(artigo_id);
        ArtigoDAO artigoDAO = new ArtigoDAO();
        return artigoDAO.getArtigoPorID(artigo_id);
    }
    
    public boolean excluirArtigo(int artigo_id){
        return new ArtigoDAO().excluirArtigo(artigo_id);
    }
}
