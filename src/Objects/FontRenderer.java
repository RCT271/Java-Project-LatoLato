package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FontRenderer {

    static Image sprite = null;
    static BufferedImage bufferedSprite = null;
    final static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
    static int[] widthOfChars = new int[chars.length()];
    static int[] xOfChars = new int[chars.length()];

    public double height, charSpacing = 4;

    public FontRenderer(String path, double height) {
        this.height = height;

        // initializes the sprite
        if (sprite == null) {
            sprite = new ImageIcon(path).getImage();
            try {
				bufferedSprite = ImageIO.read(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            System.out.println("w: " + bufferedSprite.getWidth());
            

            // initialize the width of every char
            int idxCount = 0, startPixel = 0, width = 0;
            for (int x = 0; x < bufferedSprite.getWidth(); x++) {
            	
                Color color = new Color(bufferedSprite.getRGB(x, 0));
                
                
                int r = color.getRed(), g = color.getGreen(), b = color.getBlue(), a = color.getAlpha();
                // get the widths of each char
                if (r == 137 && g == 137 && b == 137) {
                    if (x > startPixel) {
//                    	System.out.println("startPixel: " + startPixel);
//                    	System.out.println("x: " + x + " id: " + idxCount + " ");
                        width = x - startPixel;
                        widthOfChars[idxCount] = width;
                        xOfChars[idxCount] = startPixel;
                        startPixel = x + 2; // in the sprites, each char is separated by 2 pixels of gray
                        idxCount += 1;
                    }
                }
            }

            System.out.println("-------------------------------------");
            for (int x : xOfChars) {
                System.out.print(x + " ");
            }
            System.out.println();
            for (int x : widthOfChars) {
                System.out.print(x + " ");
            }
            System.out.println("\n-------------------------------------");
        }

    }

//    public void render(String text, int xPos, int yPos, Graphics2D g) {
//
//        int x = xPos;
//
//        for (int i = 0; i < text.length(); i++) {
//            int charI = FontRenderer.chars.indexOf(text.charAt(i));
//
//            // get scaled width and height
//            int scaledH = (int) this.height;
//            int scaledW = widthOfChars[charI] * scaledH / sprite.getHeight(null);
//
//            Image img = 
//            
//            Bitmap bmp = Bitmap.createBitmap(sprite, xOfChars[charI], 0, widthOfChars[charI], sprite.getHeight());
//            bmp = Bitmap.createScaledBitmap(bmp, scaledW, scaledH, false);
//
//            canvas.drawBitmap(bmp, x, yPos, null);
//
//            x += scaledW + charSpacing;
//        }
//
//    }
//
//    public int getStringWidth(String text) {
//        int out = 0;
//
//        for (int i = 0; i < text.length(); i++) {
//            int charI = XFont.chars.indexOf(text.charAt(i));
//
//            // get scaled width and height
//            int scaledH = (int) this.height;
//            int scaledW = widthOfChars[charI] * scaledH / sprite.getHeight();
//
//            out += scaledW + charSpacing;
//        }
//
//        return out;
//    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCharSpacing(double spacing) {
        this.charSpacing = spacing;
    }



}
