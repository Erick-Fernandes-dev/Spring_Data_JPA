package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private CargoRepository cargoRepository;

    private Boolean system = true;

    public SpringDataApplication(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
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
        }

        Cargo cargo = new Cargo();
        cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");
        cargoRepository.save(cargo);

    }
}
