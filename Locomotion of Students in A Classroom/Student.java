package kindergarten;

public class Student {
    private String firstName;
    private String lastName;
    private int    height;

    public Student ( String f, String l, int h ) {
		firstName = f;
        lastName  = l;
		height    = h;
	}

    public Student () {
        this(null, null, 0);
    }

    public int compareNameTo ( Student other ) {
        
        int lastNameCompare = this.lastName.compareToIgnoreCase(other.getLastName());
        
        if ( lastNameCompare == 0 ) {
            return this.firstName.compareToIgnoreCase(other.getFirstName());
        }
        
        return lastNameCompare;
    }

    public String print () {
        String printName = "";
        printName += this.firstName.charAt(0) + ". ";
        if ( lastName.length() > 4 ) {
            printName += this.lastName.substring(0, 4);
        } else {
            printName += lastName;
        }
        return printName;
    }

    public String getFirstName () { return firstName; }
    public void setFirstName ( String f ) { firstName = f; }

    public String getLastName () { return lastName; }
    public void setLastName ( String l ) { lastName = l; }

    public int getHeight () { return height; }
    public void setHeight ( int h ) { height = h; }
    
    public String getFullName () { return this.firstName + " " + this.lastName;}
}
