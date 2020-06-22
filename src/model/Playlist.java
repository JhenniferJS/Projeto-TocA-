/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 * Classe que contem as informações sobre as playlists
 * @author Jhennifer
 */
public class Playlist {
    private String nome;
    private int numMusicas = 0; //numero de musica ao criar a playlist

    //Construtor para playlists que receberem apenas nome como parametro
    public Playlist(String nome) {
        this.nome = nome;
    }

    //Construtor para playlists que receberem nome e numero de musicas como parametro
    public Playlist(String nome, int numMusicas) {
        this.nome = nome;
        this.numMusicas = numMusicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumMusicas() {
        return numMusicas;
    }
}
