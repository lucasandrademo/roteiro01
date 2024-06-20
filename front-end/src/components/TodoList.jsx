import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { Card, CardContent, Typography, IconButton } from "@mui/material";
import { EditTodoForm } from "./EditTodoForm";
import { DeleteTodoForm } from "./DeleteTodoForm";

export const TodoList = ({ task, deleteTodo, editTodo, saveTodo }) => {
  const [isEditOpen, setIsEditOpen] = useState(false);
  const [isDeleteOpen, setIsDeleteOpen] = useState(false);
  const [currentTask, setCurrentTask] = useState(null);

  const getType = () => {
    if (task.dueDate && task.dueDays && task.dueDays !== 0) return "Prazo";
    if (task.dueDate) return "Data";
    return "Livre";
  };

  const calculateDueDays = (dueDate) => {
    const currentDate = new Date();
    const dueDateObj = new Date(dueDate);
    const diffTime = dueDateObj - currentDate;
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  };

  const handleEditOpen = (task) => {
    setCurrentTask(task);
    setIsEditOpen(true);
  };

  const handleEditClose = () => {
    setCurrentTask(null);
    setIsEditOpen(false);
  };

  const handleDeleteOpen = () => setIsDeleteOpen(true);
  const handleDeleteClose = () => setIsDeleteOpen(false);

  const handleDeleteConfirm = async () => {
    await deleteTodo(task.id);
    handleDeleteClose();
  };

  return (
    <>
      <Card className="Todo" sx={{ marginBottom: 2 }}>
        <CardContent>
          <Typography variant="h5" component="div">
            {task.description}
          </Typography>
          <Typography color="textSecondary">
            Tipo: {getType()}
          </Typography>
          <Typography color="textSecondary">
            Tipo de Task: {task.type}
          </Typography>
          <Typography color="textSecondary">
            Prioridade: {task.priority}
          </Typography>
          {task.dueDate && (
            <Typography color="textSecondary">
              Data de Vencimento: {task.dueDate}
            </Typography>
          )}
          {task.dueDate && (
            <Typography color="textSecondary">
              Deadline: {calculateDueDays(task.dueDate)}
            </Typography>
          )}
          {task.dueDays && (
            <Typography color="textSecondary">
              Prazo: {task.dueDays}
            </Typography>
          )}
          <div>
            <IconButton onClick={() => handleEditOpen(task)}>
              <FontAwesomeIcon icon={faPenToSquare} />
            </IconButton>
            <IconButton onClick={handleDeleteOpen}>
              <FontAwesomeIcon icon={faTrash} />
            </IconButton>
          </div>
        </CardContent>
      </Card>
      {currentTask && (
        <EditTodoForm
          task={currentTask}
          saveTodo={saveTodo}
          open={isEditOpen}
          onClose={handleEditClose}
        />
      )}
      <DeleteTodoForm
        open={isDeleteOpen}
        onClose={handleDeleteClose}
        onConfirm={handleDeleteConfirm}
        taskTitle={task.description}
      />
    </>
  );
};
