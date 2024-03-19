package utils;

public class ParseArrayToInts {
    private final static int ARRAY_SIZE = 4;

    public int[][] toInt(String[][] strings) throws LocalDataException {
        if (strings.length > ARRAY_SIZE || strings.length < ARRAY_SIZE) {
            throw new LocalSizeException(
                    String.format("Array has a wrong length %s. Required size is %s", strings.length, ARRAY_SIZE)
            );
        }

        int[][] ints = new int[ARRAY_SIZE][ARRAY_SIZE];

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length > ARRAY_SIZE || strings[i].length < ARRAY_SIZE) {
                throw new LocalSizeException(
                        String.format("Array has a wrong height %s. Required size is %s", strings[i].length, ARRAY_SIZE));
            }
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    ints[i][j] = Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new LocalDataException("Not digit was found:  " + strings[i][j] + " . Please correct and return", e);
                }
            }
        }
        return ints;
    }

}
