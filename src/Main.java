import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {


    public static void main(String[] args) {
        List<Employee> emp = Arrays.asList(
                new Employee(3, "Abhinav", 3000),
                new Employee(2, "Ab", 5000),
                new Employee(1, "Sachin", 1000)
        );
        System.out.println(emp);
//        Integer[] e = {3,6,2,1};

        System.out.println("+++++++++++1++++++++++++++");
        List<Employee> collect = sortBySalary(emp);
        System.out.println(collect);

        System.out.println("++++++++++++2+++++++++++++");
        List<String> names = Arrays.asList("Abhinav", "Ab", "Sachin");
        System.out.println(concatStrings(names));

        System.out.println("++++++++++++3+++++++++++++");
        String str = "naman";
        System.out.println(firstNonRep(str));

        System.out.println("++++++++++++4+++++++++++++");
        List<Movie> movies = Arrays.asList(
                new Movie("Inception", "Sci-Fi"),
                new Movie("The Dark Knight", "Action"),
                new Movie("Interstellar", "Sci-Fi"),
                new Movie("The Godfather", "Crime"),
                new Movie("Pulp Fiction", "Crime"),
                new Movie("The Matrix", "Sci-Fi")
        );
        String genre = "Sci-Fi";
        System.out.println(filterMovies(movies, genre));

        System.out.println("++++++++++++5+++++++++++++");
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
                new Person("Diana", 32)
        );
        System.out.println(findFirstNameByAge(people));

        System.out.println("++++++++++++6+++++++++++++");
        List<String> s = Arrays.asList("Abhinav", null, "Ab", null, null, "Sachin");
        System.out.println(filterNull(s));

        System.out.println("++++++++++++7+++++++++++++");
        List<Integer> l = Arrays.asList(1,2,3,4);
        System.out.println(square(l));

        System.out.println("++++++++++++8+++++++++++++");
        List<Integer> n = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(skipElements(n,7));

        System.out.println("++++++++++++9+++++++++++++");
        System.out.println(printTen());

        System.out.println("++++++++++++10+++++++++++++");
        List<String> list = Arrays.asList("Abhinav","Sachin","Abhi","Ab");
        System.out.println(startsWith(list));

        System.out.println("++++++++++++11+++++++++++++");
        List<Integer> l1 = Arrays.asList(1,2,3,4,5,6);
        List<Integer> l2 = Arrays.asList(4,5,6,7,8,9);
        System.out.println(mergeAndUnique(l1,l2));

        System.out.println("++++++++++++12+++++++++++++");
        l1 = Arrays.asList(4,5,6,7,8,9);
        System.out.println(greaterAndAvg(l1));

        System.out.println("++++++++++++13+++++++++++++");
        l1 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        partitionByOddEven(l1);
    }

    private static void partitionByOddEven(List<Integer> list) {
        Map<Boolean, List<Integer>> collect = list.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        List<Integer> odd = collect.get(false);
        List<Integer> even = collect.get(true);
        System.out.println(odd);
        System.out.println(even);

    }

    private static double greaterAndAvg(List<Integer> list) {
        return list
                .stream()
                .filter(i -> i > 6)
                .mapToDouble(n -> n)
                .average()
                .orElse(0.0);
    }


    private static List<Integer> mergeAndUnique(List<Integer> l1, List<Integer> l2) {
        return Stream.concat(l1.stream(),l2.stream())
                .distinct()
                .toList();
    }

    private static List<String> startsWith(List<String> list) {
    return list
            .stream()
            .filter(s->s.startsWith("Ab"))
            .toList();
    }

    private static List<Integer> printTen() {
//        return Stream.iterate(1,i-> i+1)
//                .limit(10)
//                .collect(Collectors.toList());

        Random r = new Random();
        return Stream.generate(r::nextInt)
                .limit(10)
                .collect(Collectors.toList());
    }

    private static List<Integer> skipElements(List<Integer> n,int k) {
        return n
                .stream()
                .skip(k)
                .toList();
    }

    private static Integer square(List<Integer> l) {
        return l
                .stream()
                .map(i -> i * i)
                .reduce(0, Integer::sum);
    }

    private static List<String> filterNull(List<String> s) {
        return s
                .stream()
                .filter(str -> str!=null)
                .toList();
    }


    private static List<Employee> sortBySalary(List<Employee> emp) {
        return emp
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary()))
                .toList();
    }

    private static String concatStrings(List<String> list) {
        String collect = list.stream().collect(Collectors.joining(","));
        return collect;
    }

    private static Character firstNonRep(String str){
        return str
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    private static Set<String> filterMovies(List<Movie> movies, String genre){
        return movies
                .stream()
                .filter(movie -> movie.getGenre().equals(genre)).map(movie -> movie.getName()).collect(Collectors.toSet());
    }



    private static String findFirstNameByAge(List<Person> people) {
        return people
                .stream()
                .max(Comparator.comparing(Person::getAge))
//                .max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .orElse(null)
                .getFirstName();

    }

}