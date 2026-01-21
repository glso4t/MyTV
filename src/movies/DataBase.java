package movies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBase {
	private ArrayList<UserAccount> userAccounts;

	
	public DataBase() {

		userAccounts = new ArrayList<UserAccount>();
		//userAccounts deserialization
		try {
			FileInputStream fileIn = new FileInputStream("userAccounts.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userAccounts = (ArrayList<UserAccount>) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("userAccounts.ser File Not Found (DataBase userAccounts deserialization)");
		} catch (IOException e) {
			System.out.println("Void userAccount List (DataBase userAccounts deserialization)");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
		}
		
	}
	
	public ArrayList<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	
	public void userAccountsSerialization() {
		try {
			FileOutputStream fileOut = new FileOutputStream("userAccounts.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(userAccounts);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("userAccounts.ser File Not Found (DataBase userAccounts serialization)");
		} catch (IOException e) {
			System.out.println("IO Exception ?");
		}
	}
	
	public void addUserAccount(UserAccount acc) {
		userAccounts.add(acc);
		this.userAccountsSerialization();
	}

	
	public void saveSignedInAccount(UserAccount signedInAccount) {
		for(UserAccount a : userAccounts) {
			if(a.getUsername().equals(signedInAccount.getUsername()))
				userAccounts.set(userAccounts.indexOf(a), signedInAccount);
		}
		this.userAccountsSerialization();
	}
	
}

