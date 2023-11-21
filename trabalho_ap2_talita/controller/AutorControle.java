package controller;

import java.util.ArrayList;

import dao.AutorDAO;
import model.Autor;

public class AutorControle {

    private AutorDAO autorDAO = new AutorDAO();
    
    public void createAutor(Autor usuario) {
        autorDAO.criarAutor(usuario);
    }

    public void updateAutor(String name, String newNome) {
        autorDAO.atualizarAutor(name, newNome);
    }

    public Autor selectAutor(String nomeAutor) {
        return autorDAO.buscarAutorEspecifico(nomeAutor);
    }

    public ArrayList<Autor> selectAllAutores() {
        return autorDAO.buscarTodosAutores();
    }

    public void deleteAutor(String cpfAutor) {
        Autor usuario = autorDAO.buscarAutor(cpfAutor);
        if (usuario != null) {
        	autorDAO.deletarRel(usuario);
        	autorDAO.deletarMusica(usuario);
            autorDAO.deletarAutor(cpfAutor);
        } else {
            System.out.println("Autor com cpf informado não existe");
        }
    }
}
