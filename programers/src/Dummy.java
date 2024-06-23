public class Dummy {


    public static void main(String[] args) {
        String a = "[[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]";
        String b = a.replace('[', '{');
        String c = b.replace(']', '}');
        System.out.println(c);
    }
}
