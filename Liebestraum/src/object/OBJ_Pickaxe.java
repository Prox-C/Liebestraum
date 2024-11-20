package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Pickaxe extends SuperObject{
	GamePanel gp;
	public OBJ_Pickaxe(GamePanel gp) {
		this.gp = gp;
		name = "Pickaxe";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/pickaxe.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
