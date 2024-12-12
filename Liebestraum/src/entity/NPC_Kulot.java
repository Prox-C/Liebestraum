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
		
		type = 1;
		direction = "left";
		speed = 0;
		
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		dialogSet = -1;
		
		getImage();
		setDialog();
	}
	
	public void getImage() {
		up1 = setup("/npc/walk-1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/walk-2", gp.tileSize, gp.tileSize);

		down1 = setup("/npc/walk-3", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/walk-4", gp.tileSize, gp.tileSize);

		right1 = setup("/npc/walk-1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/walk-2", gp.tileSize, gp.tileSize);

		left1 = setup("/npc/walk-3", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/walk-4", gp.tileSize, gp.tileSize);
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
				direction = "left";
			}
			if(i > 75) {
				direction = "left";
			}
			actionLockCounter = 0;
			
		}
		
	}
	
	public void setDialog() {
		dialog[0][0] = "Kulot: Greetings, traveler!";
		dialog[0][1] = "Kulot: So you don't recall anything huh?";
		dialog[0][2] = "Kulot: You must be one of those that came  from outside of\nHeartland!";
		
		dialog[1][0] = "Kulot: There's a road down these woods called 'Memory Lane'";
		dialog[1][1] = "Kulot: I'm sure you'll find something there that will help you with\nyour journey.";
		dialog[1][2] = "Kulot: Good luck!";

		
	}
	public void speak() {
		facePlayer();
		startDialog(this, dialogSet);
		
		dialogSet++;
		
		if(dialog[dialogSet][0] == null) {
			dialogSet = 0;
		}
	}
	
}
