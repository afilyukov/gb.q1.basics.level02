public class Ls1Main {
    public static void main(String[] args) {
        Interference[] interferences = {new Wall(5), new Treadmill(10), new Treadmill(50), new Wall(2)};
        Challenger[] challengers = {new Cat("Barsik", 15, 2),
                new Human("Petr", 5, 10),
                new Robot("Verter", 50, 1),
                new Human("Barsik", 15, 2),
                new Robot("Bender", 5, 11),
                new Cat("Vaska", 20, 5)};
        for (int i = 0; i < challengers.length; i++) {
            for (int j = 0; j < interferences.length; j++) {
                if (!challengers[i].doSomething(interferences[j])) {break;}
            }
        }
    }
}
