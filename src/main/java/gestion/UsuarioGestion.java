
package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Usuario;

public class UsuarioGestion {
    
    private static final String SQL_VALIDaADMIN="select nombreUsuario,idRol from usuarios where idUsuario=?"
            + " and contraUsuario=MD5(?)";
    
    public static Usuario ValidaAdmin (String idUsuario, String password){
        
        Usuario usuario= null;
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_VALIDaADMIN);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, password);
            ResultSet rs= sentencia.executeQuery();
            if (rs.next()){
                
                usuario= new Usuario (idUsuario,rs.getString("nombreUsuario"),rs.getInt("idRol"));
            }

        }catch (SQLException ex){
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return usuario;
        
    }
    
     private static final String SQL_VALIDAUSUARIO="select nombreUsuario,idRol from usuarios where idUsuario=?"
            + " and contraUsuario=MD5(?)";
    
    public static Usuario ValidaUsuario (String idUsuario, String password){
        
        Usuario usuario= null;
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_VALIDAUSUARIO);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, password);
            ResultSet rs= sentencia.executeQuery();
            
            if (rs.next()){
                
                usuario= new Usuario (idUsuario,rs.getString(1),rs.getInt(2));
            }

        }catch (SQLException ex){
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return usuario;
        
    }
    
     private static final String SQL_VALIDABACKUP="select nombreUsuario,idRol from usuarios where idUsuario=?"
            + " and contraUsuario=MD5(?)";
    
    public static Usuario ValidaBackup (String idUsuario, String password){
        
        Usuario usuario= null;
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_VALIDAUSUARIO);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, password);
            ResultSet rs= sentencia.executeQuery();
            
            if (rs.next()){
                
                usuario= new Usuario (idUsuario,rs.getString("nombreUsuario"),rs.getInt("idRol"));
            }

        }catch (SQLException ex){
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return usuario;
        
    }
}  

