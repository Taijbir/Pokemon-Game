
import java.util.*;
/* returning langauge [x, y];
 * x:
 * 0 means applied to enemy player 
 * 1 means applied to self
 * 2 means evade (or block if we end up implementing that)
 * y:
 * Damage value. Either damage healed or damage delt.
*/

public class Elite extends Character{
 private String[] abils = {"Swift Stab[0]", "Punch[1]", "Plasma Rifle[2]"}; 
 
 Elite () { 
  super(250, "ELITE");
 }
 
 public String getMove() {
  return name;
 }
   
 public void getAbils() {
  System.out.println(abils[0]);
  System.out.println(abils[1]);
  System.out.println(abils[2]); 
 }
 
 //Plasma Rifle - Semi damage
 public double[] abilThree() {
  double results[] = new double[2];
     results[0] = 0;
     results[1] = 20;
  System.out.println(getName() + " used Plasma Rifle!");
  return results;
 }
 
 //Punch - Semi Damage 
 public double[] abilTwo() {
  double results[] = new double[2];
     results[0] = 0;
     results[1] = 15;
  System.out.println(getName() + " used Punch!");
  return results;
 }
 
 //Swift Stab - high to low Damage
 //randomly picks from 0, 10, 20, 30, 40, or 50
 public double[] abilOne() {
   double results[] = new double[2];
   results[0] = 0;
   Random rand = new Random();
   int randNum = rand.nextInt(5) + 1;
   results[1] = randNum*10;
  System.out.println(getName() + " used Swift Stab!");
  return results;
 }

  
}