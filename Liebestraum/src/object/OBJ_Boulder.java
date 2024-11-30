package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boulder extends Entity{
	GamePanel gp;
	
	public OBJ_Boulder(GamePanel gp) {
		super(gp);
		name = "Boulder";
		down1 = setup("/object/boulder", gp.tileSize, gp.tileSize);	
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
