package cpsc2150.quizzes;

public class Person {
    /**Constructor: sets firstName and lastName according to the parameters.
     @pre [ first and last names are valid ]
     @post firstName = first AND lastName = last
     */
    public Person(String first, String last) {};

    /**Function to output the first name and last name in the form (with a space separating the two strings): firstName lastName
     @pre None
     @post [ Output person's name to console ] AND firstName = #firstName AND lastName = #lastName
     */
    public final void print() {};

    /**Function to set firstName and lastName according to the parameters
     @pre [ first and last names are valid ]
     @post firstName = first AND lastName = last
     */
    public void setName(String first, String last) {};

    /**Function to return the first name
     @pre None
     @post getFirstName = firstName AND firstName = #firstName AND lastName = #lastName
     */
    public final String getFirstName() {};

    /**Function to return the last name
     @pre None
     @post getLastName = lastName AND firstName = #firstName AND lastName = #lastName
     */
    public final String getLastName() {};

    private String firstName;
    private String lastName;
}
