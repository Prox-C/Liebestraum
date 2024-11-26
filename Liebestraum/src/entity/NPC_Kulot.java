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
		setDialog();
	}
	
	public void getImage() {
		up1 = setup("/npc/walk-1");
		up2 = setup("/npc/walk-2");

		down1 = setup("/npc/walk-3");
		down2 = setup("/npc/walk-4");

		right1 = setup("/npc/walk-1");
		right2 = setup("/npc/walk-2");

		left1 = setup("/npc/walk-3");
		left2 = setup("/npc/walk-4");
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
	
	public void setDialog() {
		dialog[0] = "You are not of this world are you?\nI have never seen you before.";
		dialog[1] = "So you're one of the seekers that came outside of Neverland!";
		dialog[2] = "Now tell me, seeker . . .";
		dialog[3] = "What is it that you seek?";
		
	}
	public void speak() {
		super.speak();
	}
	
}
