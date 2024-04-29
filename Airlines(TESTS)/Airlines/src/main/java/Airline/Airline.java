package Airline;



public class Airline {
    private String name;
    private double rating;
    private String hotline;
    private String nationality;

    // Constructor
    public Airline(String name, double rating, String hotline, String nationality) {
        this.name = name;
        this.rating = rating;
        this.hotline = hotline;
        this.nationality = nationality;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    public Airline() {
		super();
	}

	// Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for Rating
    public double getRating() {
        return rating;
    }

    // Setter for Rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    // Getter for Hotline
    public String getHotline() {
        return hotline;
    }

    // Setter for Hotline
    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    // Getter for Nationality
    public String getNationality() {
        return nationality;
    }

    // Setter for Nationality
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String AssignRanking () {
    	
    	if(this.rating < 1.5)return "Bad";
    	else if(this.rating >= 1.5 && this.rating <= 4)return "Good";
    	else if(this.rating > 4 && this.rating <= 5)return "Excellent";
    	else return "Invalid Rating";
    }
    
    public void ChangeHotline (String Number) {
    	this.hotline = Number;
    }
    

}

