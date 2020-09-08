
package DAO;

import Classe.Cargo;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DaoCargo {
    private java.sql.Connection conecta;
    
    public DaoCargo(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Cargo cargo){
        String sql = "INSERT INTO tb_cargo(id_cargo, sigla, descricao, salario) VALUES(?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, cargo.getId());
            stmt.setString(2, cargo.getSigla());
            stmt.setString(3, cargo.getDescricao());
            stmt.setFloat(4, cargo.getSalario());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(DaoCargo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro ao inserir dados!");
        }
    }
    //Altera um cargo a partir de uma classe cargo com dados inseridos
    public void Alterar(Cargo cargo){
        String sql = "UPDATE tb_cargo SET sigla = ?, descricao = ?, salario = ?"
                    +"WHERE id=?";
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, cargo.getSigla());
            stmt.setString(2, cargo.getDescricao());
            stmt.setFloat(3, cargo.getSalario());
            stmt.setInt(4, cargo.getId());
            stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Função gerada para Deletar cargos
    public void Deletar(Cargo cargo){
        String sql = "DELETE FROM tb_cargo"
                    +"WHERE id=?";
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, cargo.getId());
            stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Função gerada para gerar lista de cargos
    public List<Cargo> listarCargos(){
        
        String sql = "SELECT * FROM tb_cargo";
        ResultSet rs;
        List<Cargo> listaCargos = new ArrayList<Cargo>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id_cargo"));
                cargo.setSigla(rs.getString("sigla"));
                cargo.setDescricao(rs.getString("descricao"));
                cargo.setSalario(rs.getFloat("salario"));
                listaCargos.add(cargo);            
            }
            rs.close();
            stmt.close();
            return listaCargos;    
    
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    //Trás a quantidade máxima de id contidas na base de dados
    public Integer ConsultarId() throws SQLException{
        String sql = "SELECT id_cargo FROM tb_cargo WHERE id_cargo = (SELECT MAX(id_cargo) FROM tb_cargo)";
        ResultSet rs;
        PreparedStatement ps = conecta.prepareStatement(sql);
        rs = ps.executeQuery();
           
        int id = 0;
            
        if(rs.next()){
            id = rs.getInt("id_cargo");
        }
        return id + 1;
    }
    //Trás todas as informações de um cargo que contém somente a descrição
    public Cargo RetornarCargo(Cargo cargo) throws SQLException{
        String sql = "SELECT * FROM tb_cargo WHERE descricao = ?";
        ResultSet rs;
        PreparedStatement ps = conecta.prepareStatement(sql);
        ps.setString(1, cargo.getDescricao());
        rs = ps.executeQuery();
        
        if(rs.next()){
            cargo.setId(rs.getInt("id_cargo"));
            cargo.setDescricao(rs.getString("sigla"));
            cargo.setSalario(rs.getFloat("salario"));
        } else{
            System.out.println("Erro na Buscar de cargo!");
        }
        return cargo;
    }
    
    /*public Integer listarUltimoCod() throws SQLException {
        Conexao c = new Conexao();
        String sql = "select cod_cliente from tb_cliente where cod_cliente = (SELECT MAX(cod_cliente) FROM tb_cliente)";
        PreparedStatement ps = Conexao.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int codigo = 0;
        

        if(rs.next()){
            codigo = rs.getInt("cod_cliente");    
        }
        
        return codigo + 1;
    }  */  
    
    /*public ArrayList<Cargo> listarCargos() throws SQLException{
        DaoUtil conexao = new DaoUtil();
        String sql = "SELECT id_cargo, sigla, descricao, salario FROM tb_cargo ORDER_BY sigla";
        PreparedStatement ps = DaoUtil.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        try{
        ArrayList listaCargos = new ArrayList();
        while(rs.next()){
            Cargo cargo = new Cargo();
            cargo.setId(rs.getInt("id_cargo"));
            cargo.setSigla(rs.getNString("sigla"));
            cargo.setDescricao(rs.getString("descricao"));
            cargo.setSalario(rs.getFloat("salario"));
            listaCargos.add(cargo);
        }
     
        return listaCargos;
        } catch(SQLException ex){
            throw new RuntimeException(ex);*/
    
}