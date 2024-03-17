package interfaces.impl;

import interfaces.Contender;
import interfaces.Obstacle;

public class Human implements Contender {
    @Override
    public void doActivity(Obstacle obstacle) {
        System.out.println("Human is going to do..");
        obstacle.start();
    }
}
