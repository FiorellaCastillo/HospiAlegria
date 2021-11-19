
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente {
  private String idPaciente;
    private String idHabitacion;
    private String nombreP;
    private String apellido1;
    private String apellido2;
    private String direccPaciente;
    private char genero;
    private String numTel;
    private Date fechaNaci;

    public Paciente() {
    }

    public Paciente(String idPaciente, String idHabitacion, String nombreP, String apellido1, String apellido2, String direccPaciente, char genero, String numTel, Date fechaNaci) {
        this.idPaciente = idPaciente;
        this.idHabitacion = idHabitacion;
        this.nombreP = nombreP;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccPaciente = direccPaciente;
        this.genero = genero;
        this.numTel = numTel;
        this.fechaNaci = fechaNaci;
    }

       public String getNombreCompleto(){
        
        String nom="";
        
        nom += nombreP!=null?nombreP+" ":"";
        nom += apellido1!=null?apellido1+" ":"";
        nom += apellido2!=null?apellido2:"";
        
        return nom;
        
    }
    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccPaciente() {
        return direccPaciente;
    }

    public void setDireccPaciente(String direccPaciente) {
        this.direccPaciente = direccPaciente;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Date getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(Date fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        String fechaNacimiento= format.format(this.fechaNaci);
        
        return "Paciente{" + 
                "Id Paciente=" + idPaciente 
                + ",Id Habitacion=" + idHabitacion 
                + ", Nombre=" + nombreP 
                + ", Primer Apellido=" + apellido1 
                + ", Segundo Apellido=" + apellido2 
                + ", Dirección=" + direccPaciente 
                + ", Género=" + genero 
                + ", Número Teléfono=" + numTel 
                + ", Fecha Nacimiento=" + fechaNacimiento + '}';
    }
}