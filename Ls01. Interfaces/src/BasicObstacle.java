import interfaces.Obstacle;

public abstract class BasicObstacle implements Obstacle {
    protected abstract void startActivity();

    @Override
    public void start() {
        startActivity();
    }
}