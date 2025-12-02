package br.com.pedija.entregador.view;
import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Entregador;

public class TelaExtratoEntregador {

    PedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido = new Pedido();
    Entregador entregadorLogado = new Entregador();

    public void extrato() {

        TelaInicialEntregador tela = new TelaInicialEntregador(entregadorLogado);

        pedidoDAO.buscarPorStatus("ENTREGUE");




    }
}