package br.com.dbatools.domain;

public class LinkCadastro {
private Long cod_link_cadastro;
private Usuario usuario = new Usuario();
private String descricao_link_cadastro;
private String link_cadastro;
private TipoConfig tipoconfig = new TipoConfig();
public Long getCod_link_cadastro() {
	return cod_link_cadastro;
}
public void setCod_link_cadastro(Long cod_link_cadastro) {
	this.cod_link_cadastro = cod_link_cadastro;
}
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public String getDescricao_link_cadastro() {
	return descricao_link_cadastro;
}
public void setDescricao_link_cadastro(String descricao_link_cadastro) {
	this.descricao_link_cadastro = descricao_link_cadastro;
}
public String getLink_cadastro() {
	return link_cadastro;
}
public void setLink_cadastro(String link_cadastro) {
	this.link_cadastro = link_cadastro;
}
public TipoConfig getTipoconfig() {
	return tipoconfig;
}
public void setTipoconfig(TipoConfig tipoconfig) {
	this.tipoconfig = tipoconfig;
}
}
