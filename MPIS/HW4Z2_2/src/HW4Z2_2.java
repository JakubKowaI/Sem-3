import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class HW4Z2_2 {
    public static void main(String[] args) {
        int[] N_values = {5, 10, 15, 20, 25, 30, 100};
        int simulations = 10000;
        Random random = new Random();

        for (int N : N_values) {
            int[] results = new int[simulations];

            for (int i = 0; i < simulations; i++) {
                int S_N = 0;
                for (int j = 0; j < N; j++) {
                    S_N += (random.nextBoolean() ? 1 : -1);
                }
                results[i] = S_N;
            }

            Arrays.sort(results);
            saveResultsToFile(results, N);
        }
    }

    private static void saveResultsToFile(int[] results, int N) {
        String filename = "random_walk_N" + N + ".csv";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Step,Value\n");
            for (int i = 0; i < results.length; i++) {
                writer.write(i + "," + results[i] + "\n");
            }
            System.out.println("Results saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}