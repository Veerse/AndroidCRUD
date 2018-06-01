package example.nassim.contactcrud.Model;

import com.orm.SugarRecord;

public class Contact extends SugarRecord {

    private String firstName;
    private String lastName;
    private int number;

    public Contact(){}

    public Contact(String firstName, String lastName, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    // toString has to be overwritten as the adapter calls contact.toString()
    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

}
