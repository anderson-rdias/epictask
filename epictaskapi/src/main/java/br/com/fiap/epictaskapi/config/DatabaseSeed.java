package br.com.fiap.epictaskapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    TaskRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // var lista = new ArrayList<Task>();
        // lista.add(task1);
        // lista.add(task2);
        repository.saveAll(List.of(
            new Task("BD", "Modelar as tabelas", 150, 0),
            new Task("Debug", "Modelar as tabelas", 20, 0),
            new Task("Cadastro", "Modelar as tabelas", 10, 0),
            new Task("Login", "Modelar as tabelas", 50, 0),
            new Task("Deploy", "Modelar as tabelas", 25, 0),
            new Task("Testes", "Modelar as tabelas", 80, 0),
            new Task("Bug", "Modelar as tabelas", 75, 0),
            new Task("Logout", "Modelar as tabelas", 5, 0),
            new Task("Internacionalização", "Modelar as tabelas", 15, 0),
            new Task("Prototipo", "Prototipar as telas do app", 20, 0)
        ));
    }
    
}
