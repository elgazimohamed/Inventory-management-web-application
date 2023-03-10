package models;
// Generated Jun 17, 2022 1:09:03 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ArticlesStock generated by hbm2java
 */
@Entity
@Table(name = "articles_stock", catalog = "stock")
public class ArticlesStock implements java.io.Serializable {

	private int codeArt;
	private int qteArt;
	private String nomArt;
	private String descArt;
	private int prixArt;

	public ArticlesStock() {
	}

	public ArticlesStock(int codeArt, int qteArt, String nomArt, String descArt, int prixArt) {
		this.codeArt = codeArt;
		this.qteArt = qteArt;
		this.nomArt = nomArt;
		this.descArt = descArt;
		this.prixArt = prixArt;
	}

	@Id

	@Column(name = "codeArt", unique = true, nullable = false)
	public int getCodeArt() {
		return this.codeArt;
	}

	public void setCodeArt(int codeArt) {
		this.codeArt = codeArt;
	}

	@Column(name = "qteArt", nullable = false)
	public int getQteArt() {
		return this.qteArt;
	}

	public void setQteArt(int qteArt) {
		this.qteArt = qteArt;
	}

	@Column(name = "nomArt", nullable = false, length = 30)
	public String getNomArt() {
		return this.nomArt;
	}

	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}

	@Column(name = "descArt", nullable = false, length = 200)
	public String getDescArt() {
		return this.descArt;
	}

	public void setDescArt(String descArt) {
		this.descArt = descArt;
	}

	@Column(name = "prixArt", nullable = false)
	public int getPrixArt() {
		return this.prixArt;
	}

	public void setPrixArt(int prixArt) {
		this.prixArt = prixArt;
	}

}
