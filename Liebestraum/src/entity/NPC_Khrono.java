package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_Khrono extends Entity {
	public NPC_Khrono(GamePanel gp) {
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
		up1 = setup("/npc/khrono-r1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/khrono-r2", gp.tileSize, gp.tileSize);

		down1 = setup("/npc/khrono-l1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/khrono-l2", gp.tileSize, gp.tileSize);

		right1 = setup("/npc/khrono-r1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/khrono-r2", gp.tileSize, gp.tileSize);

		left1 = setup("/npc/khrono-l1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/khrono-l2", gp.tileSize, gp.tileSize);
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
		dialog[0][0] = "Khrono: I am Khrono, he who knows all!";
		dialog[0][1] = "Khrono: Many have travelled far to see me in search of answers.";
		dialog[0][2] = "Khrono: Now, what is it that you seek, boy?";
		dialog[0][3] = "( You explain your situation to Khrono. )";
		dialog[0][4] = "Khrono: I see . . . It seems that you don't even know what you are searching for.";
		dialog[0][5] = "Khrono: You are in Heartland, a place where everything is driven by desires.";
		dialog[0][6] = "Khrono: In order to find what you're looking for, you must venture into the trial chambers.";
		dialog[0][7] = "Khrono: Take the road south. But beware, for the trial chambers extracts a heavy price.";
		dialog[0][8] = "[ SYSTEM ] Quest: Go to the trial chambers.";
		
		dialog[1][0] = "Khrono: When will you ever realize . . .";
		dialog[2][0] = "Khrono: Everything is not as it seems . . .";
		dialog[3][0] = "Khrono: Wake up . . .";



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
