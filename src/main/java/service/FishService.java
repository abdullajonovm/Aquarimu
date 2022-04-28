package service;

import database.AquariumDatabase;
import database.FishesDatabase;
import entity.Aquarium;
import entity.Arxiv;
import entity.Fish;

import java.time.LocalDateTime;
import java.util.Random;

public class FishService {
    public static void live(Fish fish) {
        Aquarium aquarium = AquariumDatabase.aquariumList.get(0);

        String str = "" + fish.getFishId() + ". " + fish.getX() + ", " + fish.getY() + ", " + fish.getZ() + ", " + fish.isGender();
//        System.out.println("str = " + str);
        clearArxiv(str);


        fish.setX(new Random().nextInt(aquarium.getA()));
        fish.setY(new Random().nextInt(aquarium.getB()));
        fish.setZ(new Random().nextInt(aquarium.getH()));
//        System.out.println("fish = " + fish);
        String str2 = "" + fish.getFishId() + ". " + fish.getX() + ", " + fish.getY() + ", " + fish.getZ() + ", " + !fish.isGender();

        if (Arxiv.liveCountFish >= aquarium.getCapacityFish()) {
            System.out.println("Aquarium baliqlarga to'lgan boshqa baliq qo'sha olmaysiz");
            return;
        }

        int id = havePartner(str2);

        if (id != -1) {

            System.out.println("Yangi baliq tug'ildi");

            if (fish.isGender()) {
                FishService.birthFish(id, fish.getFishId());
                System.out.println(id + "- baliq (ayol) va " + fish.getFishId() + "- baliq (erkak)" +
                        "(" + fish.getX() + ", " + fish.getY() + ", " + fish.getZ() + ") joyda ko'rishishdi");
            } else {
                FishService.birthFish(fish.getFishId(), id);
                System.out.println(fish.getFishId() + "- baliq (ayol) va " + id + "- baliq (erkak)" +
                        "(" + fish.getX() + ", " + fish.getY() + ", " + fish.getZ() + ") joyda ko'rishishdi");
            }


        } else {
            String str3 = "" + fish.getFishId() + ". " + fish.getX() + ", " + fish.getY() + ", " + fish.getZ() + ", " + fish.isGender();
            Arxiv.arxiv += str3;
        }
    }

    public static boolean birthFish(Integer matherId, Integer fatherId) {

        Fish fish = new Fish();
        fish.setFishId(FishesDatabase.fishesList.get(FishesDatabase.fishesList.size() - 1).getFishId() + 1);
        fish.setTimeOfBirth(LocalDateTime.now());
        fish.setGender(new Random().nextBoolean());
        fish.setFatherId(fatherId);
        fish.setMatherId(matherId);
        fish.setTimeOfDeath(LocalDateTime.now().plusSeconds(new Random().nextLong(120)));
        FishesDatabase.fishesList.add(fish);

        String str3 = " " + fish.getFishId() + ". " + fish.getX() + ", " + fish.getY() + ", " + fish.getZ() + ", " + fish.isGender();
        Arxiv.arxiv += str3;

        System.out.println("Yangi baliq tug'ildi \n" + fish);

        return true;
    }


    public static void clearArxiv(String str) {
        String str1 = Arxiv.arxiv;
//        System.out.println("str1 = " + str1);
//        System.out.println("str = " + str);
        int i = str1.indexOf(str);
//        System.out.println("i = " + i);
        if (i != -1) {
//            System.out.println("Clearning ichi Arxiv.arxiv = " + Arxiv.arxiv);
            Arxiv.arxiv = str1.replaceAll(str, "");
//            System.out.println("Arxiv.arxiv = " + Arxiv.arxiv);
        }
    }

    public static int havePartner(String str) {
        int i = Arxiv.arxiv.indexOf(str);
        if (i != -1) {
            System.out.println("Tugiladigon payt Arxiv.arxiv = " + Arxiv.arxiv);
//            String substring = Arxiv.arxiv.substring(i, i + 1);
            char c = Arxiv.arxiv.charAt(i);
            System.out.println("Arxiv.arxiv = " + Arxiv.arxiv);
            System.out.println("c = " + c);
            return c - '0';
        }
        return i;
    }

    public static void run(Fish fish) {
        int count = Arxiv.liveCountFish;
        System.out.println(fish);

        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        if (fish.getTimeOfDeath().isBefore(LocalDateTime.now()) && fish.getIsLive()) {
            System.out.println("fish.getTimeOfDeath().isAfter(LocalDateTime.now()) = " + fish.getTimeOfDeath().isAfter(LocalDateTime.now()));
            System.out.println("\n\n============================================================================\n");
            System.out.println(LocalDateTime.now());
            System.out.println(fish);
            fish.setIsLive(false);
            System.out.println("\n\n\nBaliq o'ldi : ");
            System.out.println(fish);
            count--;
            Arxiv.liveCountFish = count;
            System.out.println("count = " + count);
            System.out.println("\n============================================================================\n\n");
        } else if (fish.getIsLive()) {
            System.out.println("count = " + count);
            FishService.live(fish);
        }
    }

}
