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
import main.UtilityTool;

public class Player extends Entity	{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public int silver_keys = 0;
	public int pickaxeDurability = 0;
	
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
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 11;
		speed = 3;
		
		direction = "down";
		
		//PLAYER STATUS
		maxHealth = 10;
		life = maxHealth;
	}
	
	public void getPlayerImage() {
		up1 = setup("/player/up-1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/up-2", gp.tileSize, gp.tileSize);

		down1 = setup("/player/down-1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/down-2", gp.tileSize, gp.tileSize);

		right1 = setup("/player/right-1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/right-2", gp.tileSize, gp.tileSize);

		left1 = setup("/player/left-1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/left-2", gp.tileSize, gp.tileSize);
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
			
			gp.keyH.enterPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
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
	}
	
	public void attack() {
		spriteCounter++;
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter<= 25) {
			spriteNum = 2;
		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
		
	}
	
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String objName = gp.obj[i].name;
			
			switch(objName) {
			case "Silver Key":
				silver_keys++;
				
				gp.ui.displayMessage("Key acquired", Color.GREEN);
				gp.playSE(4);
				try {
					gp.obj[i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/blank.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
				gp.obj[i] = null;
				break;
				
			case "Pickaxe":
				if(silver_keys > 0) {
					if(gp.obj[i].touchedBefore == false) {
						silver_keys--;
						pickaxeDurability = 100;
						gp.ui.displayMessage("Pickaxe obtained", Color.GREEN);
						gp.playSE(4);
						try {
							gp.obj[i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/chest_open.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						gp.obj[i].touchedBefore = true;
					}
				}
				if(silver_keys <= 0 && gp.obj[i].touchedBefore == false) {
					gp.ui.displayMessage("You need a key to open this chest.", Color.RED);
				}
				break;
				
			case "Boulder":
				if(pickaxeDurability != 0) {
					pickaxeDurability -= 20;
					gp.ui.displayMessage("Obstacle cleared", Color.GREEN);
					gp.playSE(3);
					try {
						gp.obj[i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/blank.png"));
					}catch(IOException e) {
						e.printStackTrace();
					}
					gp.obj[i] = null;
				}
				else {
					gp.ui.displayMessage("You need a Pickaxe to remove this boulder.", Color.RED);
				}
				break;
				
			case "Speed Potion":
				if(silver_keys > 0) {
					if(gp.obj[i].touchedBefore == false) {
						gp.ui.displayMessage("+2 Movement Speed", Color.GREEN);
						silver_keys--;
						speed += 2;
						gp.playSE(1);
						try {
							gp.obj[i].down1 = ImageIO.read(getClass().getResourceAsStream("/object/chest_open.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						gp.obj[i].touchedBefore = true;
					}
				}
				if(silver_keys <= 0 && gp.obj[i].touchedBefore == false) {
					gp.ui.displayMessage("You need a key to open this chest.", Color.RED);
				}
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
		if(gp.keyH.enterPressed == true) {
			if(i != 999) {
				gp.gameState = gp.dialogState;
				gp.npc[i].speak();
			}
			else {
				attacking = true;
			}
		}
	}
	
	public void mobContact(int i) {
		if(i != 999) {
			if(immune == false) {
				life -= 1;
				immune = true;
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
			}
			if(attacking == true) {
				tempScreenY= screenY - gp.tileSize;
				if(spriteNum == 1) {image = atk_up1;}
				if(spriteNum == 2) {image = atk_up2;}
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = atk_down1;}
				if(spriteNum == 2) {image = atk_down2;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = atk_right1;}
				if(spriteNum == 2) {image = atk_right2;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = atk_left1;}
				if(spriteNum == 2) {image = atk_left2;}
			}
			break;
		}
		
		if(immune == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//		g2.setColor(Color.blue);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
		
	}
	
}
