package com.labdesoft.roteiro01.entity;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

@Entity
@Data
@Schema(description = "Todos os detalhes sobre uma tarefa. ")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Boolean completed;
    
    private Type type;

    private Priority priority;

    @FutureOrPresent(message = "Data superior a data de hoje")
    private LocalDate date;

    private Integer days;

    public Task(String description, Type type){
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" + completed + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    public Priority getPriority(){
        return priority;
    }

    public void setPriority(Priority priority){
        this.priority = priority;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public Integer getDays(){
        return days;
    }

    public void setDays(Integer days){
        this.days = days;
    }
       
}
