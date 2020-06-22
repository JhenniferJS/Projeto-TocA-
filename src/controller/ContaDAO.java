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
import model.Conta;
import model.Endereco;
import model.Usuario;

/**
 * Classe que interage com a tabela Conta do Banco de Dados
 * @author Jhennifer
 */
public class ContaDAO {
    //objeto responsavel por preparar as consultas dinamicas
    PreparedStatement pst;
    //objeto responsavel por referenciar a tabela resultante da busca
    ResultSet rs;

    boolean sucesso = false;
    
    //Inserir dados
    public boolean inserirConta(Usuario novoUsuario,Conta novaConta){
        //conectar ao banco de dados
        Connection con = AccessDataBase.connectToDb();
        //comando em SQL
        String sql = "INSERT INTO Conta(login,senha,usuario_cpf) values (?,?,?)";
        //comando recebe parametros - consulta dinamica
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1,novaConta.getLogin()); 
            pst.setString(2,novaConta.getSenha()); 
            pst.setString(3,novoUsuario.getCpf()); 
            pst.execute();
            sucesso = true; 
        }
        catch(SQLException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        finally{
            try{
                //Encerra a conexao
                con.close();
                pst.close();
            }
            catch(SQLException ex){
                System.out.println("Erro: " + ex.getMessage());
            }     
        }
        return sucesso;
    }
    
    //Deletar conta e usuario 
    public boolean deletarConta(String cpf) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "DELETE Conta, Usuario FROM Conta INNER JOIN Usuario ON Conta.Usuario_CPF = Usuario.CPF WHERE Conta.Usuario_CPF = ?";
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, cpf);
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

    //Atualizar dados da conta
    public boolean atualizaConta(String cpfUsuario, String login, String senha) {
        Connection con = AccessDataBase.connectToDb();
        //Comando em SQL
        String sq1 = "UPDATE Conta, Usuario set login=? , senha=? WHERE cpf=?";
        //O comando recebe parametros -> consulta dinamica (pst)
        try {
            pst = con.prepareStatement(sq1);
            pst.setString(1, login);
            pst.setString(2, senha);
            pst.setString(3, cpfUsuario);
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

    //busca a existencia de um login na tabela conta
    public boolean buscarLogin(String loginConta, String senhaConta) {
        Connection con = AccessDataBase.connectToDb();
        //comando SQL para buscar : seleciona a senha q esta armazenada na tabela Conta 
        String sql = "SELECT senha FROM Conta WHERE login = ?";
        String aux;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, loginConta);
            rs = pst.executeQuery();//Executa a consulta SQL e retorna um objeto
            while (rs.next()) {// rs.nest retorna false se nao huver mais nenhuma linha a ser processado
                aux = rs.getString("senha");
                if (aux.isEmpty())//se nao ha uma senha, entao o email nao foi encontrado no sistema
                {
                    sucesso = false;
                    System.out.println("LOGIN ERRADO!");
                } else if (!aux.equals(senhaConta))// se a senha digitada pelo usuario estiver errada
                {

                    sucesso = false;
                    System.out.println("SENHA ERRDA!");
                } else {
                    sucesso = true;
                }

            }

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

    //buscar o cpf da conta logada
    public String buscarCPF(String loginConta, String senhaConta) {
        Connection con = AccessDataBase.connectToDb();
        //comando SQL para buscar : seleciona a senha q esta armazenada na tabela Conta 
        String sql = "SELECT Usuario_CPF FROM Conta WHERE login = ? and senha = ?";
        String aux = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, loginConta);
            pst.setString(2, senhaConta);
            rs = pst.executeQuery();
            if(rs.next()){
                aux = rs.getString("Usuario_CPF");
            }
 
        } catch (SQLException ex) {
            System.out.println("ERRO bucarcpf: " + ex.getMessage());
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
        return aux ;
    }
    
    //buscar endereco do usuario logado
    public ArrayList<Endereco> buscarEndereco(String cpf) {
        ArrayList<Endereco> listaEndereco = new ArrayList<>();
        Connection con = AccessDataBase.connectToDb();

        //COMANDO EM SQL
        String sql = "SELECT pais,uf, cidade, bairro, rua, complemento, numero_Casa FROM Usuario WHERE cpf = ?";
        //O comando nao recebe parametros -> consulta estatica (st)
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
                  Endereco endereco = new Endereco(rs.getString("Pais"), rs.getString("UF"), rs.getString("Cidade"), rs.getString("Bairro"), rs.getString("Rua"),rs.getString("Complemento"),rs.getInt("Numero_casa"));  
                  listaEndereco.add(endereco);                
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
        return listaEndereco;
    }
    
}
