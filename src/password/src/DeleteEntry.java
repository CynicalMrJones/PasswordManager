package password.src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
public class DeleteEntry {
	ReadFile input = new ReadFile();
	private String[] userName;
	private String[] passWord;
	private String[] services;
	private int linecount;
	public void deleteEntry(String Text) {
		services = input.getService();
		userName = input.getUsername();
		passWord = input.getPassword();
		linecount = input.getLineCount();
		
		File exterminate = new File(PasswordApp.userHomeDir);
		exterminate.delete();
		try {
			exterminate.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		ArrayList<String> list3 = new ArrayList<>();
		for(int i = 0; i < linecount; i++) {
			list1.add(services[i]);
			list2.add(userName[i]);
			list3.add(passWord[i]);
		}
		for(int i = 0; i < list1.size(); i++) {
			if(list1.get(i).equalsIgnoreCase(Text)) {
				list1.remove(i);
				list2.remove(i);
				list3.remove(i);
			}
		}
		for(int j = 0; j < list1.size(); j++) {
			try {
				FileWriter output = new FileWriter(PasswordApp.userHomeDir, true);
				output.write(list1.get(j) + " ");
				output.write(list2.get(j) + " ");
				output.write(list3.get(j));
				output.write(10);
				output.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
