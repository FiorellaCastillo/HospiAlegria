
package controller;

import gestion.CitasGestion;
import gestion.PacienteGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Citas;


@Named(value = "citasController")
@SessionScoped

public class CitasController extends Citas implements Serializable {

    public CitasController() {
    }
   public String insertaAdmin(){
        
        if (CitasGestion.insertar(this)){
            
            return "confirmacionAdmin.xhtml";
            
        }else{
          
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "Posible identificación duplicada o no existe paciente registrado");
            FacesContext.getCurrentInstance().addMessage("citaForm:identificacion", mensaje);
            return "tramitaCAdmin.xhtml";
        }
        
    }
     public String insertaUser(){
        
        if (CitasGestion.insertar(this)){
            
            return "confirmacionUser.xhtml";
            
        }else{
          
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "Posible identificación duplicada o no existe paciente registrado");
            FacesContext.getCurrentInstance().addMessage("citaForm:identificacion", mensaje);
            return "tramitaCUser.xhtml";
        }
        
    }

    public List<Citas> getCitas(){
        
        return CitasGestion.getCitas();
        
    }  
    
     public void respaldoCitas(){
        
        String json  = PacienteGestion.GenerarJson();
        try{
            File f = new File (FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Respaldos")+"CitasRegistradas.zip");
            ZipOutputStream out = new ZipOutputStream (new FileOutputStream(f));
            ZipEntry e = new ZipEntry ("CitasRegistradas.json");
            try{     
                out.putNextEntry(e);
                byte[] data =json.getBytes();
                out.write(data,0,data.length);
                out.closeEntry();
                out.close();

                File zipPath = new File (FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/Respaldos")+"CitasRegistradas.zip");
                
                byte[] zip = Files.readAllBytes(zipPath.toPath());
                
                HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
                
                ServletOutputStream sos = respuesta.getOutputStream();
            
                respuesta.setContentType("application/zip");
                respuesta.addHeader("Content-Disposition","attachment; filename=CitasRegistradas.zip");
                
                sos.write(zip);
                sos.flush();
                FacesContext.getCurrentInstance().responseComplete();
                
            }catch (IOException ex){
            }
        }
        catch(FileNotFoundException ex){
            
        }
    }
}