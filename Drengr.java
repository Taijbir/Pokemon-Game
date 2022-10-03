/* returning langauge [x, y];
 * x:
 * 0 means applied to enemy player 
 * 1 means applied to self
 * 2 means evade (or block if we end up implementing that)
 * y:
 * Damage value. Either damage healed or damage delt.
*/
import java.util.Random;

public class Drengr extends Character{
 private String[] abils = {"Mark of Death[0]", "The Focus of The Nornir[1]", "The Tree of Life[2]"}; 
 
 public Drengr () { 
  super(180, "DRENGR");
 }
 
 public String getMove() {
  return name;
 }
   
 public void getAbils() {
  System.out.println(abils[0]);
  System.out.println(abils[1]);
     System.out.println(abils[2]); 
 }
 
 //The Tree of Life - Heal by 25
 public double[] abilThree() {
  double results[] = new double[2];
     results[0] = 1;
     results[1] = 25;
  System.out.println(getName() + " heal themself with The Tree of Life!");
  return results;
 }
 
 //The Focus of The Nornir - Semi Damage 
 public double[] abilTwo() {
  double results[] = new double[2];
     results[0] = 0;
     results[1] = 35;
  System.out.println(getName() + " The Focus of The Nornir!");
  return results;
 }
 
 //Mark of Death - high to low Damage
 //randomly picks from 0, 10, 20, 30, 40, or 50
 public double[] abilOne() {
  double results[] = new double[2];
     results[0] = 0;
     Random rand = new Random();
  double randNum = 5 + (Math.random() * (10 - 5));
     results[1] = randNum;
  System.out.println(getName() + " used The Mark of Death!");
  return results;
 }

  
}