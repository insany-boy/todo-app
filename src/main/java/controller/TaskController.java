/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;


/**
 *
 * @author kauan
 */
public class TaskController {
    
    @SuppressWarnings("empty-statement")
    public void save(Task task) throws SQLException{
    
        String sql = "INSERT INTO tasks (idproject, " 
                + "name, "
                + "description, "
                + "completed, "
                + "notes, "
                + "deadline, "
                + "createdAt, "
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
          try { 
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             statement.setInt(1, task.getIdProject());
             statement.setString(2, task.getName());
             statement.setString(3, task.getDescription());
             statement.setBoolean(4, task.isIsCompleted());
             statement.setString(5, task.getNotes());
             statement.setDate(6, new Date(task.getDeadline().getTime()));
             statement.setDate(7, new Date(task.getCreatedAt().getTime()));
             statement.setDate(8, new Date(task.getUpdatedAt().getTime()));

             statement.execute();
          } catch (Exception ex) {
              throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
          } finally {
              ConnectionFactory.closeConnection(connection);                          
          }
        
    }
    
    public void update(Task task){
       String sql = "UPDATE tasks SET " 
               + "idProject = ?, "
               + "name = ?, "
               + "description = ?, "
               + "notes = ?, "
               + "completed = ?, "
               + "deadline = ?, "
               + "createdAt = ?, "
               + "updatedAt = ?"
               + "WHERE id = ?";
               //+"WHERE id = ?";
          
         Connection connection = null;
         PreparedStatement statement = null;
         
         try {
             //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando  a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores no statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date (task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            /*statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());*/
            
            //Executando a query
            statement.execute();
           
         
         
            
         } catch (Exception ex){
             throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage(), ex);
         }
    }
    
    public void removeById(int taskId) {
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {    
            // Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            // Preparando a Query
            statement = connection.prepareStatement(sql);
            
            // Setando os valores
            statement.setInt(1, taskId);
            
            //Executando a query
            statement.execute();                    
        } catch (SQLException e){
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        // Lista de tarefas que sera desenvolvida quando a chamada do metodo acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            // Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //Setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            // Enquanto houverem valores a serem pecorridos no meu resultSet
            while(resultSet.next()){
            
               Task task = new Task();
               task.setId(resultSet.getInt("id"));
               task.setIdProject(resultSet.getInt("IdProject"));
               task.setName(resultSet.getString("name"));
               task.setDescription(resultSet.getString("description"));
               task.setNotes(resultSet.getString("notes"));
               task.setIsCompleted(resultSet.getBoolean("completed"));
               task.setDeadline(resultSet.getDate("deadline"));
               task.setCreatedAt(resultSet.getDate("createdAt"));
               task.setUpdatedAt(resultSet.getDate("updatedAt"));
               
               tasks.add(task);
            }
            
        } catch (Exception ex){
            throw new RuntimeException("Erro ao inserir a tarefa ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        return tasks; // Lista de tarefas que foi criada e carregada do banco de dados
    }
}
