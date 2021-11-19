
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
import modelo.Doctor;


public class DoctorGestion {
  private static final String SQL_SELECT_DOCTOR= "select * from Doctor where idDoctor=?";

private static final String SQL_INSERT_DOCTOR= "insert into Doctor (idDoctor,"
            + "nombreD,apellido1,apellido2,idEspecialidad,numTel) values (?,?,?,?,?,?)";
    
    public static boolean insertar(Doctor doctor){

        try {
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_INSERT_DOCTOR);
            sentencia.setString(1, doctor.getIdDoctor());
            sentencia.setString(2, doctor.getNombreD());
            sentencia.setObject(3, doctor.getApellido1());
            sentencia.setString(4, doctor.getApellido2());
            sentencia.setInt(5, doctor.getIdEspecialidad());
            sentencia.setString(6, doctor.getNumTel());
            return sentencia.executeUpdate()>0; 
            
        } catch (SQLException ex) {
            Logger.getLogger(DoctorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
     private static final String SQL_DELETE_DOCTOR= "delete from Doctor where IdDoctor=?";
    public static boolean eliminar(Doctor doctor){
        
        try {
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_DELETE_DOCTOR);
            consulta.setString(1,doctor.getIdDoctor());
            return consulta.executeUpdate()>0; 
        } catch (SQLException ex) {
            Logger.getLogger(DoctorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    public static Doctor getDoctor(String id){

        Doctor doc=null;

        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_DOCTOR);
            consulta.setString(1, id);
            ResultSet datos= consulta.executeQuery();
            if (datos.next()){
                doc= new Doctor(
                datos.getString(1),
                datos.getString(2),
                datos.getString(3),
                datos.getString(4),
                datos.getInt(5),
                datos.getString(6)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(DoctorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return doc;

    }


    private static final String SQL_SELECT_DOCTORES="Select * from Doctor";

    public static ArrayList<Doctor> getDoctores(){

        ArrayList<Doctor> lista= new ArrayList<>();  
        try {

            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_DOCTORES);
            ResultSet rs= consulta.executeQuery();
            while (rs!=null && rs.next()){
                lista.add(new Doctor(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getString(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DoctorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
  public static String GenerarJson(){
        
        Doctor doc= new Doctor();
        String tiraJson="";
        
        try {
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_DOCTORES);
            ResultSet rs= consulta.executeQuery();
            while (rs!=null && rs.next()){
               doc.setIdDoctor(rs.getString(2));
               doc.setNombreD(rs.getString(3));
               doc.setApellido1(rs.getString(4));
               doc.setApellido2(rs.getString(5));
               doc.setIdEspecialidad(rs.getInt(6));
               doc.setNumTel(rs.getString(7));
               
                JsonObjectBuilder jsonObjectBuilder= Json.createObjectBuilder();
                JsonObject jsonObject = jsonObjectBuilder.
                add("Id Doctor", doc.getIdDoctor()).
                add("Nombre", doc.getNombreD()).
                add("Primer Apellido", doc.getApellido1()).
                add("Segundo Apellido", doc.getApellido2()).
                add("Id Especialidad", doc.getIdEspecialidad()).
                add("Número de Teléfono", doc.getNumTel()).build();
                
                
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
