package object;

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
	}
}
