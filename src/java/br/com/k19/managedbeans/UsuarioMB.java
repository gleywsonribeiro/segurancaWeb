/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.k19.managedbeans;
import br.com.k19.entidades.Grupo;
import br.com.k19.entidades.Usuario;
import br.com.k19.sessionbeans.GrupoRepositorio;
import br.com.k19.sessionbeans.UsuarioRepositorio;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author gleyw
 */
@ManagedBean
public class UsuarioMB {
    @EJB
    private UsuarioRepositorio usuarioRepositorio;
    @EJB
    private GrupoRepositorio grupoRepositorio;
    
    private Usuario usuario;
    
    private List<String> nomesDosGrupos;
    private List<Usuario> usuarios;
    private List<Grupo> grupos;
    
    
    public UsuarioMB() {
        this.usuario = new Usuario();
    }
   
    public void adiciona() throws NoSuchAlgorithmException {
        //associando os grupos ao novo usuario
        for(String nomeDoGrupo:nomesDosGrupos) {
            Grupo g = new Grupo();
            g.setNome(nomeDoGrupo);
            this.usuario.getGrupos().add(g);
        }
        //Criptografando a senha
        MessageDigest md  = MessageDigest.getInstance("MD5");
        md.update(this.usuario.getSenha().getBytes());
        BigInteger hash = new BigInteger(1, md.digest());
        String senhaCriptografada = hash.toString(16);
        while (senhaCriptografada.length() < 32) {            
            senhaCriptografada = "0" + senhaCriptografada;
        }
        this.usuario.setSenha(senhaCriptografada);
        
        //salvando o usuario
        this.usuarioRepositorio.adiciona(usuario);
        this.usuario = new Usuario();
        this.usuarios = null;
        
    }
    
    public List<Grupo> getGrupos() {
        if(this.grupos == null) {
            this.grupos = grupoRepositorio.buscaTodos();
        }
        return this.grupos;
    }

    public List<Usuario> getUsuarios() {
        if(usuarios == null) {
            usuarios = usuarioRepositorio.buscaTodos();
        }
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getNomesDosGrupos() {
        return nomesDosGrupos;
    }

    public void setNomesDosGrupos(List<String> nomesDosGrupos) {
        this.nomesDosGrupos = nomesDosGrupos;
    }
    
    
}
