/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe que contém as informações sobre as músicas
 * @author Jhennifer
 */
public class Musica {
    private String nome;
    private String duracao;
    private String artista;
    private String genero;
    private String album;

    //Construtor para as musicas que tiverem seus albuns especificados
    public Musica(String nome, String duracao, String artista, String genero, String album) {
        this.nome = nome;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
        this.album = album;
    }

   //Construtor para as musicas que nao tiverem seus albuns especificados
    public Musica(String nome, String duracao, String artista, String genero) {
        this.nome = nome;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public String getAlbum() {
        return album;
    }   
}
