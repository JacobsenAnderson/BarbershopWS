package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

public class UsuarioDAO {
    
    public UsuarioDAO()
    {
    
    }
    
     /*LISTAR USUARIO PRONTO*/
    public List<Usuario> listar()
    {
         String sql = "SELECT * FROM tabelas.tb_users";
        List<Usuario> retorno = new ArrayList<Usuario>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Usuario item = new Usuario();
                item.setLogin(res.getString("login"));
                item.setSenha(res.getString("senha"));
                item.setEmail(res.getString("email"));
                item.setPerfil(res.getString("perfil"));
                item.setNome(res.getString("nome"));
                item.setTelefone(res.getString("telefone"));
                item.setUser_id(res.getInt("user_id"));
                
                retorno.add(item);
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;

    }
    
        /*BUSCAR USUARIO PRONTO*/
    public Usuario buscar(Usuario usuario)
    {
         String sql = "SELECT * FROM tabelas.tb_users where login=?";
        Usuario retorno = null;
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            pst.setString(1, usuario.getLogin());
            ResultSet res = pst.executeQuery();
            
            if(res.next())
            {
                retorno = new Usuario();
                retorno.setLogin(res.getString("login"));
                retorno.setSenha(res.getString("senha"));
                retorno.setEmail(res.getString("email"));
                retorno.setPerfil(res.getString("perfil"));
                retorno.setNome(res.getString("nome"));
                retorno.setTelefone(res.getString("telefone"));
                retorno.setUser_id(res.getInt("user_id"));
                                
            }   
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }        
        return retorno;   
    
    }

    /*Inserir USUARIO PRONTO*/
    public boolean inserir(Usuario usuario)
    {
        String sql = "INSERT INTO tabelas.tb_users(login,nome,email,senha,telefone,perfil) VALUES(?,?,?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, usuario.getLogin());
            pst.setString(2, usuario.getNome());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getSenha());
            pst.setString(5, usuario.getTelefone());
            pst.setString(6, usuario.getPerfil());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    
    /*Excluir USUARIO PRONTO*/
    public boolean excluir(Usuario usuario)
    {
        String sql = "DELETE FROM tabelas.tb_users where login=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
                     
            pst.setString(1, usuario.getLogin());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
       
    /*Atualizar USUARIO PRONTO*/
    public boolean atualizar(Usuario usuario)
    {
        String sql = "UPDATE tabelas.tb_users set nome=?,email=?,senha=?,telefone=?,perfil=? where login=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getTelefone());
            pst.setString(5, usuario.getPerfil());
            pst.setString(6, usuario.getLogin());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }             
                        
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }        
        return retorno;    
    }
    


}
