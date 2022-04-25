package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }


    public void inicial(Scanner scanner) {

        while (system) {

            System.out.println("Qual acao de cargo deseja executar");
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

        //salvar(scanner);
//        atualizar(scanner);

    }

    //SALVAR
    public void salvar(Scanner scanner) {
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    // ATUALIZAR / ALTERAR
    public void atualizar(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizadop");
    }

    //VIZUALIZAR
    public void visualizar() {

        Iterable<Cargo> cargos = this.cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));

    }

    public void deletar(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        this.cargoRepository.deleteById(id);
        System.out.println("deletado");
    }









}
