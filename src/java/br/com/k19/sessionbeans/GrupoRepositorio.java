/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.k19.sessionbeans;

import br.com.k19.entidades.Grupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author gleyw
 */
@Stateless
public class GrupoRepositorio {
    @PersistenceContext
    private EntityManager manager;
    
    public void adiciona(Grupo g) {
        this.manager.persist(g);
    }
    
    public List<Grupo> buscaTodos() {
        TypedQuery<Grupo> query = this.manager.createQuery("SELECT x FROM Grupo x", Grupo.class);
        return query.getResultList();
    }
}

