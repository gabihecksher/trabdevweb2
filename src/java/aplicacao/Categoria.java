package Aplicacao;

import Model.CategoriaDAO;

public class Categoria {
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Categoria getCategoria(int categoria_id){
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.getCategoriaPorID(categoria_id);
    }
}
