package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import modelo.Conexion;
import modelo.Paciente;

public class PacienteGestion {

    private static final String SQL_INSERT_PACIENTE = "insert into Paciente (idPaciente,idHabitacion,"
            + "nombreP,apellido1,apellido2,direccPaciente,genero,numTel,fechaNaci) values (?,?,?,?,?,?,?,?,?)";

    public static boolean insertar(Paciente paciente) {

        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_INSERT_PACIENTE);
            sentencia.setString(1, paciente.getIdPaciente());
            sentencia.setString(2, paciente.getIdHabitacion());
            sentencia.setString(3, paciente.getNombreP());
            sentencia.setString(4, paciente.getApellido1());
            sentencia.setString(5, paciente.getApellido2());
            sentencia.setString(6, paciente.getDireccPaciente());
            sentencia.setString(7, "" + paciente.getGenero());
            sentencia.setString(8, paciente.getNumTel());
            sentencia.setObject(9, paciente.getFechaNaci());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(PacienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    private static final String SQL_SELECT_PACIENTE = "select * from Paciente where idPaciente=?";

    public static Paciente getPaciente(String id) {

        Paciente paciente = null;

        try {

            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_PACIENTE);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            if (datos.next()) {
                paciente = new Paciente(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7).charAt(0),
                        datos.getString(8),
                        datos.getDate(9)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paciente;

    }

    private static final String SQL_DELETE_PACIENTE = "delete from Paciente where idPaciente=?";

    public static boolean eliminar(Paciente paciente) {

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_DELETE_PACIENTE);
            consulta.setString(1, paciente.getIdPaciente());
            return consulta.executeUpdate() > 0; 
        } catch (SQLException ex) {
            Logger.getLogger(PacienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private static final String SQL_SELECT_PACIENTES = "Select * from Paciente";

    public static ArrayList<Paciente> getPacientes() {

        ArrayList<Paciente> lista = new ArrayList<>();

        try {

            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_PACIENTES);
            ResultSet rs = consulta.executeQuery();
            while (rs != null && rs.next()) {
                lista.add(new Paciente(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7).charAt(0),
                        rs.getString(8),
                        rs.getDate(9)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
  public static String GenerarJson(){
        
        Paciente paciente= new Paciente();
       String tiraJson="",fechaNaci;
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    
        try {
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_PACIENTES);
            ResultSet rs= consulta.executeQuery();
            while (rs!=null && rs.next()){
               paciente.setIdPaciente(rs.getString(2));
               paciente.setIdHabitacion(rs.getString(3));
               paciente.setNombreP(rs.getString(4));
               paciente.setApellido1(rs.getString(5));
               paciente.setApellido2(rs.getString(6));
               paciente.setDireccPaciente(rs.getString(7));
               paciente.setGenero(rs.getString(8).charAt(0));
               paciente.setNumTel(rs.getString(9));
               paciente.setFechaNaci(rs.getDate(10));
               
                JsonObjectBuilder jsonObjectBuilder= Json.createObjectBuilder();
                JsonObject jsonObject = jsonObjectBuilder.
                add("Id Paciente", paciente.getIdPaciente()).
                add("Id Habitación", paciente.getIdHabitacion()).
                add("Nombre", paciente.getNombreP()).
                add("Primer Apellido", paciente.getApellido1()).
                add("Segundo Apellido", paciente.getApellido2()).
                add("Dirección", paciente.getDireccPaciente()).
                add("Número de Teléfono", paciente.getNumTel()).
                add("Fecha Nacimiento",dateFormat.format(paciente.getFechaNaci())).build();
                
                
                StringWriter tira= new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(jsonObject);
                
                if (tiraJson==null){
                    tiraJson= tira.toString() + "\n";
                }else{
                    tiraJson= tiraJson + tira.toString() + "\n";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return tiraJson;
    }
}
