public class Lab1 {
    public static void main(String[] args) {
        int[] d = new int[8];
        for (int i = 0; i < 8; i += 1) {
            d[i] = (i + 1) * 2;
        }

        float[] x = new float[12];
        for (int i = 0; i < 12; i ++) {
            x[i] = (float) (Math.random() * 21 - 6);
        }

        double[][] n = new double[8][12];
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 12; j ++) {
                if (d[i] == 10) {
                    n[i][j] = 2 * Math.pow(Math.E, Math.pow(x[j], 0.5 * x[j]));
                } else if (d[i] == 2 || d[i] == 6 || d[i] == 8 || d[i] == 16) {
                    n[i][j] = Math.pow(Math.cos(Math.pow(x[j], 3)) / 2, 2);
                } else {
                    n[i][j] = Math.sin(Math.atan(Math.cos(x[j]))) / (Math.pow(Math.log(Math.pow(2 * Math.abs(x[j]), x[j])), 1.0 / 3.0) + 1);
                }
            }
        }

        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 12; j ++) {
                System.out.printf("%.4f ", n[i][j]);
            }
            System.out.println();
        }
    }
}
