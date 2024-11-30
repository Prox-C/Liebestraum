package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		
		image = setup("/object/heart_full", gp.tileSize, gp.tileSize);
		image2 = setup("/object/heart_half", gp.tileSize, gp.tileSize);
		image3 = setup("/object/heart_empty", gp.tileSize, gp.tileSize);
	}
}
