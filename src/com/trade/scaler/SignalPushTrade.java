package com.trade.scaler;

public class SignalPushTrade {
	
	private String spIpAddress; 
	private String spPort; 
	private String spDirection;
	private String spAsset; 
	private String spAmount;
	private String spExpiry; 
	private String spApi;
	private String spName; 
	private String spRate;
	private String platform;
	private boolean martingale;
	private boolean family;

	public SignalPushTrade() {
		// TODO Auto-generated constructor stub
	}
	
	public String getSpIpAddress() {
		return spIpAddress;
	}

	public SignalPushTrade(String spIpAddress, String spPort, String spDirection, String spAsset, String spAmount,
			String spExpiry, String spApi, String spName, String spRate, String platform, boolean martingale,
			boolean family) {
		super();
		this.spIpAddress = spIpAddress;
		this.spPort = spPort;
		this.spDirection = spDirection;
		this.spAsset = spAsset;
		this.spAmount = spAmount;
		this.spExpiry = spExpiry;
		this.spApi = spApi;
		this.spName = spName;
		this.spRate = spRate;
		this.platform = platform;
		this.martingale = martingale;
		this.family = family;
	}

	public void setSpIpAddress(String spIpAddress) {
		this.spIpAddress = spIpAddress;
	}

	public String getSpPort() {
		return spPort;
	}

	public void setSpPort(String spPort) {
		this.spPort = spPort;
	}

	public String getSpDirection() {
		return spDirection;
	}

	public void setSpDirection(String spDirection) {
		this.spDirection = spDirection;
	}

	public String getSpAsset() {
		return spAsset;
	}

	public void setSpAsset(String spAsset) {
		this.spAsset = spAsset;
	}

	public String getSpAmount() {
		return spAmount;
	}

	public void setSpAmount(String spAmount) {
		this.spAmount = spAmount;
	}

	public String getSpExpiry() {
		return spExpiry;
	}

	public void setSpExpiry(String spExpiry) {
		this.spExpiry = spExpiry;
	}

	public String getSpApi() {
		return spApi;
	}

	public void setSpApi(String spApi) {
		this.spApi = spApi;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpRate() {
		return spRate;
	}

	public void setSpRate(String spRate) {
		this.spRate = spRate;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public boolean isMartingale() {
		return martingale;
	}

	public void setMartingale(boolean martingale) {
		this.martingale = martingale;
	}

	public boolean isFamily() {
		return family;
	}

	public void setFamily(boolean family) {
		this.family = family;
	}

}
