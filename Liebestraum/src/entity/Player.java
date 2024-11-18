package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity	{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int silver_keys = 0;
	boolean hasPickaxe = false;
	
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
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up-1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up-2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down-1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down-2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right-1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right-2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left-1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left-2.png"));			
		}catch(IOException e){
			e.printStackTrace();
		}
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
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String objName = gp.obj[i].name;
			
			switch(objName) {
			case "Silver Key":
				silver_keys++;
				gp.playSE(2);
				gp.obj[i] = null;
				break;
			case "Pickaxe":
				hasPickaxe = true;
				gp.playSE(1);
				gp.obj[i] = null;
				break;
			case "Boulder":
				if(hasPickaxe != false) {
					gp.playSE(3);
					gp.obj[i] = null;
				}
				break;
			case "Speed Potion":
				speed += 2;
				gp.playSE(1);
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
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
	}
	
}
