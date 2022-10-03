import java.util.*;
/* returning langauge [x, y];
 * x:
 * 0 means applied to enemy player 
 * 1 means applied to self
 * 2 means evade (or block if we end up implementing that)
 * y:
 * Damage value. Either damage healed or damage delt.
*/
class Grunt extends Character {
  private String[] abils = {"Plasma Grenade[0]", "Charge Shot[1]", "Healing Gas[2]"}; 
  Grunt () { // these parameters will later get their values from notepads 
    super(100, "GRUNT");
  }
  
  public String getMove() {
    return name;
  }
  
  public void getAbils() {
    System.out.println(abils[0]);
    System.out.println(abils[1]);
    System.out.println(abils[2]); 
  }
  
  public double[] abilOne() {
    // Plasma Grenade:
    // Deals 80 damage, 66% chance of working
    double results[] = new double[2];

    Random rand = new Random();
    int chance = rand.nextInt(100) + 1;
    if (chance <= 66) {
      results[0] = 0;
      results[1] = 80;
      System.out.println(getName() + " used Plasma Grenade!");
    } else {
      results[0] = 0;
      results[1] = 0;
      System.out.println(getName() + " used Plasma Grenade! And.... Missed. LOL");
    }
    
    
    return results;   
  }
  
  public double[] abilTwo() {
    // Charge Shot:
    // Deals 30 damage
    
    double results[] = new double[2];
    results[0] = 0;
    results[1] = 30;
    System.out.println(getName() + " used Charge Shot!");
    return results;
  }
  
  public double[] abilThree() {
    // Healing Gas:  
    // Heals %40 of current health
    double results[] = new double[2];
    results[0] = 1;
    double heal = getHealth() * 40/100;
    results[1] = heal;
    System.out.println(getName() + " used Healing Gas!");
    return results;
  }
  
  
  
} // class end 