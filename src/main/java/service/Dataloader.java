package service;

import database.AquariumDatabase;
import database.FishesDatabase;
import entity.Aquarium;
import entity.Arxiv;
import entity.Fish;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class Dataloader {
    public static final Scanner SCANNERNUM = new Scanner(System.in);

    public void createAquarium() {
        System.out.print("Aquariumning bo'yini kiriting(sm) : ");
        int a = SCANNERNUM.nextInt();

        System.out.print("Aquariumning enini kiriting(sm) : ");
        int b = SCANNERNUM.nextInt();

        System.out.print("Aquariumning balandligini kiriting(sm) : ");
        int h = SCANNERNUM.nextInt();

        System.out.println("Aquariumning maximum baliqlar sig'imini kiritishni istaysizmi ?");
        System.out.println("1.Ha");
        System.out.println("2.Yo'q");
        byte chek = SCANNERNUM.nextByte();
        int capacity = 0;
        if (chek == 1) {
            System.out.print("Capaity = ");
            capacity = SCANNERNUM.nextInt();
        } else {
            capacity = (a * b * h) / 1000;   // 1 ta baliq uchun default holatda 1 dm^3 joy ajratib qo'ydim
        }

        Aquarium aquarium = new Aquarium(a, b, h, capacity);
        AquariumDatabase.aquariumList.add(aquarium);

        addFish(aquarium);
    }

    private void addFish(Aquarium aquarium) {
        int maleFishCount = new Random().nextInt(2,3); // 4 ni o'rniga shuni qo'ysak ham bo'ladi aquarium.getCapacityFish()
        int fmaleFishCount = new Random().nextInt(2,3); // 4 ni o'rniga shuni qo'ysak ham bo'ladi aquarium.getCapacityFish()

        Arxiv.liveCountFish = maleFishCount + fmaleFishCount;
        System.out.println("Arxiv.liveCountFish = " + Arxiv.liveCountFish);
        System.out.println("erkak baliqlar soni = " + maleFishCount);
        System.out.println("ayol baliqlar soni = " + fmaleFishCount);

        System.out.println("Aquariumda");
        for (int i = 0; i < maleFishCount; i++) {
            Fish fish = new Fish();

            fish.setFishId(i);
            fish.setGender(true);

            fish.setTimeOfBirth(LocalDateTime.now());

// Baliqning yashash vaqtini maximum 120 sekund qilib qo'ydim chunki sikl juda ko'payib ketib qolishligi mumkin
            fish.setTimeOfDeath(fish.getTimeOfBirth().plusSeconds(new Random().nextInt(120)));

            fish.setName(i + "- baliq");

            FishesDatabase.fishesList.add(fish);

            try {
                Fish.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fish.start();
        }

        for (int i = 0; i < fmaleFishCount; i++) {

//            System.out.println("\n\n\nArxiv.arxiv = " + Arxiv.arxiv);
            Fish fish = new Fish();

            fish.setFishId(i+maleFishCount);
            fish.setGender(false);

            fish.setTimeOfBirth(LocalDateTime.now());

// Baliqning yashash vaqtini maximum 120 sekund qilib qo'dim chunki sikl juda ko'payib ketib qolishligi mumkin
            fish.setTimeOfDeath(fish.getTimeOfBirth().plusSeconds(new Random().nextInt(10,120)));

            fish.setName((maleFishCount + i )+ "- baliq");

            FishesDatabase.fishesList.add(fish);

//            try {
//                Fish.sleep(200);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            fish.start();

        }
    }


}
