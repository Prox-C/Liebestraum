package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Leo extends Entity{
	GamePanel gp;
	public OBJ_Leo(GamePanel gp) {
		super(gp);
		name = "Leo";
		down1 = setup("/object/leo", gp.tileSize, gp.tileSize);	
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
	}
	
}
