package minimum_spanning_tree;

public class reverse_deleting_algorithm <Key extends Comparable<Key>, Value> {
    protected class Node {
        protected Key key;
        protected Value value;

        protected Node left;
        protected Node right;

        protected int size; //# of nodes in subtree rooted here

        public String print(int layer){

            if(left != null){
                System.out.println("layer: "+layer+": key:"+key+", my value is: "+value+", my left node is: "+left.print(layer+1));
            }
            if(right != null){
                System.out.println("layer: "+layer+": key:"+key+", my value is: "+value+", my right node is: "+right.print(layer+1));
            }
            if(value != null){
                return key+" : "+value;
            }
            else{return "";}
        }

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    protected Node root;

    public Value get(Key key) {
        if (key == null) {
            return null;
        }

        return get(root, key);
    }
    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return get(node.left, key);
        } else if (compare > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }

        if (value == null) {
            delete(key);
            return;
        }

        root = put(root, key, value);
    }

    public int size() {
        return size(root);
    }

    protected int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.size;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }

        int compare = key.compareTo(node.key);

        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.size = size(node.left) + 1 + size(node.right);
        return node;
    }

    public boolean isEmpty() {
        return size(root) == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument to contains() cannot be null");
        }
        return get(key) != null;
    }

    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }

        if (!contains(key)) {
            return;
        }

        root = delete(root, key);
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMin(node.left);
        node.size = size(node.left) + 1 + size(node.right);
        return node;
    }

    private Node delete(Node node, Key key) {
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = delete(node.left, key);
        } else if (compare > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node aux = node;
                node = min(aux.right);
                node.right = deleteMin(aux.right);
                node.left = aux.left;
            }
        }

        node.size = size(node.left) + 1 + size(node.right);
        return node;
    }

    //funktionen her tager en node, hvis den har nodes som ikke er null går den til dem, osv. indtil den finder en med tomme nodes
    //hvilket vil være i bunden af træet, og slette dem
    //bagefter vil den så slette dem som har fået slettet deres nodes da de nu er null.
    public void deleteAllRecursive(Node node){
        if(node.right != null){
            deleteAllRecursive(node.right);
            delete(node,node.right.key);
        }
        if(node.left != null){
            deleteAllRecursive(node.left);
            delete(node,node.left.key);
        }

    }


    public static void main(String[] args) {
        reverse_deleting_algorithm<Integer, String> bst = new reverse_deleting_algorithm();
        //tallene nedenfor er numeriske værdier af  E A S Y Q U T I O N fra opgave 3.3.10
        int[] testArr = {5,1,19,25,17,21,20,9,15,14};
        for(int key : testArr){
            bst.put(key, "Value " + key);
        }

        //drawing of tree can be seen at 3.3.1_binarytree

        bst.root.print(1);

        System.out.println("deleting key: 3");
        bst.delete(3);
        bst.root.print(1);

        //en bst er en black-red binarysearchtree...
        //hvor hver node har 2nodes, en mindre værdier mindre end den selv
        //en med større
        //ideen er at værdier imellem 2 nodes bliver placeret imellem
        //dette kan også ses på 3.3.1_binarytree at hvis en værdi bliver sat ind, fx 17 i ovenstående vil den blive sat ind i venstre node for 19.
        // da det er en værdi imellem 5 og 19, senere vil 15 blive sat ind på venstre node af 17, da den er imellem 17 og 5
        // hvis det 18 blev sat ind ville den være på højre node af 17. da den er større end 17 men mindre end 19.



        System.out.println("deleting all recursively");
        //denne funktion vil slette alle nodes recurve style
        bst.deleteAllRecursive(bst.root);
        bst.root.print(1);
    }
}
