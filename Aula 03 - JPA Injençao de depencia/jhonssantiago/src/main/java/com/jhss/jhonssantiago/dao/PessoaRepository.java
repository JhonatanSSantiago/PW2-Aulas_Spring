/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhss.jhonssantiago.dao;

import com.jhss.jhonssantiago.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhons
 */
@Repository
public class PessoaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public Pessoa pessoa(int id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> pessoas() {
        Query query = em.createQuery("from Pessoa");
        return query.getResultList();
    }

    public void remove(int id) {
        Pessoa p = em.find(Pessoa.class, id);
        em.remove(p);
    }

    public void update(Pessoa pessoa) {
        em.merge(pessoa);
    }
}
