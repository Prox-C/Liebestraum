package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
	public BufferedImage atk_up1, atk_up2, atk_up3, atk_down1, atk_down2, atk_down3, atk_right1, atk_right2, atk_right3, atk_left1, atk_left2, atk_left3;
	
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0, 0, 30, 30); 
	public Rectangle attackArea = new Rectangle(0, 0, 0 ,0);
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	public boolean collisionOn = false; 
	public int actionLockCounter = 0;
	
	String dialog[] = new String[20]; 
	int dialogIndex = 0;public int life;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public boolean touchedBefore = false;
	public boolean immune = false;
	public int immunityCounter = 0;
	public int type; // 1 = NPC, 2 = MOB
	public boolean attacking = false;
	
	//CHARACHTER STATUS
	public int maxHealth;
	public int health;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		 
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
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
			
//			FOR OBJECT COLLISION AREA DEBUGGING
	 	 	
//			g2.setColor(Color.red);
//			g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			
	}
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+ ".png"));
			image = uTool.scaleImage(image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void setAction() {}
	public void speak() {
		if(dialog[dialogIndex] == null) {
			dialogIndex = 0;
		}
		gp.ui.currentDialog = dialog[dialogIndex];
		dialogIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.collChecker.checkTile(this);
		gp.collChecker.checkObject(this, false);
		gp.collChecker.checkEntity(this, gp.mob); 
		gp.collChecker.checkEntity(this, gp.npc); 
		boolean playerContact = gp.collChecker.checkPlayer(this);
		
		if(this.type == 2 && playerContact == true) {
			if(gp.player.immune == false) {
				gp.player.life -= 1;
				gp.player.immune = true;
			}
		}
		
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

}
