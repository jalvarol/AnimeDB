/**
* AnimeChar.java
* @author Jose Leos
*/

public class AnimeChar implements Comparable<AnimeChar>{
    private String name;
    private String show;
    private String gender;
    private String voiceActor;
    private int year;
    
    /****CONSTRUCTOR****/
    
    /**
     * Constructor for the AnimeChar class
     * @param name of the character
     * @param show which the character is from
     * @param gender of the character
     * @param voice actor of the character
     * @param age of the character
     */
    public AnimeChar(String name, String show, String gender, String voiceActor, int year) {
    	this.name = name;
    	this.show = show;
    	this.gender = gender;
    	this.voiceActor = voiceActor;
    	this.year = year;
    }
    
    /****ACCESSORS****/
    
    /**
     * Access the name of the character
     * @return the character's name
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Access the show which the character is from
     * @return the name of the show
     */
    public String getShow() {
    	return show;
    }
    
    /**
     * Access the gender of the character
     * @return gender of the character
     */
    public String getGender() {
    	return gender;
    }
    
    /**
     * Access the voice actor of the character
     * @return the character's voice actor
     */
    public String getVoiceActor() {
    	return voiceActor;
    }
    
    /**
     * Access the release year of the show
     * @return the year of the show
     */
    public int getYear() {
    	return year;
    }
    
    /****MUTATORS****/

    /**
     * sets the name of the character
     * @param name the character's name
     */
    public void setName(String name) {
    	this.name = name;
    }
    
    /**
     * sets the name of the show
     * @param name the name of the show
     */
    public void setShow(String show) {
    	this.show = show;
    }
    
    /**
     * sets the gender of the character
     * @param gender the gender of the character
     */
    public void setGender(String gender) {
    	this.gender = gender;
    }
    
    /**
     * sets the voice actor of the character
     * @param voiceActor the voice actor of the character
     */
    public void setVoiceActor(String voiceActor) {
    	this.voiceActor = voiceActor;
    }
    
    /**
     * sets the age of the character
     * @param age the age of the character
     */
    public void setYear(int year) {
    	this.year = year;
    }
    
    /****ADDITIONAL OPERATIONS****/

    /**
     * Creates a String of the Movie information
     * the following format:
     * Name: <name>
     * Show: <show>
     * Gender: <gender>
     * Voice Actor: <voiceActor>
     * Release Year: <year>
     */
    @Override public String toString() {
    	String result = 
    			"Name: " + name +
        		"\nShow: " + show +
        		"\nGender: " + gender +
        		"\nVoice Actor: " + voiceActor + 
        		"\nRelease year: " + year + "\n"
        		;
    	
		return result;  	
    }
    
    /**
     * Determines whether two AnimeChar objects are
     * equal by comparing names and shows
     * @param otherAC the second AnimeChar object
     * @return whether the AnimeChar are equal
     */
    @Override public boolean equals(Object o) {
    	if (o == this) {
    		return true;
    	}else if (!(o instanceof AnimeChar) || o == null) {
    		return false;
    	}else {
    		AnimeChar temp = (AnimeChar) o;
    		return name.equalsIgnoreCase(temp.name) && show.equalsIgnoreCase(temp.show);        
    	}
    		
    }
    
    
    /**
     * Compares two AnimeChar objects to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this character's name comes alphabetically
     * before the other character's name.
     * Returns 1 if the other character's name comes
     * alphabetically before this character's name
     * If the two characters's names are the same, will
     * differentiate by show's name (alphabetical
     * comparison)
     * @param the other AnimeChar object to compare to this
     * @return 0 (same character), -1 (this character ordered first)
     * or 1 (the other character ordered first)
     */
	@Override
    public int compareTo(AnimeChar other) {
        return this.name.compareToIgnoreCase(other.name);
    }


	/**
     * Returns a consistent hash code for
     * each AnimeChar by summing the Unicode values
     * of each character in the key
     * Key = name
     * @return the hash code
     */
    @Override public int hashCode() {
    	String key = this.name+this.show;
    	int sum = 0;
    	for(int i=0; i<key.length(); i++) 
        {
    		sum += (int)key.charAt(i); 
    	}
        return sum;
    }

}
