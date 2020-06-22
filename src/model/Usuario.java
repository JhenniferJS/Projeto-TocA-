/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe que contem informações sobre o usuario
 * @author Jhennifer
 */
public class Usuario {
    private String cpf;
    private String nome;
    private String sexo;

    public Usuario(String cpf, String nome, String sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

}
