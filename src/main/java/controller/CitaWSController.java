
package controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


@Named(value = "citaWSController")
@RequestScoped
public class CitaWSController {

    private int id;
    private String salida;
    private final String URI="http://localhost/hospitalAlegria-1.0-SNAPSHOT/resources/citasWS";
    
    public CitaWSController() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }
    
    public void hacerGet(){
               
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI+"/"+id);
        JsonObject response = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        salida= response.asJsonObject().toString();
    }
    
    public void hacerDelete(){
               
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI+"/"+id);
        JsonObject response = target.request(MediaType.APPLICATION_JSON).delete(JsonObject.class);
        salida= response.asJsonObject().toString();
        
    }
}
