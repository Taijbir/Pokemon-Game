
/* returning langauge [x, y];
 * x:
 * 0 means applied to enemy player 
 * 1 means applied to self
 * 2 means evade (or block if we end up implementing that)
 * y:
 * Damage value. Either damage healed or damage delt.
*/
import java.util.Random;

public class ODST extends Character{
 private String[] abils = {"Orbital Drop[0]", "Stealth[1]", "Grenade[2]"}; 
 
 public ODST () { 
  super(170, "ODST");
 }
 
 public String getMove() {
   return name;
 }
 
 public void getAbils() {
   System.out.println(abils[0]);
   System.out.println(abils[1]);
   System.out.println(abils[2]); 
 }
 
 //Grenade - Semi damage
 public double[] abilThree() {
   double results[] = new double[2];
   results[0] = 0;
   results[1] = 20;
   System.out.println(getName() + " used Grenade!");
   return results;
 }
 
 //Stealth  - evade 
 public double[] abilTwo() {
   double results[] = new double[2];
   results[0] = 2;
   results[1] = 0;
   System.out.println(getName() + " Stealth!");
   return results;
 }
 
 //Orbital Drop (Deals random damage from 5 - 10) 
 public double[] abilOne() {
   double results[] = new double[2];
   results[0] = 0;
   double randNum = 5 + (Math.random() * (10 - 5));
   double num = Math.floor(randNum);
   results[1] = num;
   System.out.println(getName() + " used Orbital Drop!");
   return results;
 }
 
 
} // ODST End 