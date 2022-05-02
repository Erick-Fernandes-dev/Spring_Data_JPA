package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

    //Derived query / QUERY METHOD
    List<Funcionario> findByNome(String nome);

    //USANDO JPQ PARA FAZER AS CONSULTAS
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

    //UTILIZNADO NATIVE QUERY
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM  funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();

    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

     /*/

        nativeQuery -> Também podemos usar SQL nativo para definir nossa consulta. Tudo o que precisamos fazer é definir
        o valor do atributo nativeQuery como true e definir a consulta SQL nativa no atributo value da anotação.

        OBS! Se usa nativeQuery e Query quando o método for muito grande e impossível de ler.
     */
}
