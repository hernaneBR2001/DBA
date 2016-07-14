package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.domain.BaseConhecimento;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;

public class BaseConhecimentoDAO {
	
	public void salvar(BaseConhecimento p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_CONHECIMENTO ");
		sql.append(" (cod_conhecimento,cod_empresa,servidor,database,titulo_doc,link) ");
		sql.append(" values (seq_conhecimento.nextval,?,?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

	
        comando.setLong(1, p.getEmpresa().getCod_empresa());
        comando.setString(2, p.getServidor().getServidor());
        comando.setString(3, p.getDatabase().getDatabase());
        comando.setString(4, p.getTitulo_doc());
        comando.setString(5, p.getLink());
        
     
        
	   comando.executeUpdate();

	}
	
	public ArrayList<BaseConhecimento> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append(" select  ");
		sql.append(" a.cod_conhecimento, e.nom_empresa, ");
		sql.append(" a.cod_empresa, ");
		sql.append(" a.servidor, ");
		sql.append(" a.database, ");
		sql.append(" a.titulo_doc, ");
		sql.append(" a.link ");
		sql.append(" from TB_CONHECIMENTO A, TB_EMPRESA E ");
		sql.append(" WHERE A.COD_EMPRESA = E.COD_EMPRESA ");


		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<BaseConhecimento> itens = new ArrayList<BaseConhecimento>();

		while (resultado.next()) {
			
            BaseConhecimento u = new BaseConhecimento();
			u.setCod_conhecimento(resultado.getLong("cod_conhecimento"));
			u.setTitulo_doc(resultado.getString("titulo_doc"));
            u.setLink(resultado.getString("link"));
            
            
            Cmdb p = new Cmdb();
            p.setServidor(resultado.getString("servidor"));

            Cmdb r = new Cmdb();
            r.setDatabase(resultado.getString("database"));
            
            
            Empresa e = new Empresa();
            e.setCod_empresa(resultado.getLong("cod_empresa"));
            e.setNom_empresa(resultado.getString("nom_empresa"));
            
            u.setServidor(p);
            u.setDatabase(r);
            u.setEmpresa(e);
            
            itens.add(u);

		}
		return itens;

	}

	public void excluir(BaseConhecimento p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_CONHECIMENTO ");
		sql.append("WHERE cod_conhecimento = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_conhecimento());
	
		comando.executeUpdate();

	}

    public void editar(BaseConhecimento p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE TB_CONHECIMENTO ");
	sql.append("set cod_empresa = ? ,servidor = ? ,database = ? ,titulo_doc = ? ,link = ?  ");
	sql.append("WHERE ");
	sql.append("cod_conhecimento = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setLong(1, p.getEmpresa().getCod_empresa());
    comando.setString(2, p.getServidor().getServidor());
    comando.setString(3, p.getDatabase().getDatabase());
    comando.setString(4, p.getTitulo_doc());
    comando.setString(5, p.getLink());
    
    
    comando.setLong(6, p.getCod_conhecimento());
	
	comando.executeUpdate();

}
	

}
