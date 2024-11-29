package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SpeedPotion extends Entity{
	GamePanel gp;
	public OBJ_SpeedPotion(GamePanel gp) {
		super(gp);
		name = "Speed Potion";
		down1 = setup("/object/chest");	
		boolean touchedBefore = false;
		collision = true;
		
	}
}
