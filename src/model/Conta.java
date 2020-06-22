/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe que contém o login e senha do usuario.  
 * @author Jhennifer
 */
public class Conta {
    private String login; 
    private String senha; 
    
    public Conta(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}
