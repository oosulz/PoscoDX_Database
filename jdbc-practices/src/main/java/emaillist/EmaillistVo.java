package emaillist;

public class EmaillistVo {
	private Long id;
	private String firstName;
	private String LastName;
	private String email;
	
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Override
	public String toString() {
		return "EmaillistVo [id=" + id + ", firstName=" + firstName + ", LastName=" + LastName + ", email=" + email
				+ "]";
	}
	
}
