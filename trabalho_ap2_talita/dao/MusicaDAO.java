package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.connector.ConnectionFactory;
import model.Musica;

public class MusicaDAO {

    private ConnectionFactory cf = new ConnectionFactory();
	private Connection cn = cf.recuperaConexao();
    
    public void criarMusica(Musica musica) {
    	 
            String sql = "INSERT INTO musica (id, titulo, letra, fk_categoria, duracao, data_lancamento) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, musica.getId());
                pstm.setString(2, musica.getTitulo());
                pstm.setString(3, musica.getLetra());
                pstm.setInt(4, musica.getCategoria());
                pstm.setObject(5, musica.getDuracao());
                pstm.setObject(6, musica.getDataLancamento());
                pstm.execute();
                
             }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public void deletarMusica(String titulo) {
        try {
            String sql = "DELETE FROM musica WHERE titulo = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, titulo);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Musica> buscarMusicaEspecifica(String samba) {

    	ArrayList<Musica> musicas = new ArrayList<Musica>();

        try {
            String sql = "SELECT * FROM categoria cat INNER JOIN musica music WHERE cat.id = music.fk_categoria and cat.nome = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, samba);
                ResultSet rst = pstm.executeQuery();
                while (rst.next()) {
                    int musicaID = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    int categoria = rst.getInt("fk_categoria");
                    String duracao = rst.getString("duracao");
                    LocalDate dataLancamento = rst.getObject("data_lancamento", LocalDate.class);

                    Musica musica = new Musica(musicaID, titulo, letra, categoria, duracao, dataLancamento);
                    musicas.add(musica);
                } 
            }
            return musicas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Musica> buscarTodasMusicas() {

        ArrayList<Musica> musicas = new ArrayList<Musica>();

        try {
            String sql = "SELECT * FROM musica";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()) {
                	int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    int categoria = rst.getInt("fk_categoria");
                    String duracao = rst.getString("duracao");
                    LocalDate dataLancamento = rst.getObject("data_lancamento", LocalDate.class);

                    Musica musica = new Musica(id, titulo, letra, categoria, duracao, dataLancamento);
                    musicas.add(musica);
                }
            }
            return musicas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public void atualizarMusica(String titulo, String novaLetra) {
		try {
            String sql = "UPDATE musica SET letra = ? WHERE titulo = ?" ;

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, novaLetra);
            pstm.setString(2, titulo);
            pstm.executeUpdate();
            
            } catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Musica buscarMusica(String music) {
		
		Musica musicas = null;

        try {
            String sql = "SELECT * FROM musica where titulo = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
            	pstm.setString(1, music);
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()) {
                	int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    int categoria = rst.getInt("fk_categoria");
                    String duracao = rst.getString("duracao");
                    LocalDate dataLancamento = rst.getObject("data_lancamento", LocalDate.class);

                    musicas = new Musica(id, titulo, letra, categoria, duracao, dataLancamento);
                }
            }
            return musicas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public void criarRelMusicaCategoria(int id, Musica musica) {
            String sql = "INSERT INTO relacionamento_autor_musica (id_tabela, autor_id, musica_id) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            	pstm.setInt(1, id);
            	pstm.setInt(2, musica.getCategoria());
                pstm.setInt(3, musica.getId());
                pstm.execute();
                
             }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public void deletarRel(Musica musica) {
		try {
            String sql = "DELETE FROM relacionamento_autor_musica WHERE id_tabela = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setInt(1, musica.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}


                