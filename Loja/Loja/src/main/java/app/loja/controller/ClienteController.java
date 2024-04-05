package app.loja.controller;

import app.loja.entity.Cliente;
import app.loja.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/save") //Endpoint para utilizar o método save;
    public ResponseEntity<String> save(@RequestBody Cliente cliente) {
        // E retorna um ResponseEntity contendo uma string
        try {

            String salvar = this.clienteService.save(cliente);
            return new ResponseEntity<>(salvar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}") //Endpoint para utilizar o método update
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Cliente cliente) {//Criando o método update que espera um objeto do tipo cliente no corpo da requisição
        //E retorna um ResponseEntity contendo uma string

        try {

            String alterar = this.clienteService.update(id, cliente); //String alterar está recebendo o método update que se encontra dentro de cliente service e recebendo como argumento
            // o id do cliente e um objeto do tipo cliente
            return new ResponseEntity<>(alterar, HttpStatus.OK);  //Se a requisição der certo ele retorna um status HTTP ok
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Caso não ele recebe um status HTTP BAD_REQUEST
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Cliente>> listAll() {
        try {

            List<Cliente> lista = this.clienteService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable long id) {

        try {

            Cliente cliente = this.clienteService.findById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        try {

            String deletar = this.clienteService.delete(id);
            return new ResponseEntity<>(deletar, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Cliente>> findByNome(@RequestParam String nome) {

        try {
            List<Cliente> cliente = this.clienteService.findByNome(nome);
            return new ResponseEntity<>(cliente, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByIdade")
    public ResponseEntity<List<Cliente>> findByIdade(@RequestParam int idade) {

        try {
            List<Cliente> cliente = this.clienteService.findByIdade(idade);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByCpf")
    public ResponseEntity<List<Cliente>> findByCpf(@RequestParam String cpf) {

        try {

            List<Cliente> cliente = this.clienteService.findByCpf(cpf);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}