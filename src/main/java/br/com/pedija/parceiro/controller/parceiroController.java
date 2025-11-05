package br.com.pedija.parceiro.controller;
import java.util.ArrayList;
import java.util.List;
import br.com.pedija.parceiro.model.Parceiro;


public class parceiroController {

        private static List<Parceiro> parceiros = new ArrayList<>();
        private static int proximoId = 1;

        //Produtos para teste sem banco de dados
        static {

            Parceiro p1 = new Parceiro(1, "Sushi loko", "Japonês", 10.00,true);
            p1.setEmail("sushi@.com");
            p1.setSenha("123");
            p1.setCnpj("11.222.333/0001-22");
            p1.setTelefone("(61) 97777-7777");
            p1.setEndereco("Rua xxxx, 300");
            parceiros.add(p1);
            proximoId = 2;
        }

        public Parceiro login(String email, String senha) {

            if (email == null || email.trim().isEmpty()) {
                System.out.println(" Email não pode ser vazio!");
                return null;
            }

            if (senha == null || senha.trim().isEmpty()) {
                System.out.println("Senha não pode ser vazia!");
                return null;
            }
            for (Parceiro p : parceiros) {
                if (p.getEmail().equalsIgnoreCase(email) &&
                        p.getSenha().equals(senha)) {
                    return p;
                }
            }

            return null;
        }

        public static boolean cadastrar(Parceiro parceiro) {

            if (parceiro.getNome() == null || parceiro.getNome().trim().isEmpty()) {
                System.out.println(" Nome é obrigatório!");
                return false;
            }

            if (parceiro.getNome().length() < 3) {
                System.out.println(" Nome deve ter pelo menos 3 caracteres!");
                return false;
            }


            if (parceiro.getEmail() == null || parceiro.getEmail().trim().isEmpty()) {
                System.out.println(" Email é obrigatório!");
                return false;
            }

            if (!parceiro.getEmail().contains("@")) {
                System.out.println(" Email inválido! Deve conter @");
                return false;
            }



            if (parceiro.getSenha() == null || parceiro.getSenha().trim().isEmpty()) {
                System.out.println(" Senha é obrigatória!");
                return false;
            }

            if (parceiro.getSenha().length() < 3) {
                System.out.println(" Senha deve ter pelo menos 3 caracteres!");
                return false;
            }


            if (parceiro.getCnpj() == null || parceiro.getCnpj().trim().isEmpty()) {
                System.out.println(" CNPJ é obrigatório!");
                return false;
            }


            if (parceiro.getTelefone() == null || parceiro.getTelefone().trim().isEmpty()) {
                System.out.println(" Telefone é obrigatório!");
                return false;
            }


            if (parceiro.getCategoria() == null || parceiro.getCategoria().trim().isEmpty()) {
                System.out.println(" Tipo de restaurante é obrigatório!");
                return false;
            }


            if (parceiro.getTaxaEntrega() < 0) {
                System.out.println("Taxa de entrega não pode ser negativa!");
                return false;
            }

            parceiro.setId(proximoId++);


            parceiro.setAberto(true);

            boolean sucesso = parceiros.add(parceiro);

            if (sucesso) {
                System.out.println(" ID gerado: " + parceiro.getId());
            }

            return sucesso;
        }


        public List<Parceiro> listarTodos() {
            return new ArrayList<>(parceiros);  // Retorna cópia
        }


        public List<Parceiro> listarAbertos() {
            List<Parceiro> abertos = new ArrayList<>();

            for (Parceiro p : parceiros) {
                if (p.isAberto()) {
                    abertos.add(p);
                }
            }

            return abertos;
        }

        public Parceiro buscarPorId(int id) {
            for (Parceiro p : parceiros) {
                if (p.getId() == id) {
                    return p;
                }
            }
            return null;
        }

        public List<Parceiro> buscarPorNome(String nome) {
            List<Parceiro> resultado = new ArrayList<>();

            for (Parceiro p : parceiros) {
                if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    resultado.add(p);
                }
            }

            return resultado;
        }


        public List<Parceiro> buscarPorTipo(String tipo) {
            List<Parceiro> resultado = new ArrayList<>();

            for (Parceiro p : parceiros) {
                if (p.getCategoria().equalsIgnoreCase(tipo)) {
                    resultado.add(p);
                }
            }

            return resultado;
        }


        public boolean atualizar(Parceiro parceiroAtualizado) {
            for (int i = 0; i < parceiros.size(); i++) {
                if (parceiros.get(i).getId() == parceiroAtualizado.getId()) {
                    parceiros.set(i, parceiroAtualizado);
                    return true;
                }
            }
            return false;
        }


        public boolean alterarStatus(int id, boolean aberto) {
            Parceiro p = buscarPorId(id);

            if (p != null) {
                p.setAberto(aberto);
                return true;
            }

            return false;
        }


        public boolean remover(int id) {
            return parceiros.removeIf(p -> p.getId() == id);
        }


        public boolean alterarTaxaEntrega(int id, double novaTaxa) {
            if (novaTaxa < 0) {
                System.out.println(" Taxa não pode ser negativa!");
                return false;
            }

            Parceiro p = buscarPorId(id);

            if (p != null) {
                p.setTaxaEntrega(novaTaxa);
                return true;
            }

            return false;
        }


    }

