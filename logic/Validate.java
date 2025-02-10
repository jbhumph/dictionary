package logic;

public class Validate {
    public static boolean numRange(int input, int i, int j) {
        if (input < i || input > j) {
            return false;
        }
        return true;
    }
}
