
/**
* ACDatabase.java
* @author Jose Leos
*/
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ACDatabase {
	private final int NUM_CHAR = 26;
	Hash<AnimeChar> ht = new Hash<>(NUM_CHAR * 2);
	BST<AnimeChar> bstByName = new BST<>(AnimeCharComparators.BY_NAME);
	BST<AnimeChar> bstByShow = new BST<>(AnimeCharComparators.BY_SHOW);

	public static void main(String[] args) throws IOException {
		ACDatabase acd = new ACDatabase();
		AnimeChar character = null;
		File file = new File("AnimeCharacter.txt");
		Scanner read;
		readFile(acd, character, file);
		boolean go = true;
			while (go) {
				menu();
				read = new Scanner(System.in);
				char ch = read.nextLine().charAt(0);
				ch = Character.toUpperCase(ch);
				if (ch == 'A') {
					addCharacter(acd,read);
				} else if (ch == 'L') {
					readDatabase(acd, read);
				} else if (ch == 'R') {
					removeCharacter(acd,read);
				} else if (ch == 'S') {
					searchCharacter(acd,read);
				} else if (ch == 'W') {
					writeToFile(acd);
				} else if (ch == 'Q') {
						go = false;
						read.close();
						break;
				} else {
					System.out.println("Invalid Selection!\n");
				}
			}
			System.out.println("\nGoodbye!");

	}
	public static void readFile(ACDatabase data, AnimeChar newEntry, File file)
	{
		System.out.println("Welcome to the Anime Character Database!\n");
		try{
			Scanner fileReader = new Scanner(file);
		while (fileReader.hasNextLine()) {
			String name = fileReader.nextLine();
			String show = fileReader.nextLine();
			String gender = fileReader.nextLine();
			String voiceActor = fileReader.nextLine();
			int year = fileReader.nextInt();

			newEntry = new AnimeChar(name, show, gender, voiceActor, year);
			data.ht.insert(newEntry);
			data.bstByName.insert(newEntry);
			data.bstByShow.insert(newEntry);

			if ((fileReader.hasNextLine())) {
				fileReader.nextLine();
			}
		}
		fileReader.close();
		}
		catch(IOException e)
		{
			e.getMessage();
		}
	}
	public static void menu() {
		System.out.println("Please select from one of the following options:\n");

		System.out.print("A. Add an Anime Character: +\n" +
				"L. List Anime Characters: \n" +
				"R. Remove an Anime Character: \n" +
				"S. Search for an Anime Character or a show: \n" +
				"W. Write the data to a file: \n" +
				"Q. Quit: \n" +
				"\nEnter your choice: ");
	}
	public static void addCharacter(ACDatabase data, Scanner nextIn)
	{
		System.out.println("\nAdding a Character");
		System.out.print("\nEnter the Character name: ");
		String tempName = nextIn.nextLine();
		System.out.print("Enter the Show name: ");
		String tempShow = nextIn.nextLine();
		System.out.print("Enter the Character gender(Male, Female, Other): ");
		String tempGender = nextIn.nextLine();
		System.out.print("Enter the name of the Voice Actor: ");
		String tempVA = nextIn.nextLine();
		System.out.print("Enter the year of release: ");
		int tempYear = nextIn.nextInt();
		AnimeChar newChar = new AnimeChar(tempName, tempShow, tempGender, tempVA, tempYear);
		data.ht.insert(newChar);
		data.bstByName.insert(newChar);
		data.bstByShow.insert(newChar);
		System.out.println("\n" + tempName + " was added!\n");
	}
	public static void readDatabase(ACDatabase data, Scanner nextIn)
	{
		while (true) {
			System.out.println("\nPlease select one of the following options: \n" +
					"P. Sorted by Character Name\n" +
					"S. Sorted by Title Name\n" +
					"U. Unsorted\n");
			System.out.print("Enter your choice: ");
			char ch = nextIn.nextLine().charAt(0);
			ch = Character.toUpperCase(ch);
			if (ch == 'U') {
				System.out.println("\nDisplaying Anime Characters: \n");
				System.out.println(data.ht);
				break;
			} else if (ch == 'P') {
				System.out.println("\nDisplaying Anime Characters: \n");
				data.bstByName.inOrderPrint();
				break;
			} else if (ch == 'S') {
				System.out.println("\nDisplaying Anime Characters: \n");
				data.bstByShow.inOrderPrint();
				break;
			} else {
				System.out.println("\nInvalid Selection!");
			}
		}
	}
	public static void removeCharacter(ACDatabase data, Scanner nextIn)
	{
		System.out.println("Removing a Character!");
		System.out.print("\nEnter the character name: ");
		String tempName = nextIn.nextLine();
		System.out.print("Enter the show name: ");
		String tempShow = nextIn.nextLine();
		AnimeChar newChar = new AnimeChar(tempName, tempShow, "", "", 0);
		if (data.ht.search(newChar) != -1) {
			data.ht.remove(newChar);
			data.bstByName.remove(newChar);
			data.bstByShow.remove(newChar);
			System.out.println("\n" + newChar.getName() + " was removed!\n");
		} else {
			System.out.println("\n" + newChar.getName() + " cannot be located in the database.\n");
		}
	}
	public static void searchCharacter(ACDatabase data, Scanner nextIn)
	{
		while (true) {
			System.out.print("\nPlease select one of the following options: \n" +
					"N. Name of the Character\n" +
					"S. Title name\n" +
					"\nEnter your choice: ");
			char ch = nextIn.nextLine().charAt(0);
			if (ch == 'N') {
				System.out.print("\nSearching for a Character!"+
									"\nEnter the character name: ");
				String tempName = nextIn.nextLine();

				AnimeChar newChar = new AnimeChar(tempName, "", "", "", 0);
				List<AnimeChar> results = data.bstByName.collectOccurrences(newChar);
				if (results.isEmpty()) {
					System.out.println("\n" + newChar.getName() + " is not in the database.\n");
				} else {
					System.out.println("\n" + newChar.getName() + " is in the database!\n");
					for (AnimeChar ac : results) {
						System.out.println(ac);
					}			
				}
				break;
			} else if (ch == 'S') {
				System.out.print("Searching for a Show!"+
								   "\nEnter the show name: ");
				String tempShow = nextIn.nextLine();
				AnimeChar check = new AnimeChar("", tempShow, "", "", 0);
				List<AnimeChar> results = data.bstByShow.collectOccurrences(check);
				if (results.isEmpty()) {
					System.out.println("\n" + check.getShow() + " is not in the database.\n");
				} else {
					System.out.println("\n" + check.getShow() + " is in the database!\n");
					for (AnimeChar ac : results) {
						System.out.println(ac);
					}
				}
				break;
			} else {
				System.out.println("\nInvalid Selection");
			}
		}
	}
	public static void writeToFile(ACDatabase data)
	{	
		try {
			PrintWriter unsorted = new PrintWriter("Character List.txt");
			unsorted.println(data.ht);
		
			unsorted.close();

		} catch (Exception e) {
			e.getMessage();
		}

		System.out.println("File has been written to Character List.txt.\n");
	}
}
