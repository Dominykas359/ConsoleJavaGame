package main.java;

import java.util.Scanner;

class Menu {

    boolean play = false, instructions = false, exit = false;

    public void doMenu() {
        String choice = "";
        Scanner input = new Scanner(System.in);
        while(!exit) {
            if(!instructions) {
                while (!choice.equals("P") && !choice.equals("I") && !choice.equals("E")) {
                    clearConsole();
                    menu();
                    System.out.print("Choose: ");
                    choice = input.nextLine();
                }
            }
            else{
                while (!choice.equals("B")) {
                    clearConsole();
                    this.displayInstructions();
                    System.out.print("To exit to menu type \"B\": ");
                    choice = input.nextLine();
                }
                instructions = false;
            }
            switch (choice) {
                case "P" -> {
                    this.play = true;
                    this.exit = true;
                }
                case "I" -> this.instructions = true;
                case "E" -> this.exit = true;
            }
        }
    }

    public void menu(){
        System.out.println("Play(P)");
        System.out.println("Instructions(I)");
        System.out.println("Exit(E)");
    }

    public void displayInstructions(){
        System.out.println("When you start the game you can choose 3 heroes.");
        System.out.println("Computer will choose 3 monsters automatically.");
        System.out.println("You can use your hero's ability once per game.");
        System.out.println("Hero with biggest strength goes first and then others go accordingly.");
        System.out.println("If your hero's class is healer, it can heal any allied hero.");
        System.out.println("If your hero's class is mage, it can increase attack of any allied hero.");
        System.out.println("If your hero's class is tank, it can put shield on itself.");
        System.out.println("Warrior class has no ability.");
    }

    boolean returnPlay(){
        return this.play;
    }

    public static void clearConsole() {
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
}
