package main;

import java.util.*;
import java.io.*;

/**
 * ppm文件可以理解为一个矩阵
 * 矩阵中的每一个元素都是 (R, G, B) 这样的一个结构体 （可以理解为一个向量，其中R G B均为正整数）
 * RGB是一种表示颜色的方式 （R，G，B）分别代表红绿蓝，通过三原色的组合我们可以得到几乎所有颜色。数值越大表示加入的这种颜色越多。
 * 比如 (255, 0, 0) 是大红色；(0, 0, 0)是白色；(255，255，255)是黑色
 *
 * 举个例子
 * 如果文件形式为
 *
 * P3
 * 2 3
 * 255
 * 0 0 0
 * 255 255 255
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 10 11 12
 *
 * 第一行P3是编码方式，不用管
 * 第二行表示 width=2 height=3 即这是一个2*3的矩阵，那么显然 size 的值应该就是 2*3=6
 * 第三行表示 RGB 的取值最大为255，即 R=255 时表示红色给满
 * 因此下面还有6行数据，就是指的矩阵中的6个元素
 * 第一个点表示白色，第二个是黑色……
 *
 * 图片其实就是由非常多个带颜色的点组成的，因此只要点够多，比如一个 495 * 557 的矩阵，就可以表示一小张图片啦
 *
 */
public class ImagePPM {
    private String format; // 编码方式
    private int width; // 矩阵的宽
    private int height; // 矩阵的高
    private int maxColour; // 矩阵中元素里的三个数值的取值上限，即 R G B 的取值范围为[0, maxColour]

    private int size; // 矩阵中一共有多少个元素
    private File file;

    private ArrayList<Integer> ascii; // ascii这个变量就是把这个 n行m列 的矩阵 换成 1行n*m列 的向量

    public ImagePPM(File file) {
        this.file = file;
        ascii = new ArrayList<Integer>();
    }

    /**
     * 初始化ppm文件
     * 并检测该文件是否复合ppm文件的格式
     * 不必细看
     */
    public void getImageData() {
        System.out.println("fileName is " + file.getName());
        try {
            Scanner sc = new Scanner(file);

            format = sc.next();

            if (format.equals("P3")) {
                width = sc.nextInt();
                height = sc.nextInt();
                maxColour = sc.nextInt();

                size = (width * height) * 3;    // size = (495 * 557) * 3;
                // size = (275715)    * 3;
                // size =  827145

	                      /*  System.out.println("format    is "+format);
						      System.out.println("width     is "+width);
						      System.out.println("height    is "+height);
						      System.out.println("maxColour is "+maxColour);
		                      System.out.println("size      is "+size);
						  */

                int temp;

                while (sc.hasNextInt()) {
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
            } else {
                System.out.println("The image is not correct format.");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Input file opening failed");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxColour() {
        return maxColour;
    }

    public int getSizeOfImage() {
        return size;
    }

    public int getAsciiValueAtIndex(int position) {
        return ascii.get(position);
    }

}