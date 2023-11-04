package main.java;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Game game = new Game();
        menu.doMenu();
        if(menu.returnPlay()){
            game.playingScreen();
        }

    }
}
