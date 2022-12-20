package model;

public enum CAT {
	
	BLUE("/resources/benz_cat.png", "src/resources/words_3_letter.txt"),
	GREEN("/resources/nico_cat.png", "src/resources/words_6_letter.txt"),
	ORANGE("/resources/kaira_cat.png", "src/resources/words_g8_letter.txt"),
	RED("/resources/franz_cat.png", "src/resources/words_g8_letter.txt");
	
	String urlShip;
	String urlLevel;
	
	private CAT(String urlShip, String urlLevel) {
		this.urlShip = urlShip;
		this.urlLevel = urlLevel;
	}
	
	public String getUrl() {
		return this.urlShip;
	}
	
	public String getUrlLevel() {
		return urlLevel;
	}

}
