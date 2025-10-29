package br.com.pedija.parceiro.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.pedija.parceiro.model.Pedido;

public class PedidoController {
    private static List<Pedido> pedidos = new ArrayList<>();
    private static int proximoId = 1;

    public boolean adicionar(Pedido pedido) {
        pedido.setId(proximoId++);
        return PedidoController.pedidos.add(pedido);
    }

    public List<Pedido>listarPorParceiroEStatus(int idParceiro, String status) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if(p.getIdParceiro() == idParceiro && p.getStatus().equals(status)) {
                pedidos.add(p);
            }
        }
        return resultado;
    }
    public Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    public boolean atualizarStaus(int id, String novoStaus){
        Pedido p = buscarPorId(id);
        if(p != null){
            p.setStatus(novoStaus);
            return true;
        }
        return false;
    }
    public int contarPorStatus(int idParceiro, String status){
        int contador = 0;
        for (Pedido p : pedidos) {
            if(p.getIdParceiro() == idParceiro && p.getStatus().equals(status)){
                contador++;
            }
        }
        return contador;
    }
}
