package com.merit.utils;

import com.swetake.util.Qrcode;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by R on 2018/7/2.
 */
public class QRCodeUtil {
    //文件保存路径
    public static String savePath = "";
    //文件格式
    public static String suffix = "";

    public static boolean createQRCode(String qrData, String fileName){
        System.out.println("生成二维码中......");
        if(TextUtils.isEmpty(savePath) || TextUtils.isEmpty(suffix)){
            System.out.println("二维码保存路径或文件格式未设置");
            return false;
        }

        Qrcode x = new Qrcode();
        x.setQrcodeErrorCorrect('M');//纠错等级
        x.setQrcodeEncodeMode('B');//N代表数字，A代表a-Z，B代表其他字符
        x.setQrcodeVersion(7);//版本
        int width = 67+12*(7-1);//尺寸公式
        int height = 67+12*(7-1)+30;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D gs = bufferedImage.createGraphics();

        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);
        gs.translate(0, 30);
        gs.drawString("入住离开请扫码", 27, -5);

        int pixoff = 2;//偏移量

        try {
            byte[] d = qrData.getBytes("gb2312");
            if(d.length>0&&d.length<120)
            {
                boolean[][] s = x.calQrcode(d);
                for(int i=0;i<s.length;i++)
                {
                    for(int j=0;j<s.length;j++)
                    {
                        if(s[j][i])
                        {
                            gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
                        }
                    }
                }
            }
            gs.dispose();
            bufferedImage.flush();
            String filePath = savePath + fileName + "." + suffix;
            ImageIO.write(bufferedImage, suffix, new File(filePath));
            //放大图片
            Thumbnails.of(filePath)
                    .size(500, 520)
                    .toFile(filePath);
            System.out.println("生成完毕，存储路径为：" + savePath + fileName + "." + suffix);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
