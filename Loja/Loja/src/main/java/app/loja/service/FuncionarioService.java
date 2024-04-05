package app.loja.service;

import app.loja.entity.Funcionario;
import app.loja.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario) {

        this.funcionarioRepository.save(funcionario);
        return funcionario.getNome() + "Funcionário cadastrado com sucesso";

    }

    public String update(long id, Funcionario funcionario) {

        funcionario.setIdFuncionario(id);
        this.funcionarioRepository.save(funcionario);

        return "Funcionário alterado com sucesso";
    }

    public List<Funcionario> listAll() {

        return this.funcionarioRepository.findAll();
    }

    public Funcionario findById(long id) {

        Funcionario funcionario = this.funcionarioRepository.findById(id).get();

        return funcionario;
    }

    public String delete(long id) {

        this.funcionarioRepository.deleteById(id);
        return "Funcionário deletado com sucesso";

    }

    public List<Funcionario> findByNome(String nome) {

        return funcionarioRepository.findByNome(nome);
    }

    public List<Funcionario> findByIdade(int idade) {

        return funcionarioRepository.findByIdade(idade);

    }

    public List<Funcionario> findByMatricula(int matricula) {

        return funcionarioRepository.findByMatricula(matricula);
    }
}