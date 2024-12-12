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
		dialog[0][0] = "Kulot: You seem lost, boy.";
		dialog[0][1] = "Kulot: Strange. You don't remember a thing huh?";
		dialog[0][2] = "Kulot: Theres a road down these woods called 'Memory Lane'";
		dialog[0][3] = "Kulot: Take it as it will lead you to an Inn Keeper named Leo.";
		dialog[0][4] = "Kulot: He knows a lot about 'this' place. I'm sure he can help you.";

		dialog[1][0] = "Kulot: Sometimes, we don't know what we have till it's gone.";
		dialog[2][0] = "Kulot: Cherish every moment.";
		dialog[3][0] = "Kulot: Live life to the fullest!";
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
