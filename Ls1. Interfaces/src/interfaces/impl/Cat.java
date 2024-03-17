package interfaces.impl;

import interfaces.Contender;
import interfaces.Obstacle;

public class Cat implements Contender {
    @Override
    public void doActivity(Obstacle obstacle) {
        System.out.println("Cat is going to do..");
        obstacle.start();
    }
}
