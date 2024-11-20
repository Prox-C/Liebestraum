package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_SpeedPotion extends SuperObject{
	GamePanel gp;
	public OBJ_SpeedPotion(GamePanel gp) {
		this.gp = gp;
		name = "Speed Potion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/speed_potion.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
