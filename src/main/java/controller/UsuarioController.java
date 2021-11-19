package controller;

import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Usuario;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    public UsuarioController() {
        super("", "", 0);
    }
    private boolean admin;
    private boolean empleado;
    private boolean respaldo;

    public String validaAdmin() {

        Usuario usuario = UsuarioGestion.ValidaAdmin(this.getIdUsuario(), this.getContraUsuario());

        if (usuario != null) {
            this.setIdUsuario(usuario.getIdUsuario());
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            this.queRol();
            if (admin == true) {

                return "indexAdmin.xhtml";
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario, "
                    + "contraseña " + "o rol inválidos.");
            FacesContext.getCurrentInstance().addMessage("loginAdminForm:clave", msg);

        }
        return "usuarioAdmin.xhtml";
    }

    public String validaUser() {

        Usuario usuario = UsuarioGestion.ValidaUsuario(this.getIdUsuario(), this.getContraUsuario());

        if (usuario != null) {

            this.setIdUsuario(usuario.getIdUsuario());
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            this.queRol();
            if (empleado == true) {

                return "indexUser.xhtml";
            }
        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario, "
                    + "contraseña " + "o rol inválidos.");
            FacesContext.getCurrentInstance().addMessage("loginUserForm:clave", msg);
        }
        return "usuarioUser.xhtml";
    }

    public String validaBackup() {

        Usuario usuario = UsuarioGestion.ValidaBackup(this.getIdUsuario(), this.getContraUsuario());

        if (usuario != null) {

            this.setIdUsuario(usuario.getIdUsuario());
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            this.queRol();
            if (respaldo == true) {

                return "indexRespaldo.xhtml";
            }
        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario, "
                    + "contraseña " + "o rol inválidos.");
            FacesContext.getCurrentInstance().addMessage("loginBackupForm:clave", msg);
            //Corregin

        }
        return "usuarioRespaldo.xhtml";
    }

    public void queRol() {
        admin = false;
        empleado = false;
        respaldo = false;
        if (this.getIdRol() == 1) {

            admin = true;

        }
        if (this.getIdRol() == 2) {

            empleado = true;

        }
        if (this.getIdRol() == 3) {

            respaldo = true;
        }
    }
}
