package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_GoldKey extends SuperObject{
	public OBJ_GoldKey() {
		name = "Gold Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/golden_key.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
