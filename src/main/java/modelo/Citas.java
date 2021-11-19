package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Citas  {

    private int idCita;
    private String idPaciente;
    private String idDoctor;
    private Date fechaCita;
    private int idServicio;
    private String idHabitacion;

    public Citas(int idCita, String idPaciente,String idDoctor, Date fechaCita, int idServicio, String idHabitacion) {
        this.idCita = idCita;
        this.idDoctor = idDoctor;
        this.idPaciente=idPaciente;
        this.fechaCita = fechaCita;
        this.idServicio = idServicio;
        this.idHabitacion = idHabitacion;
    }

    public Citas() {
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String toStringQR() {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        String fechaC= format.format(this.fechaCita);
        
        return "Cita Registrada{" +"\n"
                +  "Id Paciente:" + idPaciente +"\n"
                +  "Id Doctor:" + idDoctor +"\n"
                +  "Fecha:" + fechaC +"\n"
                +  "Servicio Médico:" + idServicio +"\n"
                +  "Id Habitacion:" + idHabitacion  + '}';
    }
}
