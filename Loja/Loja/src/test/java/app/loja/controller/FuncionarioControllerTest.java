package app.loja.controller;

import app.loja.entity.Funcionario;
import app.loja.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc

public class FuncionarioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FuncionarioController funcionarioController;

    @MockBean
    FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setup(){

        List<Funcionario> lista = new ArrayList<>();

        lista.add(new Funcionario(1,"José Alves",18,2559874,null));
        lista.add(new Funcionario(2,"Daniel Fraga",19,9874568,null));
        when(this.funcionarioRepository.findAll()).thenReturn(lista);
        when(this.funcionarioRepository.save(Mockito.any())).thenReturn(new Funcionario(3,"Alfonso Davis",25,98756417,null));
        when(this.funcionarioRepository.findById(Mockito.any())).thenReturn(Optional.of(new Funcionario(4,"Alfonso Davis",25,98756417,null)));
    }

    @Test
    void cenario01(){

        ResponseEntity<List<Funcionario>> response = this.funcionarioController.listAll();

        assertEquals(2,response.getBody().size());
    }

    @Test
    void TestMetodoSave(){

        Funcionario funcionario = new Funcionario(5,"John Jones",25,97845681,null);

        ResponseEntity<String> response = this.funcionarioController.save(funcionario);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        System.out.println("Funcionário Salvo: " + funcionario.getNome());
    }

    @Test
    void TestMetodoUpdate(){

        Funcionario funcionario = new Funcionario(6,"Oliveira filho",35,9878877,null);

        ResponseEntity<String> response = this.funcionarioController.update(funcionario.getIdFuncionario(),funcionario);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Cadastro Alterado: " + funcionario.getNome());
    }

    @Test
    void TestMetodoFindById(){

        Funcionario funcionario = new Funcionario(6,"JOnes Jones",34,9785546,null);

        long id = funcionario.getIdade();

        ResponseEntity<Funcionario> response = this.funcionarioController.findById(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id: " + funcionario.getIdFuncionario() + " -> Nome: " + funcionario.getNome());
    }

    @Test
    void TestDeleteById(){

        Funcionario funcionario = new Funcionario(7,"Almeida Campos",35,75498732,null);

        long id = funcionario.getIdFuncionario();

        ResponseEntity<String> response = this.funcionarioController.delete(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id: " + funcionario.getIdFuncionario() + " -> Funcionário excluido: " + funcionario.getNome());
    }

    @Test
    void TestFindByNome(){

        Funcionario funcionario = new Funcionario(8,"Karla Perez",58,97668898,null);

        String nome = funcionario.getNome();

        ResponseEntity<List<Funcionario>> response = this.funcionarioController.findByNome(nome);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Funcionário: " + nome);
    }

    @Test
    void TestFindByIdade(){

        Funcionario funcionario = new Funcionario(9,"Joanesburgo",26,7899997,null);

        Integer idade = funcionario.getIdade();

        ResponseEntity<List<Funcionario>> response = this.funcionarioController.findByIdade(idade);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Idade: " + idade);
    }

    @Test
    void TestFindByMatricula(){

        Funcionario funcionario = new Funcionario(10,"Mike Tyson",54,988888878,null);

        Integer matricula = funcionario.getMatricula();

        ResponseEntity<List<Funcionario>> response = this.funcionarioController.findByMatricula(matricula);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Número de Matricula: " + matricula);
    }

}
