package WebService;

import gestion.CitasGestion;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Citas;

@Path("citasWS")
@RequestScoped
public class CitaWs {

  
    @Context
    private UriInfo context;

   
    public CitaWs() {
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Citas getCita(@PathParam("id") int id){
        return CitasGestion.getCita(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Citas deleteCita(@PathParam("id") int id){
        Citas cita = CitasGestion.getCita(id);
        
        if (cita!=null){
            if (CitasGestion.eliminar(cita)){
                return cita;
            }
        }       
        return null;
    }
}

		
	

