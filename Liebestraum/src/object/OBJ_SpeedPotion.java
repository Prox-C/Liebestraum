package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SpeedPotion extends Entity{
	GamePanel gp;
	public OBJ_SpeedPotion(GamePanel gp) {
		super(gp);
		name = "Speed Potion";
		down1 = setup("/object/speed_potion", gp.tileSize, gp.tileSize);	
		boolean touchedBefore = false;
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
	}
}
