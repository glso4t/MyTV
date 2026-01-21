package movies;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserAccount implements Serializable {
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	
	public UserAccount (String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void signedInAccountSerialization() {
		try {
			FileOutputStream fileOut = new FileOutputStream("signedInAccount.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("signedInAccount.ser File Not Found (signed in account serialization)");
		} catch (IOException e) {
			System.out.println("IO Exception ?");
		}
	}
	
}
