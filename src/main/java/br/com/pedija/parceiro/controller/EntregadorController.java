package br.com.pedija.parceiro.controller;

import br.com.pedija.parceiro.model.Entregador;

import java.util.ArrayList;
import java.util.List;

public class EntregadorController {

    private static List<Entregador> entregadores = new ArrayList<>();
    private static int proximoId = 1;


    static {

        Entregador e1 = new Entregador(1, "NOME1", "(61) XXXXX-XXXX", "Moto");
        e1.setCpf("123.456.789-00");

        e1.setDisponivel(true);
        entregadores.add(e1);

        // Entregador 2
        Entregador e2 = new Entregador(2, "NOME2", "(61) XXXX-XXXX", "Bicicleta");
        e2.setCpf("111111111111111");
        e2.setDisponivel(true);
        entregadores.add(e2);

        // Entregador 3 - Indisponível
        Entregador e3 = new Entregador(3, "NOME3", "(61) XXX-XXXX", "Carro");
        e3.setDisponivel(false);  // Em entrega
        entregadores.add(e3);

        proximoId = 4;
    }

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

