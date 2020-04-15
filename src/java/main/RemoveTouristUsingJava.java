package main;

import java.util.*;
import java.io.*;

public class RemoveTouristUsingJava {

    public static void main(String[] args) {
        final String FORMAT = "P3";
        /*
        System.out.println();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("filtered")) {
                System.out.println("It is better to delete all the previous versions of the filtered ppm files ");
                System.out.println("before running this program");
                System.out.println();
                return;
            }
        }
        */
        File ppmDir = new File("src/resources/ppm");
        File resDir = new File("src/resources/result");

        File[] ppmImages = ppmDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String tmp = name.toLowerCase();
                return tmp.endsWith("ppm");
            }
        });

        int numOfImageFiles = ppmImages.length;

        if (numOfImageFiles < 3) {
            System.out.println("\nYou need more images files");
            return;
        }

        ArrayList<ImagePPM> images = new ArrayList<ImagePPM>();

        for (int i = 0; i < numOfImageFiles; i++) {
            String imageName = ppmImages[i].getName();

            ImagePPM pic = new ImagePPM(imageName);

            pic.getImageData();

            images.add(pic);
        }
        //###################################################################
        ArrayList<Integer> filteredImage = new ArrayList<Integer>();

        filteredImage.add(images.get(0).getWidth());
        filteredImage.add(images.get(0).getHeight());
        filteredImage.add(images.get(0).getMaxColour());

        for (int i = 0; i < images.get(0).getSizeOfImage(); i++) {
            ArrayList<Integer> tempArray = new ArrayList<Integer>();

            for (int j = 0; j < numOfImageFiles; j++) {
                tempArray.add(images.get(j).getAsciiValueAtIndex(i));
            }
	             					
				   /* System.out.println("Before the sort ------------------------------" );	
					   for(int p=0; p < mode.size(); p++)
	                   {   System.out.println("mode.get("+p+") is "+mode.get(p)+" " ); } 
				   */

            Collections.sort(tempArray);

                   /* System.out.println("After the sort ------------------------------" );	
					  for(int p=0; p < mode.size(); p++)
	                  {  System.out.println("mode.get("+p+") is "+mode.get(p)+" " ); }   
				   */

            int medianValue = 0;

            if (tempArray.size() / 2 == 0) {
                int first_middle_index = (tempArray.size() / 2);
                int first_middle_value = tempArray.get(first_middle_index);

                int second_middle_index = first_middle_index + 1;
                int second_middle_value = tempArray.get(second_middle_index);

                medianValue = (first_middle_value + second_middle_value) / 2;
            } else {
                int midindex = (tempArray.size() + 1) / 2;
                medianValue = tempArray.get(midindex);
            }

            filteredImage.add(medianValue);
        }

        //##################################################################################

        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(new File(resDir + "/result.ppm")));
            writer.write(FORMAT + "\n");
            writer.write(filteredImage.get(0) + " " + filteredImage.get(1) + "\n");
            writer.write(filteredImage.get(2) + "\n");

            for (int i = 3; i < filteredImage.size(); i++) {
                // format file nicely
                if (i % 3 == 0 && i != 3) {
                    writer.write("\n");
                }
                writer.write(filteredImage.get(i) + " ");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*

On Windows
-----------
To compile type in the following:
javac RemoveTouristUsingJava.java

To Run type in the following:
java RemoveTouristUsingJava *.ppm

this will create an image file called filteredImageUsingJava.ppm

which can be view using IrfanView 

http://www.irfanview.com/
http://www.irfanview.net/faq.htm


> javac RemoveTouristUsingJava.java

> java RemoveTouristUsingJava *.ppm

fileName is G0010099.ppm
fileName is G0010108.ppm
fileName is G0010109.ppm
fileName is G0010110.ppm
fileName is G0010111.ppm
fileName is G0010132.ppm
fileName is G0010159.ppm
fileName is G0010161.ppm
fileName is G0010163.ppm

>java RemoveTouristUsingJava *.ppm

It is better to delete all the previous versions of the filtered ppm files
before running this program

*/

