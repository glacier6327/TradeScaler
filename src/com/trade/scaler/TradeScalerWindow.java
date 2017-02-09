package com.trade.scaler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.google.gson.Gson;
import com.trade.scaler.configs.ClientConfig;
import com.trade.scaler.configs.TradeScalerConfig;

public class TradeScalerWindow {

	private JFrame frmTradescaler;
	JComboBox cmbTradeExpire;
	JComboBox<String> cmbCurrencyPair;
	JTextField txtTradeAmount;
	TradeScalerConfig appConfigs;
	JList<String> lstClients;
	JLabel lblClientsLoaded;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JButton btn5;
	JButton btn6;
	JButton btn7;
	JButton btn8;
	String direction;
	/**
	 * Create the application.
	 */
	public TradeScalerWindow() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TradeScalerWindow window = new TradeScalerWindow();
					window.frmTradescaler.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTradescaler = new JFrame();
		frmTradescaler.setTitle("TradeScaler Beta");
		frmTradescaler.setBounds(100, 100, 752, 484);
		frmTradescaler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTradescaler.getContentPane().setLayout(null);
		frmTradescaler.setAlwaysOnTop(true);

		/**
		 * Add Call Button
		 */
		JButton btnCall = new JButton("Call");
		btnCall.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnCall.setBounds(548, 338, 107, 43);
		frmTradescaler.getContentPane().add(btnCall);
		btnCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Build the Trade List
				List<SignalPushTrade> tradeList = buildTrades("Call");
				// Initalize the TradeManager
				TradeManager tradeManager = new TradeManager(tradeList);
				// Request Trade
				tradeManager.sendTrades(); 

			}
		});

		/**
		 * Add Put Button
		 */
		JButton btnPut = new JButton("Put");
		btnPut.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnPut.setBounds(363, 340, 107, 43);
		frmTradescaler.getContentPane().add(btnPut);
		btnPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Build the Trade List
				List<SignalPushTrade> tradeList = buildTrades("Put");
				// Initalize the TradeManager
				TradeManager tradeManager = new TradeManager(tradeList);
				// Request Trade
				tradeManager.sendTrades(); 
			}
		});

		/**
		 * Add Scrolling Pane for the Clients List
		 */
		JScrollPane scp = new JScrollPane();
		scp.setBounds(56, 73, 223, 207);
		frmTradescaler.getContentPane().add(scp);

		/**
		 * Add Clients List to Scroll Pane
		 */
		lstClients = new JList<String>();
		lstClients.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lstClients.setBounds(56, 73, 223, 207);
		scp.setViewportView(lstClients);

		/**
		 * Add Clients Loaded Info Label
		 */
		lblClientsLoaded = new JLabel("No Clients Loaded");
		lblClientsLoaded.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClientsLoaded.setBounds(101, 301, 162, 26);
		frmTradescaler.getContentPane().add(lblClientsLoaded);

		/**
		 * Add Select Clients Button
		 */
		JButton btnLoadClients = new JButton("Load Clients");
		btnLoadClients.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoadClients.setBounds(71, 338, 193, 43);
		frmTradescaler.getContentPane().add(btnLoadClients);

		/**
		 * Add Currency Pair Combo Box
		 */
		cmbCurrencyPair = new JComboBox<String>();
		cmbCurrencyPair.setToolTipText("Select the type of Forex Trade");
		cmbCurrencyPair.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cmbCurrencyPair.setBounds(533, 73, 135, 32);
		frmTradescaler.getContentPane().add(cmbCurrencyPair);

		/**
		 * Add Text Input Field for Dollar Value for trade
		 */
		txtTradeAmount = new JTextField();
		txtTradeAmount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtTradeAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtTradeAmount.setText("");
		txtTradeAmount.setBounds(533, 179, 139, 32);
		frmTradescaler.getContentPane().add(txtTradeAmount);
		txtTradeAmount.setColumns(10);

		/**
		 * Add Expire Combo Box
		 */
		cmbTradeExpire = new JComboBox();
		cmbTradeExpire.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cmbTradeExpire.setBounds(533, 126, 135, 32);
		frmTradescaler.getContentPane().add(cmbTradeExpire);

		/**
		 * Add Listener for Add Clients Button
		 */
		btnLoadClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGUI();

			}

		});

		/**
		 * Add Quick Set Buttons for Price
		 */
		btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton1Text().contains(".") ? appConfigs.getButton1Text() : appConfigs.getButton1Text() + ".00");
			}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.setBounds(340, 245, 67, 23);
		frmTradescaler.getContentPane().add(btn1);

		btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton4Text().contains(".") ? appConfigs.getButton4Text() : appConfigs.getButton4Text() + ".00");
			}
		});
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn4.setBounds(611, 245, 67, 23);
		frmTradescaler.getContentPane().add(btn4);

		btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton2Text().contains(".") ? appConfigs.getButton2Text() : appConfigs.getButton2Text() + ".00");
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.setBounds(428, 245, 67, 23);
		frmTradescaler.getContentPane().add(btn2);

		btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton3Text().contains(".") ? appConfigs.getButton3Text() : appConfigs.getButton3Text() + ".00");
			}
		});
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn3.setBounds(523, 245, 67, 23);
		frmTradescaler.getContentPane().add(btn3);

		btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton5Text().contains(".") ? appConfigs.getButton5Text() : appConfigs.getButton5Text() + ".00");
			}
		});
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn5.setBounds(338, 289, 67, 23);
		frmTradescaler.getContentPane().add(btn5);

		btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton6Text().contains(".") ? appConfigs.getButton6Text() : appConfigs.getButton6Text() + ".00");
			}
		});
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn6.setBounds(426, 289, 67, 23);
		frmTradescaler.getContentPane().add(btn6);

		btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton7Text().contains(".") ? appConfigs.getButton7Text() : appConfigs.getButton7Text() + ".00");
			}
		});
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn7.setBounds(521, 289, 67, 23);
		frmTradescaler.getContentPane().add(btn7);

		btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTradeAmount.setText(appConfigs.getButton8Text().contains(".") ? appConfigs.getButton8Text() : appConfigs.getButton8Text() + ".00");
			}
		});
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn8.setBounds(609, 289, 67, 23);
		frmTradescaler.getContentPane().add(btn8);

		/**
		 * Add Labels
		 */
		JLabel lblTardeType = new JLabel("Currency Pair");
		lblTardeType.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTardeType.setBounds(338, 77, 150, 26);
		frmTradescaler.getContentPane().add(lblTardeType);

		JLabel lblClients = new JLabel("Clients");
		lblClients.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblClients.setBounds(117, 21, 119, 32);
		frmTradescaler.getContentPane().add(lblClients);

		JLabel lblTradeWindow = new JLabel("Trade Expire");
		lblTradeWindow.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTradeWindow.setBounds(338, 129, 150, 26);
		frmTradescaler.getContentPane().add(lblTradeWindow);

		JLabel lblTradeAmount = new JLabel("Trade Amount");
		lblTradeAmount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTradeAmount.setBounds(338, 176, 150, 26);
		frmTradescaler.getContentPane().add(lblTradeAmount);

		JLabel lblMakeATrade = new JLabel("Trading");
		lblMakeATrade.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMakeATrade.setBounds(469, 21, 90, 32);
		frmTradescaler.getContentPane().add(lblMakeATrade);

	}

	/**
	 *  Creates a List of SignalPushTrade from the appConfigs, Form Fields, and Trade Button
	 */
	protected List<SignalPushTrade> buildTrades(String direction) {
		List<SignalPushTrade> theseSpTrades = new ArrayList<SignalPushTrade>();
		for(ClientConfig thisClient : appConfigs.getClients()){
			SignalPushTrade spTrade = new SignalPushTrade();
			spTrade.setFamily(appConfigs.getFamilyOn());
			spTrade.setMartingale(spTrade.isMartingale()) ;
			spTrade.setPlatform(appConfigs.getBinaryPlatform());
			spTrade.setSpAmount(txtTradeAmount.getText());
			spTrade.setSpApi(thisClient.getApi());
			spTrade.setSpAsset(cmbCurrencyPair.getSelectedItem().toString());
			spTrade.setSpDirection(direction);
			spTrade.setSpExpiry(cmbTradeExpire.getSelectedItem().toString());
			spTrade.setSpIpAddress(thisClient.getIpaddress());
			spTrade.setSpName(appConfigs.getScriptName());
			spTrade.setSpPort(thisClient.getPort());
			spTrade.setSpRate("ratemissing");
			theseSpTrades.add(spTrade);
		}	
		return theseSpTrades;
	}

	/**
	 *  Loads AppConfigs file and Configures UI
	 */
	protected void loadGUI() {
		DefaultListModel<String> DLM = new DefaultListModel<String>();
		int clientCount = 0;
		
		//Load Application Configs
		appConfigs = readAppConfig();
		// Set Drop Down List from App Configs
		String[] currencyPairs = appConfigs.getCurrencyPairs().split("\\|");
		String[] expiryCodes = appConfigs.getExpireCodes().split("\\|");
		cmbCurrencyPair.setModel(new DefaultComboBoxModel<String>(currencyPairs));
		cmbTradeExpire.setModel(new DefaultComboBoxModel<String>(expiryCodes));
		// Set Clients List
		List<ClientConfig> clientConfigs = appConfigs.getClients();
		clientCount = clientConfigs.size();
		System.out.println("Client Count" + clientCount);
		for (ClientConfig thisConfig : clientConfigs) {
			DLM.addElement(thisConfig.getName());
		}
		lstClients.setModel(DLM);
		// Update Label for Clients Count
		lblClientsLoaded.setText(clientCount + " Clients Loaded");
		txtTradeAmount.setText(appConfigs.getDefaultPrice());
		btn1.setText(appConfigs.getButton1Text());
		btn2.setText(appConfigs.getButton2Text());
		btn3.setText(appConfigs.getButton3Text());
		btn4.setText(appConfigs.getButton4Text());
		btn5.setText(appConfigs.getButton5Text());
		btn6.setText(appConfigs.getButton6Text());
		btn7.setText(appConfigs.getButton7Text());
		btn8.setText(appConfigs.getButton8Text());
		
	}
	
	/**
	 * Reads the App Config File
	 */
	private TradeScalerConfig readAppConfig() {

		Gson gson = new Gson();
		TradeScalerConfig tdConfigs = null;

		try {
			BufferedReader br = new BufferedReader(
					new FileReader("C:\\Users\\Robert\\workspace\\TradeScaler\\resources\\TradeScaler.json"));
			tdConfigs = gson.fromJson(br, TradeScalerConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tdConfigs;
	}

}
