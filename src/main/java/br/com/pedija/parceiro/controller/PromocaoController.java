package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.model.Promocao;
import java.util.ArrayList;
import java.util.List;

public class PromocaoController {
    private static List<Promocao> promocoes = new ArrayList<>();
    private static int proximoId = 1;


    public boolean adicionar(Promocao promocao) {
        promocao.setId(proximoId++);
        return promocoes.add(promocao);
    }

    public List<Promocao> listarPorParceiro(int idParceiro) {
        List<Promocao> resultado = new ArrayList<>();
        for (Promocao p : promocoes) {
            if (p.getIdParceiro() == idParceiro) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    public boolean remover(int id) {
        return promocoes.removeIf(p -> p.getId() == id);
    }



}