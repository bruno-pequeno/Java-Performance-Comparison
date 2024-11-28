import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    public static int[] readNumbersFromFile(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    numbers.add(Integer.parseInt(token.trim()));
                }
            }
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        String filePath = "src/array_data.txt";
        try {
            int[] numbers = readNumbersFromFile(filePath);
            long startTime = System.currentTimeMillis();
            selectionSort(numbers);
            long endTime = System.currentTimeMillis();
            System.out.println("Tempo total: " + (endTime - startTime) + " ms");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("sorted_numbers.txt"))) {
                for (int num : numbers) {
                    bw.write(num + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}