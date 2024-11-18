package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Pickaxe extends SuperObject{
	public OBJ_Pickaxe() {
		name = "Pickaxe";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/pickaxe.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
