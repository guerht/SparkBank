import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client {
	// required for frame, page 1 and miscellaneous
	private JMenuBar jmb;
	private final static JFrame frame = new JFrame("SparkBank 2.0 (pre-alpha)");
	private JMenu file, help, cp;
	private JMenuItem read, save, refresh, exit, about;
	private static JPanel p = new JPanel();
	private JLabel welcome;
	private JLabel status;
	private JLabel options;
	private final static JLabel copyright = new JLabel("\u00a9 Copyright 2016 Seunghoon Park All Rights Reserved", SwingConstants.CENTER);
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JLabel blank, blank2, blank3, blank4, blank5;
	private ActiveEvent handler = new ActiveEvent();
	private Boolean confirmBoolean = true;
	//required for createAnAccountPage() panel;
	private JLabel description;
	private JLabel newFirstName;
	private JLabel newLastName;
	private JLabel newEmail;
	private JLabel newPIN;
	private JLabel confirmPin;
	private JTextField newFirstNametf;
	private JTextField newLastNametf;
	private JTextField newEmailtf;
	private JPasswordField newPINpf;
	private JPasswordField confirmPinpf;
	private JLabel asterisk;
	private JButton button_6;
	private JButton button_7;
	// private String cName;
	// private String cEmail;
	// private Integer cPIN;
	// used for logging in
	private JTextField loginAccountID = new JTextField();
	private JPasswordField loginPIN = new JPasswordField();
	private JCheckBox rememberMe = new JCheckBox("Remember Me");
	// used for logged in
	private JLabel loginWelcome;
	private JLabel loginMenu;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private int iac;
	private Object[] message = {"Account ID: ", loginAccountID, "PIN Number: ", loginPIN, rememberMe};
	// for withdrawal and deposits
	private JLabel balChangeDirects = new JLabel("Enter the amount you would like to withdraw: ");
	private JTextField listAmount = new JTextField();
	private JButton num1 = new JButton("1");
	private JButton num2 = new JButton("2");
	private JButton num3 = new JButton("3");
	private JButton[] firstRow = {num1, num2, num3};
	private JButton num4 = new JButton("4");
	private JButton num5 = new JButton("5");
	private JButton num6 = new JButton("6");
	private JButton[] secondRow = {num4, num5, num6};
	private JButton num7 = new JButton("7");
	private JButton num8 = new JButton("8");
	private JButton num9 = new JButton("9");
	private JButton[] thirdRow = {num7, num8, num9};
	private JButton decimal = new JButton(".");
	private JButton num0 = new JButton("0");
	private JButton clearinfo = new JButton("C");
	private JButton[] fourthRow = {decimal, num0, clearinfo};
	private Object[] numPad = {balChangeDirects, listAmount, firstRow, secondRow, thirdRow, fourthRow};
	private class FrameEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == refresh) {
				clearpage();
				page1();
			}
			else if(event.getSource() == exit) {
				System.exit(0);
			}
		}
	}
	private class ActiveEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == button_1) {
				if(currentServer.size() >= 10) {
					JOptionPane.showMessageDialog(frame, "The server has reached its user capacity. Please change to another server to register.");
				}
				else {
					JOptionPane.showMessageDialog(frame, "<html>Please note that when you create your account, your Account ID is automatically generated. <br>Your unique account ID cannot be modified once created. <br>Your newly generated account ID will always be required for all future logins, so please take a note of it.</html>");
					clearpage();
					createAnAccountPage();
				}
			}
			else if(event.getSource() == button_2) {
				int loginOption = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
				if(loginOption == JOptionPane.OK_OPTION) {
					if(currentServer.size() != 0) {
						for(iac = 0; iac < currentServer.size(); iac++) {
							if(currentServer.get(iac).getAccountID() == Integer.parseInt(loginAccountID.getText())) {
								if(currentServer.get(iac).getPIN() == Integer.parseInt(loginPIN.getText())) {
									JOptionPane.showMessageDialog(frame, "Welcome " + currentServer.get(iac).getName() + "! You are now logged in.");
									clearpage();
									loggedInPage();
									break;
								}
								else {
									JOptionPane.showMessageDialog(frame, "Incorrect PIN Number. Please Try again.");
									break;
								}
							}
							if(iac+1 == currentServer.size())
								JOptionPane.showMessageDialog(frame, "Incorrect Account ID or PIN Number. Please Try again.");
						}
					}
					else if(loginAccountID.getText().equals("") || loginPIN.getText().equals("") || loginAccountID.getText().equals(null) || loginPIN.getText().equals(null)) {
						JOptionPane.showMessageDialog(frame, "Please enter your Account ID and PIN Number correctly.");
					}
					else {
						JOptionPane.showMessageDialog(frame, "There are no registered users in this server.");
					}
					if(!(rememberMe.isSelected())) {
						loginAccountID.setText("");
					}
					loginPIN.setText("");
				}
				else {
					if(!(rememberMe.isSelected())) {
						loginAccountID.setText("");
					}
					loginPIN.setText("");
				}
			}
			else if(event.getSource() == button_3) {
				String s = (String) JOptionPane.showInputDialog(frame, "Note that different servers contain different account information.\n" + "An account made in one server cannot be used to be accessed in another.", "Change Server", JOptionPane.INFORMATION_MESSAGE, null, possibilities, "Asia");
				if(s.equals("Asia")) {
					serverNumber = 1;
					currentServer = asia;
					clearpage();
					page1();
				}
				else if(s.equals("America")) {
					serverNumber = 2;
					currentServer = america;
					clearpage();
					page1();
				}
				else if(s.equals("Europe")) {
					serverNumber = 3;
				 	currentServer = europe;
				 	clearpage();
				 	page1();
				 }
			}
			else if(event.getSource() == button_5) {
				System.out.println(currentServer.size());
			}
			else if(event.getSource() == button_4) {
				System.exit(0);
			}
			else if(event.getSource() == button_6) {
				for(int i = 0; i < newPINpf.getText().length(); i++) {
					if(newPINpf.getText().charAt(i) < '0' || newPINpf.getText().charAt(i) > '9') {
						JOptionPane.showMessageDialog(frame, "The PIN Numbers must contain only numbers.");
						confirmBoolean = false;
						break;
					}
				}
				if(confirmBoolean == true) {
					if(newPINpf.getText().length() != 6) {
						JOptionPane.showMessageDialog(frame, "The PIN Number you entered is not 6 digits.");
					}
					else if(Integer.parseInt(newPINpf.getText()) != Integer.parseInt(confirmPinpf.getText())) {
						JOptionPane.showMessageDialog(frame, "Your PIN does not match your confirmation PIN.");
					}
					else {
						int option = JOptionPane.showConfirmDialog(frame, "<html>Is the following information correct?<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name: " + newFirstNametf.getText() + " " + newLastNametf.getText() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;e-mail Address: " + newEmailtf.getText() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PIN Number: " + "******</html>", "Confirmation", JOptionPane.YES_NO_OPTION);
						if (option == 0) {
							int random = (int) (Math.random()*10000000);
							currentServer.add(new User(newFirstNametf.getText() + " " + newLastNametf.getText(), random, Integer.parseInt(newPINpf.getText())));
							JOptionPane.showMessageDialog(frame, "<html>Your new account has been generated!<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name: " + newFirstNametf.getText() + " " + newLastNametf.getText() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Account ID: " + random + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PIN Number: " + "******</html>");
							clearpage();
							page1();
						}
					}
				}
				confirmBoolean = true;
			}
			else if(event.getSource() == button_7) {
				clearpage();
				page1();
			}
			else if(event.getSource() == button_8) {
				num1.setBounds(20,30,50,30);
				JOptionPane.showMessageDialog(frame, "You have currently " + currentServer.get(iac).getBalance() + " credit(s) in your account.");
			}
			else if(event.getSource() == button_9) {
				int withdrawAmount = JOptionPane.showConfirmDialog(null, numPad, "Withdraw", JOptionPane.OK_CANCEL_OPTION);
			}
			else if(event.getSource() == button_10) {
				
			}
			else if(event.getSource() == button_11) {
				
			}
			else if(event.getSource() == button_12) {
				JOptionPane.showMessageDialog(frame, "You have successfully logged out. You will be redirected to the main page.");
				clearpage();
				page1();
			}

		}
	}
	// private static void testingNewPanel {
	// 	Jframe testframe = new Jframe();
	// 	testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// 	frame.setLocationRelativeTo(null);
	// 	frame.setVisible(true);

	// 	JPanel testpanel - new JPanel();
	// }
	private ArrayList<User> asia = new ArrayList<User>();
	private ArrayList<User> america = new ArrayList<User>();
	private ArrayList<User> europe = new ArrayList<User>();
	private ArrayList<User> currentServer;
	private Byte serverNumber = 1;
	private Object[] possibilities = {"Asia", "America", "Europe"};
	public void mainFrame() {
		// f = new JFrame("SparkBank 2.0");
		FrameEvent frameEvent = new FrameEvent();
		jmb = new JMenuBar();
		frame.setJMenuBar(jmb);
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		jmb.add(file);
		read = new JMenuItem("Import...");
		file.add(read);
		save = new JMenuItem("Save");
		file.add(save);
		file.addSeparator();
		refresh = new JMenuItem("Refresh Server");
		refresh.addActionListener(frameEvent);
		file.add(refresh);
		file.addSeparator();
		exit = new JMenuItem("Exit");
		exit.addActionListener(frameEvent);
		file.add(exit);
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		jmb.add(help);
		about = new JMenuItem("About...");
		help.add(about);
		frame.setVisible(true);
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void refreshServer() {
		if(serverNumber == 1)
			currentServer = asia;
		else if(serverNumber == 2)
			currentServer = america;
		else if(serverNumber == 3)
			currentServer = europe;
		if(currentServer.size() < 7)
			status = goodServer();
		else if(currentServer.size() < 9)
			status = fairServer();
		else if(currentServer.size() <= 10 || currentServer.size() > 10)
			status = fullServer();
		if(serverNumber == 1)
			status.setToolTipText(currentServer.size() + "/10 users registered in Asian server");
		else if(serverNumber == 2)
			status.setToolTipText(currentServer.size() + "/10 users registered in American server");
		else if(serverNumber == 3)
			status.setToolTipText(currentServer.size() + "/10 users registered in European server");

	}
	public JLabel fullServer() {
		return new JLabel("<html>Server Status: <font color='red'>BUSY</font></html>", SwingConstants.CENTER);
	}
	public JLabel fairServer() {
		return new JLabel("<html>Server Status: <font color='orange'>FAIR</font></html>", SwingConstants.CENTER);
	}
	public JLabel goodServer() {
		return new JLabel("<html>Server Status: <font color='green'>GOOD</font></html>", SwingConstants.CENTER);
	}
	public void page1() {
		p.setLayout(new GridLayout(10, 1, 3, 3));
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		welcome = new JLabel("Welcome to SparkBank 2.0 (pre-alpha)", SwingConstants.CENTER);
		p.add(welcome);
		refreshServer();
		p.add(status);
		options = new JLabel("Click on one of the options below: ", SwingConstants.CENTER);
		p.add(options);
		frame.add(p);
		button_1 = new JButton("Create an Account");
		button_1.setToolTipText("Create a bank account in the current server. Creating a bank account can only be done when the current server is not full.");
		p.add(button_1);
		button_2 = new JButton("Log In");
		button_2.setToolTipText("Log into the server with an existing bank account. An array of options will be available once logged in.");
		p.add(button_2);
		button_3 = new JButton("Change Server");
		button_3.setToolTipText("<html>Change the server in which the client is to be accessed. <br>Note that different servers contain different account information. <br>An account made in one server cannot be used to be accessed in another.</html>");
		p.add(button_3);
		button_5 = new JButton("Help");
		p.add(button_5);
		button_4 = new JButton("Exit");
		p.add(button_4);
		blank = new JLabel();
		button_1.addActionListener(handler);
		button_2.addActionListener(handler);
		button_3.addActionListener(handler);
		button_4.addActionListener(handler);
		button_5.addActionListener(handler);
		p.add(blank);
		copyright.setFont(copyright.getFont().deriveFont(Font.BOLD, 9));
		p.add(copyright);
	}
	public void createAnAccountPage() {
		// p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		description = new JLabel("Fill in the following form: ");
		p.add(description);
		blank = new JLabel();
		p.add(blank);
		newFirstName = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First Name<span color = 'red'>*</span>(?):</html> ");
		newFirstName.setToolTipText("<html>Include your middle name too if you have one.</html>");
		p.add(newFirstName);
		newFirstNametf = new JTextField(10);
		p.add(newFirstNametf);
		newLastName = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Last Name<span color='red'>*</span>:</html>");
		p.add(newLastName);
		newLastNametf = new JTextField(10);
		p.add(newLastNametf);
		newEmail = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;e-mail Address:</html> ");
		p.add(newEmail);
		newEmailtf = new JTextField(10);
		p.add(newEmailtf);
		newPIN = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PIN Number<span color = 'red'>*</span>(?):</html> ");
		newPIN.setToolTipText("Enter a six digit passcode.");
		p.add(newPIN);
		newPINpf = new JPasswordField(6);
		p.add(newPINpf);
		confirmPin = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Confirm PIN Number:<span color='red'>*</span></html>");
		p.add(confirmPin);
		confirmPinpf = new JPasswordField(6);
		p.add(confirmPinpf);
		asterisk = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<em><b>Note: </b><span color='red'>* </span>fields must be completed.<em></html>");
		p.add(asterisk);
		blank2 = new JLabel();
		p.add(blank2);
		blank3 = new JLabel();
		p.add(blank3);
		blank4 = new JLabel();
		p.add(blank4);
		button_6 = new JButton("Confirm");
		button_7 = new JButton("Cancel");
		p.add(button_6);
		p.add(button_7);
		button_6.addActionListener(handler);
		button_7.addActionListener(handler);
	}
	public void loggedInPage() {
		loginWelcome = new JLabel("Welcome, " + currentServer.get(iac).getName() + ".", SwingConstants.CENTER);
		p.add(loginWelcome);
		loginMenu = new JLabel("Click on one of the options below.", SwingConstants.CENTER);
		p.add(loginMenu);
		button_8 = new JButton("Check Balance");
		button_9 = new JButton("Withdraw");
		button_10 = new JButton("Deposit");
		button_11 = new JButton("Modify Account Information");
		button_12 = new JButton("Log out");
		button_8.addActionListener(handler);
		button_9.addActionListener(handler);
		button_10.addActionListener(handler);
		button_11.addActionListener(handler);
		button_12.addActionListener(handler);
		p.add(button_8);
		p.add(button_9);
		p.add(button_10);
		p.add(button_11);
		p.add(button_12);
	}
	public void clearpage() {
		p.removeAll();
		p.revalidate();
		p.repaint();
	}
	public static void main(String args[]) {
		Client client = new Client();
		client.mainFrame();
		client.clearpage();
		client.page1();
	}
}