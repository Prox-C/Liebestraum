package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
		this.gp = gp;
		name = "Silver Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/object/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/object/heart_empty.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(gp.tileSize / 2 - 16, gp.tileSize / 2 - 16, 32, 32);
	    this.solidAreaDefaultX = gp.tileSize / 2 - 16; // Set default X position for collision area
	    this.solidAreaDefaultY = gp.tileSize / 2 - 16; // Set default Y position for collision area
	}
}
