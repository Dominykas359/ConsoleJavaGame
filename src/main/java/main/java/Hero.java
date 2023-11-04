package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

class Hero {
    @JsonProperty("Class")
    private String Class;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("HP")
    private int HP;
    @JsonProperty("Attack")
    private int Attack;
    @JsonProperty("Strength")
    private int Strength;
    @JsonProperty("Ability")
    private int Ability;

    public Hero() {
        // Default constructor
    }
    public Hero(String Class, String Name, int HP, int Attack, int Strength, int Ability) {
        this.Class = Class;
        this.Name = Name;
        this.HP = HP;
        this.Attack = Attack;
        this.Strength = Strength;
        this.Ability = Ability;
    }
    public void setClass(String class1){
        this.Class = Class;
    }
    public void setName(String name){
        this.Name = Name;
    }
    public void setHP(int hp){
        this.HP = HP;
    }
    public void setAttack(int attack){
        this.Attack = attack;
    }
    public void setStrength(int strength){
        this.Strength = strength;
    }
    public void setAbility(int ability){
        this.Ability = ability;
    }
    public String getClass1() {
        return Class;
    }
    public String getName() {
        return Name;
    }
    public int getHP() {
        return HP;
    }
    public int getAttack() {
        return Attack;
    }
    public int getStrength() {
        return Strength;
    }
    public int getAbility() {
        return Ability;
    }
    public void updateHP(int attack){
        this.HP -= attack;
    }
    public void heal(int heal){
        this.HP += heal;
    }
    public void updateAbility(){
        this.Ability = 0;
    }
    public void updateAttack(int bonus){
        this.Attack += bonus;
    }
}
