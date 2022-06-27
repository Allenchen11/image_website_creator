package com.iwc.bean.vo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "web_download_record")
public class WebDownloadRecordVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seq;
	@Column(name = "main_pic_file")
	private Blob mainPicFile;
	@Column(name = "main_pic")
	private String mainPic;
	@Column(name = "main_pic_color")
	private String mainPicColor;
	@Column(name = "portfolio_img1_file")
	private Blob portfolioImg1File;
	@Column(name = "portfolio_img2_file")
	private Blob portfolioImg2File;
	@Column(name = "portfolio_img3_file")
	private Blob portfolioImg3File;
	@Column(name = "portfolio_img1")
	private String portfolioImg1;
	@Column(name = "portfolio_img2")
	private String portfolioImg2;
	@Column(name = "portfolio_img3")
	private String portfolioImg3;

	@Column(name = "title")
	private String title;
	@Column(name = "navbar_brand")
	private String navbarBrand;
	@Column(name = "masthead_heading")
	private String mastheadHeading;
	@Column(name = "masthead_sub_heading")
	private String mastheadSubheading;
	@Column(name = "location")
	private String location;
	@Column(name = "about_footer")
	private String aboutFooter;
	@Column(name = "copyright")
	private String copyright;
	@Column(name = "about_left")
	private String aboutLeft;
	@Column(name = "about_right")
	private String aboutRight;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Blob getMainPicFile() {
		return mainPicFile;
	}
	public void setMainPicFile(Blob mainPicFile) {
		this.mainPicFile = mainPicFile;
	}
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getMainPicColor() {
		return mainPicColor;
	}
	public void setMainPicColor(String mainPicColor) {
		this.mainPicColor = mainPicColor;
	}
	public Blob getPortfolioImg1File() {
		return portfolioImg1File;
	}
	public void setPortfolioImg1File(Blob portfolioImg1File) {
		this.portfolioImg1File = portfolioImg1File;
	}
	public Blob getPortfolioImg2File() {
		return portfolioImg2File;
	}
	public void setPortfolioImg2File(Blob portfolioImg2File) {
		this.portfolioImg2File = portfolioImg2File;
	}
	public Blob getPortfolioImg3File() {
		return portfolioImg3File;
	}
	public void setPortfolioImg3File(Blob portfolioImg3File) {
		this.portfolioImg3File = portfolioImg3File;
	}
	public String getPortfolioImg1() {
		return portfolioImg1;
	}
	public void setPortfolioImg1(String portfolioImg1) {
		this.portfolioImg1 = portfolioImg1;
	}
	public String getPortfolioImg2() {
		return portfolioImg2;
	}
	public void setPortfolioImg2(String portfolioImg2) {
		this.portfolioImg2 = portfolioImg2;
	}
	public String getPortfolioImg3() {
		return portfolioImg3;
	}
	public void setPortfolioImg3(String portfolioImg3) {
		this.portfolioImg3 = portfolioImg3;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNavbarBrand() {
		return navbarBrand;
	}
	public void setNavbarBrand(String navbarBrand) {
		this.navbarBrand = navbarBrand;
	}
	public String getMastheadHeading() {
		return mastheadHeading;
	}
	public void setMastheadHeading(String mastheadHeading) {
		this.mastheadHeading = mastheadHeading;
	}
	public String getMastheadSubheading() {
		return mastheadSubheading;
	}
	public void setMastheadSubheading(String mastheadSubheading) {
		this.mastheadSubheading = mastheadSubheading;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAboutFooter() {
		return aboutFooter;
	}
	public void setAboutFooter(String aboutFooter) {
		this.aboutFooter = aboutFooter;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getAboutLeft() {
		return aboutLeft;
	}
	public void setAboutLeft(String aboutLeft) {
		this.aboutLeft = aboutLeft;
	}
	public String getAboutRight() {
		return aboutRight;
	}
	public void setAboutRight(String aboutRight) {
		this.aboutRight = aboutRight;
	}
	
	

}
