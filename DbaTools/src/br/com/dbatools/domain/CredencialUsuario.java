package br.com.dbatools.domain;

public class CredencialUsuario {
private Long cod_credencial;
private Usuario usuario = new Usuario();
private InstalacaoConfig instalacaoconfig = new InstalacaoConfig();
private String caminho;
private String comando;
private TipoConfig tipoconfig = new TipoConfig();
public Long getCod_credencial() {
	return cod_credencial;
}
public void setCod_credencial(Long cod_credencial) {
	this.cod_credencial = cod_credencial;
}
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public InstalacaoConfig getInstalacaoconfig() {
	return instalacaoconfig;
}
public void setInstalacaoconfig(InstalacaoConfig instalacaoconfig) {
	this.instalacaoconfig = instalacaoconfig;
}
public String getCaminho() {
	return caminho;
}
public void setCaminho(String caminho) {
	this.caminho = caminho;
}
public String getComando() {
	return comando;
}
public void setComando(String comando) {
	this.comando = comando;
}
public TipoConfig getTipoconfig() {
	return tipoconfig;
}
public void setTipoconfig(TipoConfig tipoconfig) {
	this.tipoconfig = tipoconfig;
}


}
