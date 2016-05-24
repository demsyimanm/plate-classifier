/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_pcd;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dhanarp
 */
public class Binarization {
    String inputPath,outputPath;
    BufferedImage  image,image2;
    int width;
    int height;
    int[] matrix;
    int i,j,k;
    public Binarization(String input){
        this.inputPath=input;
    }
    
    public String thresholdImage(int threshold) throws IOException {
        File input = new File(inputPath);
        image = ImageIO.read(input);
        image2=ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        
        for(i=0;i<height;i++){
            for(j=0;j<width;j++){
                Color c = new Color(image.getRGB(j, i));
                int gsc = (int)(c.getRed());
                if(gsc<threshold) gsc=0;
                else gsc=255;
                Color newColor = new Color(gsc,gsc,gsc);
                image2.setRGB(j,i,newColor.getRGB());
            }
        }
    
    
        outputPath="data/BinaryImage.jpg";
        File ouptut = new File(outputPath);
        ImageIO.write(image2, "jpg", ouptut);
        return outputPath;
    }
    
    public String invThresholdImage(int threshold) throws IOException{
    File input = new File(inputPath);
        image = ImageIO.read(input);
        image2=ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        
        for(i=0;i<height;i++){
            for(j=0;j<width;j++){
                Color c = new Color(image.getRGB(j, i));
                int gsc = (int)(c.getRed());
                if(gsc<threshold) gsc=255;
                else gsc=0;
                Color newColor = new Color(gsc,gsc,gsc);
                image2.setRGB(j,i,newColor.getRGB());
            }
        }
    
    
        outputPath="data/InvertedBinaryImage.jpg";
        File ouptut = new File(outputPath);
        ImageIO.write(image2, "jpg", ouptut);
        return outputPath;
    }
}
