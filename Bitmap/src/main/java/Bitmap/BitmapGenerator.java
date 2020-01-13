package Bitmap;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitmapGenerator {

    // instance img and mutation type
    BufferedImage img;
    String mutationType;

    // instance resource variables
    File inFile;
    File outFile;


    public BitmapGenerator(String inFileName, String outFileName, String mutationType) {

        // take user's input path to create a BufferedImage
        this.inFile = new File(inFileName);
        this.img = setImage(this.inFile);

        // create file with user's input path
        this.outFile = new File(outFileName);

        // mutate image based off user's mutation type request
        this.mutationType = String.format("transformImg_%s", mutationType);
        this.handleInputMutationType(mutationType);

    }

    public BufferedImage setImage(File inputFile) {

        try {
            this.img = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println("Bad input file path.  File failed at image read.");
            e.printStackTrace();
        }

        return img;
    }

    private void handleInputMutationType(String mutationType) {

        // if user supplies good bitmap file and good mutation type, print success message
        if (this.mutateImageAndSave(mutationType)) {

            String successMessage = String.format("Successful mutation of \"%s\" using transform of \"%s\".\nImage saved to: \"%s\"",
                    this.inFile.toString(), this.mutationType, this.outFile.toString());

            System.out.println(successMessage);

        } else { // if user supplies bad input

            String failMessage = String.format("Possible bad input file (%s) image or bad mutation type name (%S).",
                    this.inFile.toString(), this.mutationType);

            System.out.println("***PROCESS FAILED***.");
            System.out.println(failMessage);
            System.out.println("Try again with one of these as your third main method argument:");
            System.out.println("1. \"cyclopisfyMario\"");
            System.out.println("2. \"mirrorMario\"");
            System.out.println("3. \"luigifyMario\"");
        }
    }

    public void saveImg() {
        try {
            ImageIO.write(this.img, "bmp", this.outFile);
        } catch (IOException e) {
            System.out.println("Image failed to save.");
            e.printStackTrace();
        }
    }

    public boolean mutateImageAndSave(String mutationType) {

        boolean ifMutatedAndSaved = false;

        if (mutationType.equals("cyclopisfyMario")) {
            this.transformImg_cyclopisfyMario();
            this.saveImg();
            ifMutatedAndSaved = true;
        }

        if (mutationType.equals("luigifyMario")) {
            this.transformImg_luigifyMario();
            this.saveImg();
            ifMutatedAndSaved = true;
        }

        if (mutationType.equals("mirrorMario")) {
            this.transformImg_mirrorMario();
            this.saveImg();
            ifMutatedAndSaved = true;
        }

        return ifMutatedAndSaved;
    }

    public void transformImg_cyclopisfyMario() {

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int nextColor = img.getRGB(j, i);
                img.setRGB(i, j, nextColor);
            }
        }
    }

    public void transformImg_luigifyMario() {

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                Color currentBitColor = new Color(this.img.getRGB(i,j));

                Color luigiGreen = new Color(72,173,22);

                int red = (int)(currentBitColor.getRed());
                int blue = (int)(currentBitColor.getBlue());
                int green = (int)(currentBitColor.getGreen());

                if (red > 0 && blue < 100 && green < 100) {
                    img.setRGB(i, j, luigiGreen.getRGB());
                }
            }
        }
    }

    // METHOD IS TAKEN FROM CLASS DEMO AND ADAPTED TO MY PROGRAM
    public void transformImg_mirrorMario() {

        for (int i = 0; i < this.img.getHeight(); i++) {
            for (int j = this.img.getWidth() - 1; j > this.img.getWidth() / 2; j--) {

                Color currentBitColor = new Color(this.img.getRGB(i, j));
                this.img.setRGB(i, this.img.getWidth() - 1 - j, currentBitColor.getRGB());
            }
        }
    }
}




