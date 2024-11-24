package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//
//import object.OBJ_Key;
//import object.OBJ_Pickaxe;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font stat_font, source_80B;
//	BufferedImage pickaxeIcon;
//	BufferedImage silverkeyIcon;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;

	public UI(GamePanel gp) {
		this.gp = gp;
		stat_font = new Font("Source Pro", Font.PLAIN, 18);
		source_80B = new Font("Source Pro", Font.BOLD, 40);
		
//		OBJ_Pickaxe pickaxe = new OBJ_Pickaxe(gp);
//		pickaxeIcon = pickaxe.image;
//		
//		OBJ_Key key = new OBJ_Key(gp);
//		silverkeyIcon = key.image;
		
	}
	
	public void displayMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(source_80B);
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.playState) {
			//play stuff
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		messageCounter++;
		if(messageCounter > 120) {
			messageCounter = 0;
			messageOn = false;
		}
		
		if(messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(14f));
			g2.drawString(message, 50, gp.tileSize*11);
		}
		
	}
	
	public void drawPauseScreen() {
		String text = "P A U S E D";
		int x = getXForText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXForText(String text) {
		int length =  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
