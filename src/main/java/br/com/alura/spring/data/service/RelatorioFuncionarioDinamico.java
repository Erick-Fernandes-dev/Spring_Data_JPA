package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Scanner;

public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    //  CONSULTA DINAMICA COM Specification
    public void inicial(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        List<Funcionario> funcionarios = funcionarioRepository
                .findAll(Specification.where(SpecificationFuncionario.nome(nome)));
    }



}