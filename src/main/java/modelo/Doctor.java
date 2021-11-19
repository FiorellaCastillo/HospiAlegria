package modelo;

public class Doctor {

    private String idDoctor;
    private String nombreD;
    private String apellido1;
    private String apellido2;
    private int idEspecialidad;
    private String numTel;

    public Doctor() {
    }

    public Doctor(String idDoctor, String nombreD, String apellido1, String apellido2, int idEspecialidad, String numTel) {
        this.idDoctor = idDoctor;
        this.nombreD = nombreD;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.idEspecialidad = idEspecialidad;
        this.numTel = numTel;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
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

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
     @Override
    public String toString() {
   
        return "Doctor{" + 
                "Id Doctor=" + idDoctor
                + ", Nombre=" + nombreD 
                + ", Primer Apellido=" + apellido1 
                + ", Segundo Apellido=" + apellido2 
                + ", Id Especialidad=" + idEspecialidad 
                + ", Número Teléfono=" + numTel + '}';
    }
}
