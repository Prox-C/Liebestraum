package mob;

import java.util.Random;

import entity.Entity;
import main.GamePanel;


public class MOB_GreenSlime extends Entity{
	GamePanel gp;
	public MOB_GreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Green Slime";
		speed = 1;
		type = 2;
		maxHealth = 5;
		life = maxHealth;
		solidArea.x = 9;
		solidArea.y = 8;
		solidArea.width = 28;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/mob/greenslime-3", gp.tileSize, gp.tileSize);
		up2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
		down1 = setup("/mob/greenslime-3", gp.tileSize, gp.tileSize);
		down2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
		right1 = setup("/mob/greenslime-1", gp.tileSize, gp.tileSize);
		right2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
		left1 = setup("/mob/greenslime-1", gp.tileSize, gp.tileSize);
		left2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 60) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "right";
			}
			if(i > 75) {
				direction = "left";
			}
			actionLockCounter = 0;
			
		}
	}
	
	public void flea() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}
