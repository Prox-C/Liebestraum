package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity	{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCancelled = false;
	
	public boolean armed = true;
	
	//EQUIPMENTS
	public int silver_keys = 0;
	public int pickaxeDurability = 0;
	public int axeDurability = 100;
	
	//MISIONS
	int greenSlimesKilled = 6;
	public boolean slimeQuest = false;
	
	public int stage = 2; //0
	public boolean questDone = false;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		//SPAWN POINT
//		worldX = gp.tileSize * 19;
//		worldY = gp.tileSize * 14; 
		
		worldX = gp.tileSize * 20;
		worldY = gp.tileSize * 15; 
 
		speed = 5;
		
		direction = "down";
		
		//PLAYER STATUS
		maxHealth = 6;
		life = maxHealth;
		
	}
	
	public void setDefaultPositions() {
		if(stage == 2) {
			worldX = gp.tileSize * 24;
			worldY = gp.tileSize * 15;
			gp.currentMap = 2;
		}
		else {
			worldX = gp.tileSize * 19;
			worldY = gp.tileSize * 14; 
		}
		
		direction = "down";
	}
	
	public void restoreHealth() {
		life = maxHealth;
		immune = false;
	}
	
	public void getPlayerImage() {
		up1 = setup("/player/up-1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/up-2", gp.tileSize, gp.tileSize);
		up3 = setup("/player/up-3", gp.tileSize, gp.tileSize);

		down1 = setup("/player/down-1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/down-2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/down-3", gp.tileSize, gp.tileSize);

		right1 = setup("/player/right-1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/right-2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/right-3", gp.tileSize, gp.tileSize);

		left1 = setup("/player/left-1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/left-2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/left-3", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage() {
		atk_up1 = setup("/player/attack-up-1", gp.tileSize, gp.tileSize*2);
		atk_up2 = setup("/player/attack-up-2", gp.tileSize, gp.tileSize*2);

		atk_down1 = setup("/player/attack-down-1", gp.tileSize, gp.tileSize*2);
		atk_down2 = setup("/player/attack-down-2", gp.tileSize, gp.tileSize*2);

		atk_right1 = setup("/player/attack-right-1", gp.tileSize*2, gp.tileSize);
		atk_right2 = setup("/player/attack-right-2", gp.tileSize*2, gp.tileSize);

		atk_left1 = setup("/player/attack-left-1", gp.tileSize*2, gp.tileSize);
		atk_left2 = setup("/player/attack-left-2", gp.tileSize*2, gp.tileSize);
	}
	
	public void update() {
		
		if(attacking == true) {
			 attack();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.rightPressed == true || keyH.leftPressed == true || keyH.enterPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.collChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.collChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION
			int npcIndex = gp.collChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//CHECK MOB COLLISION
			int mobIndex = gp.collChecker.checkEntity(this, gp.mob);
			mobContact(mobIndex);
			
			//CHECK EVENTS
			gp.eHandler.checkEvent();
			

			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false) {
				switch(direction){
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "right": worldX += speed; break;
					case "left": worldX -= speed; break;
				}
			}
			
			if(keyH.enterPressed == true && attackCancelled == false && armed) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			attackCancelled = false;
			gp.keyH.enterPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 2;
				}
				spriteCounter = 0;
			}
		}
		else {
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		if(immune == true) {
			immunityCounter++;
			if(immunityCounter > 60) {
				immune = false;
				immunityCounter = 0;
			}
		}
		
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1;
			gp.stopMusic(0);
			gp.playSE(11);
		}
	}
	
	public void attack() {
		spriteCounter++;
		if(spriteCounter <= 10) {
			spriteNum = 1;
		}
		if(spriteCounter > 10 && spriteCounter<= 15) {
			spriteNum = 2;
			//SAVE CURRENT POSITION
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//ATTACK AREA
			switch(direction){
				case "up":worldY -= attackArea.height; break;
				case "down":worldY += attackArea.height; break;
				case "right":worldX += attackArea.width; break;
				case "left":worldX -= attackArea.width; break;
			}
			//MODIFIED SOLID AREA
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//CHECK MOB COLLISION
			int mobIndex = gp.collChecker.checkEntity(this, gp.mob);
			attackMob(mobIndex);
			
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight; 
			
		}
		if(spriteCounter > 15) {
			spriteNum = 3;
			spriteCounter = 0;
			attacking = false;
		}
		
	}
	
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String objName = gp.obj[gp.currentMap][i].name;
			
			switch(objName) {
			case "Silver Key":
				silver_keys++;
				
				gp.ui.displayMessage("Key acquired", Color.GREEN);
				gp.playSE(4);
				try {
					gp.obj[gp.currentMap][i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/blank.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
				gp.obj[gp.currentMap][i] = null;
				break;
				
			case "Axe":
				if(silver_keys > 0) {
					if(gp.keyH.enterPressed) {
						if(gp.obj[gp.currentMap][i].touchedBefore == false) {
							silver_keys--;
							axeDurability = 100;
							gp.ui.displayMessage("Axe obtained", Color.GREEN);
							gp.playSE(4);
							try {gp.obj[gp.currentMap][i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/chest_open.png"));}
							catch(IOException e) {e.printStackTrace();}
							gp.obj[gp.currentMap][i].touchedBefore = true;
						}
					}	
				}
				if(silver_keys <= 0 && gp.obj[gp.currentMap][i].touchedBefore == false) {
					gp.ui.displayMessage("You need a key to open this chest.", Color.red);
				}
				break;
			case "Pickaxe":
				if(silver_keys > 0) {
					gp.ui.displayMessage("Press [enter] to open chest.", Color.WHITE);
					if(gp.keyH.enterPressed) {
						if(gp.obj[gp.currentMap][i].touchedBefore == false) {
							silver_keys--;
							pickaxeDurability = 100;
							gp.ui.displayMessage("Pickaxe obtained", Color.GREEN);
							gp.playSE(4);
							try {gp.obj[gp.currentMap][i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/chest_open.png"));}
							catch(IOException e) {e.printStackTrace();}
							gp.obj[gp.currentMap][i].touchedBefore = true;
						}
					}
				}
				if(silver_keys <= 0 && gp.obj[gp.currentMap][i].touchedBefore == false) {
					gp.ui.displayMessage("You need a key to open this chest.", Color.RED);
				}
				break;
				
			case "Tree":
				if(axeDurability != 0) {
					gp.ui.displayMessage("Press [enter] to remove bush.", Color.WHITE);
					if(gp.keyH.enterPressed) {
						attackCancelled = true;
						axeDurability -= 20;
						gp.playSE(3);
						try {gp.obj[gp.currentMap][i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/blank.png"));}
						catch(IOException e) {e.printStackTrace();} 
						gp.obj[gp.currentMap][i] = null;
					}
				}
				else {gp.ui.displayMessage("You need an Axe to remove this tree.", Color.RED);}
				break;
			case "Boulder":
				if(pickaxeDurability != 0) {
					gp.ui.displayMessage("Press [enter] to remove boulder.", Color.WHITE);
					if(gp.keyH.enterPressed) {
						attackCancelled = true;
						pickaxeDurability -= 20;
						gp.playSE(3);
						try {gp.obj[gp.currentMap][i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/blank.png"));}
						catch(IOException e) {e.printStackTrace();}
						gp.obj[gp.currentMap][i] = null;
					}
				}
				else {gp.ui.displayMessage("You need a Pickaxe to remove this boulder.", Color.RED);}
				break;
				
			case "Sign":
				gp.ui.displayMessage("Memory Lane", Color.WHITE);
				break;
			case "Void":
				if(stage < 1) {gp.ui.displayMessage("Complete the quest to proceed.", Color.YELLOW);}
				else {gp.obj[gp.currentMap][i] = null;}
				break;
			case "Void2":
				if(stage < 2) {gp.ui.displayMessage("Complete the quest to proceed.", Color.YELLOW);}
				else {gp.obj[gp.currentMap][i] = null;}
				break;
			case "Void3":
				if(stage < 3) {gp.ui.displayMessage("Complete the quest to proceed.", Color.YELLOW);}
				else {gp.obj[gp.currentMap][i] = null;}
				break;
				
			case "Sword":
				gp.playSE(4);
				armed = true;
				stage++;
				gp.obj[gp.currentMap][i] = null;
				break;
				
			case "Speed Potion":
				gp.playSE(4);
				gp.obj[gp.currentMap][i] = null;
				break;
				
//			case "Gold Key":
//				gp.ui.displayMessage("Gold Key Acquired");
//				gp.playSE(4);
//				gp.ui.gameFinished = true;
//				gp.stopMusic(0);
//				gp.playSE(5);
//				gp.obj[i] = null;
//				break;
			}
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			gp.ui.displayMessage("Press [enter] to talk.", Color.WHITE);
			if(gp.keyH.enterPressed) {
				attackCancelled = true;
				gp.npc[gp.currentMap][i].speak();
			}
		}
		
//		if(gp.keyH.enterPressed == true) {
//			if(i != 999) {
//				
//				attackCancelled = true;
//				gp.npc[gp.currentMap][i].speak();
//			}
//		}
	}
	
	public void mobContact(int i) {
		if(i != 999) {
			if(immune == false && gp.mob[gp.currentMap][i].dying == false) {
				gp.playSE(9);
				life -= 1;
				immune = true;
			}
		}
	}
	
	public void attackMob(int i) {
		if(i != 999) {
			if(gp.mob[gp.currentMap][i].immune == false) {
				gp.playSE(8);
				gp.mob[gp.currentMap][i].life -= 1;
				gp.mob[gp.currentMap][i].immune = true;
				gp.mob[gp.currentMap][i].flea();
				
				if(gp.mob[gp.currentMap][i].life < 0) {
					gp.mob[gp.currentMap][i].speed = 0;
					gp.mob[gp.currentMap][i].dying = true;
					if(gp.mob[gp.currentMap][i].mobID == 0 && greenSlimesKilled < 7) {
						greenSlimesKilled++;
						gp.ui.displayMessage("Biohazard Slime defeated. ("+greenSlimesKilled+"/7)", Color.white);
						if(greenSlimesKilled == 7) {
							gp.ui.displayMessage("Quest done!", Color.green);
							stage++;
							slimeQuest = true;
						}
					}
				}
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.red);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); 
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
			}
			if(attacking == true) {
				tempScreenY= screenY - gp.tileSize;
				if(spriteNum == 1) {image = atk_up1;}
				if(spriteNum == 2) {image = atk_up2;}
				if(spriteNum == 3) {image = atk_up3;}

			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = atk_down1;}
				if(spriteNum == 2) {image = atk_down2;}
				if(spriteNum == 3) {image = atk_down3;}

			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = atk_right1;}
				if(spriteNum == 2) {image = atk_right2;}
				if(spriteNum == 3) {image = atk_right3;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = atk_left1;}
				if(spriteNum == 2) {image = atk_left2;}
				if(spriteNum == 3) {image = atk_left3;}
			}
			break;
		}
		
		if(immune == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		//FOR DEBUG
//		g2.setColor(Color.blue);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
		
	}
	
}
