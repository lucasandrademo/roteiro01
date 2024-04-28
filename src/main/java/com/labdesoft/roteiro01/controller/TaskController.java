package com.labdesoft.roteiro01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labdesoft.roteiro01.service.TaskService;
import com.labdesoft.roteiro01.entity.Task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    @Operation(summary = "Lista todas as tarefas da lista")
    public ResponseEntity<List<Task>> listAll() {
        try{
            List<Task> allTasks = taskService.findAllTask();
            if (allTasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Task>>(allTasks, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "trás dados de uma tarefa específica")
    public ResponseEntity<Task> listOne(@PathVariable Long id) {
        try{
            Task task = taskService.findTaskById(id);;
            if (task == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Cria uma nova tarefa")
	@PostMapping(path = "/")
	public ResponseEntity<Object> create(@RequestBody Task task) {
		try {
            if(
				task.getDescription() == null ||
				task.getType() == null
			){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
			return new ResponseEntity<Object>(taskService.create(task), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @Operation(summary = "Atualiza uma tarefa já existente")
	@PutMapping(path = "/")
	private ResponseEntity<Object> update(@RequestBody Task task) {
		try {
            if(
				task.getDescription() == null ||
				task.getType() == null ||
				task.getPriority() == null ||
				task.getId() == null
			){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
			return new ResponseEntity<Object>(taskService.update(task), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @Operation(summary = "Delete uma tarefa já existente")
	@DeleteMapping(path = "/{id}")
	private ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			taskService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @Operation(summary = "finaliza uma tarefa ou retira o status de finalizada")
	@PutMapping(path = "/done/{id}")
	private ResponseEntity<Object> done(@PathVariable Long id) {
		try {
            if(id == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Object>(taskService.complete(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
