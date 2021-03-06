import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.text.NumberFormat;
import javax.swing.ImageIcon;
import java.util.Random;

/**
 * @author	Seunghoon Park <spark.knights.rule@gmail.com>
 * @version 2.1.1 Build 0009
 * @date 13th March 2017 03:23PM
 * @since 2.0.1
 * 
 * Version 2.0.1 Build 0002 Patch notes:
 *	* Added a modifyAccountPage()!
 *		* Constructed very similarly to the createUserAccountPage, except it modifies the user's personal information instead
 * 	* Fixed an issue where the confirmPinpf on the createAnAccountPage() would not recognise an error if there were a non-numerical character on its field.
 *  * Fixed a bug where if the user attempted to login with a blank loginPIN it would return an exception on the terminal
 *	* Fixed a bug where if the user attempted to login with characters on the loginPIN passwordfield it would return an exception on the terminal
 * 
 * Version 2.0.1 Build 0003 Patch Notes
 *	* Changed all words pre-alpha to beta
 *
 * Version 2.1.0 Build 0004 Patch Notes
 *	* Added an administrative settings page!
 *	* Added Administrative.java, which is a read-only class that has information regarding administrative logins.
 *
 * Version 2.1.0 Build 0005 Patch Notes
 *	* Added a feature where if a user attempts five or more unsuccessful administrative logins the program will lock the administrative settings feature
 *
 * Version 2.1.0 Build 0006 Patch Notes
 *	* Fixed an issue where the administrative login pane would keep popping up after selecting buttons other than ok
 *	* Fixed an issue where the administrative login pane would keep popping up after successfully logging in
 *
 * Version 2.1.0 Build 0007 Patch Notes
 *	* Created an algorithm that could change the password for Administrative login
 *	* Created a new JMenu and two JMenuItem: tools, createP and createNP, respectively
 *	* Random() seems to not generate a random length for generating a new administrative password. Must be sought out
 *	* Whenever the user attempts to request for an administrative password, a joptionpanel message dialog will pop out
 *
 * Version 2.1.1 Build 0008 Patch Notes
 *	* Finally a program that is no longer in its beta stage!
 *	* Administrative page is now fully functional! There, you can:
 *		* View all registered users in the server
 *		* Copy, move or remove registered users in the server
 *	* The getString() method in the Administrator class was reborn for usage
 *
 * Version 2.1.1 Build 0009 Patch Notes
 *	* Added a function to modify individual user's information in the Administrator's page
 *	* Added some exception catchers to the switch functions in the Administrator's page
 *
 *
 */

public class Client {
	// required for frame, page 1 and miscellaneous
	private JMenuBar jmb;
	private final static JFrame frame = new JFrame("SparkBank 2.1.1");
	private JMenu file, tools, help;
	private JMenuItem read, save, refresh, exit, requestP, requestNP, about;
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
	private JLabel aboutMsgLabel = new JLabel("<html><span style=font-size:15px;>SparkBank 2.1.1<br></span><span font-size:10px>Under Development<br><br><br></span><span font-size:9px>\u00a9 Copyright 2016 Seunghoon Park All Rights Reserved<br>Version 2.1.1 Build 0009</span></html>");
	private Object[] aboutMsg = {aboutMsgLabel};
	// used for modifying user information while logged in
	private JPasswordField modLoginPIN = new JPasswordField();
	private Object[] modAccountMessage = {"<html>Please enter your account <br>PIN for further access: </html>", modLoginPIN};
	// Administrator account
	// private Administrator administrator = new Administrator();
	private JTextField adminUserName = new JTextField();
	private JPasswordField adminPassWord = new JPasswordField();
	private Object[] adminLogin = {"Administrator username: ", adminUserName, "Password: ", adminPassWord};
	private Boolean confirmBoolean4 = true;
	private Boolean confirmBoolean5 = true;
	private static byte administratorUnsuccessfulAttemptCount = 0;
	private Boolean loopBreaker = false;
	private Random randie;
	// Used for CUI Administrative Page
	private boolean cuiLoop = true;
	private String cuiCommands;
	private ArrayList<String> arguments = new ArrayList<String>();
	private Scanner scan = new Scanner(System.in);
	private Scanner delim;
	private int adminServerType = 0; // 0=all 1=asia 2=america 3=europe
	private boolean isUserRemoved = false;
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
    // for generating random number 
    private int randomNumber() {
    	randie = new Random();
    	return randie.nextInt(4)+8;
    	// return ((int) (Math.random()*5))+8;
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
			else if(event.getSource() == requestP) {
				JOptionPane.showMessageDialog(null, "Check your command line for the generated password!", "Notification", JOptionPane.INFORMATION_MESSAGE);
				System.out.println(Administrator.getPassword());
			}
			else if(event.getSource() == requestNP) {
				JOptionPane.showMessageDialog(null, "Check your command line for the newly generated password!", "Notification", JOptionPane.INFORMATION_MESSAGE);
				Administrator.setPassword();
				Administrator.resetPasswordLength(randomNumber());
				System.out.println(Administrator.getPassword());
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
				if(administratorUnsuccessfulAttemptCount >= 5)
					JOptionPane.showMessageDialog(null, "You have made too many unsuccessful attempts trying to gain administrative access.", "Login Error", JOptionPane.ERROR_MESSAGE);
				while (administratorUnsuccessfulAttemptCount < 5 && !(loopBreaker)) {
					int loginOption2 = JOptionPane.showConfirmDialog(null, adminLogin, "Administrative Login", JOptionPane.OK_CANCEL_OPTION);
					if(loginOption2 != JOptionPane.OK_OPTION) {
						loopBreaker = true;
					}
					else if(loginOption2 == JOptionPane.OK_OPTION) {
						if(adminUserName.getText().equals(null) || adminUserName.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter the administrative username and password.");
							administratorUnsuccessfulAttemptCount++;
						}
						else if(!(adminUserName.getText().equals(Administrator.getUsername()))) {
							JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Login error", JOptionPane.ERROR_MESSAGE);
							administratorUnsuccessfulAttemptCount++;
						}
						else if(adminPassWord.getText().equals(null) || adminPassWord.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter the administrative username and password.");
							administratorUnsuccessfulAttemptCount++;
						}
						else {
							if(adminPassWord.getPassword().length != Administrator.getPassword().length) {
								JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Login error", JOptionPane.ERROR_MESSAGE);
								confirmBoolean4 = false;
								administratorUnsuccessfulAttemptCount++;
							}
							if(confirmBoolean4) {
								for(int i = 0; i < Administrator.getPassword().length; i++) {
									if(adminPassWord.getPassword()[i] != Administrator.getPassword()[i]) {
										confirmBoolean5 = false;
										JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Login error", JOptionPane.ERROR_MESSAGE);
										administratorUnsuccessfulAttemptCount++;
										break;
									}
								}
								if(confirmBoolean5) {
									administratorUnsuccessfulAttemptCount = 0;
									loopBreaker = true;
									adminUserName.setText("");
									adminPassWord.setText("");
									administratorPage();
								}
							}
						}
					}
					confirmBoolean4 = true;
					confirmBoolean5 = true;
					adminUserName.setText("");
					adminPassWord.setText("");
					if(administratorUnsuccessfulAttemptCount >= 5)
						JOptionPane.showMessageDialog(null, "You have made too many unsuccessful attempts trying to gain administrative access.", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
				loopBreaker = false;
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
		// f = new JFrame("SparkBank 2.1.0");
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
		tools = new JMenu("Tools");
		tools.setMnemonic(KeyEvent.VK_T);
		jmb.add(tools);
		requestP = new JMenuItem("Request Administrative Password...");
		tools.add(requestP);
		requestP.addActionListener(frameEvent);
		requestNP = new JMenuItem("Request New Administrative Password...");
		tools.add(requestNP);
		requestNP.addActionListener(frameEvent);
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		jmb.add(help);
		about = new JMenuItem("About...");
		about.addActionListener(frameEvent);
		help.add(about);
		frame.setVisible(true);
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Administrator.createPassword(randomNumber());
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
		welcome = new JLabel("Welcome to SparkBank 2.1.1", SwingConstants.CENTER);
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
		button_5 = new JButton("Administrator Settings");
		button_5.setToolTipText("<html>Configure and manage settings in the program. </html>");
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
	public void administratorPage() {
		// Because GUI scaling is not possible at the moment, Administrative tasks will be done using the command line.
		System.out.println("Welcome to the Administrator's Page!\n\nHere, you are capable of modifying information of all users in all servers. \nIf you need help using this prompt, return \"help\".");
		do {
			switch(adminServerType) {
				case 0:
					System.out.print("admin@all: ");
					break;
				case 1:
					System.out.print("admin@Asia: ");
					break;
				case 2:
					System.out.print("admin@America: ");
					break;
				case 3:
					System.out.print("admin@Europe: ");
					break;
				default:
					System.out.print("admin@all: ");
					break;
			}
			cuiCommands = scan.nextLine();
			delim = new Scanner(cuiCommands).useDelimiter(" ");
			while(delim.hasNext()) {
				arguments.add(delim.next());
			}
			switch(arguments.get(0)) {
				case "help":
					System.out.println("Here is a list of commands you can use for this page: \n");
					System.out.println("exit\n\tLogs out of the administrator page.\n");
					System.out.println("pws\n\tPrints your current accessibility of servers.\n");
					System.out.println("change [server_name]\n\tChanges the server you want to modify. Type in:\n\tasia: to modify the Asian server.\n\tamerica: to modify the American server.\n\teurope: to modify the European server.\n\tall: to modify all servers in general.\n");
					System.out.println("list\n\tLists all registered users on the currently selected server.\n");
					System.out.println("rm [server_name(if on all-accessor)] [user_account_ID]\n\tRemoves a user from the system.\n");
					System.out.println("cp [server_name(if on all-accessor)] [user_account_ID] [server_name]\n\tCopies a user to another server.\n");
					System.out.println("move [server_name(if on all-accessor)] [user_account_ID] [server_name]\n\tMoves a user to another server.\n");
					System.out.println("reset [server_name(if on all-accessor)]\n\tRemoves all users.\n\tRequires Administrative password.\n\tIf there are no fields other than reset on the command line,\n\tthe system will erase all users from all servers.\n");
					System.out.println("modify [server_name(if on all-accessor) [user_account_ID] [info_type_to_modify] [desired_change_info]\n\tModifies a user's stored information.\n\tIn the [info_type_to_modify] field, type:\n\tname: to modify name\n\taccount_ID: to modify the acocunt ID manually\n\tpin: to modify the user's pin\n\tbalance: to modify the user's balance.\n");
					break;
				case "change":
					try {
						switch(arguments.get(1)) {
						case "asia":
							System.out.println("You are now modifying the following server: Asian.");
							adminServerType = 1;
							break;
						case "america":
							System.out.println("You are now modifying the following server: American.");
							adminServerType = 2;
							break;
						case "europe":
							System.out.println("You are now modifying the following server: European.");
							adminServerType = 3;
							break;
						case "all":
							System.out.println("You are now modifying the following server: All.");
							adminServerType = 0;
							break;
						default:
							System.out.println("Did not successfully change server. Check for typos.");
							break;
						}
					}
					catch(IndexOutOfBoundsException e) {
						System.out.println("Please type in the server name you would like to modify. For more information, type \"help\".");
					}
					break;
				case "pws":
					switch(adminServerType) {
						case 0:
							System.out.print("You are currently modifying all servers.\n");
							break;
						case 1:
							System.out.print("You are currently modifying the Asian servers.\n");
							break;
						case 2:
							System.out.print("You are currently modifying the American servers.\n");
							break;
						case 3:
							System.out.print("You are currently modifying the Euroepean servers.\n");
							break;
						default:
							System.out.print("ERROR");
							break;
					}
					break;
				case "list":
					System.out.println("|Name:\t\t|Account ID:\t|Balance:\t|Pin:\t|");
					System.out.println("|---------------|---------------|---------------|-------|");
					switch(adminServerType) {
						case 0:
							System.out.println("|ASIAN SERVER");
							for(User u : asia) {
								System.out.println("|" + u.getName() + "\t|" + u.getAccountID() + "\t|" + u.getBalance() + "\t\t|" + u.getPIN() + "\t|");
							}
							System.out.println("|---------------|---------------|---------------|-------|");
							System.out.println("|AMERICAN SERVER");
							for(User u : america) {
								System.out.println("|" + u.getName() + "\t|" + u.getAccountID() + "\t|" + u.getBalance() + "\t\t|" + u.getPIN() + "\t|");
							}
							System.out.println("|---------------|---------------|---------------|-------|");
							System.out.println("|EUROPEAN SERVER");
							for(User u : europe) {
								System.out.println("|" + u.getName() + "\t|" + u.getAccountID() + "\t|" + u.getBalance() + "\t\t|" + u.getPIN() + "\t|");
							}
							System.out.println("|---------------|---------------|---------------|-------|");
							break;
						case 1:
							for(User u : asia) {
								System.out.println("|" + u.getName() + "\t|" + u.getAccountID() + "\t|" + u.getBalance() + "\t\t|" + u.getPIN() + "\t|");
							}
							System.out.println("|---------------|---------------|---------------|-------|");
							break;
						case 2:
							for(User u : america) {
								System.out.println("|" + u.getName() + "\t|" + u.getAccountID() + "\t|" + u.getBalance() + "\t\t|" + u.getPIN() + "\t|");
							}
							System.out.println("|---------------|---------------|---------------|-------|");
							break;
						case 3:
							for(User u : europe) {
								System.out.println("|" + u.getName() + "\t|" + u.getAccountID() + "\t|" + u.getBalance() + "\t\t|" + u.getPIN() + "\t|");
							}
							System.out.println("|---------------|---------------|---------------|-------|");
							break;
						default:
							System.out.print("ERROR");
							break;
					}
					break;
				case "rm":
					try {
						switch(adminServerType) {
							case 0:
								switch(arguments.get(1)) {
									case "asia":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == asia.get(i).getAccountID()) {
												asia.remove(i);
												System.out.println("Removed user successfully.");
												isUserRemoved = true;
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									case "america":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == america.get(i).getAccountID()) {
												america.remove(i);
												System.out.println("Removed user successfully.");
												isUserRemoved = true;
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									case "europe":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == europe.get(i).getAccountID()) {
												europe.remove(i);
												System.out.println("Removed user successfully.");
												isUserRemoved = true;
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									default:
										System.out.println("Server name not recognised. Please enter a valid server name.");
										break;
								}
								break;
							case 1:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == asia.get(i).getAccountID()) {
										asia.remove(i);
										System.out.println("Removed user successfully.");
										isUserRemoved = true;
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							case 2:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(2)) == america.get(i).getAccountID()) {
										america.remove(i);
										System.out.println("Removed user successfully.");
										isUserRemoved = true;
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							case 3:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(2)) == europe.get(i).getAccountID()) {
										europe.remove(i);
										System.out.println("Removed user successfully.");
										isUserRemoved = true;
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							default:
								System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								break;
						}
					}
					catch (Exception e) {
						System.out.println("Field is empty. Please enter further commands to continue.");
					}
					break;
				case "cp":
					try {
						switch(adminServerType) {
							case 0:
								switch(arguments.get(1)) {
									case "asia":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == asia.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "asia":
														System.out.println("User is already in this server.");
														isUserRemoved = true;
														break;
													case "america":
														if(america.size() < 10) {
															america.add(asia.get(i));
															isUserRemoved = true;
															System.out.println("User successfully copied.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													case "europe":
														if(europe.size() < 10) {
															europe.add(asia.get(i));
															isUserRemoved = true;
															System.out.println("User successfully copied.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													default:
														System.out.println("Our system did not recognise the name of the server that the user will be copied to. Please try again.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									case "america":
										for(int i = 0; i < america.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == america.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "america":
														System.out.println("User is already in this server.");
														isUserRemoved = true;
														break;
													case "asia":
														if(asia.size() < 10) {
															asia.add(america.get(i));
															isUserRemoved = true;
															System.out.println("User successfully copied.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													case "europe":
														if(europe.size() < 10) {
															europe.add(america.get(i));
															isUserRemoved = true;
															System.out.println("User successfully copied.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													default:
														System.out.println("Our system did not recognise the name of the server that the user will be copied to. Please try again.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									case "europe":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == europe.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "europe":
														System.out.println("User is already in this server.");
														isUserRemoved = true;
														break;
													case "america":
														if(america.size() < 10) {
															america.add(europe.get(i));
															isUserRemoved = true;
															System.out.println("User successfully copied.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													case "asia":
														if(asia.size() < 10) {
															asia.add(europe.get(i));
															isUserRemoved = true;
															System.out.println("User successfully copied.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													default:
														System.out.println("Our system did not recognise the name of the server that the user will be copied to. Please try again.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									default:
										System.out.println("Server name not recognised. Please enter a valid server name.");
										break;
								}
								break;
							case 1:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == asia.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "asia":
												System.out.println("User is already in this server.");
												isUserRemoved = true;
												break;
											case "america":
												if(america.size() < 10) {
													america.add(asia.get(i));
													isUserRemoved = true;
													System.out.println("User successfully copied.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											case "europe":
												if(europe.size() < 10) {
													europe.add(asia.get(i));
													isUserRemoved = true;
													System.out.println("User successfully copied.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											default:
												System.out.println("Our system did not recognise the name of the server that the user will be copied to. Please try again.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							case 2:
								for(int i = 0; i < america.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == america.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "america":
												System.out.println("User is already in this server.");
												isUserRemoved = true;
												break;
											case "asia":
												if(asia.size() < 10) {
													asia.add(america.get(i));
													isUserRemoved = true;
													System.out.println("User successfully copied.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											case "europe":
												if(europe.size() < 10) {
													europe.add(america.get(i));
													isUserRemoved = true;
													System.out.println("User successfully copied.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											default:
												System.out.println("Our system did not recognise the name of the server that the user will be copied to. Please try again.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							case 3:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == europe.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "europe":
												System.out.println("User is already in this server.");
												isUserRemoved = true;
												break;
											case "america":
												if(america.size() < 10) {
													america.add(europe.get(i));
													isUserRemoved = true;
													System.out.println("User successfully copied.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											case "asia":
												if(asia.size() < 10) {
													asia.add(europe.get(i));
													isUserRemoved = true;
													System.out.println("User successfully copied.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											default:
												System.out.println("Our system did not recognise the name of the server that the user will be copied to. Please try again.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							default:
								System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								break;
						}
					}
					catch (Exception e) {
						System.out.println("Field is empty. Please enter further commands to continue.");
					}
					break;
				case "move":
					try {
						switch(adminServerType) {
							case 0:
								switch(arguments.get(1)) {
									case "asia":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == asia.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "asia":
														System.out.println("User is already in this server.");
														isUserRemoved = true;
														break;
													case "america":
														if(america.size() < 10) {
															america.add(asia.get(i));
															asia.remove(i);
															isUserRemoved = true;
															System.out.println("User successfully moved.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													case "europe":
														if(europe.size() < 10) {
															europe.add(asia.get(i));
															asia.remove(i);
															isUserRemoved = true;
															System.out.println("User successfully moved.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													default:
														System.out.println("Our system did not recognise the name of the server that the user will be moved to. Please try again.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									case "america":
										for(int i = 0; i < america.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == america.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "america":
														System.out.println("User is already in this server.");
														isUserRemoved = true;
														break;
													case "asia":
														if(asia.size() < 10) {
															asia.add(america.get(i));
															america.remove(i);
															isUserRemoved = true;
															System.out.println("User successfully moved.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													case "europe":
														if(europe.size() < 10) {
															europe.add(america.get(i));
															america.remove(i);
															isUserRemoved = true;
															System.out.println("User successfully moved.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													default:
														System.out.println("Our system did not recognise the name of the server that the user will be moved to. Please try again.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									case "europe":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == europe.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "europe":
														System.out.println("User is already in this server.");
														isUserRemoved = true;
														break;
													case "america":
														if(america.size() < 10) {
															america.add(europe.get(i));
															europe.remove(i);
															isUserRemoved = true;
															System.out.println("User successfully moved.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													case "asia":
														if(asia.size() < 10) {
															asia.add(europe.get(i));
															europe.remove(i);
															isUserRemoved = true;
															System.out.println("User successfully moved.");
														}
														else {
															System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
															isUserRemoved = true;
														}
														break;
													default:
														System.out.println("Our system did not recognise the name of the server that the user will be moved to. Please try again.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
										}
										isUserRemoved = false;
										break;
									default:
										System.out.println("Server name not recognised. Please enter a valid server name.");
										break;
								}
								break;
							case 1:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == asia.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "asia":
												System.out.println("User is already in this server.");
												isUserRemoved = true;
												break;
											case "america":
												if(america.size() < 10) {
													america.add(asia.get(i));
													asia.remove(i);
													isUserRemoved = true;
													System.out.println("User successfully moved.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											case "europe":
												if(europe.size() < 10) {
													europe.add(asia.get(i));
													asia.remove(i);
													isUserRemoved = true;
													System.out.println("User successfully moved.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											default:
												System.out.println("Our system did not recognise the name of the server that the user will be moved to. Please try again.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							case 2:
								for(int i = 0; i < america.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == america.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "america":
												System.out.println("User is already in this server.");
												isUserRemoved = true;
												break;
											case "asia":
												if(asia.size() < 10) {
													asia.add(america.get(i));
													america.remove(i);
													isUserRemoved = true;
													System.out.println("User successfully moved.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											case "europe":
												if(europe.size() < 10) {
													europe.add(america.get(i));
													america.remove(i);
													isUserRemoved = true;
													System.out.println("User successfully moved.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											default:
												System.out.println("Our system did not recognise the name of the server that the user will be moved to. Please try again.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							case 3:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == europe.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "europe":
												System.out.println("User is already in this server.");
												isUserRemoved = true;
												break;
											case "america":
												if(america.size() < 10) {
													america.add(europe.get(i));
													europe.remove(i);
													isUserRemoved = true;
													System.out.println("User successfully moved.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											case "asia":
												if(asia.size() < 10) {
													asia.add(europe.get(i));
													europe.remove(i);
													isUserRemoved = true;
													System.out.println("User successfully moved.");
												}
												else {
													System.out.println("The server you are trying to reallocate the user to is full. Delete users on the server and try again.");
													isUserRemoved = true;
												}
												break;
											default:
												System.out.println("Our system did not recognise the name of the server that the user will be moved to. Please try again.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								}
								isUserRemoved = false;
								break;
							default:
								System.out.println("Our system did not register a user with such an account ID. Please double check the account ID and try again.");
								break;
						}
					}
					catch (Exception e) {
						System.out.println("Field is empty. Please enter further commands to continue.");
					}
					break;
				case "reset":
					System.out.print("Please enter the administrative password: ");
					cuiCommands = scan.nextLine();
					if(cuiCommands.equals(Administrator.getString())) {
						switch(adminServerType) {
							case 1:
								asia.clear();
								System.out.println("Successfully removed all users.");
								break;
							case 2:
								america.clear();
								System.out.println("Successfully removed all users.");
								break;
							case 3:
								europe.clear();
								System.out.println("Successfully removed all users.");
								break;
							case 0:
								if(arguments.size() == 2) {
									switch(arguments.get(1)) {
										case "asia":
											asia.clear();
											System.out.println("Successfully removed all users.");
											break;
										case "america":
											america.clear();
											System.out.println("Successfully removed all users.");
											break;
										case "europe":
											europe.clear();
											System.out.println("Successfully removed all users.");
											break;
										default:
											System.out.println("Our system was unable to recognise the server name. Please try again.");
											break;
									}
								}
								else {
									asia.clear();
									america.clear();
									europe.clear();
									System.out.println("Successfully removed all users from all servers.");
								}
								break;
						}
					}	
					else {
						System.out.println("The password is incorrect. Please try again.");
					}
					break;
				case "modify":
					try {
						switch(adminServerType) {
							case 0:
								switch(arguments.get(1)) {
									case "asia":
										for(int i = 0; i < asia.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == asia.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "name":
														asia.get(i).setName(arguments.get(4) + " " + arguments.get(5));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "account_ID":
														asia.get(i).setAccountID(Integer.parseInt(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "pin":
														asia.get(i).setPIN(Integer.parseInt(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "balance":
														asia.get(i).setBalance(Double.parseDouble(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													default:
														System.out.println("Please type in what you want to change the information to.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not detect such a registered user.");
										}
										isUserRemoved = false;
										break;
									case "america":
										for(int i = 0; i < america.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == america.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "name":
														america.get(i).setName(arguments.get(4) + " " + arguments.get(5));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "account_ID":
														america.get(i).setAccountID(Integer.parseInt(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "pin":
														america.get(i).setPIN(Integer.parseInt(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "balance":
														america.get(i).setBalance(Double.parseDouble(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													default:
														System.out.println("Please type in what you want to change the information to.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not detect such a registered user.");
										}
										isUserRemoved = false;
										break;
									case "europe":
										for(int i = 0; i < europe.size(); i++) {
											if(Integer.parseInt(arguments.get(2)) == europe.get(i).getAccountID()) {
												switch(arguments.get(3)) {
													case "name":
														europe.get(i).setName(arguments.get(4) + " " + arguments.get(5));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "account_ID":
														europe.get(i).setAccountID(Integer.parseInt(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "pin":
														europe.get(i).setPIN(Integer.parseInt(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													case "balance":
														europe.get(i).setBalance(Double.parseDouble(arguments.get(4)));
														System.out.println("Changes successfully made.");
														isUserRemoved = true;
														break;
													default:
														System.out.println("Please type in what you want to change the information to.");
														break;
												}
											}
										}
										if (!isUserRemoved) {
											System.out.println("Our system did not detect such a registered user.");
										}
										isUserRemoved = false;
										break;
									default:
										System.out.println("Our system did not recognise the server name you typed in. Please try again.");
										break;
								}
								break;
							case 1:
								for(int i = 0; i < asia.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == asia.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "name":
												asia.get(i).setName(arguments.get(3) + " " + arguments.get(4));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "account_ID":
												asia.get(i).setAccountID(Integer.parseInt(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "pin":
												asia.get(i).setPIN(Integer.parseInt(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "balance":
												asia.get(i).setBalance(Double.parseDouble(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											default:
												System.out.println("Please type in what you want to change the information to.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not detect such a registered user.");
								}
								isUserRemoved = false;
								break;
							case 2:
								for(int i = 0; i < america.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == america.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "name":
												america.get(i).setName(arguments.get(3) + " " + arguments.get(4));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "account_ID":
												america.get(i).setAccountID(Integer.parseInt(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "pin":
												america.get(i).setPIN(Integer.parseInt(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "balance":
												america.get(i).setBalance(Double.parseDouble(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											default:
												System.out.println("Please type in what you want to change the information to.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not detect such a registered user.");
								}
								isUserRemoved = false;
								break;
							case 3:
								for(int i = 0; i < america.size(); i++) {
									if(Integer.parseInt(arguments.get(1)) == america.get(i).getAccountID()) {
										switch(arguments.get(2)) {
											case "name":
												america.get(i).setName(arguments.get(3) + " " + arguments.get(4));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "account_ID":
												america.get(i).setAccountID(Integer.parseInt(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "pin":
												america.get(i).setPIN(Integer.parseInt(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											case "balance":
												america.get(i).setBalance(Double.parseDouble(arguments.get(3)));
												System.out.println("Changes successfully made.");
												isUserRemoved = true;
												break;
											default:
												System.out.println("Please type in what you want to change the information to.");
												break;
										}
									}
								}
								if (!isUserRemoved) {
									System.out.println("Our system did not detect such a registered user.");
								}
								isUserRemoved = false;
								break;
							default:
								System.out.println("ERROR!");
								break;
						}
					}
					catch (Exception e) {
						System.out.println("Field incomplete. Please enter the appropriate command in the correct syntax. For more information, return \"help\".");
					}
					break;
				case "exit":
					System.out.println("Logged out successfully.");
					adminServerType = 0;
					cuiLoop = false;
					break;
				default:
					System.out.println("The command \"" + cuiCommands + "\" was not recognised. Please enter a valid command with the appropriate syntax.");
			}
			arguments.clear();
		} while(cuiLoop);
		cuiLoop = true;
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
