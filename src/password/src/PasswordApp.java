package password.src;

import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.io.File;

import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;


public class PasswordApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text1;
	private JPasswordField text2;
	private JTextField textField_2;
	private JTextField textA;
	private JTextField textB;
	private JTextField textC;
	private static String[] userName;
	private static String[] passWord;
	private static String[] storage;
	private static int linecount;
	public static String userHomeDir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			    try {
			    	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			    	PasswordApp frame = new PasswordApp();
					frame.setVisible(true);
					Encrypt startup = new Encrypt();
					
					
					KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
					keyPairGen.initialize(4096);
					KeyPair pair = keyPairGen.generateKeyPair();
					PublicKey publicKey = pair.getPublic();
					Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
					
					
					WindowListener exitListener = (WindowListener) new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
					        startup.doEncrypt(publicKey, pair, cipher );
					    }
					};
					
					
					frame.addWindowListener(exitListener);
					File create = new File("password.txt");
					create.createNewFile();
					userHomeDir = create.getAbsolutePath();
					startup.doDecrypt(publicKey, pair, cipher);

				} catch (Exception e) {
					e.printStackTrace();
				}
				

				ReadFile file = new ReadFile();
				storage = file.getService();
				userName = file.getUsername();
				passWord = file.getPassword();
				linecount = file.getLineCount();
				
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public PasswordApp() {
		setForeground(Color.DARK_GRAY);
		setBackground(Color.WHITE);
		setTitle("Juicys Password Manager");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 500, 320);
		setResizable(false);
				
		//Content Pane 1
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][grow]", "[][][][][]"));
		
		
		
		
		
		//Components for password input
		JLabel lblNewLabel_3 = new JLabel("What is the Password used for?:");
		contentPane.add(lblNewLabel_3, "cell 1 1");
		lblNewLabel_3.setForeground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("What is the Username?:");
		contentPane.add(lblNewLabel, "cell 1 2");
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("What is the Password?:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1, "cell 1 3");
		lblNewLabel_1.setForeground(Color.WHITE);
		
		JLabel lblNewLabel_2 = new JLabel("");
		contentPane.add(lblNewLabel_2, "cell 1 4");
		lblNewLabel_2.setForeground(Color.RED);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setEnabled(true);
		contentPane.add(btnNewButton, "cell 3 4");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		
		
		
		
		
		
		
		//Components for password search
		JLabel service = new JLabel("What is the name?:");
		contentPane.add(service, "cell 1 7");
		service.setForeground(Color.WHITE);
		
		JLabel uName = new JLabel("The Username is:");
		contentPane.add(uName, "cell 1 8");
		uName.setForeground(Color.WHITE);
		
		JLabel pWord = new JLabel("The Password is:");
		contentPane.add(pWord, "cell 1 9");
		pWord.setForeground(Color.WHITE);
		
		JButton search = new JButton("Search");
		contentPane.add(search, "cell 3 10");
		search.setBackground(Color.LIGHT_GRAY);
		
		JButton delete = new JButton("Delete");
		contentPane.add(delete, "cell 3 10");
		delete.setBackground(Color.LIGHT_GRAY);
		
		JLabel message = new JLabel("");
		contentPane.add(message, "cell 1 10");
		message.setForeground(Color.GREEN);
		
		JSeparator space = new JSeparator();
		contentPane.add(space, "cell 3 5");
		space.setForeground(Color.GRAY);
		
		JSeparator space2 = new JSeparator();
		contentPane.add(space2, "cell 3 6");
		space2.setForeground(Color.GRAY);
		
		JSeparator space3 = new JSeparator();
		contentPane.add(space3, "cell 1 5");
		space3.setForeground(Color.GRAY);
		
		JSeparator space4 = new JSeparator();
		contentPane.add(space4, "cell 1 6");
		space4.setForeground(Color.GRAY);
		
		
		
		
		
		
		
		//Input Fields for input
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 3 1,growx");
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		
		text1 = new JTextField();
		contentPane.add(text1, "cell 3 2,growx");
		text1.setColumns(10);
		text1.setBackground(Color.LIGHT_GRAY);
		
		text2 = new JPasswordField();
		contentPane.add(text2, "cell 3 3,growx");
		text2.setColumns(10);
		text2.setBackground(Color.LIGHT_GRAY);
		
		JCheckBox box = new JCheckBox("Show", false);
		contentPane.add(box, "cell 3 4,growx");
		box.setHorizontalAlignment(SwingConstants.CENTER);
		box.setBackground(Color.LIGHT_GRAY);
		box.setForeground(Color.WHITE);
		
		box.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {   
            	if(box.isSelected()) {
            		text2.setEchoChar((char)0);
            	}
            	else {
            		text2.setEchoChar('*');
            	}
                   
            }    
         });    
		
		
		
		
		
		
		
		//Input Fields for search
		textA = new JTextField();
		contentPane.add(textA, "cell 3 7,growx");
		textA.setColumns(10);
		textA.setBackground(Color.LIGHT_GRAY);
		
		textB = new JTextField();
		contentPane.add(textB, "cell 3 8,growx");
		textB.setColumns(10);
		textB.setEditable(false);
		textB.setBackground(Color.LIGHT_GRAY);
		
		textC = new JTextField();
		contentPane.add(textC, "cell 3 9,growx");
		textC.setColumns(10);
		textC.setEditable(false);
		textC.setBackground(Color.LIGHT_GRAY);
		
		class CustomKeyListener implements KeyListener {
		      public void keyTyped(KeyEvent e) {
		      }
		      public void keyPressed(KeyEvent e) {
		         if(e.getKeyCode() == KeyEvent.VK_ENTER){
		            btnNewButton.doClick();
		            textField_2.requestFocusInWindow();
		         }
		      }
		      public void keyReleased(KeyEvent e) {
		      }   
		   }
		text2.addKeyListener(new CustomKeyListener());
		text1.addKeyListener(new CustomKeyListener());
		
		class CustomKeyListener2 implements KeyListener{
		      public void keyTyped(KeyEvent e) {
		      }
		      public void keyPressed(KeyEvent e) {
		         if(e.getKeyCode() == KeyEvent.VK_ENTER){
		            search.doClick();
		         }
		      }
		      public void keyReleased(KeyEvent e) {
		      }   
		   }
		
		textA.addKeyListener(new CustomKeyListener2());
		
		
		
		
		
		
		
		
		//Document listener for service field
		class Used implements DocumentListener{
				public void changedUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    changed();
				  }
				  public void changed() {
					  if(textField_2.getText().isEmpty()) {
						  lblNewLabel_2.setText(null);
						  btnNewButton.setEnabled(true);
						  textField_2.setForeground(Color.BLACK);
						  lblNewLabel_2.setForeground(Color.RED);
					  }
						  for(int i = 0; i < linecount; i++) {
							  if(textField_2.getText().equalsIgnoreCase(storage[i])) {
								  btnNewButton.setEnabled(false);
								  lblNewLabel_2.setText("Service already has an entry");
								  textField_2.setForeground(Color.RED);
								  }
						  }
					  }
				};
			textField_2.getDocument().addDocumentListener(new Used());
		
				
			
			
			
			
			
			//Button to Save the file
			btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean empty = true;
				String purpose = textField_2.getText();
				String username = text1.getText();
				char[] data = text2.getPassword();
				String password = new String(data);
				String finalInput = purpose + " " + username + " " + password;
				ReadFile input = new ReadFile();
				
				while(empty) {
					if(text1.getText().isEmpty()) {
						lblNewLabel_2.setText("Save Failed: Please enter a Username");
						break;
					}
					else if(password.isEmpty()) {
						lblNewLabel_2.setText("SaveFailed: Please enter a Password");
						break;
					}
					else if(textField_2.getText().isEmpty()) {
						lblNewLabel_2.setText("SaveFailed: Please enter a Purpose");
						break;
					}
					else if(textField_2.getText().contains(" ")) {
						lblNewLabel_2.setText("Purpose has to be one word");
						break;
					}
				try {
				FileWriter output = new FileWriter(userHomeDir, true);
			    output.write(finalInput);
			    output.write(10);
			    output.flush();
			    output.close();
			    
			    text1.setText(null);
			    text2.setText(null);
			    textField_2.setText(null);
			    storage = input.getService();
			    linecount = input.getLineCount();
			    userName = input.getUsername();
				passWord = input.getPassword();
			    }
				catch(Exception e1)
				{
					e1.getStackTrace();
					lblNewLabel_2.setText("Failed...");
				}
				lblNewLabel_2.setText("Saved!");
				lblNewLabel_2.setForeground(Color.GREEN);
				break;
				}
			}
		});
				
			
			
			
			
			
			//Document listener for textA
				class Empty implements DocumentListener{
					public void changedUpdate(DocumentEvent e) {
					    changed();
					  }
					  public void removeUpdate(DocumentEvent e) {
					    changed();
					  }
					  public void insertUpdate(DocumentEvent e) {
					    changed();
					  }
					  public void changed() {
						  if(textA.getText().isEmpty()) {
							  message.setText(null);
							  textB.setText(null);
							  textC.setText(null);
						  }
					  }
				}
			textA.getDocument().addDocumentListener(new Empty());
						  
			
			
			
			
			
			
			//Search Button action  
			search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ReadFile input = new ReadFile();
				for(int i = 0; i < linecount; i++) {
					  if(textA.getText().equalsIgnoreCase(storage[i])) {
						  textB.setText(userName[i]);
						  textC.setText(passWord[i]);
						  message.setText("Search Sucessfull");
						  storage = input.getService();
						  userName = input.getUsername();
						  passWord = input.getPassword();
						  linecount = input.getLineCount();
						  break;
						  }
					  }
			}
		});
			//Delete Button
			delete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					DeleteEntry delete = new DeleteEntry();
					delete.deleteEntry(textA.getText());
					
					ReadFile input = new ReadFile();
					storage = input.getService();
					userName = input.getUsername();
					passWord = input.getPassword();
					linecount = input.getLineCount();
					
					message.setText("Entry Deleted");
					textB.setText(null);
					textC.setText(null);
				}
			});
	}
}
