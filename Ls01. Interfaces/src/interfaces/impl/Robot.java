package interfaces.impl;

import interfaces.Contender;
import interfaces.Obstacle;

public class Robot implements Contender {

    @Override
    public void doActivity(Obstacle obstacle) {
        System.out.println("Robot is going to do..");
        obstacle.start();
    }
}
