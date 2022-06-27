package com.iwc.bean.param;

import org.springframework.web.multipart.MultipartFile;

public class GenImageWebsiteParamBean {
	
	private MultipartFile mainPic;
	private String mainPicColor;
	private MultipartFile portfolioImg1;
	private MultipartFile portfolioImg2;
	private MultipartFile portfolioImg3;
	
	private String title;
	private String navbarBrand;
	private String mastheadHeading;
	private String mastheadSubheading;
	private String location;
	private String aboutFooter;
	private String copyright;
	private String aboutLeft;
	private String aboutRight;
	
	public String getMainPicColor() {
		return mainPicColor;
	}
	public void setMainPicColor(String mainPicColor) {
		this.mainPicColor = mainPicColor;
	}
	public MultipartFile getMainPic() {
		return mainPic;
	}
	public void setMainPic(MultipartFile mainPic) {
		this.mainPic = mainPic;
	}
	public MultipartFile getPortfolioImg1() {
		return portfolioImg1;
	}
	public void setPortfolioImg1(MultipartFile portfolioImg1) {
		this.portfolioImg1 = portfolioImg1;
	}
	public MultipartFile getPortfolioImg2() {
		return portfolioImg2;
	}
	public void setPortfolioImg2(MultipartFile portfolioImg2) {
		this.portfolioImg2 = portfolioImg2;
	}
	public MultipartFile getPortfolioImg3() {
		return portfolioImg3;
	}
	public void setPortfolioImg3(MultipartFile portfolioImg3) {
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
