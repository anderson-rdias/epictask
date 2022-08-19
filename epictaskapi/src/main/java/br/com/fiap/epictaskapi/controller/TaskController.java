package br.com.fiap.epictaskapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService service;
    
    @GetMapping
    public List<Task> index(){
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid Task task){
        service.save(task);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        var optional = service.getById(id);

        if (optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> show(@PathVariable Long id){
        //System.out.println("buscando tarefa " + id);
        // Optional<Task> task = service.getById(id);

        // if (task.isEmpty())
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        // return ResponseEntity.ok(task.get());  
        
        return ResponseEntity.of(service.getById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody @Valid Task newTask){
        //buscar a tarefa no bd
        var optional = service.getById(id);

        //verificar se existe tarefa com esse id
        if (optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        //atualizar os dados
        var task = optional.get();
        // task.setTitle(newTask.getTitle());
        // task.setDescription(newTask.getDescription());
        // task.setScore(newTask.getScore());
        // task.setStatus(newTask.getStatus() );
        BeanUtils.copyProperties(newTask, task);
        task.setId(id);

        //salvar no bd
        service.save(task);

        return ResponseEntity.ok(task);
    }
}
