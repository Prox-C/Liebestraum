package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{
	GamePanel gp;
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		name = "Pickaxe";
		boolean touchedBefore = false;
		down1 = setup("/object/chest");	
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
	}
	
}
