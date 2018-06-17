package Analysis;

public class egg_throwing {
    //max lg(n) throws and max lg(n) broken eggs
    public void eggThrow1(int floors, int notSafeFloor){
        int eggThrows = 0;
        int brokenEggs = 0;
        int low = 1;
        int high = floors;
        while(high - low > 1){
            eggThrows++;
            int throwFloor = (low + high)/2;
            if(throwFloor >= notSafeFloor){
                brokenEggs++;
                high = throwFloor;
            }
            else{
                low = throwFloor;
            }
        }
        System.out.println("egg1: number of floors: "+floors+", found notsafefloor?: "+(notSafeFloor == high)+", broken eggs: "+brokenEggs+", number of throws: "+eggThrows +", log(n): "+Math.log(floors));
    }
    public void eggThrow2(int floors, int notSafeFloor){
        int eggThrows = 0;
        int brokenEggs = 0;
        int low = 0;
        int high = 1;
        while(high < notSafeFloor){
            eggThrows++;
            low = high;
            high = 2*high;
            if(high > floors){
                high = floors;
            }
        }
        brokenEggs++;
        while(high - low > 1){
            eggThrows++;
            int throwFloor = (low + high)/2;
            if(throwFloor >= notSafeFloor){
                brokenEggs++;
                high = throwFloor;
            }
            else{
                low = throwFloor;
            }
        }
        System.out.println("egg2: number of floors: "+floors+", found notsafefloor?: "+(notSafeFloor == high)+", broken eggs: "+brokenEggs+", number of throws: "+eggThrows +", log(n): "+Math.log(floors));
    }

    public static void main(String[] args) {
        egg_throwing et = new egg_throwing();
        System.out.println(Math.log(10000));
        et.eggThrow1(10000,555);
        et.eggThrow2(10000,555);
    }
}
