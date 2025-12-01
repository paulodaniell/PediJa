package br.com.pedija.entregador.view;
import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;

public class TelaExtratoEntregador {

    PedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido = new Pedido();

    public void extrato() {

        TelaInicialEntregador tela = new TelaInicialEntregador();

        pedidoDAO.buscarPorStatus("ENTREGUE");




    }
}