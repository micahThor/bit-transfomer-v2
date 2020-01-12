package Bitmap;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitmapGenerator {

    BufferedImage img;
    File outFile;


    public BitmapGenerator(String inFileName, String outFileName, String mutationType)  {

        // take user's input path to create a BufferedImage
        this.img = setImage(inFileName);

        // create file with user's input path
        this.outFile = new File(outFileName);

        // mutate image based off user's mutation type request
        this.handleInputMutationType(mutationType);

    }

    public BufferedImage setImage(String inFileName) {

        File inputFile = new File(inFileName);
        try {
            this.img = ImageIO.read(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private void handleInputMutationType(String mutationType) {

        if (this.mutateImageAndSave(mutationType)) {
            System.out.println(String.format("Successful mutation of %s. Image saved to %s", mutationType, outFile.toURI()));
        } else {
            System.out.println("Bad mutation type name.  Try again with one of these as your third main method argument:");
            System.out.println("1. \"transformImg_CyclopisfyMario\"");
        }

    }

    public void saveImg() {
        try {
            ImageIO.write(this.img, "bmp", this.outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean mutateImageAndSave(String mutationType) {

        boolean ifMutatedAndSaved = false;

        if (mutationType.equals("transformImg_CyclopisfyMario")) {
            this.transformImg_CyclopisfyMario();
            this.saveImg();
            ifMutatedAndSaved = true;
        }


        return ifMutatedAndSaved;
    }

    public void transformImg_CyclopisfyMario() {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int nextColor = img.getRGB(j, i);
                img.setRGB(i, j, nextColor);
            }
        }
    }
}



