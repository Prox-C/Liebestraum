package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_Kulot extends Entity {
	public NPC_Kulot(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/player/up-1");
		up2 = setup("/player/up-2");

		down1 = setup("/player/down-1");
		down2 = setup("/player/down-2");

		right1 = setup("/player/right-1");
		right2 = setup("/player/right-2");

		left1 = setup("/player/left-1");
		left2 = setup("/player/left-2");
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
	
}
