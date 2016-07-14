package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.InstalacaoConfigDAO;
import br.com.dbatools.dao.TipoConfigDAO;
import br.com.dbatools.dao.LinkCadastroDAO;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.LinkCadastro;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBLinkCadastro")
@ViewScoped
public class LinkCadastroBean {
    private ArrayList<LinkCadastro> itens;
    private ArrayList<LinkCadastro> itensFiltrados;
    
    private LinkCadastro linkcadastro;
    private ArrayList<Usuario> comboUsuarios;
 
    private ArrayList<TipoConfig> comboTipoConfigs;
     

    public ArrayList<TipoConfig> getcomboTipoConfigs() {
		return comboTipoConfigs;
	}


	public void setcomboTipoConfigs(ArrayList<TipoConfig> comboTipoConfigs) {
		this.comboTipoConfigs = comboTipoConfigs;
	}

    
    


	public ArrayList<LinkCadastro> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<LinkCadastro> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<LinkCadastro> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<LinkCadastro> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public LinkCadastro getLinkCadastro() {
		return linkcadastro;
	}
    
    public void setLinkCadastro(LinkCadastro linkcadastro) {
		this.linkcadastro = linkcadastro;
	}
    
    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}
    
    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    
    
	public ArrayList<LinkCadastro> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<LinkCadastro> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
		LinkCadastroDAO dao = new LinkCadastroDAO();
		itens = dao.listar();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
		linkcadastro = new LinkCadastro();
		
		UsuarioDAO dao = new UsuarioDAO();
		InstalacaoConfigDAO per = new InstalacaoConfigDAO();
		TipoConfigDAO tic = new TipoConfigDAO();
		
		comboUsuarios = dao.listar();
	
		comboTipoConfigs = tic.listar();
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		LinkCadastroDAO dao = new LinkCadastroDAO();
		dao.salvar(linkcadastro);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("LinkCadastro Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			LinkCadastroDAO dao = new LinkCadastroDAO();
			
			dao.excluir(linkcadastro);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("LinkCadastro Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			
			UsuarioDAO dao = new UsuarioDAO();
			InstalacaoConfigDAO per = new InstalacaoConfigDAO();
			TipoConfigDAO tic = new TipoConfigDAO();
			
			comboUsuarios = dao.listar();

			comboTipoConfigs = tic.listar();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		LinkCadastroDAO dao = new LinkCadastroDAO();
		
		dao.editar(linkcadastro);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("LinkCadastro editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
