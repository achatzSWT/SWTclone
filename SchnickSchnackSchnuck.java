package de.achatz.schnickschnackschnuck;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SchnickSchnackSchnuck {

    Scanner scanner = new Scanner (System.in);

    static final String STEIN = "Stein";
    static final String SCHERE = "Schere";
    static final String PAPIER = "Papier";
    static final int ONE = 1;
    static final int TWO = 2;
    static final int THREE = 3;
    static final int FOUR = 4;
    static final int FIVE = 5;

    private Map<String, Integer> itemsHash;

    public SchnickSchnackSchnuck() {
        itemsHash = new HashMap<String, Integer>();
        itemsHash.put(STEIN, ONE);
        itemsHash.put(SCHERE, TWO);
        itemsHash.put(PAPIER, THREE);
    }

    public static void main(String[] args) {
        SchnickSchnackSchnuck sss = new SchnickSchnackSchnuck();
        sss.run();
    }

    public void run() {
        int me = new Random().nextInt(itemsHash.size()) + 1;
        int user = userChoice();
        while (user < 0) {
            System.out.println("Ungültige Eingabe, wähle erneut.\n");
            user = userChoice();
        }
        compareItemIndex(me, user);
        nextGame();
    }

    /** User Eingabe handhaben
     *
     * @return positiver wert für item, -1 für ungültige Eingabe.
     */
    public int userChoice() {
        int choice;
        System.out.println("\nIch habe mein Item bereits vorgewählt. Gib nun deine Wahl ein.\n");
        System.out.println("Schere, Stein, Papier?");
        String input = scanner.next();
        if (SCHERE.equalsIgnoreCase(input)) {
            choice = itemsHash.get(SCHERE);
        } else if (STEIN.equalsIgnoreCase(input)) {
            choice = itemsHash.get(STEIN);
        } else if (PAPIER.equalsIgnoreCase(input)) {
            choice = itemsHash.get(PAPIER);
        } else {
            choice = -1;
        }
        return choice;
    }

    public void compareItemIndex(int me, int user) {
        String stone = "\nStein macht Schere stumpf.";
        String cutter = "\nSchere zerschneidet Papier.";
        String paper = "\nPapier wickelt Stein ein.";

        String mewin = "Ich habe gewonnen.\n";
        String userwin = "Du hast gewonnen. \n";
        if(me == user) {
            System.out.println("Wir haben das gleiche Item gewählt - unentschieden. \n");
        } else {
            // case-Werte --> Summe der Werte der HashMap-keys
            switch(me + user) {
                case THREE:
                    System.out.println(stone);
                    runCaseThreeFive(me, user, mewin, userwin);
                    break;
                case FOUR:
                    System.out.println(paper);
                    runCaseFour(me, user, mewin, userwin);
                    break;
                case FIVE:
                    System.out.println(cutter);
                    runCaseThreeFive(me, user, mewin, userwin);
                    break;
                default:
                    break;
            }
        }
    }

    public void runCaseThreeFive(int me, int user, String mewin, String userwin ) {
        if(me < user) {
            System.out.println(mewin);
        } else {
            System.out.println(userwin);
        }
    }

    public void runCaseFour(int me, int user, String mewin, String userwin ) {
        if(me > user) {
            System.out.println(mewin);
        } else {
            System.out.println(userwin);
        }
    }

    public void nextGame() {
        System.out.println("Na, noch ein Spiel? Ja / Nein");
        String input = scanner.next();
          if ("ja".equalsIgnoreCase(input)) {
            run();
        } else if ("nein".equalsIgnoreCase(input)) {
            System.out.println("Ende");
        } else {
            System.out.println("Ungültige Eingabe");
            nextGame();
        }

    }

}
