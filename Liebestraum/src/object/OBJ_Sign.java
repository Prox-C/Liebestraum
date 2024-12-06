package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sign extends Entity{
	GamePanel gp;
	
	public OBJ_Sign(GamePanel gp) {
		super(gp);
		name = "Sign";
		down1 = setup("/object/sign", gp.tileSize, gp.tileSize);	
	
		solidArea.x = 20;
		solidArea.y = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 10;
		solidArea.height = 10;
	}
}
