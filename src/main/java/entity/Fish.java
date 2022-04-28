package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.FishService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fish extends Thread {
    private Integer fishId;
    private boolean gender;
    private LocalDateTime timeOfBirth;
    private LocalDateTime timeOfDeath;
    private Integer x;
    private Integer y;
    private Integer z;

    private Integer fatherId;

    private Integer matherId;

    private Boolean isLive = true;


    @Override
    public void run() {
            Fish fish = (Fish) currentThread();
            FishService.run(fish);
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return getName() + "{" +
                "\n fishId = " + fishId +
                ", gender = " + gender +
                ",\n tug'ilgan kuni = " + formatter.format(timeOfBirth) +
                ",\n o'ladigonkuni = " + formatter.format(timeOfDeath) +
                ",\n kordinatasi (x = " + x +
                ", y = " + y +
                ", z = " + z +
                "),\n fatherId = " + fatherId +
                ", matherId = " + matherId +
                ", isLive = " + isLive +
                '}';
    }
}
