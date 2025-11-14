package br.com.pedija.parceiro.controller;

import br.com.pedija.parceiro.model.Promocao;
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


    public Promocao buscarPorId(int id) {
        for (Promocao p : promocoes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public boolean remover(int id) {
        return promocoes.removeIf(p -> p.getId() == id);
    }


    public boolean desativar(int id) {
        Promocao p = buscarPorId(id);
        if (p != null) {
            p.setAtiva(false);
            return true;
        }
        return false;
    }
}