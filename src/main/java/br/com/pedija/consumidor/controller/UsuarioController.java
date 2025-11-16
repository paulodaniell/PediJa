package br.com.pedija.consumidor.controller;

import br.com.pedija.superadm.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller mínimo para trabalhar com VIEW + MODEL sem DAO.
 * Atualmente usa uma lista em memória como "banco fake".
 *
 * // AQUI ENTRA O BANCO DE DADOS (FUTURO)
 * - Substituir a lista por chamadas ao DAO/Repository que acesse o BD.
 */

public class UsuarioController {

    // armazenamento em memória para testes (substituir pelo BD depois)
    private static ArrayList<Usuario> base = new ArrayList<>();
    private static int id = 1;

    //seed de teste
    static{
        Usuario exemplo = new Usuario();
        exemplo.setId(id++);
        exemplo.setEmail("user@gmail.com");
        exemplo.setTelefone("11999999999");
        exemplo.setcpf("11111111111");
        exemplo.setNome("Usuário Teste");
        exemplo.setEndereco("Rua Exemplo");
        exemplo.setFormadepagamento("Cartão");
        base.add(exemplo);
    }

    public boolean validarLogin(String email, String telefone) {
        return base.stream().anyMatch(u ->
                u.getEmail().equalsIgnoreCase(email)
                        && u.getTelefone().equals(telefone)
        );
    }

    /**
     * CADASTRO: só adiciona o usuário na "BASE" fake.
     */
    public void cadastrarUsuario(Usuario u) {
        u.setId(id++);
        base.add(u);
    }


    public void atualizarUsuario(Usuario u) {
        if (u == null) return;
        for (int i = 0; i < base.size(); i++) {
            if (base.get(i).getId() == u.getId()) {
                base.set(i, u);
                return;
            }
        }
        // se não existir, adicionar (opcional)
        u.setId(id++);
        base.add(u);
    }

}