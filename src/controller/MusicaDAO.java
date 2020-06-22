/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Musica;

/**
 * Classe que interage com a tabela Musica do Banco de Dados
 * @author Jhennifer
 */
public class MusicaDAO {

    //objeto responsavel por preparar as consultas dinamicas
    PreparedStatement pst;
    //objeto responsavel por preparar as consultas estaticas
    Statement st;
    //objeto responsavel por referenciar a tabela resultante da busca
    ResultSet rs;

    boolean sucesso = false;

    //Inserir dados
    public boolean inserirMusica(Musica novaMusica) {
        //conectar ao banco de dados
        Connection con = AccessDataBase.connectToDb();
        //comando em SQL
        String sql;
        if (novaMusica.getAlbum() == null) {
            sql = "INSERT INTO Musica(nome,duracao,artista,genero) values (?,?,?,?)";
        } else {
            sql = "INSERT INTO Musica(nome,duracao,artista,genero,album) values (?,?,?,?,?)";
        }
        //comando recebe parametros - consulta dinamica
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novaMusica.getNome()); 
            pst.setString(2, novaMusica.getDuracao()); 
            pst.setString(3, novaMusica.getArtista()); 
            pst.setString(4, novaMusica.getGenero()); 
            if (novaMusica.getAlbum() != null) {
                pst.setString(5, novaMusica.getAlbum()); 
            }
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            try {
                //Encerra a conexao com banco
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Deletar uma musica 
    public boolean deletarMusica(String nomeMusica, String nomeArtista) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "DELETE FROM Musica WHERE Musica.nome = ? AND Musica.artista = ?";
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, nomeMusica);
            pst.setString(2, nomeArtista);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                //Encerra a conexao com banco
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Buscar todas a musicas
    public ArrayList<Musica> buscarMusicaSemFiltro() {
        ArrayList<Musica> listaDeMusica = new ArrayList<>();
        Connection con = AccessDataBase.connectToDb();

        //COMANDO EM SQL
        String sql = "SELECT * FROM Musica";
        //O comando nao recebe parametros -> consulta estatica (st)
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                  Musica musica = new Musica(rs.getString("Nome"), rs.getString("Duracao"), rs.getString("Artista"), rs.getString("Genero"), rs.getString("Album"));  
                  listaDeMusica.add(musica);
                  
            }
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } finally {
            try {
                //Encerra a conexao com banco
                con.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return listaDeMusica;
    }
    //Buscar as musicas de cada playlist
    public ArrayList<Musica> buscarMusicaDePlaylist(String nomePlaylist) {
        ArrayList<Musica> listaDeMusica = new ArrayList<>();
        Connection con = AccessDataBase.connectToDb();

        //COMANDO EM SQL
        String sql = "SELECT * FROM Musica,Playlist,Playlist_has_Musica WHERE idMusica = Musica_idMusica AND idPlaylist = Playlist_idPlaylist AND Playlist.nome = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomePlaylist);
            rs = pst.executeQuery();
            while (rs.next()) {
                  Musica musica = new Musica(rs.getString("Nome"), rs.getString("Duracao"), rs.getString("Artista"), rs.getString("Genero"), rs.getString("Album"));  
                  listaDeMusica.add(musica);
                  
            }
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } finally {
            try {
                //Encerra a conexao com banco
                con.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return listaDeMusica;
    }
    
    //busca a existencia de uma musica específica
    public boolean buscarMusica(String nomeMusica, String nomeArtista) {
        boolean achou = false;
        Connection con = AccessDataBase.connectToDb();
        //comando SQL para buscar : seleciona a senha q esta armazenada na tabela Conta 
        String sql = "SELECT nome FROM Musica WHERE nome = ? AND artista = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeMusica);
            pst.setString(2, nomeArtista);
            rs = pst.executeQuery();//Executa a consulta SQL e retorna um objeto
            
            if(rs.next()){
                achou = true;
            }

        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            achou = false;
        } finally {
            try {
                //Encerra a conexao com banco
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return achou;
    }
}
