package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.model.Entregador;

import java.util.ArrayList;
import java.util.List;

public class EntregadorController {

    private static List<Entregador> entregadores = new ArrayList<>();
    private static int proximoId = 1;


    public List<Entregador> listarTodos() {
        return new ArrayList<>(entregadores);
    }

    public List<Entregador> listarDisponiveis() {
        List<Entregador> disponiveis = new ArrayList<>();

        for (Entregador e : entregadores) {
            if (e.isDisponivel()) {
                disponiveis.add(e);
            }
        }

        return disponiveis;
    }

    public Entregador buscarPorId(int id) {
        for (Entregador e : entregadores) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }


    public boolean atribuirPedido(int idEntregador) {
        Entregador e = buscarPorId(idEntregador);

        if (e != null && e.isDisponivel()) {
            e.setDisponivel(false);
            return true;
        }

        return false;
    }

}