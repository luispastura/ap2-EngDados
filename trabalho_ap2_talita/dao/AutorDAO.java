package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.connector.ConnectionFactory;
import model.Autor;
import model.Categoria;

public class AutorDAO {
	
	private ConnectionFactory cf = new ConnectionFactory();
	private Connection cn = cf.recuperaConexao();
	
    public AutorDAO() {
    }
    
	public void criarAutor(Autor autor) {
        try {
            String sql = "INSERT INTO autor (id, cpf, nome_original, nome_artistico) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, autor.getId());
                pstm.setString(2, autor.getCpf());
                pstm.setString(3, autor.getNomeOriginal());
                pstm.setString(4, autor.getNomeArtistico());
    
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                    	autor.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarAutor(String cpfAutor) {
        try {
            String sql = "DELETE FROM autor WHERE cpf = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, cpfAutor);
                pstm.execute();
            }
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public Autor buscarAutorEspecifico(String rock) {

    	Autor usuario = null;

        try {
            String sql = "SELECT * FROM autor atr INNER JOIN categoria cat WHERE atr.id = cat.id and cat.nome = ?" ;

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, rock);
                ResultSet rst = pstm.executeQuery();
                
                while (rst.next()) {
                    usuario = new Autor(rst.getInt("id"), rst.getString("cpf"), 
                    			rst.getString("nome_original"), rst.getString("nome_artistico"));
                } 
            }
            return usuario;
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Autor> buscarTodosAutores() {

        ArrayList<Autor> usuarios = new ArrayList<Autor>();

        try {
            String sql = "SELECT * FROM autor";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()) {
                	int id = rst.getInt("id");
                    String nome_artistico = rst.getString("nome_artistico");
                    String nome_original = rst.getString("nome_original");
                    String cpf = rst.getString("cpf");

                    Autor u = new Autor(id, cpf, nome_original, nome_artistico);
                    usuarios.add(u);
                }
            }
            return usuarios;
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

	public void atualizarAutor(String name, String newName) {
        try {
            String sql = "UPDATE autor SET nome_artistico = ? WHERE nome_original = ?" ;

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, newName);
            pstm.setString(2, name);
            pstm.executeUpdate();
            
            } catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Autor buscarAutor(String cpfAutor) {
		Autor usuario = null;

        try {
            String sql = "SELECT * FROM autor WHERE cpf = ?" ;

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, cpfAutor);
                ResultSet rst = pstm.executeQuery();
                
                while (rst.next()) {
                    usuario = new Autor(rst.getInt("id"), rst.getString("cpf"), 
                    			rst.getString("nome_original"), rst.getString("nome_artistico"));
                } 
            }
            return usuario;
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletarRel(Autor usuario) {
		try {
            String sql = "DELETE FROM relacionamento_autor_musica WHERE id_tabela = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setInt(1, usuario.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void deletarMusica(Autor usuario) {
		try {
            String sql = "DELETE FROM relacionamento_autor_musica WHERE id_tabela = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setInt(1, usuario.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
