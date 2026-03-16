/*
John Slaughter jws0328
Simple key-focused database storage
3 commands: SET <key> <value>, GET <key>, EXIT
*/
//Importing packages
import java.util.Scanner; //Useful for inputs
import java.util.ArrayList; //for arraylist
import java.io.*; //I/O interactions, which is important for file interaction

//function prototypes
//private static void DBStartup();
//private static void setter(String, String);
//private static String getter(String);

public class Main {
   //initializing global arrays for keys and values
    public static ArrayList<String> keys = new ArrayList<>();
    public static ArrayList<String> values = new ArrayList<>();
  
  public static void main(String[] args) {
    //Call startup function
    DBStartup();

   //setting up for main interactivity + initializing scanner for input
    boolean flag = true;
    Scanner scanner = new Scanner(System.in);
    while (flag) {
      /*
      Console gives info and checks for response
      User can give either SET, GET, or EXIT - loops back if different
      Desired outputs "SET <key> <value>", "GET <key> <value>, "EXIT"
      SET and GET call respective functions and loops back
      EXIT ends program
      */
      System.out.println("Please enter SET <key> <value>, GET <key>, or EXIT");
      String input = scanner.nextLine();
      String[] parts = input.split(" ", 3);
      if (parts[0].equals("SET") && parts.length == 3) {
        setter(parts[1], parts[2]);
      } else if (parts[0].equals("GET") && parts.length == 2) {
        getter(parts[1]);
      } else if (parts[0].equals("EXIT")) {
        System.out.println("Exit confirmed!");
        flag = false;
      } else {
        System.out.println("Command was not identified as GET, SET, or EXIT. Please try again.");
      }
    }
  }
    private static void DBStartup() {
    // read text file line by line example https://www.w3schools.com/java/java_bufferedreader.asp
    try (BufferedReader DB = new BufferedReader(new FileReader("data.db"))) {
      /*
      Store each line in database file to line, one at a time
      split between key and value in each line
      add both key and value if key isn't present in array
      if key is present, change its value with the new value
      */
      String line;
      while ((line = DB.readLine()) != null) {
        String[] parts = line.split(" ", 2);
        String key = parts[0];
        String value = parts[1];
        int index = keys.indexOf(key);
        if (index >= 0) {
          //System.out.println("switching value for " + key + " to " + value);
          values.set(index, value);
        } else {
          //System.out.println("Adding " + value + " to " + key + " key");
          keys.add(key);
          values.add(value);
        }
      } //catch (IOException e) {
        //System.out.println("Database file not found.");
      //DB.close();
    } catch (IOException e) {
        System.out.println("Database file not found.");
    }
  }

  /*
  check if key exists in array, if so replace its corresponding value, otherwise add both to end of arrays
  open file and reach the end of it, append key and value to it
  */
  private static void setter(String key, String value) {
    int index = keys.indexOf(key);
    if (index >= 0) {
      values.set(index, value);
    } else {
      keys.add(key);
      values.add(value);
    }
    //https://www.w3schools.com/JAVA/java_bufferedwriter.asp, add ", true" to enable append mode
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("data.db", true))) {
      bw.newLine();
      bw.write(key + " " + value);
      bw.close();
    } catch (IOException e) {
      System.out.println("Error when writing to db");
    }
  }
  /*
    check keys array for if the key is present
    if key is present, return its corresponding value
    if key is not present, return null
  */
  private static String getter(String key) {
    int index = keys.indexOf(key);
    if (index == -1) {
      System.out.println(key + " is not stored and thus has no value");
      return null;
    } else {
      System.out.println("The value of key " + key + " is " + values.get(index));
      return values.get(index);
    }
  } 
}






