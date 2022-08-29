public class CollisionsMain {

    public static void main (String[] args){
        //Max Value: 1000000000000000000
        Blocks blocks  = new Blocks(100,100000000,1000,1300);

        new DisplayCollisions(1000, 500, blocks);
    }
}
