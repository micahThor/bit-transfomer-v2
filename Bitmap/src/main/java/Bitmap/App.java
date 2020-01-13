/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Bitmap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        // get user input image path
        String inFileName = new String(args[0]);
        // get user input path where the altered images will be saved
        String outFileName = new String(args[1]);
        // get user input for what type of image mutation
        String imageMutationType = new String(args[2]);

        BitmapGenerator bmpGenerator = new BitmapGenerator(inFileName, outFileName, imageMutationType);
    }

}