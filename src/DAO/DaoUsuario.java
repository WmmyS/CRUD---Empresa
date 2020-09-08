
package DAO;

import Classe.Usuario;
import static DAO.DaoUtil.*;
import View.TelaLogin;
import View.TelaPrincipal;
import java.sql.*;
import javax.swing.JOptionPane;

public class DaoUsuario {
    Usuario usuario = new Usuario();
    DaoUtil conecta = new DaoUtil();
    
    //Método para comparação de login de usuário
    public void LoginUser(Usuario usuario) throws SQLException{
        System.out.println("User: "+usuario.getLoginUser()+" Senha: "+usuario.getPassUser());
        //Consulta no banco para encontrar o usuário
        String sql = "SELECT * FROM tb_users WHERE user_login = ? AND user_senha= ?";
        PreparedStatement ps = DaoUtil.conecta().prepareStatement(sql);
        ps.setString(1, usuario.getLoginUser());
        ps.setString(2, usuario.getPassUser());
        ResultSet rs = ps.executeQuery();
        //Condição que irá comparar a conexão
        if(rs.next()){
            try{
                TelaPrincipal telaprincipal = new TelaPrincipal();
                telaprincipal.setVisible(true);
            } catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Digite algo");
            }
        } else{
            JOptionPane.showMessageDialog(null, "Senha ou usuário inválidos!");
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        }
    }  
}

