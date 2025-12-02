# Projeto POO - PediJá
# Grupo 4 - Paulo Daniel de Moraes Ferreira , Álef Davi Alves de Carvalho,Luiz Miguel Nunes Pimenta e Lucas Alves Izaias.
## Fluxo Principal do Projeto PediJá

1. Entrar (caso já tenha feito o cadastro) ou cadastrar no **Parceiro**.  
2. Ir na opção **"Gerenciar Cardápio"**.  
3. Ir até a opção **"Adicionar Produto"** e inserir as informações do produto.  
4. Depois de adicionado o produto, voltar à tela principal e escolher a opção **"Consumidor"**.  
5. Entrar (caso já tenha feito o cadastro) ou cadastrar no **Consumidor**.  
6. Ir na opção **"Buscar produtos"** e digitar o nome do produto adicionado.  
7. Adicionar o produto buscado no **carrinho**.  
8. Voltar ao **Menu Principal Consumidor** e navegar até a opção **"Ver carrinho"**.  
9. Apertar na opção **"Finalizar Pedido"** para concluir o pedido.  
10. Escolher uma **forma de pagamento**.  
11. Escolher a opção **"Confirmar Pedido"**.  
12. Voltar para a tela principal e escolher a opção **"Parceiro"**, entrando com o login previamente cadastrado.  
13. Acessar a opção **"Gerenciar Pedidos"**, onde o pedido feito pelo consumidor estará em **Pedidos Pendentes**.  
14. Aceitar ou rejeitar o pedido.  
15. Se aceitar, informar o **tempo de preparo**.  
16. O pedido será atualizado para **Pedidos em Preparo**.  
17. Ir na opção **"Pedidos em Preparo"** e marcar se o pedido está pronto.  
18. O pedido será movido para **Pedidos em Entrega**.  
19. Voltar à tela principal e escolher a opção **"Entregador"**.  
20. Entrar com login ou cadastrar um novo entregador.  
21. Tornar-se disponível para novas entregas.  
22. Visualizar pedidos prontos para entrega (status "PRONTO").  
23. Escolher um pedido para aceitar.  
24. Digitar o **ID do pedido** que deseja aceitar.  
25. Confirmar a aceitação do pedido.  
26. O status do pedido será atualizado para **EM ENTREGA**.  
27. Visualizar detalhes do pedido: cliente, endereço, forma de pagamento, itens e valor total.  
28. Acompanhar status do pedido (**EM ENTREGA** ou **ENTREGUE**).  
29. Confirmar quando a entrega for realizada.  
30. O status do pedido será atualizado para **ENTREGUE**.  
31. Voltar ao menu inicial do entregador para aceitar novas entregas ou encerrar a sessão.  
32. Voltar para a tela principal e escolher a opção **"Consumidor"**, entrando com o login previamente cadastrado.  
33. Ir ao **Menu Principal Consumidor**.  
34. Escolher a opção **"Ver Pedidos"**.  
35. Apertar a opção **"Pedidos à Caminho"** para ver o pedido que havia confirmado.  
36. Digitar o número do pedido visível na tela para ver mais informações.  
37. Confirmar se o pedido foi recebido pelo consumidor.  
38. O pedido será movido para **Pedidos Concluídos** tanto para o **Consumidor** quanto para o **Parceiro**.  
39. Todas as outras funções são navegáveis ou visuais.



## Conceitos de POO utilizados:  
1- Abstração: representamos entidades do mundo real (ou conceitos) no código, focando apenas nos atributos e comportamentos essenciais.  
2- Encapsulamento: protegemos os dados internos de um objeto, escondendo a complexidade e permitindo o acesso apenas por meio de métodos controlados (getters e setters).  
3- Polimorfismo: colocamos métodos para se comportarem de diferentes maneiras, dependendo do objeto que o invoca. Ele tem tanto comportamentos via via overriding (sobrescrita) ou overloading(sobrecarga).  
