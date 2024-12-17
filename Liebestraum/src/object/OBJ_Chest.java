package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	GamePanel gp;
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name = "";
		boolean touchedBefore = false;
		down1 = setup("/object/chest", gp.tileSize, gp.tileSize);	
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
	}
	
}
