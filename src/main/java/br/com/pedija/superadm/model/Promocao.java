package br.com.pedija.superadm.model;


public class Promocao {
    private int id;
    private int idProduto;
    private int idParceiro;
    private double precoOriginal;
    private double precoPromocional;

    private double percentualDesconto;
    private boolean ativa;

    public double getPercentualDesconto() {
     if (precoOriginal == 0) {
         return 0;
     }
     return ((precoOriginal - precoPromocional) / precoOriginal) * 100;
    }

    public void setPercentualDesconto(double percentualDesconto) {this.percentualDesconto = percentualDesconto;}

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(int idParceiro) {
        this.idParceiro = idParceiro;
    }

    public double getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(double precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public double getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(double precoPromocional) {
        this.precoPromocional = precoPromocional;
    }
}
