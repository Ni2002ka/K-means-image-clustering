import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IMG {

	BufferedImage inputIMG;
	private final int H;
	private final int W;
	private final int size;
	public IMG(File file) throws IOException {
		inputIMG=ImageIO.read(file);
		W=inputIMG.getWidth();
		H=inputIMG.getHeight();
		size=W*H;
	}
	
	int[] getPixelColors(int x, int y) {
		int clr = inputIMG.getRGB(x,y);
		int alpha=(clr>>24) & 0xff;
	    int red = (clr>>16) & 0xff;
	    int green = (clr>>8) & 0xff;
	    int blue = clr & 0xff;
	    
	    int[] color={alpha,red, green, blue};
	    return color;
	}

	int [][] getColorVectors (){
		int colorVectors[][]= new int [size][4];
		for (int i=0;i<size; i++) {
			colorVectors[i]=getPixelColors(i%W, i/W);
		}
		
		return colorVectors;
	}
	
	public void getClusteringResults (int [] pixels) throws IOException {
		inputIMG.setRGB(0, 0, W, H, pixels, 0, W);
		File file=new File("src/GraphicsStuff/img1out.jpg");
		ImageIO.write(inputIMG, "jpg", file);
		
	}
	
	public int[] rgbaToPixels(int [][] rgbaVectors, int a_size) {
		int[] pixels=new int[a_size];
		
		for (int i=0;i<a_size;i++) {
			pixels[i]=0;
			pixels[i]=(rgbaVectors[i][0]<<24 |rgbaVectors[i][1]<<16 |rgbaVectors[i][2]<<8 |rgbaVectors[i][3] );
		}
		
		return pixels;
	}
	
	public int getSize() {return size;}
}
