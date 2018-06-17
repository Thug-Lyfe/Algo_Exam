package Analysis;

public class compare_int_arrays {

    public void compare(int[] arr1, int[] arr2){
        int i1 = 0;
        int i2 = 0;
        System.out.println("finding numbers: ");
        while(i1 <arr1.length && i2 < arr2.length){
            if(arr1[i1] == arr2[i2]){
                System.out.print(arr1[i1]+", ");
                i1++;
                i2++;
            }
            else if(arr1[i1] < arr2[i2]){
                i1++;
            }
            else{
                i2++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3,5,8,9,10};
        int[] arr2 = {1,2,3,4,7,8};
        compare_int_arrays test = new compare_int_arrays();
        test.compare(arr1,arr2);
    }
}
