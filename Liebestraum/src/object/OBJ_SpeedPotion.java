package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SpeedPotion extends SuperObject{
	public OBJ_SpeedPotion() {
		name = "Speed Potion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/speed_potion.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
