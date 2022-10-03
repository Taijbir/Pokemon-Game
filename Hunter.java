/* returning langauge [x, y];
 * x:
 * 0 means applied to enemy player 
 * 1 means applied to self
 * 2 means evade (or block if we end up implementing that)
 * 6 means skip villain turn
 * 7 means takes two turns
 * y:
 * Damage value. Either damage healed or damage delt.
*/
import java.util.Random;

public class Hunter extends Character{
 private String[] abils = {"Fuel Rod[0]", "Massive smack[1]", "Shield [2]"}; 
 
 public Hunter () { 
  super(300, "HUNTER");
 }
 
 public String getMove() {
  return name;
 }
   
 public void getAbils() {
   System.out.println(abils[0]);
   System.out.println(abils[1]);
   System.out.println(abils[2]); 
 }
 
 //Shield  - evade 
 public double[] abilThree() {
  double results[] = new double[2];
  results[0] = 2;
  results[1] = 0;
  System.out.println(getName() + " Shielded off your attack!");
  return results;
  }
 
 //Massive smack (take’s two turns) - Massive damage 
 public double[] abilTwo() {
   double results[] = new double[2];
   results[0] = 7;
   results[1] = 65;
   //System.out.println(getName() + " used Massive smack!");
   return results;
 }

 
 //Fuel Rod - Semi damage
 public double[] abilOne() {
  double results[] = new double[2];
  results[0] = 0;
  results[1] = 20;
  System.out.println(getName() + " used Fuel Rod!");
  return results;
 }
}