package br.com.pedija.superadm.model;

public class ItemPedido {
        private int id;
        private int pedidoId;
        private int produtoId;
        private int quantidade;
        private double precoUnitario;
        private String nomeProduto;
        private double subTotal;

        public ItemPedido() {}

        public String getNomeProduto() {
            return nomeProduto;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }

        public double getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(double subTotal) {
            this.subTotal = subTotal;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getPedidoId() { return pedidoId; }
        public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }

        public int getProdutoId() { return produtoId; }
        public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

        public double getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

        public double getTotal() {
            return quantidade * precoUnitario;
        }

}
