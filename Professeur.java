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

public class Professeur extends Character{
 private String[] abils = {"Missile launcher[0]", "Atomic attack[1]", "Vanish [2]"}; 
 
 Professeur () { 
  super(200, "PROFESSEUR");
 }
 
 public String getMove() {
  return name;
 }
   
 public void getAbils() {
   System.out.println(abils[0]);
   System.out.println(abils[1]);
   System.out.println(abils[2]); 
 }
 
 //Vanish  - evade 50% chance
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
     
     System.out.println("Uh Oh" + getName() + " Vanished!");
     return results;   
 }
 
 //Atomic attack (take’s two turns) - Massive damage 
 public double[] abilTwo() {
   double results[] = new double[2];
   results[0] = 7;
   results[1] = 65;
   //System.out.println(getName() + " used Atomic Attack");
   return results;
 }

 
 //Missile launcher - Semi damage
 public double[] abilOne() {
  double results[] = new double[2];
  results[0] = 0;
  results[1] = 20;
  System.out.println(getName() + " used Missile!");
  return results;
 }
}