package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_Yuji extends Entity {
	public NPC_Yuji(GamePanel gp) {
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
		up1 = setup("/npc/yuji-r1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/yuji-r2", gp.tileSize, gp.tileSize);

		down1 = setup("/npc/yuji-l1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/yuji-l2", gp.tileSize, gp.tileSize);

		right1 = setup("/npc/yuji-r1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/yuji-r2", gp.tileSize, gp.tileSize);

		left1 = setup("/npc/yuji-l1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/yuji-l2", gp.tileSize, gp.tileSize);
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
		dialog[0][0] = "Yuji: Stop right there! I;m a patrol captain and this area\nhas been marked unsafe.";
		dialog[0][1] = "Yuji: There has been a recent surge of biohazard slimes in\nRavalon.";
		dialog[0][2] = "Yuji: Those puny slimes may not be a threat to me, but they\ncan do serious harm to you especially when you're unarmed.";
		dialog[0][3] = "Yuji: Old weaponry are scattered in this place. Rummage\nthrough those pot holes and get yourself some equipment.";
		dialog[0][4] = "Yuji: There are even rumors that the X-Calibur, the all-powerful\nlegendary sword is hidden in these very grounds.";
		dialog[0][5] = "Yuji: Anyways, I must return to my post. At ease, soldier!";
		dialog[0][6] = "[ SYSTEM ] Quest: Look for equipment.";


		dialog[1][0] = "Yuji: Many have lost their way in this place.";
		dialog[2][0] = "Yuji: Damned monsters, I will hunt them all!";
		dialog[3][0] = "Yuji: Pot holes are full of surprises.";
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
