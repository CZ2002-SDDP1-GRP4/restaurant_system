package main.Staff;

public class Staff {
	
	private String name;
	public enum Gender {Male, Female, Others};	
	private Gender gender;
	private int id;
	private String title;
	
	public Staff(String name,int id,String title,int sex) {

		this.name = name;
		this.id = id;
		this.title = title;
		
		switch(sex) {
    	case 1: 
    		this.gender = Gender.Male;
    	break;
    	
    	case 2:
    		this.gender = Gender.Female;
    	break;
    	
    	case 3: 
    		this.gender = Gender.Others;
    	break;
		}
		
	}
	
	public void printDetails() {
		
		System.out.printf("The details are:\nName: %s\nID: %d\nTitle: %s\nGender: %s\n",
							this.name,this.id,this.title,this.gender);
		
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + ", " + "ID: " + this.id + ", "+
	"Gender: " + this.gender + ", " +"Title: " + this.title;
	}
	
	// COMMENT bleow i added this for StaffApp --> getStaffId
	public int getStaffId() {
		return id;
	}
}
