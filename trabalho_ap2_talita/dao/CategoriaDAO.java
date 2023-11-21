package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.connector.ConnectionFactory;
import model.Categoria;

public class CategoriaDAO {

    private ConnectionFactory cf = new ConnectionFactory();
	private Connection cn = cf.recuperaConexao();


	public Categoria buscarCategoriaEspecifica(String hiphop) {

        Categoria categoria = null;
        try {
            String sql = "SELECT * FROM categoria WHERE nome = ?";
            
            try(PreparedStatement pstm = (PreparedStatement) cn.prepareStatement(sql)){
                pstm.setString(1, hiphop);
                ResultSet rst = pstm.executeQuery();
                while (rst.next()){
                    categoria = new Categoria(rst.getInt("id"), rst.getString("nome"));
                }
            }
            return categoria;
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

	public void criarCategoria(Categoria categoria) {
		 try {
	            String sql = "INSERT INTO categoria (id, nome) VALUES (?, ?)";

	            try (PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	                pstm.setInt(1, categoria.getId());
	                pstm.setString(2, categoria.getNome());
	    
	                pstm.execute();

	                try (ResultSet rst = pstm.getGeneratedKeys()) {
	                    while (rst.next()) {
	                    	categoria.setId(rst.getInt(1));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
		
	public void atualizarCategoria(String rock, String mpb) {
		 try {
	            String sql = "UPDATE categoria SET nome = ? WHERE nome = ?" ;

	            PreparedStatement pstm = cn.prepareStatement(sql);
	            pstm.setString(1, mpb);
	            pstm.setString(2, rock);
	            pstm.executeUpdate();
	            
	            } catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	public ArrayList<Categoria> buscarTodasCategorias() {
		ArrayList<Categoria> categoria = new ArrayList<Categoria>();

        try {
            String sql = "SELECT * FROM categoria";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()) {
                	int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                
                    Categoria u = new Categoria(id, nome);
                    categoria.add(u);
                }
            }
            return categoria;
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

	public void deletarCategoria(String mpb) {
		try {
            String sql = "DELETE FROM categoria WHERE nome = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, mpb);
                pstm.execute();
            }
        } catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
