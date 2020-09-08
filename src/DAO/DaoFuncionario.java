
package DAO;

import Classe.Cargo;
import Classe.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoFuncionario {
    
    private java.sql.Connection conecta;
    
    public DaoFuncionario(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Funcionario funcionario){
        
        String sql = "INSERT INTO tb_funcionario(id_funcionario, nome, sobrenome, cpf, rg, id_cargo) VALUES(?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getSobrenome());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getRg());
            stmt.setInt(6,funcionario.getCargo().getId());
            System.out.println(funcionario.getId()+funcionario.getNome()+funcionario.getSobrenome()+funcionario.getCpf()+"---"+funcionario.getRg());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Funcionário inserido com sucesso!");
    
        }catch(Exception ex){
            Logger.getLogger(DaoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro ao inserir funcionário!!"+ex);
  
        }
    }
    
    public void Alterar(Funcionario funcionario){
        String sql = "UPDATE tb_funcionario SET id_funcionario=?, nome=?, sobrenome=?, cpf=?, rg=?, id_cargo=?"
                    +"WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getSobrenome());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getRg());
            stmt.setString(6, funcionario.getCargo().getDescricao());
            stmt.executeQuery();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Função gerada para Deletar funcionarios
    public void Deletar(Funcionario funcionario){
        String sql = "DELETE FROM tb_funcionario"
                    +"WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, funcionario.getId());
            stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Função gerada para gerar lista de funcionarios
    public List<Funcionario> listarFuncionarios(){
        
        String sql = "SELECT * \n" +
                     "FROM tb_funcionario f, tb_cargo c\n" +
                     "WHERE f.id_cargo = c.id_cargo;";
        ResultSet rs;
        List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                Cargo cargo = new Cargo();
                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSobrenome(rs.getString("sobrenome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                cargo.setId(rs.getInt("id_cargo"));
                cargo.setSigla(rs.getString("sigla"));
                cargo.setDescricao(rs.getString("descricao"));
                cargo.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(cargo);

                listaFuncionarios.add(funcionario);            
            }
            rs.close();
            stmt.close();
            return listaFuncionarios;
    
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public Integer ConsultarId() throws SQLException{
        String sql = "SELECT id_funcionario FROM tb_funcionario WHERE id_funcionario = (SELECT MAX(id_funcionario) FROM tb_funcionario)";
        ResultSet rs;
        PreparedStatement ps = conecta.prepareStatement(sql);
        rs = ps.executeQuery();
           
        int id = 0;
            
        if(rs.next()){
            id = rs.getInt("id_funcionario");
        }
        return id + 1;
    }
}
