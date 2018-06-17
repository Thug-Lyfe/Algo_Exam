package application_queue;

import stuff.Queue;

import java.util.HashSet;
import java.util.Iterator;

public class uniqueue {

    private class UniQueue<Item> implements Iterable<Item> {

        private Queue<Item> queue;
        private HashSet<Item> seen;
        //initialize 1 queue and 1 hashset
        UniQueue() {
            seen = new HashSet<>();
            queue = new Queue<>();
        }
        //check queue size
        public int size() {
            return queue.size();
        }
        // throw item to queue if seen !contains
        public void enqueue(Item item) {
            if (seen.contains(item)) {
                return;
            }

            queue.enqueue(item);
            seen.add(item);
        }
        //remove and return the last entered item
        public Item dequeue() {
            return queue.dequeue();
        }
        //return queue for iteration
        public Iterator<Item> iterator() {
            return queue.iterator();
        }

    }

    public static void main(String[] args) {
        uniqueue base = new uniqueue();
        UniQueue qe = base.new UniQueue<>();

        qe.enqueue(0);
        qe.enqueue(1);
        qe.enqueue(2);
        qe.enqueue(4);
        qe.enqueue(8);

        System.out.println("size should be 5, and it is: "+qe.size());
        qe.enqueue(2);
        System.out.println("size should be 5, and it is: "+qe.size());

        qe.dequeue();
        System.out.println("Size should be 4, and it is: " + qe.size());
        System.out.println("8 was removed so lets try and add it again?");
        qe.enqueue(8);
        System.out.println("Size should be 4, and it is: " + qe.size());


        qe.enqueue(16);
        System.out.println("size should be 5, and it is: "+qe.size());

        // printing the queue to be sure we have what we expect
        // should be 1,2,4,8,16
        for (Object i: qe){
            System.out.print(i+", ");
        }
    }

}
