
package main;

import controller.ProjectController;
import controller.TaskController;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;
import java.util.ArrayList;

/**
 *
 * @author kauan
 */
public class Main {
    
    public static void main(String[] args) throws SQLException {
      
       ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto Teste");
        project.setDescription("description");
        projectController.save(project); 
        
       /* ProjectController projectController = new ProjectController();;
        
        Project project = new Project();
        project.setId(1);
        project.setName("Novo nome do projeto");
        project.setDescription("description");
       
        projectController.update(project);
        
        projectController.removeById(2); */
        List<Project> projects = projectController.getAll();
        System.out.println("Total de projetos = " + projects.size()); 
        
        TaskController taskController = new TaskController();
        
        Task task = new Task();
        task.setIdProject(2);
        task.setName("Criar as telas de aplicação"); 
        task.setDescription("Devem criar telas para cadastro");
        task.setNotes("Sem notas");
        task.setIsCompleted(false);
        task.setDeadline(new Date());
        
        taskController.save(task);
        
        task.setName("Alterar telas de aplicação");
        taskController.update(task);
        List<Task> tasks = taskController.getAll(0);
        System.out.println("Total de tarefas = " + tasks.size());
        
    }
}
