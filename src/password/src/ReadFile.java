package password.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	private int linecount;
	private String[] service;
	private String[] userName;
	private String[] passWord;
	
	public String[] getService() {
	try {
		Scanner input = new Scanner(new FileInputStream(PasswordApp.userHomeDir));
		service = new String[2000];
		for(int i = 0; input.hasNext(); i++) {
			service[i] = input.next();
			input.nextLine();
			}
		input.close();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	return service;
	}
	
	public String[] getUsername() {
		try {
			Scanner input2 = new Scanner(new FileInputStream(PasswordApp.userHomeDir));
			userName = new String[2000];
			for(int i = 0; input2.hasNext(); i++) {
				input2.next();
				userName[i] = input2.next();
				input2.nextLine();
				}
			input2.close();
			}
			catch(Exception e) {
				e.getStackTrace();
			}
		return userName;
		}
	
	public String[] getPassword() {
		try {
			Scanner input3 = new Scanner(new FileInputStream(PasswordApp.userHomeDir));
			passWord = new String[2000];
			for(int i = 0; input3.hasNext(); i++) {
				input3.next();
				input3.next();
				passWord[i] = input3.next();
				input3.nextLine();
				}
			input3.close();
			}
			catch(Exception e) {
				e.getStackTrace();
			}
		return passWord;
		}
	
	public int getLineCount() {
		try {
			Scanner input4 = new Scanner(new FileInputStream(PasswordApp.userHomeDir));
			linecount = 0;
			while(input4.hasNextLine()) {
				input4.nextLine();
				linecount++;
			}
			input4.close();
			} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return linecount;
	}
}
