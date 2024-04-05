package app.loja.service;

import app.loja.entity.Cliente;
import app.loja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save(Cliente cliente) {

        this.clienteRepository.save(cliente);
        return cliente.getNome() + "Salvo";
    }

    public String update(long id, Cliente cliente) {
        cliente.setIdCliente(id);
        this.clienteRepository.save(cliente);

        return "Cliente alterado!";
    }

    public List<Cliente> listAll() {

        return this.clienteRepository.findAll();

    }

    public Cliente findById(long id) {

        Cliente cliente = this.clienteRepository.findById(id).get();

        return cliente;

    }

    public String delete(long id) {

        this.clienteRepository.deleteById(id);

        return "Cliente deletado!";
    }

    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public List<Cliente> findByIdade(int idade) {
        return clienteRepository.findByIdade(idade);
    }

    public List<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
}