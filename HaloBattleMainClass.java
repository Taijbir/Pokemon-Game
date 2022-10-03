/* 
    haloBattleMainClass.java
    Author: Taijbir Kohli and Srinhoy Choudhury
    Teacher: Mr.Chu
    Date: January 22, 2021
    Assignment: ICS4U - Final Project
*/

import java.util.*;

class haloBattleMainClass {
  public static void main (String [] args) throws Exception {
    System.out.println("----------------------------------------------------HALO BATTLE--------------------------------------------------------");
    Scanner myScanner = new Scanner(System.in);
    ArrayList<Character> characters = new ArrayList<Character>(8);
    // "New" creates a new instance of the class, meaning all the data is wiped, which is problematic for character swapping. Thus, we must create objects for 
    // all character classes.  
    // Creates all object character classes, stores them in the characters arraylist
    Character masterchief = new Masterchief();
    Character ai = new AI();
    Character odst = new ODST();
    Character drengr = new Drengr();
    Character professeur = new Professeur();
    Character elite = new Elite();
    Character grunt = new Grunt();
    Character hunter = new Hunter();
    
    characters.add(masterchief);
    characters.add(ai);
    characters.add(odst);
    characters.add(drengr);
    characters.add(professeur);
    characters.add(elite);
    characters.add(grunt);
    characters.add(hunter);
    // Creates arraylist for each team 
    int userTeamCounter = 0;
    ArrayList<Character> userRoster = new ArrayList<Character>(4);
    ArrayList<Character> botRoster = new ArrayList<Character>(4);
    // Character select for user, characters not chosen go to the bot 
    while (userTeamCounter < 4) {
      System.out.println("Choose characters to add to your roster");
      for (int i = 0; i < characters.size(); i++) {
        System.out.println(characters.get(i).getName() + "[" + i + "]");
      }
      try {
        int userChoice = myScanner.nextInt();
        
        if (userChoice < characters.size()) {
          userRoster.add(characters.get(userChoice));
          System.out.println("User added " + characters.get(userChoice).getName() + " to their roster!");
          characters.remove(userChoice);
          userTeamCounter++;
          System.out.println("");
        } else {
          System.out.println("Invalid input");
        }
      } catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input");
      }
    } // User Character select End
    
    
    // Bot roster generation 
    
    
    System.out.println("User's Roster:");
    for (int i  = 0; i < userRoster.size(); i++) {
      System.out.println(userRoster.get(i).getName());
    }
    
    System.out.println("");
    
    System.out.println("Bot's Roster: ");
    for (int i = 0; i < characters.size(); i++) {
      botRoster.add(characters.get(i));
      System.out.println(botRoster.get(i).getName());
    }
    System.out.println("");
    
    // Deploy  
    System.out.println("User sent out " + userRoster.get(0).getName());
    Character userChar = userRoster.get(0);
    
    System.out.println("Bot sent out " + botRoster.get(0).getName());
    Character botChar = botRoster.get(0); 
   
    
    //------------------------------------------------------Character Team Select End-----------------------------------------------------------------------------
    Items.generateItems();
    boolean turn = true;
    // true is user, false is bot    
    // true is user, false is bot    
    // initializes important vars
    boolean userEvade = false;
    boolean botEvade = false; 
    boolean botSkip = false;
    boolean userSkip = false;
    boolean userTwoTurn = false; 
    boolean botTwoTurn = false; 
    double userTwoTurnVal = 0;
    // Game Loop End 
    outer:
    while (true) {
      userInterface(userChar, botChar);  
      boolean damageMod = false;
      boolean userDone = false;
      
      // User Turn -----------------------------------------------------------------------------------------------------------------------------------------
      if (turn) {
        // turn boolean indicates whose turn it is 
        if (userSkip == false) {
          // conditional for moves that skip turns 
          boolean skipHappened = false; 
          if (botSkip == true) {
            botSkip = false; 
          }
          // initializes returnArray[] for later use 
          // initialies itemInUse
          boolean itemInUse = false;
          double returnArray[] = new double[2];
          while(userDone == false) {
         // conditional to determine if the user gets a choice in regards to what they do
            int decision;
            if (userTwoTurn) {
              decision = 0;
              returnArray[0] = 0;
              returnArray[1] = userTwoTurnVal;
            } else {
            	if (userChar.getHealth() <=0) {
            		decision=2;
                   	System.out.println("Oh No "+userChar.getName()+"Is dead");
            	} else {
            		decision = userMenu();
            	}
            }
            
         
          
            
            // Decision 0 is the ability selection
            if (decision == 0) {
              // if user did not previously use a two turn move 
              if (userTwoTurn == false) {
                returnArray = new double[2];
                returnArray = abilities(userChar);
              } else {
                userTwoTurn = false; 
              }
              // User evade conditional
              if (returnArray[0] == 2) {
                userEvade = true;
                userDone = true;
                // bot skip conditional 
              } else if (returnArray[0] == 6) {
                botSkip = true; 
                userDone = true;
                skipHappened = true; 
                // Two turn ability activated conditional
              } else if (returnArray[0] == 7) {
                System.out.println(userChar.getName() + "is preparing for an attack...");
                userTwoTurnVal = returnArray[1]; 
                userTwoTurn = true;
                userDone = true;
              }
              // implementation of damage mod 
              else {
                double dmVal = 1;
                if (damageMod) {
                  dmVal = 2.5;
                }
                // getAttackCry() is for characters who used a two turn move
                userChar.getAttackCry();
                //implements ability
                implement(returnArray[0], returnArray[1], turn, botEvade, userChar, botChar, dmVal);
                userDone = true;
                damageMod = false;
                dmVal = 1;
                userTwoTurnVal = 0;
                
              }
            } // Ability decision End ------------------------------------------------------------------------------------------------------------------------
            
            
            
            // Item decision 
            else if (decision == 1) {
              // checks if item count is greater than 0
              if (Items.countItems(turn) > 0) { 
                // checks if there is an item currently in use 
                if (itemInUse == false) { 
                   
                  returnArray = new double[2];
                  
                  returnArray = Items.getItems(true); 
                   
                  
                  double dmVal = 1; // Overwritten 
                  implement(returnArray[0], returnArray[1], turn, botEvade, userChar, botChar, dmVal);
                  //implements item (only health pack, damage mod is actually implemented in ability section)  
                  // activates damage mod 
                  if (returnArray[0] == 5) {
                    damageMod = true; 
                    itemInUse = true;
                  } else if (returnArray[0] == 3) {
                    itemInUse = true;
                  }
                } else {
                  System.out.println("Item already in use!");
                }
              } else {
                System.out.println("No more items left!");
              }
            } // Item Decision End --------------------------------------------------------------------------------------------------------------------------- 
            // character swap deccision 
            else if (decision == 2) {
              // swaps the characters 
              int charIndex = charSwap(userChar, userRoster, turn);
              Character save = userRoster.get(0);
              userRoster.set(0, userRoster.get(charIndex));
              userRoster.set(charIndex, save); 
              userChar = userRoster.get(0);
              System.out.println("User swapped to " + userChar.getName() + "!");
              userDone = true;   
            } // Character Swap End -------------------------------------------------------------------------------------------------------------------------
            
          } // User Loop End 
          
           
          botEvade = false;
          if (skipHappened == false) {
            turn = false;
          }
          
        } // userSkip Conditional End  
        else if (userSkip == true) {
          System.out.println("USER GOT SKIPPED");
          userSkip = false;
        } 
      } else if (turn == false){
        boolean skipHappened = false;
        if (botSkip == false) { // Bot's Turn ------------------------------------------------------------------------------------------------------- 
           // everything for bot is the same as user 
            if (userSkip == true) {
              userSkip = false;
          }
          damageMod = false;
          double dmVal = 1;
          Random rand = new Random();
          double returnArray[] = new double[2];
        //Removes and swaps dead characters
          if(botChar.getHealth() <=0) {
           System.out.println("Oh no "+ botChar.getName() +" is dead");
           //Removes Dead Character
        botRoster.remove(botChar);
        //Determines if there are any bot characters left, meaning user wins
        if (botRoster.size() ==0 ) {
             System.out.println("ALL BOT CHARACTERS ARE DEAD, YOU WIN!");
             break outer;
        }
        //Picks a random character out of the bot characters left
        int randNum = rand.nextInt(botRoster.size());
        botChar = botRoster.get(randNum);
        System.out.println("Bot swapped to " + botChar.getName() + "!");
          }
          
          //Random Character Swap 
    int randSwap= rand.nextInt(10);
    //10% chance of a random swap when there are at least 2 characters
          if (botRoster.size()!=1 && randSwap == 1) {
           int randNum = rand.nextInt(botRoster.size());
        botChar = botRoster.get(randNum);
        System.out.println("Bot decided to swap to " + botChar.getName() + "!");
          }
          returnArray = botMain(turn, botChar);
          if (returnArray[0] == 2) {
            botEvade = true;
          } else if (returnArray[0] == 6) {
            userSkip = true;
            skipHappened = true;
          } else { 
            implement(returnArray[0], returnArray[1], turn, userEvade, userChar, botChar, dmVal);
          }
          
        } // botSkip Conditional End 
        
        //boolean skipHappened = false; 
        else if (botSkip == true) {
          System.out.println("BOT GOT SKIPPED!"); 
          botSkip  = false;
        } 
        
        if (skipHappened == false) {
          turn = true; 
        }
        userEvade = false;
        
      }// Bot Turn End 

      } // Game Loop End
      
    } // Main End
  
  
  
  
  
  // ----------------------------------------------------------------USER INTERFACE---------------------------------------------------------------------------
    // user interface prints the health and name of the current in play characters
  public static void userInterface(Character userChar, Character botChar) {
    Character currentChar; 
    // Printing ----------------------
    for (int x = 0; x < 2; x++) {
      if (x == 0) {
        currentChar = botChar;
      } else {
        currentChar = userChar;
      }
      double healthBar = 100 * (currentChar.getHealth() / currentChar.getMaxHealth());
      int barLength = 100;
      System.out.println(currentChar.getName());
      for (int i = 0; i < barLength; i++) {
        if (i == 0) {
          System.out.print("[");
        }
        if (i <= healthBar) {
          System.out.print("+");
        } else {
          System.out.print("_");
        }
        if (i == barLength - 1) {
          System.out.println("] HP: " + currentChar.getHealth());
        }
      } // For Loop End 
    } // Master for loop End
  } // User Interface End 
  
  
  
  
  
  //-------------------------------------------------------------------USER MENU-------------------------------------------------------------------------------
  
  
  
  // user decides to use an ability, or item, or swap characters
  public static int userMenu() throws Exception {
    Scanner myScanner = new Scanner(System.in);
    boolean looping = true;
    while (looping) {
      // What would you like do do? Ability + Counter, Item + Counter, Swap Out
      System.out.println("What would you like to do?");
      System.out.println("ABILITIES[0]");
      System.out.println("ITEMS[1]");
      System.out.println("SWAP CHARACTER[2]");
      String input = myScanner.nextLine();
      if (input.trim().equals("0")) {
        looping = false;
        return 0;
      } 
      
      else if (input.trim().equals("1")) {
        looping = false; 
        return 1;
      }
       
       
       else if (input.trim().equals("2")) {
       looping = false;
       return 2; 
       
       }
       
    } // while loop End
    // This return is never reached 
    return 7;
  } // userMenu End 
  
  
  
  
  //---------------------------------------------------------------ABILITIES---------------------------------------------------------------------------------
  
  
  
  // this method allows user to choose ability to perform, and it returns the affects of the method. See method description for more details in deliverables doc.
    public static double[] abilities(Character userChar) {
      double returnArray[] = new double[2];
      //Intializers (Placeholders)
      returnArray[0] = 333;
      returnArray[1] = 27;
      Scanner myScanner = new Scanner(System.in);
      boolean looping = true;
      while(looping) {
        System.out.println("Choose an Ability");
        userChar.getAbils(); 
        // try is to catch non integer input 
        try {
          int input = myScanner.nextInt();
          // access abilities
          if (input == 0) {  
            returnArray = userChar.abilOne();
            looping = false;
            // returns affects of ability 
            return returnArray;
            
          } else if (input == 1) {
            returnArray = userChar.abilTwo();
            looping = false;
            return returnArray; 
          } else if (input == 2) {
            returnArray = userChar.abilThree();
            looping = false;
            return returnArray; 
          }
          
        } catch (java.util.InputMismatchException e) {
          System.out.println("Invalid Input");
        }
      }
      
      // it will never get to this return 
      return returnArray;
    } // Abilities End
    
    // ----------------------------------------------------------CHARACTER SWAP------------------------------------------------------------------------------
    public static int charSwap(Character inPlay, List<Character> team, boolean turn) {
      Scanner myScanner = new Scanner(System.in);
      int choice = 9; // overwritten 
      // Makes sure that the character that is being swapped to isn't dead 
      // returns position of character player wants to swap to  
      while (true) {
        if (turn) {
          System.out.println("Choose a character to swap to: ");
          for(int i = 0; i < team.size(); i++) {
            System.out.println(team.get(i).getName() + " [" + i + "]");
          }
          try {
          choice = myScanner.nextInt();
          } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid Input");
          }
        } // turn conditional End
        // if the choice is less than the size of the team. If it's greater, then it's invalid. 
        if (choice < team.size()) {
          if (team.get(choice).getHealth() > 0 && (team.get(choice).equals(inPlay)) == false) {
            return choice;
          } else if (team.get(choice).getHealth() <= 0) {
            if (turn) {
              System.out.println("That character is dead.");
            }
          } else {
            if (turn) {
              System.out.println("That character is currently in-play.");
            }
          }
        } else {
          if (turn) {
            System.out.println("Invalid selection");
          }
        }
        
      } // While Loop End 
      
    } // charSwap End 
    
    // -----------------------------------------------------------IMPLEMENTATION-----------------------------------------------------------------------------
    public static void implement(double resOne, double resTwo, boolean turn, boolean oppEvade, Character userChar, Character botChar, double damageMod) {
      // implementation for user's turn 
      if (turn) {
        // if ability is an attack ability and the opponent did not evade
        if (resOne == 0 && oppEvade == false) {
          double newHealth = botChar.getHealth() - (resTwo * damageMod);
          botChar.setHealth(newHealth);
          // if they did evade 
        } else if (resOne == 0 && oppEvade == true) {
          System.out.println(botChar.getName() + " evaded " + userChar.getName() + "'s attack!");
        }
        // if it's a heal ability 
        if (resOne == 1) {
          double newHealth = userChar.getHealth() + resTwo;
          if (newHealth > userChar.getMaxHealth()) {
            newHealth = userChar.getMaxHealth();
          }
          userChar.setHealth(newHealth);
        }
        if (resOne == 3) {
          double newHealth = userChar.getHealth() + resTwo;
          if (newHealth > userChar.getMaxHealth()) {
            newHealth = userChar.getMaxHealth();
          }
          userChar.setHealth(newHealth); 
        } 
        //health pack application 
      } else { // implementation for bot 
        if (resOne == 0 && oppEvade == false) {
          double newHealth = userChar.getHealth() - resTwo;
          userChar.setHealth(newHealth);
        } else if (resOne == 0 && oppEvade == true) {
          System.out.println(userChar.getName() + " evaded " + botChar.getName() + "'s attack!");
        }
        if (resOne == 1) {
          double newHealth = botChar.getHealth() + resTwo;
          if (newHealth > botChar.getMaxHealth()) {
            newHealth = botChar.getMaxHealth();
          }
          botChar.setHealth(newHealth);
        } if (resOne == 3) {
          botChar.setHealth(botChar.getHealth() + resTwo);
        }
      } 
    } // Implement End
    


 // ----------------------------------------------------------------------BOT CODE-----------------------------------------------------------------------------
    public static double[] botMain(boolean turn, Character botChar) throws Exception {
        Random rand = new Random();
        double returnArray[] = new double[2];
        
        //Gives a 5% chance each bot turn to use a bot item
        int decision1 = rand.nextInt(20); 
        boolean damageMod = false;
        if (decision1 == 0) {
      	  returnArray = Items.getBotItems(turn, botChar);
      	  if (returnArray[0] == 5) {
      		  damageMod = true;
            }
        } 
        if(decision1 != 0 || returnArray[0] == 5)  {
      	// Bot abilities
            int chooseAbil = rand.nextInt(3);
            if (chooseAbil == 0) {
              returnArray = botChar.abilOne();
              if (damageMod == true && returnArray[0] == 0) {
            	  returnArray[1]=returnArray[1]*2.5;
              }
              return returnArray;
            } else if (chooseAbil == 1) {
              returnArray = botChar.abilTwo();
              if (returnArray[0]==7) {
             	 returnArray = botChar.abilOne();
              }
              if (damageMod == true && returnArray[0] == 0) {
            	  returnArray[1]=returnArray[1]*2.5;
              }
              return returnArray;
            } else {
              returnArray = botChar.abilThree();
              if (damageMod == true && returnArray[0] == 0) {
            	  returnArray[1]=returnArray[1]*2.5;
              }
              return returnArray;
            }
        }
        return returnArray;
      } // Bot Main End 
  } // Class End 