package com.springtodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springtodo.model.Todo;
import com.springtodo.service.TodoService;

@RestController
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todos")
	public List<Todo> getAllTodos(){
		return todoService.getAllTodos();
	}
	
	@PostMapping("/create")
	public Object addTodo(@RequestBody String cont){
		if(cont.length() > 50 || cont.length() < 1)
			return null;
		return todoService.addTodo(cont);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable Long id){
		todoService.delete(id);
	}

	@PatchMapping("/complete/{id}")
	public void complete(@PathVariable Long id){
		todoService.complete(id);
	}
	
	@PatchMapping("/uncomplete/{id}")
	public void uncomplete(@PathVariable Long id){
		todoService.uncomplete(id);
	}
	
	@PutMapping("/edit/{id}")
	public String edit(@PathVariable Long id, @RequestBody String cont){
		if(cont.length() > 50 || cont.length() < 1)
			return "error";
		todoService.edit(id, cont);
		return null;
	}


}
