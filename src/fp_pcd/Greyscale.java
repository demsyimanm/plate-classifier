/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_pcd;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author dhanarp
 */
public class Greyscale {
    BufferedImage  image;
    String inputPath,outputPath=new String();
    int width;
    int height;
    
    public Greyscale(String inputPath){
        this.inputPath=inputPath;
    }
    
    
    public String toGreyscale(){
   
        try {
            File input = new File(inputPath);
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            for(int i=0; i<height; i++){

               for(int j=0; j<width; j++){

                  Color c = new Color(image.getRGB(j, i));
                  int red = (int)(c.getRed() * 0.299);
                  int green = (int)(c.getGreen() * 0.587);
                  int blue = (int)(c.getBlue() *0.114);
                  Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                  
                  

                  image.setRGB(j,i,newColor.getRGB());
               }
            }

            outputPath="data/grayscale.jpg";
            File ouptut = new File(outputPath);
            ImageIO.write(image, "jpg", ouptut);

           

      } catch (Exception e) {}
    
    return outputPath;
   }
   
   
}
