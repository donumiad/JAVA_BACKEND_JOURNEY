package br.com.raimundo.taskmanager.app;

import br.com.raimundo.taskmanager.comparator.TaskPorDataComparator;
import br.com.raimundo.taskmanager.comparator.TaskPorPrioridadeComparator;
import br.com.raimundo.taskmanager.comparator.TaskPorTituloComparator;
import br.com.raimundo.taskmanager.exception.DuplicateTaskException;
import br.com.raimundo.taskmanager.exception.InvalidTaskDataException;
import br.com.raimundo.taskmanager.exception.TaskNotFoundException;
import br.com.raimundo.taskmanager.model.Prioridade;
import br.com.raimundo.taskmanager.model.Status;
import br.com.raimundo.taskmanager.model.Task;
import br.com.raimundo.taskmanager.repository.TaskRepositoryMemoria;
import br.com.raimundo.taskmanager.service.TaskService;
import br.com.raimundo.taskmanager.testes.Produto;
import br.com.raimundo.taskmanager.testes.SetProduto;

import java.time.LocalDate;
import java.util.*;

/*LIST
* mantém ordem de inserção na maioria dos usos práticos
* permite elementos repetidos
* acessa por índice
* list.add() adiciona um elemento no fim da lista
* list.get(posição) retorna o elemento na posição indicada
* list.remove(elemento/posição) remove o elemento/posição indicado
* list.size() retorna o tamanho da lista em numero de elementos
* list.set(posição, elemento) altera o elemento da posíção indicada
* list.contains(elemento) verifica se o elemento esta contido na lista
* list.isEmpty() verifica se a lista esta vazia
* list.clear() remove todos os elementos
* */

public class Main {
    public static void main(String[] args) {
        TaskRepositoryMemoria repositorio = new TaskRepositoryMemoria();
        TaskService service = new TaskService(repositorio);

        Task tarefa1 = new Task(
                1L,
                "Aprender List",
                "Aprender listas",
                Status.PENDENTE,
                Prioridade.ALTA,
                LocalDate.of(2026,5,8));

        Task tarefa2 = new Task(
                2L,
                "Preparar Set",
                "Aprender conjuntos",
                Status.EM_ANDAMENTO,
                Prioridade.MEDIA,
                LocalDate.of(2026,5,6));
        Task tarefa3 = new Task(3L,
                "Estudar Map",
                "Aprender mapeamentos",
                Status.PENDENTE,
                Prioridade.BAIXA,
                LocalDate.of(2026,4,30));
        Task tarefa4 = new Task(
                4L,
                "Criar enum",
                "Treinar Status e Prioridade",
                Status.CONCLUIDO,
                Prioridade.MINIMA,
                LocalDate.of(2026,4,25));
        Task tarefa5 =new Task(
                5L,
                "Analisar toString para Aprender",
                "Melhorar impressão de objetos",
                Status.PENDENTE,
                Prioridade.BAIXA,
                LocalDate.of(2026,5,10));
        Task tarefa6 = new Task(
                6L,
                "Revisar Streams",
                "Praticar filter e map",
                Status.PENDENTE,
                Prioridade.ALTA,
                LocalDate.of(2026, 5, 12));

        Task tarefa7 = new Task(
                7L,
                "Estudar Optional",
                "Entender orElse e ifPresent",
                Status.EM_ANDAMENTO,
                Prioridade.MEDIA,
                LocalDate.of(2026, 5, 14));

        Task tarefa8 = new Task(
                8L,
                "Praticar Comparator",
                "Ordenar tarefas por data e prioridade",
                Status.CONCLUIDO,
                Prioridade.BAIXA,
                LocalDate.of(2026, 4, 28));

        Task tarefa9 = new Task(
                9L,
                "Aprender LocalDate",
                "Treinar manipulação de datas",
                Status.PENDENTE,
                Prioridade.MEDIA,
                LocalDate.of(2026, 5, 9));

        Task tarefa10 = new Task(
                10L,
                "Criar relatório",
                "Mostrar quantidades por status e prioridade",
                Status.EM_ANDAMENTO,
                Prioridade.ALTA,
                LocalDate.of(2026, 5, 11));

        Task tarefa11 = new Task(
                11L,
                "Refatorar Main",
                "Reduzir repetição de código",
                Status.PENDENTE,
                Prioridade.BAIXA,
                LocalDate.of(2026, 5, 15));

        Task tarefa12 = new Task(
                12L,
                "Estudar groupingBy",
                "Agrupar tarefas por status",
                Status.CONCLUIDO,
                Prioridade.MINIMA,
                LocalDate.of(2026, 4, 26));

        Task tarefa13 = new Task(
                13L,
                "Treinar busca por título",
                "Filtrar tarefas contendo palavras específicas",
                Status.PENDENTE,
                Prioridade.URGENTE,
                LocalDate.of(2026, 5, 7));

        Task tarefa14 = new Task(
                14L,
                "Melhorar nomes de métodos",
                "Padronizar nomes no service e repository",
                Status.EM_ANDAMENTO,
                Prioridade.MEDIA,
                LocalDate.of(2026, 5, 13));

        Task tarefa15 = new Task(
                15L,
                "Testar exceções",
                "Preparar terreno para TaskNotFoundException",
                Status.PENDENTE,
                Prioridade.ALTA,
                LocalDate.of(2026, 5, 16));
        Task tarefa16 = new Task(
                16L,
                "Documentar projeto",
                "Escrever README inicial do gerenciador de tarefas",
                Status.PENDENTE,
                Prioridade.MEDIA,
                LocalDate.of(2026, 5, 17));

        Task tarefa17 = new Task(
                17L,
                "Praticar busca por id",
                "Testar Optional com ids existentes e inexistentes",
                Status.CONCLUIDO,
                Prioridade.ALTA,
                LocalDate.of(2026, 5, 2));

        Task tarefa18 = new Task(
                18L,
                "Criar testes manuais",
                "Validar filtros por status e prioridade",
                Status.EM_ANDAMENTO,
                Prioridade.BAIXA,
                LocalDate.of(2026, 5, 18));

        Task tarefa19 = new Task(
                19L,
                "Estudar Collectors",
                "Aprofundar toList, toSet e groupingBy",
                Status.PENDENTE,
                Prioridade.ALTA,
                LocalDate.of(2026, 5, 6));

        Task tarefa20 = new Task(
                20L,
                "Ajustar TaskService",
                "Padronizar nomes dos métodos de busca e listagem",
                Status.EM_ANDAMENTO,
                Prioridade.MEDIA,
                LocalDate.of(2026, 5, 19));

        Task tarefa21 = new Task(
                21L,
                "Treinar ordenação decrescente",
                "Ordenar tarefas pela data mais recente primeiro",
                Status.CONCLUIDO,
                Prioridade.MINIMA,
                LocalDate.of(2026, 4, 24));

        Task tarefa22 = new Task(
                22L,
                "Criar tarefas vencidas",
                "Preparar massa de dados para testar listarVencidas",
                Status.PENDENTE,
                Prioridade.URGENTE,
                LocalDate.of(2026, 5, 1));

        Task tarefa23 = new Task(
                23L,
                "Separar responsabilidades",
                "Revisar o papel de Main, Repository e Service",
                Status.EM_ANDAMENTO,
                Prioridade.ALTA,
                LocalDate.of(2026, 5, 20));

        Task tarefa24 = new Task(
                24L,
                "Estudar map em stream",
                "Gerar listas derivadas de títulos e descrições",
                Status.CONCLUIDO,
                Prioridade.BAIXA,
                LocalDate.of(2026, 4, 27));

        Task tarefa25 = new Task(
                25L,
                "Preparar menu console",
                "Planejar opções do sistema para a próxima etapa",
                Status.PENDENTE,
                Prioridade.MEDIA,
                LocalDate.of(2026, 5, 21));

        try {
            service.cadastrar(tarefa1);
            service.cadastrar(tarefa2);
            service.cadastrar(tarefa3);
            service.cadastrar(tarefa4);
            service.cadastrar(tarefa5);
            service.cadastrar(tarefa6);
            service.cadastrar(tarefa7);
            service.cadastrar(tarefa8);
            service.cadastrar(tarefa9);
            service.cadastrar(tarefa10);
            service.cadastrar(tarefa11);
            service.cadastrar(tarefa12);
            service.cadastrar(tarefa13);
            service.cadastrar(tarefa14);
            service.cadastrar(tarefa15);
            service.cadastrar(tarefa16);
            service.cadastrar(tarefa17);
            service.cadastrar(tarefa18);
            service.cadastrar(tarefa19);
            service.cadastrar(tarefa20);
            service.cadastrar(tarefa21);
            service.cadastrar(tarefa22);
            service.cadastrar(tarefa23);
            service.cadastrar(tarefa24);
            service.cadastrar(tarefa25);
            System.out.println("Tarefa cadastrada com sucesso.");
        } catch (InvalidTaskDataException | DuplicateTaskException e) {
            System.out.println("Erro ao cadastrar tarefa: " + e.getMessage());
        }


        try {
            service.cadastrar(tarefa1);
        } catch (InvalidTaskDataException | DuplicateTaskException e) {
            System.out.println("Erro ao cadastrar tarefa: " + e.getMessage());
         }

        try {
            service.buscarObrigatoriaPorId(26L);
        } catch (TaskNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }


    }

    public static <T> void imprimirLista(String titulo, List<T> lista){
        System.out.println();
        System.out.println(titulo);

        for (T item: lista){
            System.out.println(item);
        }
    }



}

/*
        System.out.println("RELATÓRIO");
        System.out.println("TOTAL DE TAREFAS: " + repositorio.listarTodas().size());
        System.out.println("PENDENTES: " + service.filtrarPorStatus(Status.PENDENTE).size());
        System.out.println("EM ANDAMENTO: " + service.filtrarPorStatus(Status.EM_ANDAMENTO).size());
        System.out.println("CONCLUIDAS: " + service.filtrarPorStatus(Status.CONCLUIDO).size());
        System.out.println("QUANTIDADE PRO PRIORIDADE: " + service.contePorPrioridade());
        System.out.println("TAREFA MAIS URGENTE");
        System.out.println(service.buscarMaisUrgente());
        System.out.println();
        System.out.println("TAREFAS POR STATUS:");
        for (Map.Entry<Status, List<Task>> listarSatus: service.listarTarefasPorStatus().entrySet()){
            System.out.println(listarSatus.getKey());
            for (Task task: listarSatus.getValue()){
                System.out.println(task);
            }
        }

        System.out.println();

        try {
            Task task = repositorio.buscarPorId(100L)
                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
            System.out.println(task);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
*/

/*
        imprimirLista("Listar",service.buscarPorTitulo("aprender"));



        repositorio.buscaPorId(1L).ifPresent(System.out::println);
        System.out.println();
        Task taskPadrao = repositorio.buscaPorId(7L).orElse(tarefaPadrao);
        System.out.println(taskPadrao);
        System.out.println();

        try {
            Task task = repositorio.buscaPorId(100L)
                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
            System.out.println(task);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
*/

/*
        Map<Status, Long> contePorStatus = service.contePorStatus();
        Map<Prioridade, Long> contePorPrioridade = service.contePorPrioridade();

        System.out.println("Pendentes: " + contePorStatus.getOrDefault(Status.PENDENTE, 0L));
        System.out.println("Concluídas: " + contePorStatus.getOrDefault(Status.CONCLUIDO, 0L));

        System.out.println("Alta: " + contePorPrioridade.getOrDefault(Prioridade.ALTA, 0L));
        System.out.println("Média: " + contePorPrioridade.getOrDefault(Prioridade.MEDIA, 0L));
        System.out.println("Baixa: " + contePorPrioridade.getOrDefault(Prioridade.BAIXA, 0L));

        imprimirLista("BUSCAR PALAVRA DENTRO DO TITULO",service.tarefaComPalavraDentroDoTitulo("Aprender"));


        imprimirLista("LISTAR TITULOS",service.listarTitulos());
        imprimirLista("LISTAR DESCRIÇÃO",service.listarDescricoes());
        imprimirLista("LISTAR TITULOS EM CAIXA ALTA",service.listarTitulosEmCaixaAlta());
        imprimirLista("LISTAR RESUMO FORMATADO",service.listarResumosFormatados());


        Map<Status, List<Task>> agrupadoPorStatus = service.tarefasPorStatus();

        for (Map.Entry<Status, List<Task>> entrada: agrupadoPorStatus.entrySet()){
            System.out.println("Status: " + entrada.getKey());
            for (Task task: entrada.getValue()){
                System.out.println(task);
            }
        }

        System.out.println();

        for (Map.Entry<Status, Long> entrada: contePorStatus.entrySet()){
            System.out.println("Status: " + entrada.getKey() + " - " + entrada.getValue());
        }

        System.out.println();
        Map<Prioridade, List<Task>> agrupadoPorPrioridade = service.tarefasPorPrioridade();

        for (Map.Entry<Prioridade, List<Task>> entrada: agrupadoPorPrioridade.entrySet()){
            System.out.println("Prioridade: " + entrada.getKey());
            for (Task task: entrada.getValue()){
                System.out.println(task);
            }
        }

        System.out.println();

        for (Map.Entry<Prioridade, Long> entrada: contePorPrioridade.entrySet()){
            System.out.println("Prioridade: " + entrada.getKey() + " - " + entrada.getValue());
        }

*/

/*

 System.out.println("TAREFAS CONCLUIDA");
        tarefas.stream()
                .filter(t -> t.getDataLimite().isBefore(hoje))
                .map(Task::getTitulo)
                .forEach(System.out::println);

        System.out.println("");
        System.out.println("TITULOS POR STREAM");
        tarefas.stream()
                .map(t -> t.getTitulo() + " - " + t.getPrioridade() + " - " + t.getStatus())
                .forEach(System.out::println);
* System.out.println("TITULOS POR FOR");
        for (Task tarefa:
             tarefas) {
            System.out.println(tarefa.getTitulo());
        }

        System.out.println("");
        System.out.println("TITULOS POR STREAM");
        tarefas.stream()
                .map(Task::getTitulo)
                .forEach(System.out::println);

        System.out.println("");
        System.out.println("CONTAR TAREFAS SEM STREAM");
        long totalSemStream = tarefas.size();
        System.out.println("Total de tarefas SEM STREAM "+totalSemStream);

        System.out.println("");
        System.out.println("CONTAR TAREFAS COM STREAM");
        long total = tarefas.stream().count();
        System.out.println("Total de tarefas cadastrada "+total);

        System.out.println("");*/

/*
USO DO COMPARATOR
*System.out.println("ANTES DA ORDENACAO");
        for (Task listaTask: tarefas) {
            System.out.println(listaTask);
        }

        LocalDate hoje = LocalDate.now();
        Collections.sort(tarefas, new TaskPorDataComparator());

        System.out.println("DEPOIS");
        for (Task listaTask: tarefas) {
            if(listaTask.getDataLimite().isBefore(hoje)){
                System.out.println("ATRASADO");
                System.out.println(listaTask);}
            if(listaTask.getDataLimite().isAfter(hoje)){
                System.out.println("FUTURA");
                System.out.println(listaTask);}
*
*
*
* List<String> nomes = new ArrayList<>();

        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("João");

        System.out.println(nomes);

//-------------List Codigo-----------------
        System.out.println("LIST CODIGO");
        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto("Teclado", 120.0));
        produtos.add(new Produto("Mouse", 80.0));
        produtos.add(new Produto("Monitor", 900.0));

        for (Produto produto : produtos) {
            System.out.println(produto);
        }

//-------------------SET CODIGO-------------------------
        System.out.println("SET CODIGO");
        Set<SetProduto> setprodutos = new HashSet<>();

        setprodutos.add(new SetProduto("P001", "Teclas"));
        setprodutos.add(new SetProduto("P001", "Teclas Mecânico"));
        setprodutos.add(new SetProduto("P002", "Mouses"));

        System.out.println(setprodutos.size());

        for (SetProduto setproduto : setprodutos) {
            System.out.println(setproduto);

        }

List<Task> tarefas = new ArrayList<>();

        tarefas.add(new Task(
                1L,
                "Trabalhar List",
                "Aprender listas",
                Status.PENDENTE,
                Prioridade.ALTA,
                LocalDate.of(2026,5,8)));
        tarefas.add(new Task(
                2L,
                "Preparar Set",
                "Aprender conjuntos",
                Status.EM_ANDAMENTO,
                Prioridade.MEDIA,
                LocalDate.of(2026,5,6)));
        tarefas.add(new Task(3L,
                "Estudar Map",
                "Aprender mapeamentos",
                Status.PENDENTE,
                Prioridade.BAIXA,
                LocalDate.of(2026,4,30)));
        tarefas.add(new Task(
                4L,
                "Criar enum",
                "Treinar Status e Prioridade",
                Status.CONCLUIDO,
                Prioridade.MINIMA,
                LocalDate.of(2026,4,25)));
        tarefas.add(new Task(
                5L,
                "Analisar toString",
                "Melhorar impressão de objetos",
                Status.PENDENTE,
                Prioridade.URGENTE,
                LocalDate.of(2026,5,10)));

*/