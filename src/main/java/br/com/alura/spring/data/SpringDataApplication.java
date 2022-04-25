package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private CrudCargoService crudCargoService;
    private CrudFuncionarioService crudFuncionarioService;
    private CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
    private Boolean system = true;


    //injeção de dependencia
    public SpringDataApplication(CrudCargoService crudCargoService,
                                 CrudFuncionarioService crudFuncionarioService,
                                 CrudUnidadeTrabalhoService crudUnidadeTrabalhoService) {

        this.crudCargoService = crudCargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
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


            int action = scanner.nextInt();

            if (action == 1) {
                crudCargoService.inicial(scanner);
            } else if (action == 2) {
                crudFuncionarioService.inicial(scanner);
            } else if (action == 3) {
                crudUnidadeTrabalhoService.inicial(scanner);
            }
            else {
                system = false;
            }
        }
//        Cargo cargo = new Cargo();
//        cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");

    }
}
