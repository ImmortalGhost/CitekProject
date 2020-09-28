import java.util.*;

class Pair implements Comparable<Pair> {
    int number, count;

    public Pair(int number, int count) {
        this.number = number;
        this.count = count;
    }

    public int compareTo(Pair pair) {
        if (this.count == pair.count) return this.number - pair.number > 0 ? 1 : -1;
        return (this.count - pair.count) > 0 ? 1 : -1;
    }
}

public class Main {
    public static void main(String[] args) {
        testTask1();
        testTask2();
        testTask3();
    }

    public static void task4() {
//        a. PostgreSQL 9.6
//        b. create table Test (my_number INT);
//              insert into Test (my_number) values(1),(2),(4),(7),(8),(11);
//        c. select (previous + 1) as missed_number,
//              (current - previous - 1) as missed_numbers_count from
//              ( select my_number as current,
//              lag(my_number) over (order by my_number) as previous from test ) sub_query
//              where (current - previous) > 1
    }

    public static int task3(int arg) {
        return arg | (arg + 1);
    }

    public static void testTask3() {
        int decNumber = 5614335;
        int task3Result = task3(decNumber);
        System.out.println(task3Result);
    }

    public static boolean task2(String regExp) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < regExp.length(); i++) {
            char currentElement = regExp.charAt(i);
            switch (currentElement) {
                case '[':
                case '(':
                    stack.push(currentElement);
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    public static void testTask2() {
        String regExp = "[()([][][][([([])])])[]]";
        boolean task2Result = task2(regExp);
        System.out.println("Is regular expression valid - " + task2Result);
    }

    public static Set<Pair> task1(int[] array) {
        HashMap<Integer, Integer> preResult = new HashMap<>(array.length);
        Set<Pair> result;
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            Integer storedValue = preResult.get(key);
            preResult.put(key, ((storedValue == null) ? 1 : (storedValue + 1)));
        }
        result = new TreeSet<>();
        for (int currentKey : preResult.keySet()) {
            result.add(new Pair(currentKey, preResult.get(currentKey)));
        }
        return result;
    }

    public static void testTask1() {
        int[] array = new int[]{0, 1, 2, 3, 4, 4, 3, 2, 1, 0, 3, 2, 5, 5, 7, 8, 10, 20, 3, 35, 3, 0, 3};
        Set<Pair> task1Result = task1(array);
        for (Pair current : task1Result) {
            System.out.println("Key is " + current.number + ", value is " + current.count);
        }
    }
}
