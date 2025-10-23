package br.com.pedija.entregador.model;

public class Duvida {

    private int id;
    private String titulo;
    private String descricao;
    private String data; // melhor usar String ou LocalDate ao invés de int

    public Duvida() {}

    public Duvida(int id, String titulo, String descricao, String data) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nTítulo: " + titulo +
                "\nDescrição: " + descricao +
                "\nData: " + data;
    }
}