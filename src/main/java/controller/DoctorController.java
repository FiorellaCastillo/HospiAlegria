package controller;

import gestion.DoctorGestion;
import gestion.PacienteGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Doctor;


@Named(value = "doctorController")
@SessionScoped
public class DoctorController extends Doctor implements Serializable {

   
    public DoctorController() {
    }
     public String inserta(){
        
        if (DoctorGestion.insertar(this)){
            return "listaDAdmin.xhtml"; 
        }else{     
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaDoctoresForm:identificacion", mensaje);
            return "editaDAdmin.xhtml";
        }  
     }
     
  public String elimina(){
        
        if (DoctorGestion.eliminar(this)){
            return "listaDAdmin.xhtml";
        }else{
             FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaDoctoresForm:identificacion", mensaje);
            return "editaDAdmin.xhtml";
        }
    }
  
   public List<Doctor> getDoctores(){
        
        return DoctorGestion.getDoctores();
    }     
   
       public String edita(String id){
        
        Doctor doc= DoctorGestion.getDoctor(id);
        if (doc!=null){
            
            this.setIdDoctor(doc.getIdDoctor());
            this.setNombreD(doc.getNombreD());
            this.setApellido1(doc.getApellido1());
            this.setApellido2(doc.getApellido2());
            this.setIdEspecialidad(doc.getIdEspecialidad());
            this.setNumTel(doc.getNumTel());
            return "editaDAdmin.xhtml";
        }else{
             FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "Posible que el doctor no exista");
            FacesContext.getCurrentInstance().addMessage("editaDoctoresForm", mensaje);
            return "editaDAdmin.xhtml";
        }

    }
   
     public void respaldoDoctores(){
        
        String json  = PacienteGestion.GenerarJson();
        try{
            File f = new File (FacesContext.getCurrentInstance().getExternalContext().getRealPath("/respaldo")+"DoctoresDisponibles.zip");
            ZipOutputStream out = new ZipOutputStream (new FileOutputStream(f));
            ZipEntry e = new ZipEntry ("DoctoresDisponibles.json");
            try{     
                out.putNextEntry(e);
                byte[] data =json.getBytes();
                out.write(data,0,data.length);
                out.closeEntry();
                out.close();

                File zipPath = new File (FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/respaldo")+"DoctoresDisponibles.zip");
                
                byte[] zip = Files.readAllBytes(zipPath.toPath());
                
                HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
                
                ServletOutputStream sos = respuesta.getOutputStream();
            
                respuesta.setContentType("application/zip");
                respuesta.addHeader("Content-Disposition","attachment; filename=DoctoresDisponibles.zip");
                
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
