package ws;

import com.google.gson.Gson;
import dao.ServicesDAO;
import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;
import modelo.Services;

/**
 * REST Web Service
 *
 * @author Anderson
 */
@Path("API")
public class BarbershopAPI {

    @Context
    private UriInfo context;

    public BarbershopAPI() {
        
    }
    /*BLOCO USUARIO*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "WEBSERVER RODANDO";   
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usuario/Get/{login}")
    public String getUsuario(@PathParam("login") String login){
        
        Usuario u = new Usuario();
        
        u.setLogin(login);
        
        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);
        
        Gson g = new Gson();        
        return g.toJson(u);
            
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usuario/List")
    public String listUsuario(){
        
        List<Usuario> lista;
        
        UsuarioDAO dao = new UsuarioDAO();
        lista = dao.listar();
                
        Gson g = new Gson();        
        return g.toJson(lista);
            
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usuario/Inserir")
    public void inserirUsuario(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserir(u);
               
    }
    
    @DELETE
    @Path("Usuario/Excluir/{login}")
    public boolean excluirUsuario(@PathParam("login") String login){
        
        Usuario u = new Usuario();
        u.setLogin(login);
        
        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);
        return dao.excluir(u);
            
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Usuario/Alterar")
    public void alterarUsuario(String content) {
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        dao.atualizar(u);
    }
    
    
    /*BLOCO SERVICES*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Services/List")
    public String listServices(){
        
        List<Services> lista;
        
        ServicesDAO dao = new ServicesDAO();
        lista = dao.listar();
             
        
        Gson g = new Gson();        
        return g.toJson(lista);
            
    } 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Services/Inserir")
    public void inserirService(String content){
        Gson g = new Gson();
        Services u = (Services) g.fromJson(content, Services.class);
        ServicesDAO dao = new ServicesDAO();
        dao.inserir(u);          
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Services/Alterar")
    public void alterarService(String content) {
        Gson g = new Gson();
        Services u = (Services) g.fromJson(content, Services.class);
        ServicesDAO dao = new ServicesDAO();
        dao.atualizar(u);
    }
    
    @DELETE
    @Path("Services/Excluir/{service}")
    public boolean excluirServices(@PathParam("service") String service){
        
        Services u = new Services();
        u.setDescricao(service);
        
        ServicesDAO dao = new ServicesDAO();
        u = dao.buscar(u);
        return dao.excluir(u);
            
    }
    
        
       
}
