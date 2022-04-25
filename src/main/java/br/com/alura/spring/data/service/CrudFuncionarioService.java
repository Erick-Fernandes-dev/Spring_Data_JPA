package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final CargoRepository cargoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;


    public CrudFuncionarioService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.cargoRepository = cargoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }


    public void inicial(Scanner scanner) {


        while (system) {

            System.out.println("Qual acao de funcionario deseja executar");
            System.out.println("0 - sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int acao = scanner.nextInt();

            switch (acao) {

                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }

        }


    }
    private void salvar(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }

    private void atualizar(Scanner scanner) {
        System.out.println("id");
        Integer id = scanner.nextInt();

        System.out.println("Digite seu nome");
        String nome = scanner.next();

        System.out.println("Digite seu cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contratacao");
        String dataCOntratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataCOntratacao, formatter));
        Optional<Cargo> cargo = this.cargoRepository.findById(id);
        funcionario.setCargo(cargo.get());

        this.funcionarioRepository.save(funcionario);
        System.out.println("Alterado");


    }

    private void visualizar() {
        Iterable<Funcionario> visualizar = this.funcionarioRepository.findAll();
        visualizar.forEach(f -> System.out.println(f));
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        this.funcionarioRepository.deleteById(id);
        System.out.println("Deletado");

    }

}