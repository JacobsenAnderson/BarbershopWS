/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Services;


/**
 *
 * @author Anderson
 */
public class ServicesDAO {
    
    public ServicesDAO()
    {
    
    }
    
    /*LISTAR SERVICER PRONTO*/
    public List<Services> listar()
    {
        String sql = "SELECT * FROM tabelas.tb_services";
        List<Services> retorno = new ArrayList<Services>();
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Services item = new Services();
                item.setService_id(res.getInt("service_id"));
                item.setDescricao(res.getString("desc_service"));
                item.setValor(res.getString("valor"));
                item.setTempo(res.getString("services_tempo"));
                
                retorno.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return retorno;
    }
        /*BUSCAR USUARIO PRONTO*/
    public Services buscar(Services service)
    {
         String sql = "SELECT * FROM tabelas.tb_services where desc_service=?";
        Services item = null;
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            pst.setString(1, service.getDescricao());
            ResultSet res = pst.executeQuery();
            
            if(res.next())
            {
                item = new Services();
                item.setService_id(res.getInt("service_id"));
                item.setDescricao(res.getString("desc_service"));
                item.setValor(res.getString("valor"));
                item.setTempo(res.getString("services_tempo"));
                                
            }   
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }        
        return item;   
    
    }    
    
    
    /*INSERIR SERVICES PRONTO*/
    public boolean inserir(Services service)
    {
        String sql = "INSERT INTO tabelas.tb_services(desc_service,valor,services_tempo) VALUES(?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, service.getDescricao());
            pst.setString(2, service.getValor());
            pst.setString(3, service.getTempo());
            
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
    
    /*ALTERAR SERVICES PRONTO*/
    public boolean atualizar(Services service)
    {
        String sql = "UPDATE tabelas.tb_services set desc_service=?,valor=?,services_tempo=? where desc_service=?";
        
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
            pst.setString(1, service.getDescricao());
            pst.setString(2, service.getValor());
            pst.setString(3, service.getTempo());
            pst.setString(4, service.getDescricao());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }             
                        
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }        
        return retorno;    
    }
    
    
    
    
    /*EXCLUIR SERVICES FAZER*/
    public boolean excluir(Services service)
    {
        String sql = "DELETE FROM tabelas.tb_services where desc_service=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {                     
            pst.setString(1, service.getDescricao());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }          
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }    
    
    
    
    
}
