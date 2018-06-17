package datastructures_and_sorting;

import java.util.*;

public class flag {
    //1.3.5
    //it is conscruting binary code one position at a time, each number added, is twice as many diferent combinations
    //a 3 digit binary can make 2^3 combinations or anything from 0 to 7
    //if N is 50 you would need a binary code at least 6 digits as it can house anything from 0-63
    //N would then become 110010
    //50%2 = 0 -> 25%2 = 1 -> 12%2 = 0 -> 6%2 = 0 -> 3%2 = 1 -> 1%2 = 1

    private List<String> buckets;
    private List<String> colors;
    private int counterSwap = 0;
    private int counterColor = 0;
    public List<String> makeBuckets(int amount,List<String> colors){
        this.colors = colors;
        buckets = new ArrayList();
        Random ran = new Random();
        for (int i = 0;i<amount;i++){
            buckets.add(colors.get(ran.nextInt(colors.size())));
        }
        return buckets;
    }
    public String color(int index){
        counterColor++;
        return buckets.get(index);
    }
    public void swap(int x,int y){
        counterSwap++;
        String temp = buckets.get(x);
        buckets.set(x,buckets.get(y));
        buckets.set(y,temp);
    }
    public void sort(){
        int firstwhite = -1;
        int nextbluehere = buckets.size()-1;
        int counter = 0;
        String presentcolour;
        while(counter <= nextbluehere)
        {
            presentcolour = color(counter);
            if(presentcolour == "blue")
            {
                swap(counter, nextbluehere);
                nextbluehere--;
            }
            else
            {
                if(presentcolour == "white")
                {
                    if(firstwhite == -1) firstwhite = counter;
                    counter++;
                }
                else
                {
                    if(firstwhite != -1)
                    {
                        swap(counter, firstwhite);
                        firstwhite++;
                    }
                    counter++;
                }
            }
        }
    }
    public void print(){
        System.out.println(buckets);
        System.out.println(counterSwap);
        System.out.println(counterColor);
    }

    public static void main(String[] args) {
        flag fl = new flag();
        fl.makeBuckets(10,Arrays.asList(new String[]{"red", "white", "blue"}));
        fl.print();
        fl.sort();
        fl.print();
    }
}
