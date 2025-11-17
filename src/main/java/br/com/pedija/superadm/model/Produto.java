package br.com.pedija.superadm.model;
import br.com.pedija.superadm.model.Categoria;

public class Produto {
    Categoria categoria;

    String descricao;
    private int id;
    private String nome;
    private double preco;



    // CAMPOS PARA PARCEIROS
    private int idParceiro;
    private boolean disponivel;
    private int quantidade;
    private int tempoPreparo; // em minutos

    // CAMPOS PARA CATEGORIA
    private Integer categoriaId;   // COMEÇA NULO
    private String categoriaNome;
    // EXTRA
    private String loja;

    public Produto() {

    }


    public Produto(int id, String nome, String descricao, double preco, String loja) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.loja = loja;
    }

    // CONSTRUTOR QUE ALGUMAS TELAS AINDA USAM
    public Produto(int id, String nome, double preco, int idParceiro) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.idParceiro = idParceiro;
        this.disponivel = true;
        this.quantidade = 0;
    }




    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getIdParceiro() { return idParceiro; }
    public void setIdParceiro(int idParceiro) { this.idParceiro = idParceiro; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getTempoPreparo() { return tempoPreparo; }
    public void setTempoPreparo(int tempoPreparo) { this.tempoPreparo = tempoPreparo; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }

    public String getLoja() { return loja; }
    public void setLoja(String loja) { this.loja = loja; }
}
