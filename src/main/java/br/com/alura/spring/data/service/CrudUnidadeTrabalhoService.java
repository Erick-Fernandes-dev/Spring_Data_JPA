package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {

            System.out.println("Qual acao de unidade de trabalho deseja executar");
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
        System.out.println("Descricao");
        String unidade = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(unidade);
        this.unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Descricao");
        String unidade = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(unidade);
        this.unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Atualizada");

    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidades = this.unidadeTrabalhoRepository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade));
    }

    private void deletar(Scanner scanner) {

        System.out.println("Id");
        int id = scanner.nextInt();
        this.unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Deletado");

    }
}