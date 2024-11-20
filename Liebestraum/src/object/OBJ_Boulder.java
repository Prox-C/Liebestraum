package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boulder extends SuperObject{
	GamePanel gp;
	public OBJ_Boulder(GamePanel gp) {
		this.gp = gp;
		name = "Boulder";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/boulder.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
