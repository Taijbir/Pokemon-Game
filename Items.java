import java.util.*;
import java.io.*;
//import java.io.InputStream;

class Items {
  // 3 is healthpack is available
  // 5 is if Damage Modifier is available 
  // 4 is if the item is unavailable 
  // item count method which is like if there are no items available they won't even let you choose the item decision 

 // Bot Item Generation
 private static int botHealthPacks = 2;
 private static int botDamageModifiers = 2;
  
  public static void generateItems() throws Exception {
    // User Item Generation (fills textfile)
    File userFile = new java.io.File("userItems.txt");
    PrintWriter userOutput = new PrintWriter(userFile);
    userOutput = new PrintWriter(new FileWriter(userFile, true));
    userOutput.println("#HP: " + 2);
    userOutput.println("#DM: " + 2);
    userOutput.close();
  }
  
  
  public static int getNumHP(boolean turn) throws Exception {
 int healthPacks = 0;
    File myFile; 
    if (turn) {
      myFile = new java.io.File("userItems.txt");
    } else {
     return botHealthPacks;
    }
    Scanner input = new Scanner(myFile);
    
    
    while (input.hasNext()) {
      if (input.next().trim().equals("#HP:")) {
        healthPacks = Integer.parseInt(input.next().trim());
      }
    }
    
    
    return healthPacks;
  } // getNumHP End
  
  

  
  public static double[] applyHealthPack(boolean turn) throws Exception {
    int healthPacks = getNumHP(turn);
    double returnArray[] = new double[2];
    if (healthPacks > 0) {
      returnArray[0] = 3; // indicates its a health pack 
      returnArray[1] = 75; // heals 75 health 
    } else {
      returnArray[0] = 4;
      returnArray[1] = 0;
    }
    return returnArray;
  } 
  
  
  // prints items availble to players and coordinates to return affects back to main class
  public static double[] getItems(boolean turn) throws Exception {
    File myFile = new java.io.File("userItems.txt");
    Scanner input = new Scanner(myFile);
    Scanner myScanner = new Scanner(System.in);
    
    
    while (true) { 
      System.out.println("Choose an item to apply");
      System.out.println("HP = HealthPack [Enter 0 to apply]");
      System.out.println("DM = Damage Modifier [Enter 1 to apply]");
      System.out.println("Enter [2] to go back");
      
        System.out.println("HP: " + getNumHP(turn));
        System.out.println("DM: " + getNumDM(turn));
    
      
      double returnArray[] = new double[2];
      int chooseItem = myScanner.nextInt();
      
      if (chooseItem == 0) {
        returnArray = applyHealthPack(turn);
        if (returnArray[0] == 4) {
          if (turn) {
          System.out.println("No more health packs left");
          }
        } else {
          System.out.println("Applied a Health Pack");
          //returnArray = applyHealthPack(turn);
          updateItems(1, 0, 0, 0, turn);
          input.close();
          return returnArray;
        }
      } else if (chooseItem == 1) {
        returnArray = applyDamageMod(turn);
        if (returnArray[0] == 4) {
          if (turn) {
          System.out.println("No more Damage Modifiers left");
          }
        } else {
          updateItems(0, 1, 0, 0, turn);
          input.close();
          System.out.println("Applied a Damage Modifier");
          return returnArray;
        }
      } else if (chooseItem == 2) {
        returnArray[0] = 37;
        returnArray[0] = 37; // Random Values, will not trigger anything
        return returnArray;
      }
    } // While End 

  } // getItems End 
  
  
  
  
  
  public static double[] getBotItems(boolean turn, Character botChar) throws Exception {
   Random rand = new Random();
   while (true) { 
    int chooseItem = rand.nextInt(2);
    double returnArray[] = new double[2];
    if(botHealthPacks ==0) { 
     chooseItem =1;
    }
    if(botDamageModifiers ==0) {
     chooseItem =0;
    }
    if (botHealthPacks ==0 && botDamageModifiers ==0) {
     returnArray[0] = 37;
        returnArray[0] = 37; // Random Values, will not trigger anything
        return returnArray;
    }
    if (chooseItem == 0) {         
      System.out.println("Bot Applied a Health Pack");
         returnArray = applyHealthPack(turn);
         updateItems(0, 0, 1, 0, turn);
         return returnArray;
    } else if (chooseItem == 1) {
      updateItems(0, 0, 0, 1, turn);
         System.out.println("Bot Applied a Damage Modifier");
         returnArray = applyDamageMod(turn);
         return returnArray;
    } else {
      continue;
    }
   } // While End 
  } // getBotItems End 
  
  
  
  
  
  // Updates the quantity of items in text files
  public static void updateItems(int newUserHP, int newUserDM, int newBotHP, int newBotDM, boolean turn) throws Exception {
    int userHP = getNumHP(turn) - newUserHP;
    int userDM = getNumDM(turn) - newUserDM;
    int botHP = getNumHP(turn) - newBotHP;
    int botDM = getNumDM(turn) - newBotDM;
    
    
    if (turn) {
     File userFile = new java.io.File("userItems.txt");
     //PrintWriter userOutput = new PrintWriter(userFile);
     PrintWriter userOutput = new PrintWriter(new FileWriter(userFile, true));
     userOutput.println("#HP: " + userHP);
     userOutput.println("#DM: " + userDM);
     userOutput.close();
    } else {
     botDamageModifiers = botDM;
        botHealthPacks = botHP;
     System.out.println("Bot HP: "+botHP);
     System.out.println("Bot DM: "+botDM);
    }
  }
  
  
  
  
  
  
  
  
  
  
  
  // Returns quantity of damage modifiers in a specific text file 
  public static int getNumDM(boolean turn) throws Exception {
    File myFile;
    int damageModifiers = 0;
    if (turn) {
      myFile = new java.io.File("userItems.txt");
    } else {
     return botDamageModifiers;
    }
    Scanner input = new Scanner(myFile);
    while (input.hasNext()) {
      if (input.next().trim().equals("#DM:")) {
        damageModifiers = Integer.parseInt(input.next().trim());
      }
    }
    return damageModifiers;
  }
  
  
  
  
  
  
  
  
  
  
  // returns affects of damageMod to main class (goes through getItems() first)
  public static double[] applyDamageMod(boolean turn) throws Exception {
    int damageModifiers = getNumDM(turn);
    double returnArray[] = new double[2];
    if (damageModifiers > 0) {
      returnArray[0] = 5;
      returnArray[1] = 2.5; // Increases damage by 2.5, this return value doesn't really matter because it's fixed in main class.  
    } else {
      returnArray[0] = 4;
      returnArray[1] = 0;
    }
    return returnArray;
  } // applyDamageMod
  
  
  
  
  
  // Count's the number of items player has
  public static int countItems(boolean turn) throws Exception {
    return getNumHP(turn) + getNumDM(turn);
  }
  
  
} // Class End 