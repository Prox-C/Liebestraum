package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_Fabron extends Entity {
	public NPC_Fabron(GamePanel gp) {
		super(gp);
		
		type = 1;
		direction = "down";
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
		up1 = setup("/npc/fabron-r1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/fabron-r2", gp.tileSize, gp.tileSize);

		down1 = setup("/npc/fabron-l1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/fabron-l2", gp.tileSize, gp.tileSize);

		right1 = setup("/npc/fabron-r1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/fabron-r2", gp.tileSize, gp.tileSize);

		left1 = setup("/npc/fabron-l1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/fabron-l2", gp.tileSize, gp.tileSize);
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
		dialog[0][0] = "Fabron: Greetings! I am Vincent Fabron, gatekeeper of the\nTrial Chamber.";
		dialog[0][1] = "Fabron: The floors below will manifest your inner conflicts,\nyou must be prepared.";
		dialog[0][2] = "Fabron: Many have sought to conquer these proving grounds\nbut only few have succeeed.";
		dialog[0][3] = "[ SYSTEM ] Quest: Descend to the lower floors.";


		
		dialog[1][0] = "Fabron: Higher and higher, we chase it.";
		dialog[2][0] = "Fabron: Pick up your weapon and face it.";
		dialog[3][0] = "Fabron: This is your moment.";
		dialog[4][0] = "Fabron: Now is your time.";




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
