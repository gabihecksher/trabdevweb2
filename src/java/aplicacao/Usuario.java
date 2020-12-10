/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacao;

import Model.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private int papel;
    private String cadastro_aprovado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getPapel() {
        return papel;
    }
    
    public String getPapelNome(){
        if(papel == 0) {
            return "Administrador";
        }
        if(papel == 1) {
            return "Autor";
        }
        if(papel == 2) {
            return "Comentarista";
        }
        return "";
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }

    public String getCadastroAprovado() {
        return cadastro_aprovado;
    }

    public void setCadastroAprovado(String cadastro_aprovado) {
        this.cadastro_aprovado = cadastro_aprovado;
    }
    
    public List<Usuario> getListaUsuarios() throws SQLException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listarTodosUsuariosDAO();
    }
    
    public List<Usuario> getListaUsuariosPorStatus(boolean aprovado) throws SQLException{
        System.out.println("procura usuarios" + aprovado);
            
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.getListaUsuariosPorStatus(aprovado);
    }
    
    public Usuario getUsuario(int usuario_id){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.getUsuarioPorID(usuario_id);
    }
    
    public Usuario getUsuarioPorId(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.getUsuarioPorId(id);
    }
    
    public Usuario getUsuarioPorLoginSenha(String cpf, String senha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.getUsuarioPorLoginSenha(cpf, senha);
    }
    
    public boolean aprovaUsuario(int usuario_id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.aprovaUsuario(usuario_id);
    }
    
    public boolean isAdmin(){
        return this.papel == 0;
    }
    
    public boolean isAutor(){
        return this.papel == 1;
    }
    
    public boolean isComentarista(){
        return this.papel == 2;
    }
        
}
