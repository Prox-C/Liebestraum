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
		dialog[0][2] = "Leo: The mind truly is mysterious. You're in luck tho, 'cause I have\na super sercret ancient magic garden!";
		dialog[0][3] = "Leo: I remember how it rid most everything that ails my guests.\nIt even healed a fatally wounded man once!";
		dialog[0][4] = "Leo: But I haven't been there in a long while now. You may\ncheck it out for yourself.";
		dialog[0][5] = "[ SYSTEM ] Quest: Visit Leo's garden and investigate the area. ";

		dialog[1][0] = "Leo: Leo's lodge is the best place to stay in all of Heartland!";
		dialog[2][0] = "Leo: Even if the mind forgets, the heart always remembers.";
		dialog[3][0] = "Leo: This forest holds many secrets . . .";
		
		dialog[6][0] = "Leo: Ligaya you say? Sorry, it doesn't ring a bell.";
		dialog[6][1] = "Leo: I keep a record of those who visit this lodge. Unfortunately,\nit doesn't seem like we've had a Ligaya in here.";
		dialog[6][2] = "( You frown as he informs you they have no history with anyone\nnamed Ligaya. )";
		dialog[6][3] = "Leo: Do not fret, young man. I may not no anyone by that name,\nbut I DO know someone who might!";
		dialog[6][4] = "Leo: There's an old wise man by the name of Khrono, he who\nknows all.";
		dialog[6][5] = "Leo: He knows EVERYTHING about Heartland! He might even\nhave the answers you are searching for. ";
		dialog[6][6] = "Leo: My patrons say that he often wanders around the raging\nrivers of Ravalon.";
		dialog[6][7] = "Leo: Take these supplies and go on with your journey. I wish\nyou good luck!";
		dialog[6][8] = "[ SYSTEM ] Quest: Go to the Ravalon district. ";

		dialog[4][0] = "Leo: Come back again!";	
	}
	public void speak() {
		facePlayer();
		startDialog(this, dialogSet);
		dialogSet++;
		
		if(dialog[dialogSet][0] == null) {
			dialogSet = 1;
		}
		if(gp.player.questDone) {
			dialogSet = 6;
			gp.player.stage++;
			gp.player.questDone = false;
		}
	}
	
}
