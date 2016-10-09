import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class prob11 {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner file = new Scanner(System.in);

        int n= file.nextInt();
        int h = file.nextInt();
        int w = file.nextInt();
        int[][] ray = new int[n/2][n/2];
        for(int i = 0; i<n/2; i++){
            for(int j = 0; j<ray[0].length-i-1; j++){
                ray[i][j]=1;
            }
        }
        System.out.println(Arrays.deepToString(ray));
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                for(int x = 0; x<ray.length; x++){
                    for(int y = 0; y<ray.length; y++){
                        if(ray[i][j]=='1'){
                            System.out.print("#");
                        } else {
                            System.out.print("/");
                        }
                    }
                    for(int y = ray.length-1; y>-1; y--){
                        if(ray[i][j]=='1'){
                            System.out.print("#");
                        } else {
                            System.out.print("\\");
                        }
                    }
                }


                System.out.println();
            }
            System.out.println();
            for(int j = 0; j<w; j++){
                for(int x = ray.length-1; x>-1; x--){
                    for(int y = 0; y<ray.length; y++){
                        if(ray[i][j]=='1'){
                            System.out.print("#");
                        } else {
                            System.out.print("\\");
                        }
                    }
                    for(int y = ray.length-1; y>-1; y--){
                        if(ray[i][j]=='1'){
                            System.out.print("#");
                        } else {
                            System.out.print("\\");
                        }
                    }
                }

                System.out.println();
            }
        }


    }
}
