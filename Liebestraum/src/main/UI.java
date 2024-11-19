package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;
import object.OBJ_Pickaxe;

public class UI {

	GamePanel gp;
	Font stat_font;
	BufferedImage pickaxeIcon;
	BufferedImage silverkeyIcon;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;

	public UI(GamePanel gp) {
		this.gp = gp;
		stat_font = new Font("Source Pro", Font.PLAIN, 18);
		
		OBJ_Pickaxe pickaxe = new OBJ_Pickaxe();
		pickaxeIcon = pickaxe.image;
		
		OBJ_Key key = new OBJ_Key();
		silverkeyIcon = key.image;
		
	}
	
	public void displayMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(stat_font);
		g2.setColor(Color.white);
//		g2.drawImage(pickaxeIcon, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//		g2.drawString("DUR = " + gp.player.pickaxeDurability + "%", 100, 50);
		
		g2.drawImage(silverkeyIcon, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.silver_keys, 65, 53);
		
		messageCounter++;
		if(messageCounter > 120) {
			messageCounter = 0;
			messageOn = false;
		}
		
		//MESSAGE
		if(messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(14f));
			g2.drawString(message, 50, gp.tileSize*11);
		}


	}
}
