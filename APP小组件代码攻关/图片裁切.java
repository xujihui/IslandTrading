package com.minxinloan.black.web.utils;

import java.awt.Graphics;
import java.awt.Image;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;
  
import javax.imageio.ImageIO;  

import org.apache.log4j.Logger;
//  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
//
//import com.sun.media.jai.codecimpl.JPEGCodec;
//import com.sun.media.jai.codecimpl.JPEGImageEncoder;
  



public class ImagesUtils {  
  
    private final static Logger log = Logger.getLogger(ImagesUtils.class);    
    // 图片允许的最大大小
    public static final int IMAGEMAXSIZE = 148*113;
    // 图片宽和高的最大尺寸  
    public static final int IMAGEMAXBIG = 2000;  
    // 图片宽和高的最小尺寸  
    public static final int IMAGEMINBIG = 200;  
    // 按原图大小生成新图  
    public static final int CREATENEWIMAGETYPE_0 = 0;  
    // 按指定的大小生成新图  
    public static final int CREATENEWIMAGETYPE_1 = 1;  
    // 按原图宽高比例生成新图-按指定的宽度  
    public static final int CREATENEWIMAGETYPE_2 = 2;  
    // 按原图宽高比例生成新图-按指定的高度  
    public static final int CREATENEWIMAGETYPE_3 = 3;  
    // 按原图宽高比例生成新图-按指定的宽和高中较大的尺寸  
    public static final int CREATENEWIMAGETYPE_4 = 4;  
    // 按原图宽高比例生成新图-按指定的宽和高中较小的尺寸  
    public static final int CREATENEWIMAGETYPE_5 = 5;  
    // 按原图宽高比例生成新图-按原图大小的90%进行修改 
    public static final int CREATENEWIMAGETYPE_6 = 6;
  
    /** 
     *  
     * @param _file 
     *            原图片 
     * @param createType 
     *            处理类型 
     * @param newW 
     *            新宽度 
     * @param newH 
     *            新高度 
     * @return 
     * @throws Exception 
     */  
    public static String createNewImage(File _file, int createType, int newW,  
            int newH) throws Exception {  
        if (_file == null)  
            return null;  
        String fileName = _file.getPath();  
        if (fileName == null || "".equals(fileName)  
                || fileName.lastIndexOf(".") == -1)  
            return null;  
        else newFileName = "islandtrading_" + newFileName; 
         
  
        String outFileName = fileName.substring(0, fileName.lastIndexOf(".")) 
                + fileName.substring(fileName.lastIndexOf("."), fileName  
                        .length());  
        String fileExtName = fileName.substring(  
                (fileName.lastIndexOf(".") + 1), fileName.length());  
        if (newW < IMAGEMINBIG)  
            newW = IMAGEMINBIG;  
        else if (newW > IMAGEMAXBIG)  
            newW = IMAGEMAXBIG;  
  
        if (newH < IMAGEMINBIG)  
            newH = IMAGEMINBIG;  
        else if (newH > IMAGEMAXBIG)  
            newH = IMAGEMAXBIG;  
  
        // 得到原图信息  
        if (!_file.exists() || !_file.isAbsolute() || !_file.isFile()  
                || !checkImageFile(fileExtName))  
            return null;  
        Image src = ImageIO.read(_file);  
        int w = src.getWidth(null);  
        int h = src.getHeight(null);  
  
        // 确定目标图片的大小  
        int nw = w;  
        int nh = h;  
        if (createType == CREATENEWIMAGETYPE_0)  
            ;  
        else if (createType == CREATENEWIMAGETYPE_1) {  
            nw = newW;  
            nh = newH;  
        } else if (createType == CREATENEWIMAGETYPE_2) {  
            nw = newW;  
            nh = (int) ((double) h / (double) w * nw);  
        } else if (createType == CREATENEWIMAGETYPE_3) {  
            nh = newH;  
            nw = (int) ((double) w / (double) h * nh);  
        } else if (createType == CREATENEWIMAGETYPE_4) {  
            if ((double) w / (double) h >= (double) newW / (double) newH) {  
                nh = newH;  
                nw = (int) ((double) w / (double) h * nh);  
            } else {  
                nw = newW;  
                nh = (int) ((double) h / (double) w * nw);  
            }  
        } else if (createType == CREATENEWIMAGETYPE_5) {  
            if ((double) w / (double) h <= (double) newW / (double) newH) {  
                nh = newH;  
                nw = (int) ((double) w / (double) h * nh);  
            } else {  
                nw = newW;  
                nh = (int) ((double) h / (double) w * nw);  
            }  
        } else if(createType == CREATENEWIMAGETYPE_6){
//            nw = (int)(w*0.5);  
//            nh = (int)(h*0.5); 
            double proportion = (double)1700/(double)w;
            nw = (int)((double)w*proportion);
            nh = (int)((double)h*proportion);
        } 
  
        // 构造目标图片  
        BufferedImage tag = new BufferedImage(nw, nh,  
                BufferedImage.TYPE_INT_RGB);  
  
        // 得到目标图片输出流  
        FileOutputStream out = new FileOutputStream(outFileName);  
  
        // 根据需求画出目标图片 方式1  
        tag.getGraphics().drawImage(src, 0, 0, nw, nh, null);  
  
        // 将画好的目标图输出到输出流 方式1  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(tag);  
        out.close();  
        return outFileName;  
    }  
  
    public static boolean checkImageFile(String extName) {  
  
        if ("jpg".equalsIgnoreCase(extName))  
            return true;  
        if ("gif".equalsIgnoreCase(extName))  
            return true;  
        if ("bmp".equalsIgnoreCase(extName))  
            return true;  
        if ("jpeg".equalsIgnoreCase(extName))  
            return true;  
        if ("png".equalsIgnoreCase(extName))  
            return true;  
        return false;  
    }  
    public static String checkImageFile2(String extName) {  
          
        if ("jpg".equalsIgnoreCase(extName))  
            return "jpg";  
        if ("gif".equalsIgnoreCase(extName))  
            return "gif";  
        if ("bmp".equalsIgnoreCase(extName))  
            return "bmp";  
        if ("jpeg".equalsIgnoreCase(extName))  
            return "jpeg";  
        if ("png".equalsIgnoreCase(extName))  
            return "jpeg";  
        return null;  
    }  
   
    
    //递归修改图片大小
    public static void changeImgSize(String filePath,int createType)
    {
        try {
            File tempFile = new File(filePath);
            if(tempFile.length()>IMAGEMAXSIZE){
                System.out.println("sss");
                changeImgSize(createNewImage(tempFile, createType, 0, 0),createType);
            }
        } catch (Exception e) {
            log.error("the changeImgSize is failed . the message is "+e.getMessage());
        }
    }
    /**
     * 缩放图像（按比例缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param scale 缩放比例
     * @param flag 缩放选择:true 放大; false 缩小;
     */
    public final static void scale(String srcImageFile,String type, String result) {
        try {
            
            File tempFile = new File(srcImageFile);
            if(tempFile.length()>IMAGEMAXSIZE){
                System.out.println("sss");
                BufferedImage src = ImageIO.read(tempFile); // 读入文件
                int width = src.getWidth(); // 得到源图宽
                int height = src.getHeight(); // 得到源图长
                
                double sc = (double)1700/(double)width;
                
             
                width = (int)((double)width * sc);
                height = (int)((double)height * sc);
              
                Image image = src.getScaledInstance(width, height,
                        Image.SCALE_DEFAULT);
                BufferedImage tag = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(image, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                ImageIO.write(tag, type, new File(result));// 输出到文件流
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void changeImgSize(File file)
     {
         try {
                // 判断文件是否是文件，如果是文件，获取路径，并计数
                if (file.isFile()) {
                    String fileExtName = file.getName().substring(  
                            (file.getName().lastIndexOf(".") + 1), file.getName().length());  
                    
                    String temp = ImagesUtils.checkImageFile2(fileExtName);
                    if(temp!=null)
                        //scale(file.getAbsolutePath(),temp,file.getAbsolutePath());
                        ImagesUtils.changeImgSize(file.getAbsolutePath(), ImagesUtils.CREATENEWIMAGETYPE_6);
                } else {
                    // 如果是文件夹，声明一个数组放文件夹和他的子文件
                    File[] f = file.listFiles();
                    // 遍历文件件下的文件，并获取路径
                    for (File file2 : f) {
                        changeImgSize(file2);
                    }
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
     }
  
    public static void main(String[] args) {
        

        
        
//
//        long start=System.currentTimeMillis();
//        String filePath = "C:\\Users\\zhangmi\\Desktop\\资料";
//        changeImgSize(new File(filePath));
//
//        long end=System.currentTimeMillis();
//        //在最好的一行加上:
//        System.out.println("执行耗时 : "+(end-start)/1000f+" 秒 ");
        
    }
}