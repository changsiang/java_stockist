package team12.stockist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "UserName")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "UserRole")
	private String userRole;
	
	//Table Relation
	//User have no table relationship at the moment (Chang Siang 8 Dec 2017)
	
	//Constructors
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String userRole) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	
	public User(User user) {

		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.userRole = user.userRole;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	//hashCode and Equals for UserId only
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
