import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Solution for USACO 2019 December Contest, Bronze Problem 3. Livestock Lineup
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=965
 */
public class App {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(Path.of("lineup.in"));
        int N = sc.nextInt();
        String[] cows = new String[]{"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
         ArrayList<Group> pos = new ArrayList<Group>();

        for (int i = 0; i < N; i++){
            String first = sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            String second = sc.next();

            int a = findCow(first, cows);
            int b = findCow(second, cows);

            if (a > b) {
                String temp = first;
                first = second;
                second = temp;
            }

            var firstGroup = findGroup(first, pos);
            var secondGroup = findGroup(second, pos);

            if (firstGroup != null) {
                if (firstGroup.first.get(0).equals(first)) {                       
                    firstGroup.swap();
                }

                if (secondGroup != null ) {
                    if (secondGroup.second.get(0).equals(second)) {
                        secondGroup.swap();
                    }
                    firstGroup.first.addAll(secondGroup.first);
                    firstGroup.second.addAll(0, secondGroup.second);
                    
                    pos.remove(secondGroup);
                }
                else {
                    firstGroup.first.add(second);
                    firstGroup.second.add(0, second);
                }

                a = findCow(firstGroup.first.get(0), cows);
                b = findCow(firstGroup.second.get(0), cows);

                if (a > b) {
                    firstGroup.swap();
                }

            } else {
                if (secondGroup != null) {
                    if (secondGroup.second.get(0).equals(second)) {
                        secondGroup.swap();
                    }
                    secondGroup.first.add(0, first);
                    secondGroup.second.add(first);
                } else {
                    var group = new Group();
                    group.first.add(first);
                    group.first.add(second);
                    group.second.add(second);
                    group.second.add(first);
                    pos.add(group);
                }
            }
        }

        sc.close();

        ArrayList<String> answer = new ArrayList<>();

        for(String name: cows) {
            Group g = findGroupInside(name, pos);

            if (g != null) {
                if (g.first.get(0).equals(name)) {
                    answer.addAll(g.first);
                }
            } else {
                answer.add(name);
            }
        }


        BufferedWriter bw = new BufferedWriter(new FileWriter("lineup.out"));
        for(String name : answer) {
            bw.write(name);
            bw.write("\n");
        }

        bw.close();
    }

    private static int findCow(String name, String[] cows) {
        for (int i = 0; i < cows.length; i++) {
            if (cows[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static Group findGroup(String name, ArrayList<Group> pos) {
        for(Group group : pos) {
            if (group.first.get(0).equals(name) || group.second.get(0).equals(name)){
                return group;
            }
        }

        return null;
    } 

    private static Group findGroupInside(String name, ArrayList<Group> pos) {
        for(Group g : pos) {
            for(String s : g.first) {
                if (s.equals(name)) {
                    return g;
                }
            }
        }

        return null;
    }
}

class Group{
    public ArrayList<String> first = new ArrayList<String>();
    public ArrayList<String> second = new ArrayList<String>();

    public void swap() {
        var temp = first;
        first = second;
        second = temp;
    }
}