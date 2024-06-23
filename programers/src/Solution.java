
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public int[] solution(int[][] inputArray) {
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
        LinkedList<Integer> nodes = new LinkedList<>();
        int maxNum = 0;
        int total = 0;
        int[] result = new int[4];
        for (int[] array : inputArray) {
            if (!hashMap.containsKey(array[0])) {
                hashMap.put(array[0], new ArrayList<Integer>());
            }
            hashMap.get(array[0]).add(array[1]);
            nodes.add(array[1]);
            maxNum = Integer.max(maxNum, Integer.max(array[0], array[1]));
        }

        for(int i = 1; i <= maxNum; i++) {
            if (hashMap.containsKey(i) && hashMap.get(i).size()>=2 && !nodes.contains(i)) {
                result[0] = i;
                total = hashMap.get(i).size();
                for(int j: hashMap.get(i)) {
                    nodes.remove(Integer.valueOf(j));
                }
                break;
            }
        }

        for(int i = 1; i <= maxNum; i++) {
            if (i != result[0] && !nodes.contains(i)) {
                result[2]++;
            }
        }
        List<Integer> uniqueNodes = nodes.stream().distinct().collect(Collectors.toList());
        for(int i: uniqueNodes) {
            nodes.remove(Integer.valueOf(i));
        }
        result[3] += nodes.size();

        result[1] = total - result[2] - result[3];
        return result;
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        System.out.println(Arrays.toString(main.solution(new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}})));

    }
}