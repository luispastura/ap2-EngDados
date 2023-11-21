package model;

public class Autor {
    private int id;
    private String cpf;
    private String nome_artistico;
    private String nome_original;
    
    public Autor(int id, String cpf, String nome_original, String nome_artistico) {
        this.id = id;
        this.cpf = cpf;
        this.nome_original = nome_original;
        this.nome_artistico = nome_artistico;
    }

    public Autor(String cpf, String nome_original, String nome_artistico) {
    	this.cpf = cpf;
        this.nome_original = nome_original;
        this.nome_artistico = nome_artistico;
    }


	public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeOriginal() {
        return this.nome_original;
    }

    public void setNomeOriginal(String nome_original) {
        this.nome_original = nome_original;
    }

    public String getNomeArtistico() {
        return this.nome_artistico;
    }

    public void setNomeArtistico(String nome_artistico) {
        this.nome_artistico = nome_artistico;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + this.getId() + "'" +
                ", Nome original ='" + this.getNomeOriginal() + "'" +
                ", Nome artistico='" + this.getNomeArtistico() + "'" +
                ", CPF='" + this.getCpf() + "'" +
                "}";
    }
}
