import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
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
            mergeSort(numbers, 0, numbers.length - 1);
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