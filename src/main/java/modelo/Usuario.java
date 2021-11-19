package modelo;

public class Usuario {

    private String idUsuario;
    private String contraUsuario;
    private String nombreUsuario;
    private String apellido1Usuario;
    private String appelido2Usuario;
    private int idRol;

    public Usuario(String idUsuario, String nombreUsuario, int idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        this.contraUsuario = contraUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellido1Usuario() {
        return apellido1Usuario;
    }

    public void setApellido1Usuario(String apellido1Usuario) {
        this.apellido1Usuario = apellido1Usuario;
    }

    public String getAppelido2Usuario() {
        return appelido2Usuario;
    }

    public void setAppelido2Usuario(String appelido2Usuario) {
        this.appelido2Usuario = appelido2Usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

}
