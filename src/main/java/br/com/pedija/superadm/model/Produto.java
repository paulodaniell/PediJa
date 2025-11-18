package br.com.pedija.superadm.model;
import br.com.pedija.superadm.model.Parceiro;


public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private int categoria_id;
    private int idParceiro;
    private boolean disponivel;
    private String NomeParceiro;

    public Produto() {
    }

    public String getNomeParceiro() {
        return NomeParceiro;
    }

    public void setNomeParceiro(String nomeParceiro) {
        NomeParceiro = nomeParceiro;
    }



    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Integer getCategoria_id() { return categoria_id; }
    public void setCategoria_id(Integer categoria_id) { this.categoria_id = categoria_id; }

    public int getIdParceiro() { return idParceiro; }
    public void setIdParceiro(int idParceiro) { this.idParceiro = idParceiro; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

}
