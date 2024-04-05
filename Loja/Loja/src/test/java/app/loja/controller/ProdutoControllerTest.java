package app.loja.controller;

import app.loja.entity.Produto;
import app.loja.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ProdutoControllerTest {

    @Autowired
    ProdutoController produtoController;

    @MockBean
    ProdutoRepository produtoRepository;

    @BeforeEach
    void setup(){
        List<Produto> lista = new ArrayList<>();
        lista.add(new Produto(1,"Rolex",1500.0,"Relógio"));
        lista.add(new Produto(2,"Calça Jeans",80.0,"Moda"));
        when(this.produtoRepository.findAll()).thenReturn(lista);
        when(this.produtoRepository.save(Mockito.any())).thenReturn(new Produto(1,"Rolex",1500.0,"Relógio"));
        when(this.produtoRepository.findById(Mockito.any())).thenReturn(Optional.of(new Produto(1,"Rolex",1500.0,"Relógio")));
    }


    @Test
    void cenario01(){
        ResponseEntity<List<Produto>> response = this.produtoController.listAll();

        assertEquals(2, response.getBody().size());
    }

    @Test
    void testMetodoSave(){

        Produto produto = new Produto(3, "Calça Sarja", 45.0, "Moda");

        ResponseEntity<String> response = this.produtoController.save(produto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        System.out.println("Salvo: " + produto.getNome());

    }

    @Test
    void testMetodoUpdate(){

        Produto produto = new Produto(4,"Regata Social",55.5,"Moda");

        ResponseEntity<String> response = this.produtoController.update(produto.getId(),produto);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Produto Alterado: " + produto.getNome());
    }

    @Test
    void testMetodoFindById(){

        Produto produto = new Produto(5, "Calça Moletom", 40.0, "Moda");

        long id = produto.getId();

        ResponseEntity<Produto> response = this.produtoController.findById(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id: " + produto.getId() + " -> Nome: " + produto.getNome());
    }

    @Test
    void testDeleteById(){

        Produto produto = new Produto(6, "Calça Moletom", 40.0, "Moda");

        long id = produto.getId();

        ResponseEntity<String> response = this.produtoController.delete(id);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Id: " + produto.getId() + " -> Produto Excluido: " + produto.getNome());
    }

    @Test
    void TestFindByNome(){

        Produto produto = new Produto(7, "Calça", 100.0, "Moda");

        String findNome = produto.getNome();

        ResponseEntity<List<Produto>> response = this.produtoController.findByNome(findNome);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Nome do Produto: " + findNome);

    }

    @Test
    void TestFindByValor(){

        Produto produto = new Produto(8, "Vestido", 100.0, "Moda");

        Double valor = produto.getValor();

        ResponseEntity<List<Produto>> response = this.produtoController.findByValor(valor);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Valor: " + "R$ " + valor);
    }

    @Test
    void TestFindByCategoria(){

        Produto produto = new Produto(9, "Nike Sb", 200.0, "Tenis");

        String categoria = produto.getCategoria();

        ResponseEntity<List<Produto>> response = this.produtoController.findByCategoria(categoria);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("Produto: " + produto.getNome() + " -> Categoria: " + categoria);
    }

}