package GraphicsStuff;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class m_Panel extends JPanel{

	Image image;
	public m_Panel() {
		image=new ImageIcon("src/GraphicsStuff/img1.jpg").getImage();
		this.setPreferredSize(new Dimension(500,500));
		
		
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(image, 0, 0,null);
//		g2d.drawLine(0,0, 500, 500);
	}

}
