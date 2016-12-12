import java.util.ArrayList;
// import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.text.NumberFormat;
import javax.swing.ImageIcon;

/**
 * @author	Seunghoon Park <spark.knights.rule@gmail.com>
 * @version 2.0.1(beta) Build 0003
 * @date 12th December 2016 01:35PM
 * @since 2.0.1
 * 
 * Version 2.0.1 Build 0002 Patch notes:
 *	* Added a modifyAccountPage()!
 *		* Constructed very similarly to the createUserAccountPage, except it modifies the user's personal information instead
 * 	* Fixed an issue where the confirmPinpf on the createAnAccountPage() would not recognise an error if there were a non-numerical character on its field.
 *  * Fixed a bug where if the user attempted to login with a blank loginPIN it would return an exception on the terminal
 *	* Fixed a bug where if the user attempted to login with characters on the loginPIN passwordfield it would return an exception on the terminal
 * Version 2.0.1 Build 0003 Patch Notes
 *	* Changed all words pre-alpha to beta
 *
 *
 *
 */

public class Client {
	// required for frame, page 1 and miscellaneous
	private JMenuBar jmb;
	private final static JFrame frame = new JFrame("SparkBank 2.0 (beta)");
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
	private JLabel blank, blank2, blank3, blank4, blank5; // blank fillers used to fill a blank grid
	private ActiveEvent handler = new ActiveEvent(); // used for main buttons
	private Boolean confirmBoolean = true; // checks when registering whether the entered PIN number is actually only consisted of numbers.
	private Boolean confirmBoolean2 = true;
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
	//required for modifyAccountPage()
	private JLabel mAPDescription;
	private JLabel mAPFirstName;
	private JLabel mAPLastName;
	private JLabel maPPIN;
	private JLabel maPconfirmPIN;
	private JTextField mAPFirstNametf;
	private JTextField mAPLastNametf;
	private JPasswordField maPPINpf;
	private JPasswordField maPconfirmPINpf;
	private JButton button_13;
	private JButton button_14;
	private Boolean confirmBoolean3 = true;
	// used for logging in
	private JTextField loginAccountID = new JTextField();
	private JPasswordField loginPIN = new JPasswordField();
	private JCheckBox rememberMe = new JCheckBox("Remember Me");
	private Boolean stringLoginPreventer;
	// used for logged in
	private JLabel loginWelcome;
	private JLabel loginMenu;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private int iac; // used for looping though the arraylist of the current server
	private Object[] message = {"Account ID: ", loginAccountID, "PIN Number: ", loginPIN, rememberMe};
	// for withdrawal and deposits
	private JLabel balChangeDirects = new JLabel();
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
	private boolean onlyOnce = false;
	private NumberEvent numberEvent = new NumberEvent();
	private double withDep$;
	private boolean isDecimalUsed = false;
	private ImageIcon tick = createImageIcon("tick.png");
	private JLabel aboutMsgLabel = new JLabel("<html><span style=font-size:15px;>SparkBank 2.0 (beta)<br></span><span font-size:10px>Under Development<br><br><br></span><span font-size:9px>\u00a9 Copyright 2016 Seunghoon Park All Rights Reserved<br>Version 2.0.1 Build 0003</span></html>");
	private Object[] aboutMsg = {aboutMsgLabel};
	// used for modifying user information while logged in
	private JPasswordField modLoginPIN = new JPasswordField();
	private Object[] modAccountMessage = {"<html>Please enter your account <br>PIN for further access: </html>", modLoginPIN};
	// used to generate the tick icon (study this in-depth later)
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Client.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    // actionlistener for jframe options
	private class FrameEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == refresh) {
				clearpage();
				page1();
			}
			else if(event.getSource() == exit) {
				System.exit(0);
			}
			else if(event.getSource() == about) {
				JOptionPane.showOptionDialog(null, aboutMsg, "About...", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);	
			}
		}
	}
	// actionlistener for numpad
	private class NumberEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == num1) {
				listAmount.setText(listAmount.getText()+"1");
			}
			else if(event.getSource() == num2) {
				listAmount.setText(listAmount.getText()+"2");
			}
			else if(event.getSource() == num3) {
				listAmount.setText(listAmount.getText()+"3");
			}
			else if(event.getSource() == num4) {
				listAmount.setText(listAmount.getText()+"4");
			}
			else if(event.getSource() == num5) {
				listAmount.setText(listAmount.getText()+"5");
			}
			else if(event.getSource() == num6) {
				listAmount.setText(listAmount.getText()+"6");
			}
			else if(event.getSource() == num7) {
				listAmount.setText(listAmount.getText()+"7");
			}
			else if(event.getSource() == num8) {
				listAmount.setText(listAmount.getText()+"8");
			}
			else if(event.getSource() == num9) {
				listAmount.setText(listAmount.getText()+"9");
			}
			else if(event.getSource() == num0) {
				listAmount.setText(listAmount.getText()+"0");
			}
			else if(event.getSource() == clearinfo) {
				listAmount.setText(null);
				decimal.setEnabled(true);
				isDecimalUsed = false;
			}
			else if(event.getSource() == decimal) {
				if(isDecimalUsed == false) {
					listAmount.setText(listAmount.getText()+".");
					decimal.setEnabled(false);
					isDecimalUsed = true;
				}
			}
		}
	}
	// actionlistener for main buttons in menu
	private class ActiveEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == button_1) {
				if(currentServer.size() >= 10) {
					JOptionPane.showMessageDialog(frame, "The server has reached its maximum user capacity. Please change to another server to register.");
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
					if(!(loginPIN.getText().equals(""))) {
						if(currentServer.size() != 0) {
							for(iac = 0; iac < currentServer.size(); iac++) {
								if(currentServer.get(iac).getAccountID() == Integer.parseInt(loginAccountID.getText())) {
									try {
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
									catch (NumberFormatException e) {
										JOptionPane.showMessageDialog(frame, "Please enter numerical characters only on the PIN field.", "Login Error", JOptionPane.ERROR_MESSAGE);
										stringLoginPreventer = true;
									}
								}
								if(iac+1 == currentServer.size() && !(stringLoginPreventer))
									JOptionPane.showMessageDialog(frame, "Incorrect Account ID or PIN Number. Please Try again.");
								stringLoginPreventer = true;
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
						JOptionPane.showMessageDialog(frame, "Please enter your PIN number.");
					}
				}
				else {
					if(!(rememberMe.isSelected())) {
						loginAccountID.setText("");
					}
					loginPIN.setText("");
				}
			}
			else if(event.getSource() == button_3) {
				try {
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
				catch (Exception e) {
					// System.out.println("There are actually no errors.");
				}
			}
			else if(event.getSource() == button_5) {
				System.out.println(currentServer.size());
			}
			else if(event.getSource() == button_4) {
				System.exit(0);
			}
			else if(event.getSource() == button_6) {
				if(newFirstNametf.getText().equals("") || newLastNametf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid name.");
				}
				else {
					for(int i = 0; i < newPINpf.getText().length(); i++) {
						if(newPINpf.getText().charAt(i) < '0' || newPINpf.getText().charAt(i) > '9') {
							JOptionPane.showMessageDialog(frame, "The PIN Numbers must contain only numbers.");
							confirmBoolean = false;
							break;
						}
					}
					if(confirmBoolean) {
						for(int i = 0; i < confirmPinpf.getText().length(); i++) {
							if(confirmPinpf.getText().charAt(i) < '0' || confirmPinpf.getText().charAt(i) > '9') {
								JOptionPane.showMessageDialog(frame, "The PIN Numbers must contain only numbers.");
								confirmBoolean = false;
								break;
							}
						}
					}
					if(confirmBoolean) {
						if(newPINpf.getText().length() != 6 || confirmPinpf.getText().length() != 6) {
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
				balChangeDirects.setText("Enter the amount you would like to withdraw: ");
				if(onlyOnce == false) {
					num1.addActionListener(numberEvent);
					num2.addActionListener(numberEvent);
					num3.addActionListener(numberEvent);
					num4.addActionListener(numberEvent);
					num5.addActionListener(numberEvent);
					num6.addActionListener(numberEvent);
					num7.addActionListener(numberEvent);
					num8.addActionListener(numberEvent);
					num9.addActionListener(numberEvent);
					num0.addActionListener(numberEvent);
					decimal.addActionListener(numberEvent);
					clearinfo.addActionListener(numberEvent);
					onlyOnce = true;
				}
				int withdrawAmount = JOptionPane.showConfirmDialog(null, numPad, "Withdraw", JOptionPane.OK_CANCEL_OPTION);
				if(withdrawAmount == JOptionPane.OK_OPTION) {
					try {
						withDep$ = Double.parseDouble(listAmount.getText());
						if (currentServer.get(iac).getBalance() < withDep$) {
							JOptionPane.showMessageDialog(frame, "You have insufficient funds to withdraw such amount from your account. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else if(withDep$ == 0) {
							JOptionPane.showMessageDialog(frame, "When Withdrawing, the value must be higher than 0. Please try again.", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
						else {
							currentServer.get(iac).withdraw(withDep$);
							JOptionPane.showMessageDialog(frame, "<html>You have successfully withdrawn " + withDep$ + " credit(s) from your account. <br>You now have " + currentServer.get(iac).getBalance() + "credit(s) remaining.</html>", "Withdrawal Success", JOptionPane.INFORMATION_MESSAGE, tick);
						}
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					finally {
						listAmount.setText(null);
						decimal.setEnabled(true);
						isDecimalUsed = false;
					}
				}
			}
			else if(event.getSource() == button_10) {
				balChangeDirects.setText("Enter the amount you would like to deposit: ");
				if(onlyOnce == false) {
					num1.addActionListener(numberEvent);
					num2.addActionListener(numberEvent);
					num3.addActionListener(numberEvent);
					num4.addActionListener(numberEvent);
					num5.addActionListener(numberEvent);
					num6.addActionListener(numberEvent);
					num7.addActionListener(numberEvent);
					num8.addActionListener(numberEvent);
					num9.addActionListener(numberEvent);
					num0.addActionListener(numberEvent);
					decimal.addActionListener(numberEvent);
					clearinfo.addActionListener(numberEvent);
					onlyOnce = true;
				}
				int withdrawAmount = JOptionPane.showConfirmDialog(null, numPad, "Withdraw", JOptionPane.OK_CANCEL_OPTION);
				if(withdrawAmount == JOptionPane.OK_OPTION) {
					try {
						withDep$ = Double.parseDouble(listAmount.getText());
						if(withDep$ == 0) {
							JOptionPane.showMessageDialog(frame, "When Depositing, the value must be higher than 0. Please try again.", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
						else {
							currentServer.get(iac).deposit(withDep$);
							JOptionPane.showMessageDialog(frame, "<html>You have successfully deposited " + withDep$ + " credit(s) from your account. <br>You now have " + currentServer.get(iac).getBalance() + "credit(s) in your account.</html>", "Deposit Success", JOptionPane.INFORMATION_MESSAGE, tick);
						}
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Please enter a valid value.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					finally {
						listAmount.setText(null);
						decimal.setEnabled(true);
						isDecimalUsed = false;
					}
				}
			}
			else if(event.getSource() == button_11) {
				int modOption = JOptionPane.showConfirmDialog(null, modAccountMessage, "Confirm PIN", JOptionPane.OK_CANCEL_OPTION);
				if(modOption == JOptionPane.OK_OPTION) {
					try {
						if(currentServer.get(iac).getPIN() == Integer.parseInt(modLoginPIN.getText())) {
							clearpage();
							modifyAccountPage();
							modLoginPIN.setText("");
						}
						else {
							JOptionPane.showMessageDialog(frame, "The PIN you enetered was incorrect. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
							modLoginPIN.setText("");
						}
					}
					catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Please enter your 6-digit PIN code for your account.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else if(event.getSource() == button_12) {
				JOptionPane.showMessageDialog(frame, "You have successfully logged out. You will be redirected to the main page.");
				clearpage();
				page1();
			}
			else if(event.getSource() == button_13) {
				if(mAPFirstNametf.getText().equals("") || mAPFirstNametf.getText().equals(null)) {
					JOptionPane.showMessageDialog(frame, "Please enter your first name.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(mAPLastNametf.getText().equals("") || mAPLastNametf.getText().equals(null)) {
					JOptionPane.showMessageDialog(frame, "Please enter your last name.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try{
					if(maPPINpf.getText().equals(null) || maPPINpf.getText().equals("") || maPPINpf.getText().length() != 6)
						JOptionPane.showMessageDialog(frame, "You must input a valid 6-digit PIN sequence.", "Modification Error.", JOptionPane.ERROR_MESSAGE);
					else {
						for(int i = 0; i < maPPINpf.getText().length(); i++) {
							if(maPPINpf.getText().charAt(i) < '0' || maPPINpf.getText().charAt(i) > '9') {
								JOptionPane.showMessageDialog(frame, "The PIN must contain only numbers.");
								confirmBoolean3 = false;
								break;
							}
						}
						if(confirmBoolean3) {
							for(int i = 0; i < maPPINpf.getText().length(); i++) {
								if(maPconfirmPINpf.getText().charAt(i) < '0' || maPconfirmPINpf.getText().charAt(i) > '9') {
									JOptionPane.showMessageDialog(frame, "The PIN must contain only numbers.");
									confirmBoolean3 = false;
									break;
								}
							}
						}
						if(confirmBoolean3) {
							if(Integer.parseInt(maPPINpf.getText()) != Integer.parseInt(maPconfirmPINpf.getText())) {
								JOptionPane.showMessageDialog(frame, "Your PIN does not match your confirmation PIN.");
							}
							else {
								currentServer.get(iac).setName(mAPFirstNametf.getText() + " " + mAPLastNametf.getText());
								currentServer.get(iac).setPIN(Integer.parseInt(maPPINpf.getText()));
								JOptionPane.showMessageDialog(frame, "Changes saved!", "Success!", JOptionPane.INFORMATION_MESSAGE, tick);
								clearpage();
								loggedInPage();
							}
						}
					}
				}
				catch(StringIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(frame, "Please enter your confirmation PIN.");
				}
				finally {
					confirmBoolean3 = true;
				}
			}
			else if(event.getSource() == button_14) {
				clearpage();
				loggedInPage();
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
	// The following arraylists are 'servers' that are interchangable by the user
	private ArrayList<User> asia = new ArrayList<User>();
	private ArrayList<User> america = new ArrayList<User>();
	private ArrayList<User> europe = new ArrayList<User>();
	private ArrayList<User> currentServer;
	private Byte serverNumber = 1; // each number represents the corresponding server.
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
		about.addActionListener(frameEvent);
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
		frame.add(p);
		p.setLayout(new GridLayout(10, 1, 3, 3));
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		welcome = new JLabel("Welcome to SparkBank 2.0 (beta)", SwingConstants.CENTER);
		p.add(welcome);
		refreshServer();
		p.add(status);
		options = new JLabel("Click on one of the options below: ", SwingConstants.CENTER);
		p.add(options);
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
		asterisk = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<em><b>Note: </b><span color='red'>* </span>fields must be completed.</em></html>");
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
		p.getRootPane().setDefaultButton(button_6);
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
	/**
	 * modifyAccountPage()
	 * @param void
	 * @return new screen that gives options to users on modifying the account
	 * @needs initial label, first name label, last name label, pin label and their respective textfields
	 * needs at least 10 labels; suspend max label to 20
	 *
	 */
	public void modifyAccountPage() {
		// Scanner delim = new Scanner(currentServer.get(iac).getName()).useDelimiter(" ");
		String[] userName = currentServer.get(iac).getName().split(" ");
		String userFirstName = "";
		String userLastName = "";
		if(userName.length == 2) {
			userFirstName = userName[0];
			userLastName = userName[1];
		}
		else {
			for(int i = 0; i < userName.length; i++) {
				if(i == 0)
					userFirstName = userName[i];
				else if(i < userName.length - 1)
					userFirstName = userFirstName + " " + userName[i];
				else
					userLastName = userName[i];
			}
		}
		mAPDescription = new JLabel("Modify all necessary changes below:");
		p.add(mAPDescription);
		blank = new JLabel();
		p.add(blank);
		mAPFirstName = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First Name: </html>");
		mAPFirstNametf = new JTextField(userFirstName);
		mAPLastName = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Last Name: </html>");
		mAPLastNametf = new JTextField(userLastName);
		maPPIN = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PIN Number: </html>");
		maPPINpf = new JPasswordField(6);
		maPconfirmPIN = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Confirm PIN Number: </html>");
		maPconfirmPINpf = new JPasswordField(6);
		blank2 = new JLabel();
		blank3 = new JLabel();
		blank4 = new JLabel();
		blank5 = new JLabel();
		button_13 = new JButton("Confirm");
		button_14 = new JButton("Cancel");
		button_13.addActionListener(handler);
		button_14.addActionListener(handler);
		p.add(mAPFirstName);
		p.add(mAPFirstNametf);
		p.add(mAPLastName);
		p.add(mAPLastNametf);
		p.add(maPPIN);
		p.add(maPPINpf);
		p.add(maPconfirmPIN);
		p.add(maPconfirmPINpf);
		p.add(blank2);
		p.add(blank3);
		p.add(blank4);
		p.add(blank5);
		p.add(button_13);
		p.add(button_14);
		p.getRootPane().setDefaultButton(button_13);
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