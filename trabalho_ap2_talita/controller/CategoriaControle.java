package controller;

import java.util.ArrayList;
import dao.CategoriaDAO;
import model.Categoria;

public class CategoriaControle {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    
    public CategoriaControle() {
    }
    
	public void createCategoria(Categoria categoria) {
        categoriaDAO.criarCategoria(categoria);
    }
    
    public void updateCategoria(String categoria, String novaCategoria) {
        categoriaDAO.atualizarCategoria(categoria, novaCategoria);
    }

    public Categoria selectCategoria(String nomeCategoria) {
        return categoriaDAO.buscarCategoriaEspecifica(nomeCategoria);
    }

    public ArrayList<Categoria> selectAllCategorias() {
        return categoriaDAO.buscarTodasCategorias();
    }

    public void deleteCategoria(String nomeCategoria) {
        Categoria categoria = categoriaDAO.buscarCategoriaEspecifica(nomeCategoria);
        if (categoria != null) {
            categoriaDAO.deletarCategoria(nomeCategoria);
        } else {
            System.out.println("Categoria informada não existe.");
        }
    }
}

