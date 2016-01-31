/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.k19.sessionbeans;

import br.com.k19.entidades.Usuario;
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
public class UsuarioRepositorio {
    @PersistenceContext
    private EntityManager manager;
    
    public void adiciona(Usuario u) {
        this.manager.persist(u);
    }
    
    public List<Usuario> buscaTodos() {
        TypedQuery<Usuario> query = this.manager.createQuery("SELECT x FROM Usuario x", Usuario.class);
        return query.getResultList();
    }
    
}
