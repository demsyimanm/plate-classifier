/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_pcd;

import java.io.IOException;


/**
 *
 * @author dhanarp
 */
public class FP_PCD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String input="train2.jpg";
        input="data/"+input;
        String greyscale = new Greyscale(input).toGreyscale();
        String filtered = new NoiseCancel(greyscale).cancel();
        String binary = new Binarization(filtered).invThresholdImage(127);
    }
    
}
