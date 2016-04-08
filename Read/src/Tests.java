import java.util.Random;

/**
 * Created by Eric on 4/6/2016.
 */
public class Tests {

    public static void main(String[] argc){


        Random rand = new Random();

        for (int i =0; i < 10;i++){
            System.out.println(rand.nextLong() >>> 1);
        }

    }


}
