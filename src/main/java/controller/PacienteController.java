package controller;

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
import modelo.Paciente;



@Named(value = "pacienteController")
@SessionScoped
public class PacienteController extends Paciente implements Serializable {

    public PacienteController() {
    }

    public String insertaAdmin() {

        if (PacienteGestion.insertar(this)) {
            return "listaPAdmin.xhtml";

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaPacientesForm:identificacion", mensaje);
            return "editaPAdmin.xhtml";
        }

    }
    
    public String insertaUser() {

        if (PacienteGestion.insertar(this)) {
            return "listaPUser.xhtml";

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaPacientesForm:identificacion", mensaje);
            return "editaPUser.xhtml";
        }

    }

    public List<Paciente> getPacientes(){
        
        return PacienteGestion.getPacientes();   
    } 
    
    public void buscaPaciente(String id){

        Paciente paciente = PacienteGestion.getPaciente(id);

        if (paciente!= null){
            this.setIdPaciente(paciente.getIdPaciente());
            this.setNombreP(paciente.getNombreP());
            this.setApellido1(paciente.getApellido1());
            this.setApellido2(paciente.getApellido2());
            
        }else {
            this.setIdPaciente("");
            this.setNombreP("");
            this.setApellido1("");
            this.setApellido2("");
           

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Encontrado",
            "Paciente No Encontrado");
            FacesContext.getCurrentInstance().addMessage("pacienteForm:idExpediente", mensaje);
        }
    }
    
     public void respaldoPacientes(){
        
        String json  = PacienteGestion.GenerarJson();
        try{
            File f = new File (FacesContext.getCurrentInstance().getExternalContext().getRealPath("/respaldo")+"PacientesRegistrados.zip");
            ZipOutputStream out = new ZipOutputStream (new FileOutputStream(f));
            ZipEntry e = new ZipEntry ("PacientesRegistrados.json");
            try{     
                out.putNextEntry(e);
                byte[] data =json.getBytes();
                out.write(data,0,data.length);
                out.closeEntry();
                out.close();

                File zipPath = new File (FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/respaldo")+"PacientesRegistrados.zip");
                
                byte[] zip = Files.readAllBytes(zipPath.toPath());
                
                HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
                
                ServletOutputStream sos = respuesta.getOutputStream();
            
                respuesta.setContentType("application/zip");
                respuesta.addHeader("Content-Disposition","attachment; filename=PacientesRegistrados.zip");
                
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
