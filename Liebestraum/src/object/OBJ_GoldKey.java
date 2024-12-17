package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GoldKey extends Entity{
	boolean touchedBefore = false;
	GamePanel gp;
	public OBJ_GoldKey(GamePanel gp) {
		super(gp);
		name = "Gold Key";
		down1 = setup("/object/golden_key", gp.tileSize, gp.tileSize);
		
		solidArea.x = 10;
		solidArea.y = 14;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 20;
	}
}
