
package controller;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Conexion;
import modelo.Paciente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


@Named(value = "reporteController")
@SessionScoped
public class ReporteController implements Serializable {
    
    public ReporteController() {
    }
    public void verMedicamentos() {
        try {
            File jasper= new File (FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/Medicamentos/medicamentos.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),null,Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type","application/pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
          } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public void descargarMedicamentos() {
        
        try {
            File jasper= new File (FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/Medicamentos/medicamentos.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),null,Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.addHeader("Content-disposition","attachement; filename=MedicamentosDisponibles.pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
      
     public void verPdf() {
        
        try {
            File jasper= new File (FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/Expediente/expedienteP.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),null,Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type","application/pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
          } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public void descargarPdf() {
        
        try {
            File jasper= new File (FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/Expediente/expedienteP.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),null,Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.addHeader("Content-disposition","attachement; filename=ExpedientePaciente.pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
      
       public void verExpediente(Paciente expe) {
        
        Map<String,Object> parametros;
        parametros = new HashMap<>();
        parametros.put("nombreP", expe.getNombreP());
        parametros.put("idPaciente", expe.getIdPaciente());           
        parametros.put("primerApe", expe.getApellido1());
        
        try {
            File jasper= new File (FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/Expediente/expedienteP.jasper"));
            
            JasperPrint reporteJasper= JasperFillManager.fillReport(jasper.getPath(),parametros,Conexion.getConexion());
            HttpServletResponse respuesta = (HttpServletResponse)
                    FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type","application/pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
            
         } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
