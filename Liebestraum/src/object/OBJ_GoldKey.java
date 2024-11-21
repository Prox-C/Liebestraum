package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_GoldKey extends SuperObject{
	GamePanel gp;
	public OBJ_GoldKey(GamePanel gp) {
		this.gp = gp;
		name = "Gold Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/golden_key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(gp.tileSize / 2 - 16, gp.tileSize / 2 - 16, 32, 32);
	    this.solidAreaDefaultX = gp.tileSize / 2 - 16; // Set default X position for collision area
	    this.solidAreaDefaultY = gp.tileSize / 2 - 16; // Set default Y position for collision area
	}
}
