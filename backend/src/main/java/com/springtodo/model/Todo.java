package com.springtodo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String cont;
	
	private boolean completed;
	
	public Todo(){}

	public Todo(String cont, boolean completed) {
		this.cont = cont;
		this.completed = completed;
	}
	
	public long getId() {
		return id;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
}
