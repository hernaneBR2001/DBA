package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.CadastroDbAppServidorDAO;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.domain.CadastroDbAppServidor;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBCadastroDbAppServidor")
@ViewScoped
public class CadastroDbAppServidorBean {
    private ArrayList<CadastroDbAppServidor> itens;
    private ArrayList<CadastroDbAppServidor> itensFiltrados;
    
    private CadastroDbAppServidor cadastrodbappservidor;
    private ArrayList<Cmdb> comboCmdbs;
    
    
    public ArrayList<CadastroDbAppServidor> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<CadastroDbAppServidor> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<CadastroDbAppServidor> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<CadastroDbAppServidor> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public CadastroDbAppServidor getCadastroDbAppServidor() {
		return cadastrodbappservidor;
	}
    
    public void setCadastroDbAppServidor(CadastroDbAppServidor cadastrodbappservidor) {
		this.cadastrodbappservidor = cadastrodbappservidor;
	}
    
    public ArrayList<Cmdb> getComboCmdbs() {
		return comboCmdbs;
	}
    
    
    public void setComboCmdbs(ArrayList<Cmdb> comboCmdbs) {
		this.comboCmdbs = comboCmdbs;
	}
    
    

	public void carregarListagem() {
		try {
		CadastroDbAppServidorDAO dao = new CadastroDbAppServidorDAO();
		itens = dao.listar();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
		cadastrodbappservidor = new CadastroDbAppServidor();
		
		CmdbDAO dao = new CmdbDAO();
		
		comboCmdbs = dao.listar();
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		CadastroDbAppServidorDAO dao = new CadastroDbAppServidorDAO();
		dao.salvar(cadastrodbappservidor);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("CadastroDbAppServidor Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			CadastroDbAppServidorDAO dao = new CadastroDbAppServidorDAO();
			
			dao.excluir(cadastrodbappservidor);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("CadastroDbAppServidor Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			
			CmdbDAO dao = new CmdbDAO();
			
			comboCmdbs = dao.listar();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		CadastroDbAppServidorDAO dao = new CadastroDbAppServidorDAO();
		
		dao.editar(cadastrodbappservidor);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("CadastroDbAppServidor editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
