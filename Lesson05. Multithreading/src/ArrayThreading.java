import java.util.Arrays;

public class ArrayThreading {
    static final int SIZE = 100000;
    static final int HALF_OF_SIZE = SIZE / 2;
    float[] arr = new float[SIZE];

    public ArrayThreading()  {
        Arrays.fill(arr, 1);
    }

    public void doMathSync() {
        long start = System.currentTimeMillis();
        doArrayMath(arr);
        long end = System.currentTimeMillis() - start;
        System.out.println("Sync math took: " + end);
    }

    public void doMathAsync() {
        long start = System.currentTimeMillis();
        float[] halfOfArray1 = new float[SIZE / 2];
        float[] halfOfArray2 = new float[SIZE / 2];
        System.arraycopy(arr, 0, halfOfArray1, 0, HALF_OF_SIZE);
        System.arraycopy(arr, HALF_OF_SIZE, halfOfArray2, 0, HALF_OF_SIZE);

        new Thread(() -> doArrayMath(halfOfArray1)).start();

       new Thread(() -> doArrayMath(halfOfArray2)).start();

        mergeArrays(halfOfArray1, halfOfArray2);

        long end = System.currentTimeMillis() - start;
        System.out.println("Async math took: " + end);
    }

    private void doArrayMath(float[] arr) {
        synchronized (this) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            }
        }
    }

    private void mergeArrays(float[] arr1, float[] arr2) {
        synchronized (this) {
            System.arraycopy(arr1, 0, arr, 0, HALF_OF_SIZE);
            System.arraycopy(arr2, 0, arr, HALF_OF_SIZE, HALF_OF_SIZE);
        }
    }
}
