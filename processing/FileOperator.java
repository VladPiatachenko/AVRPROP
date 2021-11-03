package processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class FileOperator {
    static int realiz=30, oznaka=3000;
        private static String path[] = {
               "K:\\hierarchy_java\\DataReader\\gestures\\doubles\\cyl_ch2.txt",
               "K:\\hierarchy_java\\DataReader\\gestures\\doubles\\hook_ch2.txt",
               "K:\\hierarchy_java\\DataReader\\gestures\\doubles\\lat_ch2.txt",
               "K:\\hierarchy_java\\DataReader\\gestures\\doubles\\palm_ch2.txt",
               "K:\\hierarchy_java\\DataReader\\gestures\\doubles\\spher_ch2.txt",
               "K:\\hierarchy_java\\DataReader\\gestures\\doubles\\tip_ch2.txt"
};
    
    
    
    public static String[] getPathG() {
        return path;
    }
    

    static double[][] getMatrix(String path) throws FileNotFoundException, IOException{
       Scanner sc = new Scanner(new FileReader(path));
       double[][] matrix = new double [realiz][oznaka];
           
        for(int i = 0; i< realiz; i++)
        {
           for(int k = 0; k < oznaka; k++) 
           {
                matrix[i][k] =sc.nextDouble();
               }
        }
        return matrix;
}
    

}

    

