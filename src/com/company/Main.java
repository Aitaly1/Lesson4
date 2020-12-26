package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 2000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int medicHeal = 40;
    public static int[] randomChance = {1,2,3,4,5};
    public static int[] heroesHealth = {290, 270, 300, 310, 500, 240, 270, 300};
    public static int[] heroesDamage = {10, 20, 8, 0, 5, 8, 15, 20};
    public static String[] heroesAttackType = {"Phisical", "Magical", "Mental"};
    public static void main(String[] args) {
        while (!isFinished()){
            round();
        }
    }
    public static void printStatistics(){
        System.out.println("-----------------");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magical health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("Golem health: " + heroesHealth[4]);
        System.out.println("Lucky health: " + heroesHealth[5]);
        System.out.println("Berserk health: " + heroesHealth[6]);
        System.out.println("Thor health: " + heroesHealth[7]);
        System.out.println("-----------------");
    }
    public static void round(){
        System.out.println("Round was started!");
        printStatistics();
        heroesHit();
        medicHeal();
        golem();
        lucky();
        berserk();
        changeBossDefence();
        if (thor());
        else bossHit();
        printStatistics();
    }

    public static void heroesHit(){
        for (int i = 0; i < heroesHealth.length; i++){
            if (bossHealth >= 0 && heroesHealth[i] > 0){
                if (bossHealth - heroesDamage[i] < 0) bossHealth = 0;
                else bossHealth = bossHealth - heroesDamage[i];
            }

        }
    }
    public static void bossHit(){

        for (int i = 0; i < heroesHealth.length; i++){
            if (heroesHealth[i] >= 0 && bossHealth > 0){

                if (heroesHealth[i] - bossDamage < 0) heroesHealth[i] = 0;
                else heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void medicHeal(){

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[3] > 0){
                heroesHealth[i] = heroesHealth[i] + medicHeal;
                break;
            }
        }
    }
    public static void golem(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0)
                heroesHealth[i] +=10;//p.s.1/5 урона по герою
             if (heroesHealth[4] - 70 < 0)heroesHealth[4] = 0;//P.S.:1/5 часть урона босса по всем
             else heroesHealth[4] = heroesHealth[4] - 70;
             break;
        }

    }
    public static void lucky() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (chanceHeroes() == 5 && heroesHealth[i] == heroesHealth[5] && heroesHealth[5] > 0) {
                heroesHealth[5] += bossDamage;
            }
        }
    }
    public static void berserk(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0 ){
                if (chanceHeroes() == 1){
                    heroesDamage[6] += 10;
                    heroesHealth[6] +=10;
                }
                else if (chanceHeroes() == 2) {
                    heroesDamage[6] +=20;
                    heroesHealth[6] +=20;
                }
                else if (chanceHeroes() == 3) {
                    heroesDamage[6] +=30;
                    heroesHealth[6] +=30;
                }
                else if (chanceHeroes() == 4){
                    heroesDamage[6] +=35;
                    heroesDamage[6] +=35;
                }
                else if (chanceHeroes() == 5){
                    heroesDamage[6] +=40;
                    heroesHealth[6] +=40;
                }
            }
        }
    }

    public static boolean thor() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[7] > 0 && chanceHeroes() == 3) {
                System.out.println("Thor stuned boss!!!");
            }
        }
        return false;
    }

    public static int chanceHeroes(){
        Random random = new Random();
       return random.nextInt(randomChance.length);
    }

    public static boolean isFinished(){
        if (bossHealth <= 0){
            System.out.println("Heroes WON!!!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0){
            System.out.println("BOSS WON!!1");
            return true;
        }

        return false;
    }

    public static void changeBossDefence(){
       Random random = new Random();
       int randomIndex = random.nextInt(heroesAttackType.length);
       bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss defence type: " + bossDefenceType);
    }
}
