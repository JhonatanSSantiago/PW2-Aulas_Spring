/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhss.jhonssantiago.dao;

import com.jhss.jhonssantiago.model.Pessoa;
import com.jhss.jhonssantiago.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fagno
 */
public class PessoaDAO {

    public List<Pessoa> buscarPessoas() {
        try {
            //criar um objeto Connection para receber a conexão
            Connection con = Conexao.criarConexao();
            //comando sql
            String sql = "select * from tb_pessoa";
            PreparedStatement ps = con.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Pessoa> pessoas = new ArrayList();
            //laço para buscar todas as pessoas do banco
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                //add pessoa na lista
                pessoas.add(p);
            }
            //retorna a lista de pessoas
            return pessoas;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Método para deletar uma pessoa pelo ID
     *
     * @param id
     * @return
     */
    public boolean remove(Long id) {
        try {
            //criar um objeto Connection para receber a conexão
            Connection con = Conexao.criarConexao();
            //comando sql
            String sql = "delete from tb_pessoa where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            if (ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Método para add uma pessoa
     *
     * @param pessoa
     * @return
     */
    public boolean save(Pessoa pessoa) {
        try {
            //criar um objeto Connection para receber a conexão
            Connection con = Conexao.criarConexao();
            //comando sql
            String sql = "insert into tb_pessoa (nome) values (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, pessoa.getNome());

            if (ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Pessoa pessoa) {
        try {
            //criar um objeto Connection para receber a conexão
            Connection con = Conexao.criarConexao();
            //comando sql
            String sql = "update tb_pessoa set nome=? where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, pessoa.getNome());
            ps.setLong(2, pessoa.getId());

            if (ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Método para retornar uma pessoa pelo ID
     *
     * @param id
     * @return
     */
    public Pessoa buscarPessoa(Long id) {
        try {
            //criar um objeto Connection para receber a conexão
            Connection con = Conexao.criarConexao();
            //comando sql
            String sql = "select * from tb_pessoa where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
