package syo_controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;


/**
 * Die Klasse BarcodeGen 
 * @author ebeckm, irgendjemand anderes, der dieses tolle FW gebaut hat!
 *
 */
public class BarcodeGen {

  /**
   * @param args
   * @throws BarcodeException 
   * @throws ConfigurationException 
   * @throws IOException 
   */
  public static void main(String[] args) throws ConfigurationException, BarcodeException, IOException {

    BarcodeUtil util = BarcodeUtil.getInstance();
    BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("code128"));

    
    OutputStream fout = new FileOutputStream("Images/code128.jpg");
    int resolution = 200;
    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
        fout, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);

    gen.generateBarcode(canvas, "10525647389200185731");
    canvas.finish();
    
    Image pic = ImageIO.read(new File("images/code128.jpg"));
    
    Printer p = new Printer(pic);
    p.setLocationRelativeTo(null);
    p.setVisible(true);
    
  }

  private static Configuration buildCfg(String type) {
    DefaultConfiguration cfg = new DefaultConfiguration("barcode");

    //Bar code type
    DefaultConfiguration child = new DefaultConfiguration(type);
      cfg.addChild(child);
    
      //Human readable text position
      DefaultConfiguration attr = new DefaultConfiguration("human-readable");
      DefaultConfiguration subAttr = new DefaultConfiguration("placement");
        subAttr.setValue("bottom");
        attr.addChild(subAttr);
        
        child.addChild(attr);
    return cfg;
  }
}



 