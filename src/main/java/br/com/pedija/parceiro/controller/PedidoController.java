package br.com.pedija.parceiro.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.pedija.superadm.model.ItemPedido;
import br.com.pedija.superadm.model.Pedido;

public class PedidoController {
    private static List<Pedido> pedidos = new ArrayList<>();
    private static int proximoId = 1;


    public static void adicionar(Pedido pedido) {
        pedido.setId(proximoId++);
        pedidos.add(pedido);
    }

    public List<Pedido> listarPorParceiroEStatus(int idParceiro, String status) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && p.getStatus().equals(status)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizarStaus(int id, String novoStatus) {
        Pedido p = buscarPorId(id);
        if (p != null) {
            p.setStatus(novoStatus);
            return true;
        }
        return false;
    }

    public int contarPorStatus(int idParceiro, String status) {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && p.getStatus().equals(status)) {
                contador++;
            }
        }
        return contador;
    }
    public double calcularFaturamentoTotal(int idParceiro) {
        double soma = 0.0;
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && "ENTREGUE".equalsIgnoreCase(p.getStatus())) {
                soma += p.getValorTotal();
            }
        }
        return soma;
    }


    public int contarPedidosEntregues(int idParceiro) {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && "ENTREGUE".equalsIgnoreCase(p.getStatus())) {
                contador++;
            }
        }
        return contador;
    }



    public boolean atribuirEntregador(int idPedido, int idEntregador) {
        Pedido p = buscarPorId(idPedido);

        if (p != null) {
            p.setIdEntregador(idEntregador);
            p.setStatus("EM_ENTREGA");
            return true;
        }

        return false;
    }


    public List<Pedido> listarAguardandoEntregador(int idParceiro) {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro &&
                    p.getStatus().equals("PRONTO") &&
                    p.getIdEntregador() == 0) {
                resultado.add(p);
            }
        }

        return resultado;
    }


}

