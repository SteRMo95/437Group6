
public class Person {

	private int PersonID;
	private String FirstName;
	private String LastName;
	private String Address1;
	private String Address2;
	private String City;
	private String SState;
	private String Zip;
	public Person(int personID, String firstName, String lastName, String address1, String address2, String city,
			String sState, String zip) {
		super();
		PersonID = personID;
		FirstName = firstName;
		LastName = lastName;
		Address1 = address1;
		Address2 = address2;
		City = city;
		SState = sState;
		Zip = zip;
	}
	public int getPersonID() {
		return PersonID;
	}
	public void setPersonID(int personID) {
		PersonID = personID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getSState() {
		return SState;
	}
	public void setSState(String sState) {
		SState = sState;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	
	
	
}
