package br.com.pedija.parceiro.model;
import java.time.LocalDate;
public class Promocao {

    private int id;
    private int idProduto;
    private int idParceiro;
    private double precoOriginal;
    private double precoPromocional;
    private double percentualDesconto;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativa;


    public Promocao() {
        this.ativa = true;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public int getIdParceiro() { return idParceiro; }
    public void setIdParceiro(int idParceiro) { this.idParceiro = idParceiro; }

    public double getPrecoOriginal() { return precoOriginal; }
    public void setPrecoOriginal(double precoOriginal) { this.precoOriginal = precoOriginal; }

    public double getPrecoPromocional() { return precoPromocional; }
    public void setPrecoPromocional(double precoPromocional) { this.precoPromocional = precoPromocional; }

    public double getPercentualDesconto() { return percentualDesconto; }
    public void setPercentualDesconto(double percentualDesconto) { this.percentualDesconto = percentualDesconto; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }


    public double calcularDesconto() {
        return precoOriginal - precoPromocional;
    }
}
