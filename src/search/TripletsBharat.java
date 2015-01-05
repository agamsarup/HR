package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

class TripletsBharat{

    static int left[], right[], sorted[];
    static int fenwick[], leftstatus[], rightstatus[];
    static int unique;

    public static void main(String[] args) throws Exception {
        FenwickTree ft = new FenwickTree();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String line[] = br.readLine().split(" ");
        int input[] = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(line[i]);
        }

        left = new int[n];
        right = new int[n];
        unique = 0;

        int sortedinput[] = new int[n];
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>(n);
        sortedinput = Arrays.copyOf(input, n);
        Arrays.sort(sortedinput);

        for (int i = 0; i < n; i++) {
            if (!temp.containsKey(sortedinput[i])) {
                unique++;
                temp.put(sortedinput[i], unique);
            }
        }
        unique++;
        fenwick = new int[unique];
        leftstatus = new int[unique];
        rightstatus = new int[unique];
        sorted = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = temp.get(input[i]);
            System.out.print(sorted[i]+" ");
        }

        System.out.println("Printing left");
        for (int i = 0; i < n; i++) {
            int by = sorted[i];
            left[i] = ft.read(fenwick, by - 1);
            if (leftstatus[by] == 0) {
                ft.update(fenwick, by, unique);
                leftstatus[by] = i + 1;
            } else {
                left[i] -= left[leftstatus[by] - 1];
            }
            System.out.print(i+":"+left[i]+" ");
        }

        fenwick = new int[unique];
        System.out.println();
        for (int i = n - 1; i > -1; i--) {
            int by = sorted[i];
            right[i] = ft.read(fenwick, unique - 1) - ft.read(fenwick, by);
            if (rightstatus[by] == 0) {
                ft.update(fenwick, by, unique);
                rightstatus[by] = i + 1;
            }
            System.out.print(i+":"+right[i]+" ");
        }
        System.out.println();
        long triplets = 0;
        for (int i = 0; i < n; i++) {
            triplets += left[i] * right[i];
            System.out.print(triplets+" ");
        }
        
        System.out.println();
        System.out.println(triplets);

    }
}

class FenwickTree {

    int[] update(int[] arr, int index, int range) {
        while (index < range) {
            arr[index]++;
            index += (index & -index);
        }
        return arr;
    }

    int read(int arr[], int index) {
        int sum = 0;
        while (index > 0) {
            sum += arr[index];
            index -= (index & -index);
        }
        return sum;
    }
}