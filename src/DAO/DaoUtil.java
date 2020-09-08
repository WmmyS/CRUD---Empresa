
package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class DaoUtil {
    public static Connection connection;
    static String url = "jdbc:postgresql://localhost:5432/EMPRESA";
    static String user = "postgres";
    static String pass = "1234";
    public ResultSet rs;
    
    //Método utilizado para o teste de conexão do servidor
    public static Connection teste(){
        
        try{          
            DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "Teste de conexão realizada com sucesso!");                        
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha na conexão!\n"+ex);
            throw new RuntimeException(ex);            
        }
    return null;    
    }
    //Método para conexões gerais
    public static Connection conecta(){     
        
        try{          
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Falha na conexão!\n"+ex);
            throw new RuntimeException(ex);            
        }
        return connection;
    }
};
