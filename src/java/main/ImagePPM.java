package main;

import java.util.*;
import java.io.*;


public class ImagePPM
{
	   private String  format;
       private int     width;
       private int     height;
	   private int     maxColour;

       private int     size;
	   private String  fileName;
	
	   private ArrayList<Integer> ascii;

       public ImagePPM(String fileN)
	   {
	          fileName = fileN;
			  ascii    = new ArrayList<Integer>();
	   }

	   public void getImageData()
 	   {
               System.out.println("fileName is "+fileName);
			   try 
			   {
					  Scanner sc  =  new Scanner(new File(fileName));

					  format      =  sc.next();
                      
					  if( format.equals("P3") )
		              {
						  width       = sc.nextInt();
						  height      = sc.nextInt();
						  maxColour   = sc.nextInt();	
						  
                          size        = (width * height) * 3;    // size = (495 * 557) * 3;  		                                                   
										                         // size = (275715)    * 3;
										                         // size =  827145

	                      /*  System.out.println("format    is "+format);
						      System.out.println("width     is "+width);
						      System.out.println("height    is "+height);
						      System.out.println("maxColour is "+maxColour);
		                      System.out.println("size      is "+size);
						  */

						  int temp;

                          while ( sc.hasNextInt() )  
			              {
                              temp = sc.nextInt();
					          ascii.add(temp);
                          }						 	
						  // pixel = R G B
						  // line number        data
						  //     1              P3
						  //     2              495 557
						  //     3              255
						  //     4              240 244 253
						  //     .              ...........
						  //     .              ...........
						  //    275716          174 165 132
						  //    275717          168 159 126
						  //    275718          164 155 122
						  //    275719          no pixel values ie blank
                      }
					  else
				      {
					     System.out.println("The image is not correct format.");
					  }
					  sc.close(); 
                } 
                catch (FileNotFoundException e) 
			    {
                      e.printStackTrace();
				      System.out.println("Input file opening failed");
                }	   
	   }

	   public int  getWidth()
	   {
	          return width;
	   }

	   public int  getHeight()
	   {
	          return height;
	   }

	   public int  getMaxColour()
	   {
              return maxColour;
	   }

	   public int  getSizeOfImage()
	   {
	          return size;
	   }
	   
	   public int  getAsciiValueAtIndex(int position)
	   {
	          return ascii.get(position);
	   }
	  
}