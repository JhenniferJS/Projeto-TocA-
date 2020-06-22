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
import model.CartaoCredito;
import model.Endereco;
import model.Usuario;

/**
 * Classe que interage com a tabela Usuario do Banco de Dados.
 * @author Jhennifer
 */
public class UsuarioDAO {

    //objeto responsavel por preparar as consultas dinamicas
    PreparedStatement pst;
    //objeto responsavel por preparar as consultas estaticas
    Statement st;
    //objeto responsavel por referenciar a tabela resultante da busca
    ResultSet rs;

    boolean sucesso = false;

    //Inserir dados
    public boolean inserirUsuario(Usuario novoUsuario, Endereco novoEndereco, CartaoCredito novoCartao) {
        //conectar ao banco de dados
        Connection con = AccessDataBase.connectToDb();
        //comando em SQL
        String sql;
        System.out.println("complemento: " + novoEndereco.getComplemento());
        if (novoEndereco.getComplemento() == null) {
            sql = "INSERT INTO Usuario(cpf,nome,sexo,cartao_titular,cartao_numero,cartao_codigo,pais,uf,cidade,bairro,rua,numero_casa) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT INTO Usuario(cpf,nome,sexo,cartao_titular,cartao_numero,cartao_codigo,pais,uf,cidade,bairro,rua,complemento,numero_casa) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        //comando recebe parametros - consulta dinamica
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoUsuario.getCpf()); 
            pst.setString(2, novoUsuario.getNome()); 
            pst.setString(3, novoUsuario.getSexo()); 
            pst.setString(4, novoCartao.getTitular()); 
            pst.setString(5, novoCartao.getNumCartao());
            pst.setInt(6, novoCartao.getCodigo());
            pst.setString(7, novoEndereco.getPais());
            pst.setString(8, novoEndereco.getUf());
            pst.setString(9, novoEndereco.getCidade());
            pst.setString(10, novoEndereco.getBairro());
            pst.setString(11, novoEndereco.getRua());
            if (novoEndereco.getComplemento() != null) {
                pst.setString(12, novoEndereco.getComplemento());
                pst.setInt(13, novoEndereco.getNumCasa());
            } else {
                pst.setInt(12, novoEndereco.getNumCasa());
            }
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

    //Deletar um usuario 
    public boolean deletarUsuario(String cpf) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "DELETE FROM Usuario WHERE cpf=?";
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, cpf);
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                //Encerra a conexao
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Atualizar nome do usuario
    public boolean atualizaNomeUsuario(String cpfUsuario, String novoNomeUsuario) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "UPDATE Usuario set Nome=? WHERE cpf=?";

        //O comando recebe parametros -> consulta dinamica (pst)
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, novoNomeUsuario);
            pst.setString(2, cpfUsuario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                //Encerra a conexao
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Atualizar sexo do usuario
    public boolean atualizaSexoUsuario(String cpfUsuario, String sexo) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "UPDATE Usuario set sexo=? WHERE cpf=?";

        //O comando recebe parametros -> consulta dinamica (pst)
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, sexo);
            pst.setString(2, cpfUsuario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                //Encerra a conexao
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //Atualizar endereço do usuario
    public boolean atualizarEnderecoUsuario(String cpfUsuario, Endereco novoEndereco) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1;
        if (novoEndereco.getComplemento() != null) {
            sq1 = "UPDATE Usuario set UF=? , Cidade=? , Bairro=? , Rua=? , Complemento=? , Numero_casa=? WHERE cpf=?";
        } else {
            sq1 = "UPDATE Usuario set UF=? , Cidade=? , Bairro=? , Rua=? , Numero_casa=? WHERE cpf=?";
        }

        //O comando recebe parametros -> consulta dinamica (pst)
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, novoEndereco.getUf());
            pst.setString(2, novoEndereco.getCidade());
            pst.setString(3, novoEndereco.getBairro());
            pst.setString(4, novoEndereco.getRua());
            if (novoEndereco.getComplemento() != null) {
                pst.setString(5, novoEndereco.getComplemento());
                pst.setInt(6, novoEndereco.getNumCasa());
                pst.setString(7, cpfUsuario);
            } else {
                pst.setInt(5, novoEndereco.getNumCasa());
                pst.setString(6, cpfUsuario);
            }
            pst.execute();

            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                //Encerra a conexao
                con.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return sucesso;
    }

    //buscar nome e sexo do usuario logado
    public ArrayList<Usuario> buscarUsuario(String cpf) {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        Connection con = AccessDataBase.connectToDb();

        //COMANDO EM SQL
        String sql = "SELECT cpf, nome, sexo FROM Usuario WHERE cpf = ?";
        //O comando nao recebe parametros -> consulta estatica (st)

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("cpf"), rs.getString("Nome"), rs.getString("Sexo"));
                listaUsuario.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } finally {
            try {
                //Encerra a conexao
                con.close();
            } catch (SQLException ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }
        }
        return listaUsuario;
    }
}
