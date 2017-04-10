package com.dz.common.ocr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;

public class ImageExe {
	
	public static void main(String[] args) {
		File inDom = new File("E:\\result\\Test");
		File outDom = new File("E:\\result\\TestResult");
		File[] flist = inDom.listFiles(new FileFilter(){
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith("jpg");
			}
		});
		
		for (int i = 0; i < flist.length; i++) {
			try {
				exec(flist[i],outDom);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void exec(File file,File outDom) throws IOException {
		// TODO Auto-generated method stub
		// File file = new File("C:\\Users\\Xiaoyao\\Desktop\\项目\\电子违章\\yzm.jpg");
		//File outDom = new File("C:\\Users\\Xiaoyao\\Desktop\\项目\\电子违章\\yzm");
		File outFile = new File(outDom,file.getName());
		
		ImageReader imageReader = ImageIO.getImageReadersByFormatName("jpg").next() ;
		ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			imageReader.setInput(iis, true);
			//System.out.println("width:" + imageReader.getWidth(0));
			//System.out.println("height:" + imageReader.getHeight(0));
			
			BufferedImage image = imageReader.read(0);
			
            int[] p = new int[9]; //最小处理窗口3*3
            //byte[] lpTemp=new BYTE[nByteWidth*nHeight];

            //--!!!!!!!!!!!!!!下面开始窗口为3×3中值滤波!!!!!!!!!!!!!!!!
            for (int y = 1; y < image.getHeight() - 1; y++) //--第一行和最后一行无法取窗口
            {
                for (int x = 1; x < image.getWidth() - 1; x++)
                {
                    //取9个点的值
                    p[0] = new Color(image.getRGB(x - 1, y - 1)).getRed();
                    p[1] = new Color(image.getRGB(x, y - 1)).getRed();
                    p[2] = new Color(image.getRGB(x + 1, y - 1)).getRed();
                    p[3] = new Color(image.getRGB(x - 1, y)).getRed();
                    p[4] = new Color(image.getRGB(x, y)).getRed();
                    p[5] = new Color(image.getRGB(x + 1, y)).getRed();
                    p[6] = new Color(image.getRGB(x - 1, y + 1)).getRed();
                    p[7] = new Color(image.getRGB(x, y + 1)).getRed();
                    p[8] = new Color(image.getRGB(x + 1, y + 1)).getRed();
                    //计算中值
                    
                    Arrays.sort(p);
                    
              //if (image.getRGB(x, y).R < dgGrayValue)
                    image.setRGB(x, y,new Color(p[4],p[4],p[4]).getRGB());//给有效值付中值
                }
            }
            
//            for (int y = 0; y < image.getHeight(); y++)
//            {
//                for (int x = 0; x < image.getWidth(); x++)
//                {
//                    int redp=new Color(image.getRGB(x, y)).getRed();
//                    if(redp>127){
//                    	image.setRGB(x, y,new Color(255,255,255,255).getRGB());
//                    }else{
//                    	image.setRGB(x, y,new Color(0,0,0,255).getRGB());
//                    }
//                }
//            }
            
            ImageOutputStream ios = ImageIO.createImageOutputStream(outFile);
            imageWriter.setOutput(ios);
            
            imageWriter.write(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RenderedOp src = JAI.create("fileload", outFile.getAbsolutePath());
		File outFile2 = new File(outDom,file.getName()+".tif");
		if(!outFile2.exists())
			outFile2.createNewFile();
		OutputStream os = new FileOutputStream(outFile2);
		TIFFEncodeParam param = new TIFFEncodeParam();
		ImageEncoder encoder = ImageCodec.createImageEncoder("TIFF", os, param);
		encoder.encode(src);
		os.close();
		
	}
}
