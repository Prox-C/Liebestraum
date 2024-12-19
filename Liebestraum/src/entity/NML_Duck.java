package entity;

import java.util.Random;
import main.GamePanel;


public class NML_Duck extends Entity {
	public NML_Duck(GamePanel gp) {
		super(gp);
		
		type = 3;
		direction = "left";
		speed = 1;
		
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/animal/duck_l1", gp.tileSize, gp.tileSize);
		up2 = setup("/animal/duck_l2", gp.tileSize, gp.tileSize);

		down1 = setup("/animal/duck_r1", gp.tileSize, gp.tileSize);
		down2 = setup("/animal/duck_r2", gp.tileSize, gp.tileSize);

		right1 = setup("/animal/duck_l1", gp.tileSize, gp.tileSize);
		right2 = setup("/animal/duck_l2", gp.tileSize, gp.tileSize);

		left1 = setup("/animal/duck_r1", gp.tileSize, gp.tileSize);
		left2 = setup("/animal/duck_r2", gp.tileSize, gp.tileSize);
	}
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 60) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "left";
			}
			if(i > 25 && i <= 50) {
				direction = "left";
			}
			if(i > 50 && i <= 75) {
				direction = "right";
			}
			if(i > 75) {
				direction = "right";
			}
			actionLockCounter = 0;
			
		}
		
	}
}
