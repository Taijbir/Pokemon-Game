import java.util.*;
/* returning langauge [x, y];
 * x:
 * 0 means applied to enemy player 
 * 1 means applied to self
 * 2 means evade (or block if we end up implementing that)
 * y:
 * Damage value. Either damage healed or damage delt.
*/
public class Masterchief extends Character {
  private String[] abils = {"Shoot[0]", "Grenade[1]", "Evade[2]"}; 
  public Masterchief () { // these parameters will later get their values from notepads 
    super(250, "MASTERCHIEF");
  }
  
  public String getMove() {
    return name;
  }
  
  public void getAbils() {
    System.out.println(abils[0]);
    System.out.println(abils[1]);
    System.out.println(abils[2]); 
  }
  
  // tbh spartan (masterchief) should not be able to evade
  public double[] abilThree() {
    // Evade: %50 chance of dodging the enemies attack.
    double results[] = new double[2];
    // Evade 
    // %40 chance of evading 
    Random rand = new Random();
    int chance = rand.nextInt(100) + 1;
    if (chance <= 50) {
      // then evade works and user takes 0 damage from oponents move 
      results[0] = 2;
      results[1] = 0;
    } else {
      // evade does not work, and because this is an evade attempt user deals 0 damage as well
      results[0] = 1;
      results[1] = 0;
    }
    
    System.out.println(getName() + " is focussing for a dodge.....");
    return results;   
  }
  
  public double[] abilTwo() {
    // Grenade: 
    // base damage 15, with a random damager modifier from 10 to 100 percent (we can change this to deal less than 15 possibly)
    double results[] = new double[2];
    results[0] = 0;
    Random rand = new Random();
    double chance = rand.nextInt(100) + 1;
   
    double damage = 15 * (chance / 100 + 1);
    results[1] = damage;
    System.out.println(getName() + " used Grenade!");
    return results;
  }
  
  public double[] abilOne() {
    // Shoot: 
    // Deals 20 damage.
    double results[] = new double[2];
    results[0] = 0;
    results[1] = 20;
    // heals 20 health.
    System.out.println(getName() + " used Shoot!");
    return results;
  }
  
  
  
} // class end 