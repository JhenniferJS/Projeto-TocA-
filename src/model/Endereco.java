/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe que contém os dados do endereço do usuario
 * @author Jhennifer
 */
public class Endereco {
    private String pais;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;
    private String complemento;
    private int numCasa;

    //Construtor para enderecos que possuem complemento
    public Endereco(String pais, String uf, String cidade, String bairro, String rua, String complemento, int numCasa) {
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.complemento = complemento;
        this.numCasa = numCasa;
    }

    //Construtor para enderecos que nao possuem complemento
    public Endereco(String pais, String uf, String cidade, String bairro, String rua, int numCasa) {
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numCasa = numCasa;
    }

    public String getPais() {
        return pais;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getNumCasa() {
        return numCasa;
    }
    
}
