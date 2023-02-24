package bean;

import java.util.List;

import javax.swing.JOptionPane;

import dao.ApprovisionementDAO;
import dao.ArticleDAO;
import models.ArticlesApprovisionnement;
import models.ArticlesStock;

public class UserBean {
	
	private Integer codeArt;
	private Integer qteArt;
	private String nomArt;
	private String descArt;
	private Integer prixArt;
	
	private Integer qteCommande;
	private String datePrevueLivraison;
	
	private ArticlesStock p = new ArticlesStock();
	private ArticleDAO catalogue = new ArticleDAO();
	
	private ArticlesApprovisionnement ap = new ArticlesApprovisionnement();
	private ApprovisionementDAO catalogue_ap = new ApprovisionementDAO();
	
	// Getters & Setters

	public Integer getCodeArt() {
		return codeArt;
	}
	public void setCodeArt(Integer codeArt) {
		this.codeArt = codeArt;
	}
	public Integer getQteArt() {
		return qteArt;
	}
	public void setQteArt(Integer qteArt) {
		this.qteArt = qteArt;
	}
	public String getNomArt() {
		return nomArt;
	}
	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}
	public String getDescArt() {
		return descArt;
	}
	public void setDescArt(String descArt) {
		this.descArt = descArt;
	}
	public Integer getPrixArt() {
		return prixArt;
	}
	public void setPrixArt(Integer prixArt) {
		this.prixArt = prixArt;
	}
	public Integer getQteCommande() {
		return qteCommande;
	}
	public void setQteCommande(Integer qteCommande) {
		this.qteCommande = qteCommande;
	}
	public String getDatePrevueLivraison() {
		return datePrevueLivraison;
	}
	public void setDatePrevueLivraison(String datePrevueLivraison) {
		this.datePrevueLivraison = datePrevueLivraison;
	}
	public ArticlesStock getP() {
		return p;
	}
	public void setP(ArticlesStock p) {
		this.p = p;
	}
	public ArticleDAO getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(ArticleDAO catalogue) {
		this.catalogue = catalogue;
	}
	public ArticlesApprovisionnement getAp() {
		return ap;
	}
	public void setAp(ArticlesApprovisionnement ap) {
		this.ap = ap;
	}
	public ApprovisionementDAO getCatalogue_ap() {
		return catalogue_ap;
	}
	public void setCatalogue_ap(ApprovisionementDAO catalogue_ap) {
		this.catalogue_ap = catalogue_ap;	
	}
	
	// fonction appelé pour vider les formulaires
	
	public void clearArticleFrom(){
	    setCodeArt(0);
	    setQteArt(0);
	    setNomArt(null);
	    setDescArt(null);
	    setPrixArt(0);
	}
	
	public void clearCommandeFrom(){
	    setCodeArt(0);
	    setQteCommande(0);
	    setDatePrevueLivraison(null);
	}
	
	// fonction appelé dans le fichier index.html qui permet d'enregistrer 
	// un nouveau article dans la BD
	public String Add() {
		if( getCodeArt()!= 0 && !getNomArt().isEmpty() && !getDescArt().isEmpty() && getQteArt()!=0 && getPrixArt()!=0 ) {
			this.p = new ArticlesStock(getCodeArt(), getQteArt(), getNomArt(),getDescArt(),getPrixArt());
			catalogue.addArticle(this.p);
			clearArticleFrom();
			return "success";
		}
		return "failure" ;
	}
	
	// fonction appelé dans le fichier index.xhtml pour afficher la liste des articles
	// disponible dans la base de données
	public List<ArticlesStock> Afficher(){
		return catalogue.ListArticle();
	}
	
	// fonction appelé dans le fichier index.xhtml pour rédiger l'utilisateur 
	// à la page modifier_article.xhtml el l'affiche les informations d'article qui'il veut modifier
	public String edit(ArticlesStock a) {
		this.p = a;
		return "modifier_article";
	}
	// fonction appelé dans le fichier modifier_article.xhtml pour modifier les informations 
	// d'article dans la base de données
	public String editArticle() {
		this.catalogue.updateArticle(this.p);
		return "save_article";
	}
	
	// fonction appelé dans le fichier index.xhtml qui permet de supprimer une article
	public void delete(ArticlesStock a) {
		this.catalogue.deleteArticle(a.getCodeArt());
	}
	// fonction appelé dans le fichier commandes.xhtml pour afficher la liste 
	// des commandes disponible dans la base de données 
	public List<ArticlesApprovisionnement> AfficherApprovisionement(){
		return catalogue_ap.ListApprovisionnement();
	}
	// fonction appelé dans le fichier index.xhtml qui rédige l'utilisateur à la page 
	// faire_une_commande.xhtml pour remplir les information sur la commande
	public String commander(ArticlesStock a) {
		this.ap.setCodeArt(a.getCodeArt()); 
		this.p=a;
		return "commander";
	}
	// fonction appelé dans le fichier faire_une_commande.xhtml qui permet d'enregistrer 
	// une nouvelle commande d'approvisionnement dans la base de données
	public String EnregistrerCommande() {	
		if( ap.getCodeArt() != 0 && getQteCommande()!= 0 && getDatePrevueLivraison() != null ) {
			this.ap = new ArticlesApprovisionnement(this.ap.getCodeArt(), getQteCommande(), getDatePrevueLivraison());
			catalogue_ap.addArticleApprovisionment(this.ap);
			clearCommandeFrom();
			return "success";
		}
		return "failure" ;
	}
	
	// fonction appelé dans le fichier commandes.xhtml qui permet de supprimer 
	// une commande d'approvisionnement
	public void deleteCommande(ArticlesApprovisionnement ap) {
		this.catalogue_ap.deleteArticle(ap);
	}
	
	// fonction appelé dans le fichier commandes.xhtml pour rédiger l'utilisateur 
	// à la page modifier_commandes.xhtml el l'affiche les info de la commande qui'il veut modifier
	public String editCommande(ArticlesApprovisionnement ap) {
		this.ap=ap;
		return "modifier_commande";
	}
	

	// fonction appelé dans le fichier modifier_commande.xhtml pour modifier les informations 
	// de la commande dans la base de données
	public String EditerCommande() {
		this.catalogue_ap.updateArticle(this.ap);
		return "save_commande";
	}
	
	//fonction qui permet de faire la redirection vers la page des articles index.xhtml
	public String AffichageArticlesPage() {
		return "articles_acceuil";
	}
	
	//fonction qui permet de faire la redirection vers la page des commandes commandes.xhtml
	public String AffichageCommandesPage() {
		return "commandes_acceuil";
	}
}
