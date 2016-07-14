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
import br.com.dbatools.domain.LinkCadastro;
import br.com.dbatools.factory.ConexaoFactory;

public class LinkCadastroDAO {
	
	public void salvar(LinkCadastro p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_link_cadastro ");
		sql.append(" (cod_link_cadastro,cod_usuario,descricao_link_cadastro,link_cadastro,cod_tipo) ");
		sql.append(" values (seq_link_cadastro.nextval,?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, p.getUsuario().getCod_usuario());
		comando.setString(2, p.getDescricao_link_cadastro());
		comando.setString(3, p.getLink_cadastro());
		comando.setLong(4, p.getTipoconfig().getCod_tipo());
		

		comando.executeUpdate();

	}
	
	public ArrayList<LinkCadastro> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();


                sql.append(" select c.cod_link_cadastro, ");
                sql.append("        u.cod_usuario, ");
                sql.append(" substr(u.nom_usuario,1,instr(u.nom_usuario,' ')-1)||' '|| ");
                sql.append(" substr(u.nom_usuario, instr(u.nom_usuario, ' ') + 1, instr(substr(u.nom_usuario, instr(u.nom_usuario, ' ') + 1, length(u.nom_usuario)), ' ') - 1) ");
                sql.append(" nom_usuario, ");
                sql.append("        c.descricao_link_cadastro, ");
                sql.append("        c.link_cadastro, ");
                sql.append("        t.cod_tipo, ");
                sql.append("        t.tipo ");
                sql.append(" from  tb_link_cadastro c,tb_usuario u, tb_tipo_config t ");
                sql.append(" where ");
                sql.append(" c.cod_usuario = u.cod_usuario and ");
                sql.append(" c.cod_tipo = t.cod_tipo and ");
                sql.append(" u.cod_usuario = 24 ");
                sql.append(" order by t.cod_tipo ");


		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<LinkCadastro> itens = new ArrayList<LinkCadastro>();

		while (resultado.next()) {
			
            LinkCadastro u = new LinkCadastro();
			
            u.setCod_link_cadastro(resultado.getLong("cod_link_cadastro"));
            u.setDescricao_link_cadastro(resultado.getString("descricao_link_cadastro"));
            u.setLink_cadastro(resultado.getString("link_cadastro"));
            
            Usuario a = new Usuario();
            
            a.setCod_usuario(resultado.getLong("cod_usuario"));
            a.setNom_usuario(resultado.getString("nom_usuario"));
            
            TipoConfig c = new TipoConfig();
            
            c.setCod_tipo(resultado.getLong("cod_tipo"));
            c.setTipo(resultado.getString("tipo"));
            
            u.setUsuario(a);
            u.setTipoconfig(c);

            itens.add(u);

		}
		return itens;

	}

	public void excluir(LinkCadastro p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_link_cadastro ");
		sql.append("WHERE cod_link_cadastro = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_link_cadastro());

		comando.executeUpdate();

	}

    public void editar(LinkCadastro p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_link_cadastro ");
	sql.append("set link_cadastro = ?  ");
	sql.append("WHERE ");
	sql.append("cod_link_cadastro = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getLink_cadastro());
    comando.setLong(2, p.getCod_link_cadastro());
	
	comando.executeUpdate();

}
	

}
