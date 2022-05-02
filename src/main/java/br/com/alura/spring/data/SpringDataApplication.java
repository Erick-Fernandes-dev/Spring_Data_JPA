package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCargoService crudCargoService;
    private final CrudFuncionarioService crudFuncionarioService;
    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
    private final RelatoriosService relatoriosService;
    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
    private Boolean system = true;


    //injeção de dependencia
    public SpringDataApplication(CrudCargoService crudCargoService,
                                 CrudFuncionarioService crudFuncionarioService,
                                 CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
                                 RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {

        this.crudCargoService = crudCargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual acao voce quer executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionario");
            System.out.println("3 - Unidade de trabalho");
            System.out.println("4 - Relatorios");
            System.out.println("5 - Relatorio dinamico");


            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    this.crudCargoService.inicial(scanner);
                case 2:
                    this.crudFuncionarioService.inicial(scanner);
                case 3:
                    this.crudUnidadeTrabalhoService.inicial(scanner);
                case  4:
                    this.relatoriosService.inicial(scanner);
                case 5:
                    this.relatorioFuncionarioDinamico.inicial(scanner);
                default:
                    system = false;
            }

        }
//        Cargo cargo = new Cargo();
//        cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");

    }
}
