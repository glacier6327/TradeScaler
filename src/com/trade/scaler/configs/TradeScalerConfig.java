package com.trade.scaler.configs;

import java.util.List;

public class TradeScalerConfig {

	private String currencyPairs;
	private String expireCodes;
	private String binaryPlatform;
	private boolean familyOn;
	private boolean martingaleOn;
	private String scriptName;
	
	private List<ClientConfig> clients = null;
	private String defaultPrice;
	
	private String button1Text;
	private String button2Text;
	private String button3Text;
	private String button4Text;
	private String button5Text;
	private String button6Text;
	private String button7Text;
	private String button8Text;
	private boolean testMode;
	

	public String getCurrencyPairs() {
		return currencyPairs;
	}

	public void setCurrencyPairs(String currencyPairs) {
		this.currencyPairs = currencyPairs;
	}

	public String getExpireCodes() {
		return expireCodes;
	}

	public void setExpireCodes(String expiryCodes) {
		this.expireCodes = expiryCodes;
	}

	public String getBinaryPlatform() {
		return binaryPlatform;
	}

	public void setBinaryPlatform(String binaryPlatform) {
		this.binaryPlatform = binaryPlatform;
	}

	public Boolean getFamilyOn() {
		return familyOn;
	}

	public void setFamilyOn(Boolean familyOn) {
		this.familyOn = familyOn;
	}

	public List<ClientConfig> getClients() {
		return clients;
	}

	public void setClients(List<ClientConfig> clients) {
		this.clients = clients;
	}

	public String getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(String defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public String getButton1Text() {
		return button1Text;
	}

	public void setButton1Text(String button1Text) {
		this.button1Text = button1Text;
	}

	public String getButton2Text() {
		return button2Text;
	}

	public void setButton2Text(String button2Text) {
		this.button2Text = button2Text;
	}

	public String getButton3Text() {
		return button3Text;
	}

	public void setButton3Text(String button3Text) {
		this.button3Text = button3Text;
	}

	public String getButton4Text() {
		return button4Text;
	}

	public void setButton4Text(String button4Text) {
		this.button4Text = button4Text;
	}

	public String getButton5Text() {
		return button5Text;
	}

	public void setButton5Text(String button5Text) {
		this.button5Text = button5Text;
	}

	public String getButton6Text() {
		return button6Text;
	}

	public void setButton6Text(String button6Text) {
		this.button6Text = button6Text;
	}

	public String getButton7Text() {
		return button7Text;
	}

	public void setButton7Text(String button7Text) {
		this.button7Text = button7Text;
	}

	public String getButton8Text() {
		return button8Text;
	}

	public void setButton8Text(String button8Text) {
		this.button8Text = button8Text;
	}

	public boolean isTestMode() {
		return testMode;
	}

	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}

	public boolean isMartingaleOn() {
		return martingaleOn;
	}

	public void setMartingaleOn(boolean martingaleOn) {
		this.martingaleOn = martingaleOn;
	}

	public void setFamilyOn(boolean familyOn) {
		this.familyOn = familyOn;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

}
