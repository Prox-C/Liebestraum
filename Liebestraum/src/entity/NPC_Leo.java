package entity;

import java.util.Random;
import main.GamePanel;


public class NPC_Leo extends Entity {
	public NPC_Leo(GamePanel gp) {
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
		up1 = setup("/npc/leo-r1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/leo-r2", gp.tileSize, gp.tileSize);

		down1 = setup("/npc/leo-l1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/leo-l2", gp.tileSize, gp.tileSize);

		right1 = setup("/npc/leo-r1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/leo-r2", gp.tileSize, gp.tileSize);

		left1 = setup("/npc/leo-l1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/leo-l2", gp.tileSize, gp.tileSize);
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
		dialog[0][0] = "Leo: Welcome to my lodge, the best place to stay in all of\nHeartland! How can I help you?";
		dialog[0][1] = "Leo: I see, so you somehow lost your memories huh?";
		dialog[0][2] = "Leo: The mind truly is mysterious. You're in luck tho, 'cause\nI have an ancient secret magic garden!";
		dialog[0][3] = "Leo: I remember how it rid most everything that ails my guests.\nIt even healed a fatally wounded man once!";
		dialog[0][4] = "Leo: But I haven't been there in a long while now. You may\ncheck it out for yourself.";
		dialog[0][5] = "[ SYSTEM ] Quest: Visit Leo's garden and investigate the area. ";

		dialog[1][0] = "Leo's lodge is the best place to stay in all of Heartland!";
		dialog[2][0] = "Even if the mind forgets, the heart always remembers.";
		dialog[3][0] = "This forest holds many secrets . . .";

		
	}
	public void speak() {
		facePlayer();
		startDialog(this, dialogSet);
		
		dialogSet++;
		
		if(dialog[dialogSet][0] == null) {
			dialogSet = 1;
		}
	}
	
}
