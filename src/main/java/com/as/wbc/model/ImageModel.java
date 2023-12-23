package com.as.wbc.model;
import org.springframework.stereotype.Component;

@Component
public class ImageModel {

	private String countryName;
	private String countryCode;
	private String countryImage;
	private String countryCapital;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryImage() {
		return countryImage;
	}

	public void setCountryImage(String countryImage) {
		this.countryImage = countryImage;
	}

	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

}
