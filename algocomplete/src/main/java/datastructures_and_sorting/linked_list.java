package datastructures_and_sorting;

import java.util.Random;


public class linked_list {

    private linked_list next;
    private int data;


    public linked_list(int data) {
        this.data = data;
    }
    public linked_list setNext(int newData){
        this.next = new linked_list(newData);
        return next;
    }
    public int getData(){
        return data;
    }
    public linked_list getNext(){
        return next;
    }

    static public int max(linked_list list){
        int currentMax = -1;
        while(list.getNext() != null){

            if(list.getData() > currentMax){
                currentMax = list.getData();
            }
            list = list.getNext();
        }
        return currentMax;
    }

    public int maxRecurv(int currentMax){
        if(next == null){
            return currentMax;
        }
        else if(currentMax>this.data){
            return next.maxRecurv(currentMax);
        }
        else{
            return next.maxRecurv(this.data);
        }
    }

    public static void main(String[] args) {
        Random ran = new Random();
        int[] arr = {5,7,6,1,8,7,3,4,9,10,15,78,41,24,6};
        linked_list firstNode = new linked_list(10);
        linked_list temp =firstNode;
        for (int i =0;i<arr.length;i++){
        temp = temp.setNext(arr[i]);
        }
        System.out.println(max(firstNode));
        System.out.println(firstNode.maxRecurv(-1));

        //2.1.14
        // bubblesort sort of...
        // man kigger på de 2 øverste, og i det her tilfælde er det ene kort rødt og det andet er sort
        // hvis man hver gang smidder det sorte nederst i dækket og man tager en tilfældig rød hvis der ikke er en
        // så vil dækket på et tidspunkt være opdelt i sort og rød
        // flere ting kan sættes på istedet så det er både opdelt efter suit og nummer (ville nok endda gøre det hurtigere/nemmere)
        // se assignment1.js for ex.

    }
}
