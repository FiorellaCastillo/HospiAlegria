
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
import modelo.Citas;
import modelo.Conexion;

public class CitasGestion {
    private static final String SQL_INSERT_CITA= "insert into Cita (idCita,idPaciente,idDoctor,fechaCita,idServicio,idHabitacion) values (?,?,?,?,?,?)";
    
    public static boolean insertar(Citas cita){

        try {
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_INSERT_CITA);
             sentencia.setInt(1, cita.getIdCita());
            sentencia.setString(2, cita.getIdPaciente());
            sentencia.setString(3, cita.getIdDoctor());
            sentencia.setObject(4, cita.getFechaCita());
            sentencia.setInt(5, cita.getIdServicio());
            sentencia.setString(6, cita.getIdHabitacion());
            return sentencia.executeUpdate()>0; 
            
        } catch (SQLException ex) {
            Logger.getLogger(CitasGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    
    private static final String SQL_SELECT_CITA= "select * from Cita where idCita=?";
    
    public static Citas getCita(int id){
        
        Citas cita=null;
        
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_CITA);
            consulta.setInt(1, id);
            ResultSet datos= consulta.executeQuery();
            if (datos.next()){
                cita= new Citas(
                datos.getInt(1),
                datos.getString(2),
                datos.getString(3),
                datos.getDate(4),
                datos.getInt(5),
                datos.getString(6)
                );    
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitasGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cita;
    }
 
    private static final String SQL_DELETE_CITA= "delete from cita where idCita=?";
    
    public static boolean eliminar (Citas cita){
        
        try {
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_DELETE_CITA);
            consulta.setInt(1, cita.getIdCita());
            return consulta.executeUpdate()>0; 
        } catch (SQLException ex) {
            Logger.getLogger(CitasGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    private static final String SQL_SELECT_CITAS="Select * from Cita";
    
    public static ArrayList<Citas> getCitas(){
        
        ArrayList<Citas> lista= new ArrayList<>();
        
        try {
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_CITAS);
            ResultSet rs= consulta.executeQuery();
            while (rs!=null && rs.next()){
                lista.add(new Citas(
                rs.getInt(1),
                rs.getString(2),       
                rs.getString(3),
                rs.getDate(4),
                rs.getInt(5),
                rs.getString(6)));           
            }

        } catch (SQLException ex) {
            Logger.getLogger(CitasGestion.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return lista;     
    } 
    
  public static String GenerarJson(){
        
        Citas cita= new Citas();
        String tiraJson="",fechaCita;
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    
        try {
                  
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_CITAS);
            ResultSet rs= consulta.executeQuery();
            while (rs!=null && rs.next()){
               cita.setIdCita(rs.getInt(2)); 
               cita.setIdPaciente(rs.getString(3));
               cita.setIdDoctor(rs.getString(4));
               cita.setFechaCita(rs.getDate(5));
               cita.setIdServicio(rs.getInt(6));
               cita.setIdHabitacion(rs.getString(7));
               
                JsonObjectBuilder jsonObjectBuilder= Json.createObjectBuilder();
                JsonObject jsonObject = jsonObjectBuilder.
                add("Id Cita", cita.getIdCita()).
                add("Id Paciente", cita.getIdPaciente()).
                add("Id Doctor", cita.getIdDoctor()).
                add("Fecha", dateFormat.format(cita.getFechaCita())).
                add("Servicio Médico", cita.getIdServicio()).
                add("Id Habitación",  cita.getIdHabitacion()).build();
                   
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
