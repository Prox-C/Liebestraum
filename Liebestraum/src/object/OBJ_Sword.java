package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity{
	boolean touchedBefore = false;
	GamePanel gp;
	public OBJ_Sword(GamePanel gp) {
		super(gp);
		name = "Sword";
		down1 = setup("/object/sword", gp.tileSize, gp.tileSize);
		
		solidArea.x = 10;
		solidArea.y = 14;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 20;
	}
}
