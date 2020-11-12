/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

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
    private char liberar;
    private char aprovado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public char getLiberar() {
        return liberar;
    }

    public void setLiberar(char liberar) {
        this.liberar = liberar;
    }

    public char getAprovado() {
        return aprovado;
    }

    public void setAprovado(char aprovado) {
        this.aprovado = aprovado;
    }
    
}
