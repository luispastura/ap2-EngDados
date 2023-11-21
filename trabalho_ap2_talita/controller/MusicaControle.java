package controller;

import java.util.ArrayList;
import dao.MusicaDAO;
import model.Musica;

public class MusicaControle {

    private MusicaDAO musicaDAO = new MusicaDAO();
    
    public MusicaControle() {
	}

	public void createMusica(int id, Musica musica) {
        musicaDAO.criarMusica(musica);
        musicaDAO.criarRelMusicaCategoria(id, musica);
    }

    public void updateMusica(String titulo, String novaLetra) {
        musicaDAO.atualizarMusica(titulo, novaLetra);
    }

    public ArrayList<Musica> selectMusica(String musica) {
        return musicaDAO.buscarMusicaEspecifica(musica);
    }

    public ArrayList<Musica> selectAllMusicas() {
        return musicaDAO.buscarTodasMusicas();
    }

    public void deleteMusica(String music) {
        Musica musica = musicaDAO.buscarMusica(music);
        if (musica != null) {
        	musicaDAO.deletarRel(musica);
            musicaDAO.deletarMusica(music);
        } else {
            System.out.println("Musica informada não existe.");
        }
    }
}

