package com.deyi.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by root on 2017/11/5 0005.
 */
public class QrCodeUtil {
    private static  Logger log = LoggerFactory.getLogger(QrCodeUtil.class);

    public static String generateMoneyQrCode(String webRoot,String qrCodeUrl, String orerNo) {
        String picCode = String.valueOf(System.currentTimeMillis());
       String imgPath =  PropertiesUtil.getProperty("project_tiaomaAli");
        System.out.println("[generateMoneyQrCode] : paramtes: webRoot=" + webRoot + ",orerNo=" + orerNo);
        String imgUrl =  PropertiesUtil.getProperty("project_tiaomaAli_url");

        //String imgFullPath =   webRoot + File.separator+ imgPath+File.separator;
        if(generateQrCode(imgPath, qrCodeUrl, orerNo)){
            return imgUrl+File.separator+orerNo+ ".bmp";
        }
        return null;
    }

    public static String generateQrCode(String merType ,String webRoot,String storeId,String storeCode,String qrCode) {
        String picCode = String.valueOf(System.currentTimeMillis());
        String qrCodeUrl =  PropertiesUtil.getProperty("qr_nobank_domain") + "?sid=" + storeId+"&qrCode="+qrCode;
        if("3".equals(merType)) qrCodeUrl =  PropertiesUtil.getProperty("qr_bank_domain")+ "?storeCode=" + storeCode+"&qrCode="+qrCode;
        System.out.println("[generateQrCode] : paramtes: merType="+merType+",webRoot=" + webRoot + ",storeId=" + storeId+ ",storeCode=" + storeCode);
        String imgPath = PropertiesUtil.getProperty("qr_img_path");
        String qrUrl = PropertiesUtil.getProperty("qr_img_url");
        //String imgFullPath =   webRoot + File.separator+ imgPath+File.separator;
       if(generateQrCode(imgPath, qrCodeUrl, picCode)){
           return qrUrl+File.separator+picCode+ ".bmp";
       }
        return null;
    }

    public static boolean generateQrCode(String imagePath, String content, String picCode) {

        log.info("[generateQrCode] : [content=" + content + ",picCode=" + picCode + ",imagePath=" + imagePath + "]");

        try {
            content = new String(content.getBytes("UTF-8"), "iso-8859-1");
            imagePath = imagePath +File.separator+ picCode + ".bmp";
            File e = new File(imagePath);
            //if(!e.exists()) e.createNewFile();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200);
            log.info("[generateQrCode] :toPath="+e.toPath());
            MatrixToImageWriter.writeToPath(matrix, "bmp", e.toPath());
            QRCodeReader reader = new QRCodeReader();
            BufferedImage image = ImageIO.read(e);
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            HybridBinarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
            reader.decode(imageBinaryBitmap);
        } catch (Exception e) {
            log.error("创建二维码异常:", e);
            return false;
        }

        log.info("[generateQrCode] : generate QrCode is finished .....");
        return true;
    }
}
