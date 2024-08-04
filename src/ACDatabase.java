/**
* ACDatabase.java
* @author Jose Leos
*/

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ACDatabase {
	private final int NUM_CHAR = 26;
	Hash<AnimeChar> ht = new Hash<>(NUM_CHAR * 2);
	BST<AnimeChar> bst1 = new BST<>();
	BST<AnimeChar> bst2 = new BST<>();

	public static void main(String[] args) throws IOException {
		String name;
		String show;
		String gender;
		String voiceActor;
		int year;
		
		ACDatabase acd = new ACDatabase();
		
		File file = new File("AnimeCharacter.txt");
		Scanner inputFile = new Scanner(file);
		inputFile.close();
		
		Scanner fileReader;
		try {
			fileReader = new Scanner(file);
			
			while(fileReader.hasNext()){
				name = fileReader.nextLine();
				show = fileReader.nextLine();
				gender = fileReader.nextLine();
				voiceActor = fileReader.nextLine();
				year = fileReader.nextInt();
				
				AnimeChar ac = new AnimeChar(name, show, gender, voiceActor, year);
				acd.ht.insert(ac);
				acd.bst1.insert1(ac);
				acd.bst2.insert2(ac);

				if ((fileReader.hasNext())) {
					fileReader.nextLine();
				}
			}
			
			fileReader.close();
			
		} catch (IOException e){
			e.getMessage();
		}
				
		
		
		System.out.println("Welcome to the Anime Character Database!\n");

		String selection = "";
		Scanner input = new Scanner(System.in);

		while(!selection.equals("Q")){
			menu();
			selection = input.next().toUpperCase();
			System.out.println();        	

			if(selection.equals("A")) {
				addChar(acd);
			}else if(selection.equals("L")) {
				listChar(acd);
			}else if(selection.equals("R")) {
				removeChar(acd);
			}else if(selection.equals("S")) {
				Scanner userInput = new Scanner(System.in);
				System.out.println("Please select one of the following options: ");

				System.out.println("\nN. Name of the Character\nS. Show name");
				System.out.println();
				System.out.print("Enter your choice: ");
				String sortChoice = userInput.next().toUpperCase();
				System.out.println();
				while(!(sortChoice.equals("N") || sortChoice.equals("S"))) {
					System.out.println("Invalid option. Please select again.");
					sortChoice = userInput.next().toUpperCase();
				}
				userInput.close();
				if(sortChoice.equals("N")) {
					searchCharName(acd);
				}else {
					searchCharShow(acd);
				}
			}else if(selection.equals("W")){
				writeData(acd);
			}else if(selection.equals("Q")){
				System.out.println("Goodbye!");
				break;
			}else {
				System.out.println("Invalid Selection!\n");
			}
		}
		input.close();
		return;
	
	}
	
	public static void addChar(ACDatabase data) {
		Scanner userInput = new Scanner(System.in);
		String tempName;
		String tempShow;
		String tempGender;
		String tempVA;
		int tempYear;
		System.out.println("Adding a Character");
		System.out.print("\nEnter the Character name: ");
		tempName = userInput.nextLine();
		System.out.print("Enter the Show name: ");
		tempShow = userInput.nextLine();
		System.out.print("Enter the Character gender(Male, Female, Other): ");
		tempGender = userInput.nextLine();
		System.out.print("Enter the name of the Voice Actor: ");
		tempVA = userInput.nextLine();
		System.out.print("Enter the year of release: ");
		tempYear = userInput.nextInt();
		
		AnimeChar newChar = new AnimeChar(tempName, tempShow, tempGender, tempVA, tempYear);
		
		data.ht.insert(newChar);
		data.bst1.insert1(newChar);
		data.bst2.insert2(newChar);
		
		System.out.println("\n" + tempName + " was added!");
		System.out.println();
		userInput.close();
		
		
	}
	
	public static void listChar(ACDatabase data) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please select one of the following options: ");

		System.out.println("\nP. Sorted by Character Name\nS. Sorted by Anime Name\nU. Unsorted");
		System.out.println();
		System.out.print("Enter your choice: ");
		String sortChoice = userInput.next().toUpperCase();
		System.out.println();
		while(!(sortChoice.equals("U") ||sortChoice.equals("P") || sortChoice.equals("S"))) {
			System.out.println("Invalid option. Please select again.");
			sortChoice = userInput.next();
		}
		if(sortChoice.equals("U")) {
			System.out.println("Displaying Anime Characters: ");
			System.out.println(data.ht);
		}else if(sortChoice.equals("P")){
			System.out.println("Displaying Anime Characters: ");
			data.bst1.inOrderPrint();
		}else if(sortChoice.equals("S")){
			System.out.println("Displaying Anime Characters: ");
			data.bst2.inOrderPrint();
		}
		userInput.close();
	}
	
	public static void removeChar(ACDatabase data) {  
		System.out.println("Removing a Character!");
		Scanner userInput = new Scanner(System.in);
		String tempName;
		String tempShow;
		System.out.print("\nEnter the character name: ");
		tempName = userInput.nextLine();
		System.out.print("Enter the show name: ");
		tempShow = userInput.nextLine();
		AnimeChar newChar = new AnimeChar(tempName, tempShow, "", "", 0);
		if(data.ht.search(newChar) != -1) {
			data.ht.remove(newChar); 
			data.bst1.remove1(newChar);
			data.bst2.remove2(newChar);
			System.out.println("\n" + newChar.getName() + " was removed!\n");
		}else {
			System.out.println("\nI cannot find " + newChar.getName() + " in the database.\n");
		}
		
		userInput.close();
	}
	
	public static void searchCharName(ACDatabase data) {
		System.out.println("Searching for a Character!");
		Scanner userInput = new Scanner(System.in);
		String tempName;
		System.out.print("\nEnter the character name: ");
		tempName = userInput.nextLine();

		AnimeChar newChar = new AnimeChar(tempName, "", "", "", 0);		

		if(data.bst1.search1(newChar)) {
			System.out.println("\n" + newChar.getName() + " is in the database!");
			System.out.println();
		}else {
			System.out.println("\n" + newChar.getName() + " is not in the database.");
			System.out.println();
		}
		userInput.close();
	}
	
	public static void searchCharShow(ACDatabase data) {
		System.out.println("Searching for a Show!");
		Scanner userInput = new Scanner(System.in);
		String tempShow;
		System.out.print("Enter the show name: ");
		tempShow = userInput.nextLine();
		AnimeChar newChar = new AnimeChar("", tempShow, "", "", 0);
		
		if(data.bst2.search2(newChar)) {
			System.out.println("\n" + newChar.getShow() + " is in the database!");
			System.out.println();
			System.out.println("Displaying targeted show: ");
			
			BST<AnimeChar> temp = new BST<AnimeChar>(data.bst2, newChar);
			temp.inOrderPrint();
			
		}else {
			System.out.println("\n" + newChar.getShow() + " is not in the database.");
			System.out.println();
		}
		userInput.close();

	}
	
	public static void menu() {
		System.out.println("Please select from one of the following options:\n");

		System.out.println("A. Add an Anime Character: ");
		System.out.println("L. List Anime Characters: ");
		System.out.println("R. Remove an Anime Character: ");
		System.out.println("S. Search for an Anime Character or a show: ");
		System.out.println("W. Write the data to a file: ");
		System.out.println("Q. Quit: ");
		System.out.print("\nEnter your choice: ");
	}
	
	public static void writeData(ACDatabase data) {
		try {
		PrintWriter unsorted = new PrintWriter("Character List.txt");
		unsorted.println(data.ht);

		unsorted.close();

		} catch (Exception e){
        	e.getMessage();		
		}

		System.out.println("File has been written to Character List.txt.");
		System.out.println();

	}
	
}
