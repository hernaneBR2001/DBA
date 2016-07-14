package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.domain.Feria;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;

public class FeriaDAO {
	
	public void salvar(Feria p) throws SQLException {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_planilha_ferias ");
		sql.append(" (cod_planilha_ferias,cod_usuario,qtidade_dias,data_inicio,substituto1,substituto2,substituto3,data_fim) ");
		sql.append(" values (seq_planilha_ferias.nextval,?,?,?,?,?,?, to_date(?,'DD/MM/YYYY') + ? ) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

        comando.setLong(1, p.getCod_usuario().getCod_usuario());	
        comando.setLong(2, p.getQtidade_dias());
        
        comando.setString(3,p.getData_inicio());
        
        comando.setString(4, p.getSubstituto1().getNom_usuario());
        comando.setString(5, p.getSubstituto2().getNom_usuario());
        comando.setString(6, p.getSubstituto3().getNom_usuario());

        
        comando.setString(7,p.getData_inicio());
        comando.setLong(8, p.getQtidade_dias());
        
        
	   comando.executeUpdate();

	}
	
	public ArrayList<Feria> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select ");
		sql.append(" s.cod_planilha_ferias,  ");
		
		
        sql.append(" substr(u.nom_usuario,1,instr(u.nom_usuario,' ')-1)||' '|| ");
        sql.append(" substr(u.nom_usuario, instr(u.nom_usuario, ' ') + 1, instr(substr(u.nom_usuario, instr(u.nom_usuario, ' ') + 1, length(u.nom_usuario)), ' ') - 1) ");
        sql.append(" nom_usuario, ");

		
		sql.append(" s.cod_usuario, ");
		
		
        sql.append(" substr(s.substituto1,1,instr(s.substituto1,' ')-1)||' '|| ");
        sql.append(" substr(s.substituto1, instr(s.substituto1, ' ') + 1, instr(substr(s.substituto1, instr(s.substituto1, ' ') + 1, length(s.substituto1)), ' ') - 1) ");
        sql.append(" substituto1, ");
		
        sql.append(" substr(s.substituto2,1,instr(s.substituto2,' ')-1)||' '|| ");
        sql.append(" substr(s.substituto2, instr(s.substituto2, ' ') + 1, instr(substr(s.substituto2, instr(s.substituto2, ' ') + 1, length(s.substituto2)), ' ') - 1) ");
        sql.append(" substituto2, ");

        sql.append(" substr(s.substituto3,1,instr(s.substituto3,' ')-1)||' '|| ");
        sql.append(" substr(s.substituto3, instr(s.substituto3, ' ') + 1, instr(substr(s.substituto3, instr(s.substituto3, ' ') + 1, length(s.substituto3)), ' ') - 1) ");
        sql.append(" substituto3, ");

		sql.append(" s.qtidade_dias, ");
		sql.append(" to_char(s.data_inicio,'DD/MM/YYYY') data_inicio, ");
		sql.append(" to_char(s.data_fim,'DD/MM/YYYY') data_fim ");
 		sql.append(" from tb_planilha_ferias s, tb_usuario u ");
 		sql.append(" where ");
 		sql.append(" s.cod_usuario = u.cod_usuario ");


		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Feria> itens = new ArrayList<Feria>();

		while (resultado.next()) {
			
            Feria u = new Feria();
			u.setCod_planilha_ferias(resultado.getLong("cod_planilha_ferias"));
            u.setQtidade_dias(resultado.getLong("qtidade_dias"));
			u.setData_inicio(resultado.getString("data_inicio"));
			u.setData_fim(resultado.getString("data_fim"));
            
            
            Usuario p = new Usuario();
            p.setCod_usuario(resultado.getLong("cod_usuario"));
            p.setNom_usuario(resultado.getString("nom_usuario"));

            Usuario q = new Usuario();
            q.setNom_usuario(resultado.getString("substituto1"));
            
            Usuario r = new Usuario();
            r.setNom_usuario(resultado.getString("substituto2"));

            Usuario s = new Usuario();
            s.setNom_usuario(resultado.getString("substituto3"));

            u.setCod_usuario(p);
            u.setSubstituto1(q);
            u.setSubstituto2(r);
            u.setSubstituto3(s);
            
            itens.add(u);

		}
		return itens;

	}

	public void excluir(Feria p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_planilha_ferias ");
		sql.append("WHERE cod_planilha_ferias = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_planilha_ferias());
	
		comando.executeUpdate();

	}

    public void editar(Feria p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_planilha_ferias ");
	sql.append("set cod_usuario = ?, qtidade_dias = ?, data_inicio = ?, substituto1 = ?, substituto2 = ?, substituto3 = ?, data_fim = (to_date(?,'DD/MM/YYYY') + ?)  ");
	sql.append("WHERE ");
	sql.append("cod_planilha_ferias = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setLong(1, p.getCod_usuario().getCod_usuario());
    comando.setLong(2, p.getQtidade_dias());
    comando.setString(3, p.getData_inicio());
    comando.setString(4, p.getSubstituto1().getNom_usuario());
    comando.setString(5, p.getSubstituto2().getNom_usuario());
    comando.setString(6, p.getSubstituto3().getNom_usuario());

    comando.setString(7, p.getData_inicio());
    comando.setLong(8, p.getQtidade_dias());

    
    comando.setLong(9, p.getCod_planilha_ferias());
	
	comando.executeUpdate();

}
	

}
