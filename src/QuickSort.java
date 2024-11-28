import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
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
            System.out.println("Arquivo lido com sucesso. Iniciando QuickSort...");

            long startTime = System.currentTimeMillis();

            quickSort(numbers, 0, numbers.length - 1);

            long endTime = System.currentTimeMillis();
            System.out.println("QuickSort concluído.");
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
