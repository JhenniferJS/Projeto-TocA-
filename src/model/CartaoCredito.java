/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe que contém os dados do cartão de crédito do usuario
 * @author Jhennifer
 */
public class CartaoCredito {
    private String titular;
    private String numCartao;
    private int codigo;

    public CartaoCredito(String titular, String numCartao, int codigo) {
        this.titular = titular;
        this.numCartao = numCartao;
        this.codigo = codigo;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public int getCodigo() {
        return codigo;
    }

}
