/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para estabelecer conexão com o Banco de Dados
 * @author Jhennifer
 */
public class AccessDataBase {
    //Nome do banco de dados
    private static final String DATABASE = "Projeto";
    //URL: verificar qual a porta
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    //Usuario 
    private static final String USER = "root";
    //Senha
    private static final String PASSWORD = "siqueira";
    
    public static Connection connectToDb(){
        //objeto responsavel pela conexao com o servidor do banco de dados
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexao realizada com sucesso! ");
        }
        catch(SQLException ex){
            System.out.println("nao conectou");
            System.out.println("Erro: " + ex.getMessage());
        }
        return con;
    }
}
