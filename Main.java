/*
John Slaughter jws0328
Simple key-focused database storage
3 commands: SET <key> <value>, GET <key>, EXIT
*/
//Importing packages
import java.util.Scanner; //Useful for inputs
import java.io.*; //I/O interactions, which is important for file interaction

//function prototypes
private static void DBStartup();
private static void set(String, String);
private static String get(String);

public class Main {
  public static void main(String[] args) {
    //initializing global arrays for keys and values
    public static ArrayList<String> keys = new ArrayList<>();
    public static ArrayList<String> values = new ArrayList<>();
    //Call startup function
    DBStartup();

    boolean flag = true;
    while (flag) {
      /*
      Console gives info and checks for response
      User can give either SET, GET, or EXIT - loops back if different
      Desired outputs "SET <key> <value>", "GET <key> <value>, "EXIT"
      SET and GET call respective functions and loops back
      EXIT ends program
      */
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
    while ((line = DB.readLine()) != NULL) {
      String[] parts = line.split(" ", 2);
      String key = parts[0];
      String value = parts[1];

      int index = keys.indexOf(key);
      if (index >= 0) {
        values.set(index, value);
      } else {
        keys.add(key);
        values.add(value);
      }
    }
    DB.close();
  }
}

/*
check if key exists in array, if so replace its corresponding value, otherwise add both to end of arrays
open file and reach the end of it, append key and value to it
*/
private static void set(String key, String value) {

}

/*
  check keys array for if the key is present
  if key is present, return its corresponding value
  if key is not present, return null
*/
private static String get(String key) {
  int index = keys.indexOf(key);
  if (index == -1) {
    return NULL;
  } else {
    return values.get(index);
  }
}
