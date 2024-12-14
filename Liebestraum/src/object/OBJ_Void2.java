package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Void2 extends Entity{
	GamePanel gp;
	
	public OBJ_Void2(GamePanel gp) {
		super(gp);
		name = "Void2";
		down1 = setup("/object/blank", gp.tileSize, gp.tileSize);	
		boolean touchedBefore = false;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
	}
}
