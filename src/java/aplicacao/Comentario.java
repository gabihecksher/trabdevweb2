/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Model.ComentarioDAO;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Comentario {
    private int id;
    private String comentario;
    private Artigo artigo;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(int artigo_id) {
        System.out.println(artigo_id);
        this.artigo = new Artigo().getArtigo(artigo_id);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario_id) {
        Usuario usuario = new Usuario();
        this.usuario = usuario.getUsuario(usuario_id);
    }
    
    
    public Comentario getUsuario(int comentario_id){
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        return comentarioDAO.getComentarioPorID(comentario_id);
    }
    
    public boolean salvarComentario(){
        return new ComentarioDAO().salvarComentario(this);
    }
    
    public List<Comentario> listarComentarioPorArtigos(List<Integer> id_artigos) {
        return new ComentarioDAO().listarComentarioPorArtigos(id_artigos);
    }
}
