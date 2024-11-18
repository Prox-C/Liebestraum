package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boulder extends SuperObject{
	public OBJ_Boulder() {
		name = "Boulder";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/boulder.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
