package Sorting;

import java.util.Arrays;
import java.util.List;

public class tasks {
    //Triplicates
    public void getTriplet(List<String> list1,List<String> list2,List<String> list3){
        java.util.Collections.sort(list2);
        java.util.Collections.sort(list3);
        //after sorting the rest should be O(n*log(n))
        for(String str : list1){
            if(list2.contains(str) && list3.contains(str)){
                System.out.println(str);
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList(new String[]{"name5", "name3", "name2", "name6", "name4", "name1"});
        List<String> list2 = Arrays.asList(new String[]{"name11", "name32", "name4", "name5", "name42", "name16"});
        List<String> list3 = Arrays.asList(new String[]{"name71", "name12", "name63", "name4", "name25", "name66"});

        tasks tk = new tasks();
        tk.getTriplet(list1,list2,list3);
    }
}
