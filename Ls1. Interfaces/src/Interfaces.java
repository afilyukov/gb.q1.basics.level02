import interfaces.Contender;
import interfaces.impl.Cat;
import interfaces.impl.Human;
import interfaces.impl.Robot;

public class Interfaces {
    public static void main(String[] args) {
        BasicObstacle[] obstacles = {new RunningRoad(), new JumpingWall(), new JumpingWall()};
        Contender[] participants = {new Human(), new Cat(), new Robot(), new Human(), new Robot()};

        for (Contender participant : participants) {
            for (BasicObstacle obstacle : obstacles) {
                participant.doActivity(obstacle);
            }
        }
    }
}
