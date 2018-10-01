package businessLogic;

import java.util.ArrayList;
import entities.*;

public class SigninControler {
	public static ArrayList<User> mockUsers =
			new ArrayList<User>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				add(new User("juanperez","123"));
				add(new User("johndoe","jdrules"));
				add(new User("mastermind","smarter"));
				}};
				
	public User getUser(User user) {
		for (User u : mockUsers) {			
			if (u.equals(user) 
					&& u.getPassword().equals(user.getPassword())) {
				return u;
			}
		}
		return null;
	}

}
