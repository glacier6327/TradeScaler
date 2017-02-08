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

/**
 * @author Robert
 *
 */
public class TradeManager {
	
	private List<SignalPushTrade> tradeList;
	private List<String> spApiUrls;

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
		String spParams = "returnType=piped&type=takeTrade" + spTrade.isMartingale() + spTrade.isFamily() + "&mode=" + spTrade.getSpDirection() + "&asset=" + spTrade.getSpAsset() +  "&amount=" + spTrade.getSpAmount() + "&expiry=" + spTrade.getSpExpiry() + "&api=" + spTrade.getSpApi() + "&platform=" + spTrade.getPlatform() + "&name=" + spTrade.getSpName() + "&rate=" + spTrade.getSpRate();
		String spUrl = "http://"+spTrade.getSpIpAddress()+":"+spTrade.getSpPort()+"/?"+spParams;
		return spUrl;
	}

	// HTTP GET request
	private void sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

}
