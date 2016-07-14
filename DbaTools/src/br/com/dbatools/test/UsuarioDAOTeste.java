package br.com.dbatools.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.Usuario;
import jdk.nashorn.internal.ir.annotations.Ignore;



public class UsuarioDAOTeste {

	@Test
	public void listar() throws SQLException {
		CmdbDAO dao = new CmdbDAO();
		
		ArrayList<Cmdb> lista = dao.listar();
		
		for(Cmdb p : lista ){
			
			System.out.println(p.getContato1().getNom_usuario());
			System.out.println("");
		}
	}


}
