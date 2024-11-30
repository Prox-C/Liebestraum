package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	boolean touchedBefore = false;
	GamePanel gp;
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = "Silver Key";
		down1 = setup("/object/silver_key", gp.tileSize, gp.tileSize);
		
		solidArea.x = 10;
		solidArea.y = 14;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 20;
	}
}
