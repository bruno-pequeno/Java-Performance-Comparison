import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
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
            System.out.println("Arquivo lido com sucesso, iniciando Bubble Sort...");

            long startTime = System.currentTimeMillis();

            bubbleSort(numbers);

            long endTime = System.currentTimeMillis();
            System.out.println("Bubble Sort concluído.");
            System.out.println("Tempo total: " + (endTime - startTime) + " ms");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("sorted_numbers.txt"))) {
                for (int num : numbers) {
                    bw.write(num + "\n");
                }
            }
            System.out.println("Números ordenados salvos em 'sorted_numbers.txt'.");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
