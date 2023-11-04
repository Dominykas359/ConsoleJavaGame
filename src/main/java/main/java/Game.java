package main.java;

import java.util.*;

class Game {
    SetHeroes s = new SetHeroes();

    //List of player selected heroes
    LinkedList<Hero> heroList = s.returnHeroes();
    //List of computer selected monsters
    LinkedList<Monster> monsterList = s.returnMonsters();
    //Queue that is shown in the game
    LinkedList<String> heroQueue = new LinkedList<>();

    //To show previous actions
    LinkedList<String> previousActions = new LinkedList<>();

    public void createTeams() {
            s.getHeroesFromJson();
            s.setHeroesToPlayer();
            s.setMonstersToComputer();
    }
    public void createQueue(){
        this.createTeams();
        Map<String, Integer> toSortHeroes = new HashMap<>();
        for (Hero h : heroList) {
            toSortHeroes.put(h.getName(), h.getStrength());
        }
        for (Monster m : monsterList) {
            toSortHeroes.put(m.getName(), m.getStrength());
        }
        List<Map.Entry<String, Integer>> listOfHeroes = new ArrayList<>(toSortHeroes.entrySet());
        listOfHeroes.sort(Map.Entry.comparingByValue());
        for(int i = 0; i < toSortHeroes.size(); i++){
            heroQueue.offer(listOfHeroes.get(listOfHeroes.size() - 1).getKey());
            listOfHeroes.remove(listOfHeroes.size() - 1);
        }
    }
    public LinkedList<String> showQueue(){
        return this.heroQueue;
    }
    public void updateQueue(){
        String name;
        name = heroQueue.poll();
        heroQueue.offer(name);
    }
    public void playingScreen() {
        Scanner input = new Scanner(System.in);
        this.createQueue();
        boolean won = false;
        boolean lost = false;
        boolean exit = false;
        String move, choice;
        Hero hero = null;
        Monster monster = null;
        previousActions.add("Nothing happened yet.");

        while (!exit && !won && !lost) {
            choice = "";
            move = heroQueue.peek();
            boolean human = false;
            for (Hero h : heroList) {
                if (h.getName().equals(move)) {
                    hero = h;
                    human = true;
                    break;
                }
                else{
                    for(Monster m: monsterList){
                        if(m.getName().equals(move)){
                            monster = m;
                            break;
                        }
                    }
                }
            }
            if (human) {
                clearConsole();
                System.out.println("Queue: " + showQueue());
                System.out.println();
                System.out.println("Player's heroes:");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Class", "Name", "HP", "Attack", "Ability");
                for (Hero h : heroList) {
                    System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", h.getClass1(), h.getName(), h.getHP(), h.getAttack(), h.getAbility());
                }
                System.out.println();
                System.out.println("Computer's monsters:");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Class", "Name", "HP", "Attack", "Ability");
                for (Monster m : monsterList) {
                    System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", m.getClass1(), m.getName(), m.getHP(), m.getAttack(), m.getAbility());
                }
                System.out.println();
                System.out.println("Previous actions: ");
                this.showActions();
                System.out.println();

                System.out.println("To attack(A), to use ability(U), to exit the game(E).");
                System.out.println("If ability is set to 0, then ability is used or hero has no ability.");
                while(!choice.equals("A") && !choice.equals("U") && !choice.equals("E")) {
                    System.out.print(move + ": ");
                    choice = input.nextLine();
                    if(choice.equals("U") && hero.getAbility() == 0){
                        choice = "";
                        System.out.println("Ability unavailable.");
                    }
                }
                switch (choice) {
                    case "A" -> this.playerAttack(hero);
                    case "U" -> this.playerUseAbility(hero);
                    case "E" -> exit = true;
                }
            }
            else {
                if(monster != null && monster.getAbility() != 0){
                    this.computerUseAbility(monster);
                }
                else if(monster != null && monster.getAbility() == 0){
                    this.computerAttack(monster);
                }
            }
            this.updateQueue();
            this.checkIfDead();
            if(heroList.size() == 0){
                lost = true;
            }
            else if(monsterList.size() == 0){
                won = true;
            }
        }
        if(won){
            clearConsole();
            System.out.println("You won!!!!");
        }
        else if(lost){
            clearConsole();
            System.out.println("You lost!!!");
        }
    }
    public void showActions(){
        if(previousActions.size() <= 5){
            for(String message: previousActions){
                System.out.println(message);
            }
        }
        else{
            for(int i = previousActions.size() - 5; i < previousActions.size(); i++){
                System.out.println(previousActions.get(i));
            }
        }
    }
    public void checkIfDead() {
        for (Hero h : heroList) {
            if (h.getHP() <= 0) {
                heroQueue.remove(h.getName());
                heroList.remove(h);
                break;
            }
        }
        for (Monster m : monsterList) {
            if (m.getHP() <= 0) {
                heroQueue.remove(m.getName());
                monsterList.remove(m);
                break;
            }
        }
    }
    public void computerAttack(Monster monster){
        Hero h1 = heroList.peek();
        for(Hero h: heroList){
            if(h.getAttack() > h1.getAttack() || (h.getAttack() == h1.getAttack() && h.getHP() < h1.getHP())){
                h1 = h;
            }
        }
        String message = monster.getName();
        message += " dealt ";
        message += Integer.toString(monster.getAttack());
        message += " damage to ";
        if(h1 != null) {
            h1.updateHP(monster.getAttack());
            message += h1.getName();
        }
        message += ".";
        previousActions.add(message);
    }
    public void computerUseAbility(Monster monster){
        String message = monster.getName();
        if(monster.getClass1().equals("Tank")){
            monster.heal(monster.getAbility());
            message += " put shield on itself";
        }
        else{
            Monster m1 = monsterList.peek();
            for(Monster m: monsterList){
                if(m.getAttack() > m1.getAttack() || (m.getAttack() == m1.getAttack() && m.getHP() > m1.getHP())){
                    m1 = m;
                }
            }
            if(monster.getClass1().equals("Healer") && m1 != null) {
                m1.heal(monster.getAbility());
                message += " healed ";
                message += m1.getName();
                message += " by ";
                message += Integer.toString(monster.getAbility());
            }
            else if(monster.getClass1().equals("Mage") && m1 != null) {
                m1.updateAttack(monster.getAbility());
                message += " increased ";
                message += m1.getName();
                message += "'s attack by ";
                message += Integer.toString(monster.getAbility());
            }
        }
        message += ".";
        monster.updateAbility();
        previousActions.add(message);
    }
    public void playerAttack(Hero hero){
        Scanner input = new Scanner(System.in);
        String name;
        String message = hero.getName();
        boolean contains = false;
        while(!contains){
            System.out.print("Choose a target: ");
            name = input.nextLine();
            for(Monster m: monsterList){
                if(m.getName().equals(name)){
                    contains = true;
                    m.updateHP(hero.getAttack());
                    message += " dealt ";
                    message += Integer.toString(hero.getAttack());
                    message += " damage to ";
                    message += m.getName();
                    message += ".";
                    break;
                }
            }
        }
        previousActions.add(message);
    }
    public void playerUseAbility(Hero hero){
        Scanner input = new Scanner(System.in);
        String name;
        String message = hero.getName();
        boolean contains = false;
        if(hero.getClass1().equals("Tank")){
            hero.heal(hero.getAbility());
            message += " put shield on itself";
        }
        else {
            while (!contains) {
                System.out.print("Choose an allay:");
                name = input.nextLine();
                for (Hero h : heroList) {
                    if (h.getName().equals(name)) {
                        contains = true;
                        if (hero.getClass1().equals("Healer")) {
                            h.heal(hero.getAbility());
                            message += " healed ";
                            message += h.getName();
                            message += " by ";
                            message += Integer.toString(hero.getAbility());
                        }
                        else if (hero.getClass1().equals("Mage")) {
                            h.updateAttack(hero.getAbility());
                            message += " increased ";
                            message += h.getName();
                            message += "'s attack by ";
                            message += Integer.toString(hero.getAbility());
                        }
                        break;
                    }
                }
            }
        }
        message += ".";
        previousActions.add(message);
        hero.updateAbility();
    }
    public static void clearConsole() {
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
}
