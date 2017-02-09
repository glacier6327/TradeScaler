package com.trade.scaler;

import javax.swing.JOptionPane;

public class TestThread extends Thread{
	private String url;

	public TestThread(String str, String url) {
		super(str);
		this.url = url;
	}

	public void run(){
		JOptionPane.showMessageDialog(null, url, "SP API Call Test... This is what would be sent...", JOptionPane.INFORMATION_MESSAGE);
	}

}
