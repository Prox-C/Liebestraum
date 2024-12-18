package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StrBuff extends Entity{
	GamePanel gp;
	public OBJ_StrBuff(GamePanel gp) {
		super(gp);
		name = "Strength Buff";
		down1 = setup("/object/strength_potion", gp.tileSize, gp.tileSize);	
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
	}
}
