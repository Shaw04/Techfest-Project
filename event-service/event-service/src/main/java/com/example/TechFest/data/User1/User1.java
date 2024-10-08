package com.example.TechFest.data.User1;





import jakarta.persistence.*;

@Entity
@Table

public class User1 {
	@Id
	@SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
	@GeneratedValue(
            generator = "user_seq",
            strategy = GenerationType.SEQUENCE
    )
	Long UserID;
	String username;
	String Password;
	String Email;
	String Role;
	
//	@OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
//    private List<Registration> registrations;
	
	public Long getUserID() {
		return UserID;
	}
	public User1() {
       
    }
	public User1(String username, String password, String email, String role) {
		this.username = username;
		this.Password = password;
		this.Email = email;
		this.Role = role;
	}
	public void update(String username, String password, String email, String role) {
		this.username = username;
		this.Password = password;
		this.Email = email;
		this.Role = role;
	}
	public void setUserID(Long userID) {
		this.UserID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		this.Role = role;
	}
	
}
