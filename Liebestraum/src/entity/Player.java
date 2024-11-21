package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity	{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public int silver_keys = 0;
	public int pickaxeDurability = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 3;
		
		direction = "down";
	}
	
	public void getPlayerImage() {
		up1 = setup("up-1");
		up2 = setup("up-2");

		down1 = setup("down-1");
		down2 = setup("down-2");

		right1 = setup("right-1");
		right2 = setup("right-2");

		left1 = setup("left-1");
		left2 = setup("left-2");
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.rightPressed == true || keyH.leftPressed == true) {
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
			
			int objIndex = gp.collChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction){
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "right": worldX += speed; break;
					case "left": worldX -= speed; break;
				}
			}
			
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
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String objName = gp.obj[i].name;
			
			switch(objName) {
			case "Silver Key":
				silver_keys++;
				gp.ui.displayMessage("Silver key obtained");
				gp.playSE(4);
				gp.obj[i] = null;
				break;
				
			case "Pickaxe":
				if(silver_keys > 0) {
					silver_keys--;
					if(gp.obj[i].touchedBefore == false) {
						pickaxeDurability = 100;
						gp.ui.displayMessage("Stone Pickaxe obtained");
						gp.playSE(4);
						try {
							gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/object/chest_open.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						gp.obj[i].touchedBefore = true;
					}
				}
				if(silver_keys <= 0 && gp.obj[i].touchedBefore == false) {
					gp.ui.displayMessage("You need a silver key to open this chest.");
				}
				break;
				
			case "Boulder":
				if(pickaxeDurability != 0) {
					pickaxeDurability -= 20;
					gp.ui.displayMessage("Obstacle Cleared");
					gp.playSE(3);
					gp.obj[i] = null;
				}
				else {
					gp.ui.displayMessage("You need a Pickaxe!");
				}
				break;
				
			case "Speed Potion":
				if(silver_keys > 0) {
					silver_keys--;
					if(gp.obj[i].touchedBefore == false) {
						gp.ui.displayMessage("+2 Movement Speed");
						speed += 2;
						gp.playSE(1);
						try {
							gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/object/chest_open.png"));
						}catch(IOException e) {
							e.printStackTrace();
						}
						gp.obj[i].touchedBefore = true;
					}
				}
				if(silver_keys <= 0 && gp.obj[i].touchedBefore == false) {
					gp.ui.displayMessage("You need a silver key to open this chest.");
				}
				break;
				
			case "Gold Key":
				gp.ui.displayMessage("Gold Key Acquired");
				gp.playSE(4);
				gp.ui.gameFinished = true;
				gp.stopMusic(0);
				gp.playSE(5);
				gp.obj[i] = null;
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.red);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); 
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
		
		
	}
	
}
