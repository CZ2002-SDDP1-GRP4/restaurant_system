package main.Staff;

/**
 * The staff class to create the staff members of the restaurant
 *
 */
public class Staff {
	/**
	 * The name of the Staff person 
	 */
	private String name;
	/**
	 * The gender options that any Staff person can take on
	 * 
	 */
	public enum Gender {
		/**
		 * The person is male
		 */
		Male,
		/**
		 * The person is female
		 */
		Female,
		/**
		 * The person defines their own gender (non-binary)
		 */
		Others};
	/**
	 * The gender of a staff option based on the above enumerated type
	 */
	private Gender gender;
	/**
	 * The Staff's id
	 */
	private int id;
	/**
	 * The title or role of the staff person
	 */
	private String title;
	/**
	 * constructor for staff object 
	 * @param name The name of the staff
	 * @param id The id of the staff
	 * @param title The title of the staff
	 * @param sex The gender of the staff
	 */
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
	/**
	 * print details of each employee
	 */
	public void printDetails() {
		
		System.out.printf("Name: %s\nID: %d\nTitle: %s\nGender: %s\n",
							this.name,this.id,this.title,this.gender);
		
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + ", " + "ID: " + this.id + ", "+
	"Gender: " + this.gender + ", " +"Title: " + this.title;
	}
	
	/**
	 * get staff id of this particular employee
	 * @return integer the staff id number 
	 */
	public int getStaffId() {
		return id;
	}
}
