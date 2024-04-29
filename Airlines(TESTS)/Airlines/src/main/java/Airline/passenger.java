package Airline;
import java.util.*;
import java.io.*;

public class passenger {
	
	public passenger(String name, String pass, int age, String telenumber, String nationality,
			List<Booking> history_Of_Bookings) {
		super();
		Name = name;
		Pass = pass;
		Age = age;
		Telenumber = telenumber;
		Nationality = nationality;
		History_Of_Bookings = history_Of_Bookings;
	}
	public passenger(String name, int age, String telenumber, String nationality) {
		super();
		Name = name;
		Age = age;
		Telenumber = telenumber;
		Nationality = nationality;
	}
	public passenger(String name,String pass) {
		Name = name;
		Pass = pass;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getTelenumber() {
		return Telenumber;
	}
	public void setTelenumber(String telenumber) {
		Telenumber = telenumber;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	
	public boolean isSenior() {
		return this.getAge()>= 60;
	}
	
	public void UpdateTeleNumber(String number) {
		this.Telenumber = number;
	}
	
	public void AddBooking(Booking B) {
		this.History_Of_Bookings.add(B);
	}
	public void Display_History_Of_Booking() {
		System.out.println(this.History_Of_Bookings);
	}
	
	public boolean AuthenticateUser() {
		boolean valid = false;
		String fileName = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/Airlines(TESTS)/Airlines/src/main/java/Airline/Passengers.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] parts = line.split(",");
            	if(parts[0].equals(this.Name) && parts[1].equals(this.Pass)) {
            		valid = true;
            	}
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        return valid;
	}
	
	private String Name;
	private String Pass;
	private int Age;
	private String Telenumber;
	private String Nationality;
	private List<Booking> History_Of_Bookings;
	public List<Booking> getHistory_Of_Bookings() {
		return History_Of_Bookings;
	}
	public void setHistory_Of_Bookings(List<Booking> history_Of_Bookings) {
		History_Of_Bookings = history_Of_Bookings;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	
	
	
	

}