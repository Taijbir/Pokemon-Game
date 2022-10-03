public class Character {
  protected double maxHealth = 0;
  protected double health = 0;
  protected String name;
  protected String[] abils;
  public Character (int maxHealth, String name) {
    // items will be intrinsic to hero character, and each time has a fixed amount of items that they can use at random, so we actually do need seperate 
    // parent classes for the user and the bot 
    // Actually, we can make the item values stored in notepad, and then make items it's seperate class. One class houses both notepads. 
    // Match Data (score) can be kept in a notepad, everything else can be kept in computer memory, makes things way easier
    this.maxHealth = maxHealth;
    health = maxHealth; 
    this.name = name;
  }
  
 
  public double getMaxHealth() {
    return maxHealth; 
  }
  
  public double getHealth() {
    return health; 
  }
  
  public void setHealth(double newHealth) {
    health = newHealth;
  }
  
  public String getName() {
    return name; 
  }
  
  public void getAbils() {
    
  }
  
  public void getAttackCry() {
    if (getName().equals("HUNTER")) {
      System.out.println(getName() + " used Massive smack!");
    } else if (getName().equals("PROFESSEUR")) {
      System.out.println(getName() + " used Atomic Attack!");
    }
  }
  
  public double[] abilOne() { 
    // is overrided, required for reference 
    double array[] = {0, 0};
    return array;
  }
  
  public double[] abilTwo() { 
    // is overrided, required for reference 
    double array[] = {0, 0};
    return array;
  }
  
  public double[] abilThree() { 
    // is overrided, required for reference 
    double array[] = {0, 0};
    return array;
  }
  
} // Class End