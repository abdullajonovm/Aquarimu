import entity.Arxiv;
import entity.Fish;
import service.Dataloader;
import service.FishService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Dataloader dataloader = new Dataloader();
        dataloader.createAquarium();
        System.out.println("\n\nArxiv.arxiv = " + Arxiv.arxiv);


        int count = 0;
        while (count < 10) {
            try {
                Fish fish = new Fish();
                Fish.sleep(100);
                fish.setTimeOfBirth(LocalDateTime.now());
                fish.setTimeOfDeath(LocalDateTime.now().plusSeconds(60));
                System.out.println("fish = " + fish);
                System.out.println("Thread.currentThread() = " + Fish.currentThread());
//                FishService.run((Fish) Thread.currentThread());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        while (Arxiv.liveCountFish > 0) {
//
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("\n\nArxiv.liveCountFish = " + Arxiv.liveCountFish);
//        }
    }
}
