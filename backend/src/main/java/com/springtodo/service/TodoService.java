package com.springtodo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtodo.model.Todo;
import com.springtodo.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;

	public List<Todo> getAllTodos(){
		return todoRepository.findAll();
	}
	
	public Todo addTodo(String cont){
		return todoRepository.save(new Todo(cont, false));
	}
	
	@Transactional
	public void delete(Long id){
		todoRepository.delete(id);
	}

	public void complete(Long id) {
		Todo t = todoRepository.findOne(id);
		t.setCompleted(true);
		todoRepository.save(t);
	}

	public void uncomplete(Long id) {
		Todo t = todoRepository.findOne(id);
		t.setCompleted(false);
		todoRepository.save(t);
	}
	
	public void edit(Long id, String cont){
		Todo t = todoRepository.findOne(id);
		t.setCont(cont);
		t.setCompleted(false);
		todoRepository.save(t);
	}

}
