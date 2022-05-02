package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;

    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;

    }



    public void inicial(Scanner scanner) {

        while (system) {

            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");
            System.out.println("3 - Busca funcionario data contratacao");
            System.out.println("4 - Pesquisa funcionario salario");

            int acao = scanner.nextInt();

            switch (acao) {

                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorDataContratacao(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    //CONSULTA POR NOME SEM USAR NENHUM COMANDO SQL E NEM JPQL
    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual o nome deseja pesquisar");
        String nome = scanner.next();
        List<Funcionario> list = this.funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);

    }

    private void buscaFuncionarioNomeSalarioMaiorDataContratacao(Scanner scanner) {

        System.out.println("Qual o nome deseja pesquisar?");
        String nome = scanner.next();

        System.out.println("Qual o salario deseja pesquisar?");
        Double salario = scanner.nextDouble();

        System.out.println("Quala a data contratacao deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formater);

        List<Funcionario> funcionarios = this.funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        funcionarios.forEach(System.out::println);

    }

    private void buscaFuncionarioDataContratacao(Scanner scanner) {
        System.out.println("Qual a data contratacao deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formater);
        List<Funcionario> funcionarios = this.funcionarioRepository.findDataContratacaoMaior(localDate);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> list = this.funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario id: " + f.getId()
        + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
    }

    //DICA! O Spring data consegue coonverter a @Query para a interface de Projecao

}
