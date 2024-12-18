package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealPotion extends Entity{
	GamePanel gp;
	public OBJ_HealPotion(GamePanel gp) {
		super(gp);
		name = "Heal Potion";
		down1 = setup("/object/healing_vial", gp.tileSize, gp.tileSize);	
		collision = false;
		
		solidArea.x = 8;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
	}
}
