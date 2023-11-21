package model;

import java.time.LocalDate;

public class Musica {
    private int id;
    private String titulo;
    private String letra;
    private int categoria; 
    private String duracao;
    private LocalDate data_lancamento;

    public Musica(int id, String titulo, String letra, int categoria, String duracao, LocalDate data_lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.letra = letra;
        this.categoria = categoria;
        this.duracao = duracao;
        this.data_lancamento = data_lancamento;
    }

    public Musica(String titulo, String letra, int categoria, String duracao, LocalDate data_lancamento) {
        this.titulo = titulo;
        this.letra = letra;
        this.categoria = categoria;
        this.duracao = duracao;
        this.data_lancamento = data_lancamento;
    }

    public int getId() {
        return this.id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLetra() {
        return this.letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getCategoria() {
        return this.categoria;
    }

    public int setCategoria(int categoria) {
        return this.categoria = categoria;
    }

     public String getDuracao() {
        return this.duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

     public LocalDate getDataLancamento() {
        return this.data_lancamento;
    }

    public void setDataLancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", Titulo da música ='" + getTitulo() + "'" +
            ", Letra da música ='" + getLetra() + "'" +
            ", Categoria ='" + getCategoria() + "'" +
            ", Duracao =" + getDuracao()+ "'" +
            ", Data de Lançamento =" +getDataLancamento()+"'" +
            "}";
    }
}