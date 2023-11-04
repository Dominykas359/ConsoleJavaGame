package main.java;

import java.util.LinkedList;
import java.util.Scanner;

class SetHeroes {
    Data data = JsonReader.readDataFromJsonFile("src/main/java/heroes.json");
    LinkedList<Hero> heroes = new LinkedList<>();
    LinkedList<Monster> monsters = new LinkedList<>();
    LinkedList<Hero> heroes3 = new LinkedList<>();
    LinkedList<Monster> monsters3 = new LinkedList<>();

    public void getHeroesFromJson() {
            heroes = data.getHeroes();
            monsters = data.getMonsters();
    }
    public void setHeroesToPlayer() {
        Scanner input = new Scanner(System.in);
        String choice;
        clearConsole();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n", "Class", "Name", "HP", "Attack", "Strength", "Ability");
        for (Hero h : heroes) {
            System.out.printf("%-15s %-15s %-15d %-15d %-15d %-15d%n",
                    h.getClass1(), h.getName(), h.getHP(), h.getAttack(), h.getStrength(), h.getAbility());
        }
        while (heroes3.size() != 3) {
            System.out.print("Choose hero by name: ");
            choice = input.nextLine();
            boolean isDuplicate = false;
            for (Hero h : heroes3) {
                if (h.getName().equals(choice)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                boolean found = false;
                for (Hero h : heroes) {
                    if (h.getName().equals(choice)) {
                        heroes3.add(h);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("There is no such hero.");
                }
            } else {
                System.out.println("Hero with the same name has already been selected. Choose another.");
            }
        }

    }

    public void setMonstersToComputer() {
        while (monsters3.size() != 3 && !monsters.isEmpty()) {
            int choice = (int) (Math.random() * monsters.size());
            Monster selectedMonster = monsters.get(choice);
            boolean isDuplicate = false;

            for (Monster m : monsters3) {
                if (m.getName().equals(selectedMonster.getName())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                monsters3.add(selectedMonster);
            }
        }
    }

    LinkedList<Hero> returnHeroes(){
        return this.heroes3;
    }
    LinkedList<Monster> returnMonsters(){
        return this.monsters3;
    }
    public static void clearConsole() {
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
}
