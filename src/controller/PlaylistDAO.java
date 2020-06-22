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
import java.util.ArrayList;
import model.Playlist;

/**
 * Classe que interage com a tabela Playlist do Banco de Dados
 * @author Jhennifer
 */
public class PlaylistDAO {

    //objeto responsavel por preparar as consultas dinamicas
    PreparedStatement pst;
    //objeto responsavel por referenciar a tabela resultante da busca
    ResultSet rs;
    boolean sucesso = false;

    //Inserir dados
    public boolean inserirPlaylist(Playlist novaPlaylist, String cpf) {
        //conectar ao banco de dados
        Connection con = AccessDataBase.connectToDb();
        //comando em SQL
        String sql = "INSERT INTO Playlist(nome,num_Musicas,Usuario_CPF) values (?,?,?)";
        //comando recebe parametros - consulta dinamica
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novaPlaylist.getNome()); // 1 se refere a primeira ?
            pst.setInt(2, novaPlaylist.getNumMusicas()); 
            pst.setString(3, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            try {
                //Encerra a conexao
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Atualizar numero de musicas da playlist 
    public boolean atualizaPlaylist(String NomePlaylist, String cpf) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "UPDATE projeto.Playlist SET Playlist.Num_Musicas = Num_Musicas + 1 WHERE Playlist.nome=? and Playlist.Usuario_CPF = ?" ;

        //O comando recebe parametros -> consulta dinamica (pst)
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, NomePlaylist);
            pst.setString(2, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            ex.printStackTrace();
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Deletar uma playlist 
    public boolean deletarPlaylist(String nomePlaylist, String cpf) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "DELETE FROM Playlist WHERE Playlist.nome=? and Usuario_CPF = ?";
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, nomePlaylist);
            pst.setString(2, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //busca a existencia de uma playlist
    public boolean buscarExistenciaDePlaylist(String nomePlaylist, String cpf) {
        boolean achou = false;
        Connection con = AccessDataBase.connectToDb();

        String sql = "SELECT * FROM Playlist WHERE Playlist.Nome= ? and Playlist.Usuario_CPF = ?";
        String aux = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomePlaylist);
            pst.setString(2, cpf);
            rs = pst.executeQuery();//Executa a consulta SQL e retorna um objeto
            if(rs.next()){
                achou = true;
            }
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return achou;
    }
    
    //Buscar as Playlists cadastradas 
     public ArrayList<Playlist> buscarPlaylistCadastradas(String cpf)  {
        ArrayList<Playlist> listaDePlaylist= new ArrayList<>();
        Connection con = AccessDataBase.connectToDb();

        //COMANDO EM SQL
        String sql = "SELECT nome, num_Musicas FROM Playlist WHERE Usuario_CPF = ? ";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
                Playlist playlistTemp = new Playlist(rs.getString("Nome"),rs.getInt("num_Musicas")); 
                listaDePlaylist.add(playlistTemp);
            }
            sucesso= true;
        }catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return listaDePlaylist;
    }
     
    public boolean addMusicaEmPlaylist(String nomeMusica, String artista, String nomePlaylist, String cpf) {
         Connection con = AccessDataBase.connectToDb();
        //comando em SQL
        String sql = "INSERT INTO projeto.playlist_has_musica(musica_idMusica, playlist_idPlaylist) values ((SELECT musica.idMusica FROM projeto.musica WHERE musica.nome = ? AND musica.artista = ?),(SELECT playlist.idPlaylist FROM projeto.playlist WHERE playlist.nome = ? AND playlist.Usuario_CPF= ?)); ";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeMusica);
            pst.setString(2, artista);
            pst.setString(3, nomePlaylist);
            pst.setString(4, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            ex.printStackTrace();
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

}
