/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_pcd;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author dhanarp
 */
public class NoiseCancel {
    BufferedImage  image,image2;
    String inputPath,outputPath;
    int width;
    int height;
    int[] matrix;
    int k=0,l=0;
    static int medianParam=1,param;
    
    public NoiseCancel(String inputPath){
        param=((medianParam*2)+1)*((medianParam*2)+1);
        this.matrix = new int[((medianParam*2)+1)*((medianParam*2)+1)];
        this.inputPath=inputPath;
    }
    
    public String cancel() throws IOException{
        
        
        File input = new File(inputPath);
            image = ImageIO.read(input);
            image2=ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            /*
            System.out.println("Height = "+ height);
            System.out.println("Width = "+ width);
            */
            for(int i=medianParam; i<height-medianParam; i++){
               for(int j=medianParam; j<width-medianParam; j++){
                    int initI=i-medianParam,initJ=j-medianParam,iterK = 0;
                    for(k=0;k<param;k++) matrix[k]=-1;
                    if(initI>=0 && initJ>=0&&initI<height-medianParam&&initJ<width-medianParam){
                        
                        for(int iterI=initI; iterI<i+medianParam+1; iterI++){
                            for(int iterJ=initJ; iterJ<j+medianParam+1; iterJ++){
                                    //System.out.println(i+" "+j+" "+iterI+" "+iterJ);
                                    Color c=new Color(image.getRGB(iterJ, iterI));
                                    matrix[iterK++]=c.getRed();
                                    //System.out.println(matrix[iterK]+"=matrix  "+iterK++ +"=iterK");
                            }
                        }
                    }   
               
                   Arrays.sort(matrix);
                   //System.out.println(matrix[13]);
                   if(matrix[0]>-1){
                        //System.out.println("Berhasil");
                        Color c2 = new Color(matrix[(param/2)+1],matrix[(param/2)+1],matrix[(param/2)+1]);
                        image2.setRGB(j,i,c2.getRGB());
                   }
               }
            }
        
        outputPath="data/noiseCancelled.jpg";
        File ouptut = new File(outputPath);
        ImageIO.write(image2, "jpg", ouptut);
        return outputPath;
    }
}
