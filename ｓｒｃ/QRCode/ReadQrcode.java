package top.importtao.ZXing;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadQrcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiFormatReader mfr = new MultiFormatReader();
		File file = new File("D:/123/2.png");
		try {
			BufferedImage big = ImageIO.read(file);
			BinaryBitmap bbm = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(big)));
			HashMap hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			Result result = mfr.decode(bbm, hints);
			System.out.println("解析结果："+result.toString());
			System.out.println("二维码格式类型：："+result.getBarcodeFormat());
			System.out.println("二维码内容：："+result.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
