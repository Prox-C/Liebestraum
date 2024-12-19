package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_Ligaya extends Entity {
	public NPC_Ligaya(GamePanel gp) {
		super(gp);
		
		type = 1;
		direction = "left";
		speed = 1;
		
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
		up1 = setup("/npc/ligaya-r1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/ligaya-r2", gp.tileSize, gp.tileSize);

		down1 = setup("/npc/ligaya-l1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/ligaya-l2", gp.tileSize, gp.tileSize);

		right1 = setup("/npc/ligaya-r1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/ligaya-r2", gp.tileSize, gp.tileSize);

		left1 = setup("/npc/ligaya-l1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/ligaya-l2", gp.tileSize, gp.tileSize);
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
				direction = "left";
			}
			if(i > 75) {
				direction = "right";
			}
			actionLockCounter = 0;
			
		}
		
	}
	
	public void setDialog() {
		dialog[0][0] = "Ligaya: You've wandered far, haven't you? The world is\nstrange when you can’t remember your past.";
		dialog[0][1] = "Ligaya: It's like you're chasing shadows. But perhaps\nthat's how we all begin... searching for who we were.";
		dialog[0][2] = "Ligaya: Don't worry. The answers you seek are within\nyour reach, even if they seem hidden.";
		dialog[0][3] = "Ligaya: There's a path ahead, one where the truth awaits.\nFollow it, and you might find your way back.";
		dialog[0][4] = "Ligaya: But be warned, the journey won't be easy. Only by\nfacing your own fears will you find what's lost.";
		dialog[0][5] = "Ligaya: Remember this, Bogart, the road to understanding is\nnever straight. It winds, and often it turns in unexpected ways.";
		dialog[0][6] = "( You have been reunited with Ligaya and decided to stay\nin Heartland.)";
		dialog[0][7] = "[ SYSTEM ] The end.";
		


		dialog[1][0] = "Ligaya: Some say the key to the past is a single moment,\na fleeting glance that we often overlook.";
		dialog[2][0] = "Ligaya: Time doesn't wait for anyone, but it can guide\nthose willing to listen to its whispers.";
		dialog[3][0] = "Ligaya: The truth is like the moon. Sometimes, it's\nhidden, but it always returns to the light.";
		dialog[4][0] = "Ligaya: You must trust the journey. Even if you can’t see\nthe destination, each step forward brings you closer to who you are meant to be.";
		dialog[5][0] = "Ligaya: Don’t rush to forget what was lost. The past has\nits lessons, even if they are painful.";
	}
	public void speak() {
		facePlayer();
		startDialog(this, dialogSet);
		
		dialogSet++;
		
		if(dialog[dialogSet][0] == null) {
			dialogSet--;
		}
	}
	
}
