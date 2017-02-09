/**
 * 
 */
package com.trade.scaler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * @author Robert
 *
 */
public class TradeManager {
	
	private List<SignalPushTrade> tradeList;
	private List<String> spApiUrls;
	private boolean isTest = true;

	/**
	 * 
	 */
	public TradeManager(List<SignalPushTrade> tradeArray) {
		tradeList = new ArrayList<SignalPushTrade>();
		this.tradeList = tradeArray;	
	}
	
	private void buildSpUrlList(){
		String spUrl = "";
		for (SignalPushTrade thisTrade : tradeList){
			spUrl = buildSpUrl(thisTrade);
			spApiUrls.add(spUrl);
			System.out.println(spUrl);
		}
		
	}
	
	private String buildSpUrl(SignalPushTrade spTrade){
		// TODO: Bid and Ask Need to be pulled from somewhere ultimately calculating rate(bid + ask)/2 ...   Could pull from here... https://www.fxstreet.com/rates-charts/rates :: 
		Double bid = 1.85;
		Double ask = 1.66;
		
		String fixedRateString = getFixRate(bid,ask);
		String martingaleParam = spTrade.isMartingale() ? "&tradeType=martingale" : "";
		String familyParam = spTrade.isFamily() ? "$ff=true" : "";
		String spParamString = "returnType=piped&type=takeTrade" + martingaleParam + familyParam + "&mode=" + spTrade.getSpDirection() + "&asset=" + spTrade.getSpAsset() +  "&amount=" + spTrade.getSpAmount() + "&expiry=" + spTrade.getSpExpiry() + "&api=" + spTrade.getSpApi() + "&platform=" + spTrade.getPlatform() + "&name=" + spTrade.getSpName() + "&rate=" + fixedRateString;
		String spFullUrl = "http://"+spTrade.getSpIpAddress()+":"+spTrade.getSpPort()+"/?"+spParamString;
		return spFullUrl;
	}

	private String getFixRate(Double bid, Double ask) {
		Double rate = (bid + ask)/2;
		String rateString = String.valueOf(rate);
		String[] strArray = rateString.split("\\.");
		String begin = strArray[0];
		String end = strArray[1];
		int endLen = rateString.split("\\.")[1].length();
		end = end.substring(0,endLen);
		end = String.format("%1$-" + 5 + "s", end).replace(' ', '0');
		String fixedRateString = begin + "." + end;// rateString.substring(0, rateString.indexOf(".") + rateString.length());
		return fixedRateString;
	}

	
	public void sendTrades() {
		String url;
		//List<String> urls = new ArrayList<String>();
		
		// Build List of URLs
		for(SignalPushTrade thisTrade : tradeList){
			url = buildSpUrl(thisTrade);
			//urls.add(url);
			System.out.println(url);
			try {
				if(isTest){
					testSpRequest(url, thisTrade.getSpApi());					
				}else{
					sendSpRequest(url, thisTrade.getSpApi());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Send the Actual GETt Request to the API 
	 * @param url
	 * @throws Exception
	 */
	private void sendSpRequest(String url, String api){
		new TradeThread(api, url).start();
	}
	
	/**
	 * Simulate the GET Request to the API 
	 * @param url
	 * @throws Exception
	 */
	private void testSpRequest(String url, String api) throws Exception {
		new TestThread(api, url).start();
	}

	public List<SignalPushTrade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<SignalPushTrade> tradeList) {
		this.tradeList = tradeList;
	}

	public boolean isTest() {
		return isTest;
	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}

}
