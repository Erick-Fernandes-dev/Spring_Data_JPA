package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;

    }



    public void inicial(Scanner scanner) {

        while (system) {

            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - sair");
            System.out.println("1 - Busca funcionario nome");

            int acao = scanner.nextInt();

            switch (acao) {

                case 1:
                    buscaFuncionarioNome(scanner);
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

}
