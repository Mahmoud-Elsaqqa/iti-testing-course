package gov.iti.jets.testing.lab01;

import java.util.ArrayList;
import java.util.Optional;

public class User {
    private String email;

    private ArrayList<String> passwords = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        passwords.add(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passwords.get(passwords.size() - 1);
    }

    public void updatePassword(String password) throws IllegalArgumentException {
        Optional<String> duplicatePassword = passwords.stream().filter(s -> s.equals(password)).findAny();
        if(duplicatePassword.isPresent()){
            throw new IllegalArgumentException("Your new password can't be the same as old password");
        }
        else {
            passwords.add(password);
        }
    }

// Implement and test this class

    // You can create a user

    // You can update a user's password, retaining a history of passwords

    // When you call getPassword() it always returns the latest password

    // When you update a user with a password that is already present,
    // it throws an IllegalArguemntException()

    // Remember: The value proposition of tests
}
