package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.FeriaDAO;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.domain.Feria;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBFeria")
@ViewScoped
public class FeriaBean {
    private ArrayList<Feria> itens;
    private ArrayList<Feria> itensFiltrados;
    
    private Feria feria;
    private ArrayList<Usuario> comboUsuarios;
    
    
    public ArrayList<Feria> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<Feria> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<Feria> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<Feria> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public Feria getFeria() {
		return feria;
	}
    
    public void setFeria(Feria feria) {
		this.feria = feria;
	}
    
    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}
    


    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    

	public void carregarListagem() {
		try {
		FeriaDAO dao = new FeriaDAO();
		itens = dao.listar();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
		feria = new Feria();
		
		UsuarioDAO dao = new UsuarioDAO();
		
		
		comboUsuarios = dao.listar();

		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		FeriaDAO dao = new FeriaDAO();
		dao.salvar(feria);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Ferias Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			FeriaDAO dao = new FeriaDAO();
			
			dao.excluir(feria);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Ferias Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			
			UsuarioDAO dao = new UsuarioDAO();
			
			
			comboUsuarios = dao.listar();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		FeriaDAO dao = new FeriaDAO();
		
		dao.editar(feria);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Ferias editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}

