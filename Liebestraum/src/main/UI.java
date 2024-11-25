package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
//import java.awt.image.BufferedImage;
//
//import object.OBJ_Key;
//import object.OBJ_Pickaxe;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font stat_font, source_80B, dialog_14;
//	BufferedImage pickaxeIcon;
//	BufferedImage silverkeyIcon;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public String currentDialog = "";

	public UI(GamePanel gp) {
		this.gp = gp;
//		stat_font = new Font("Source Pro", Font.PLAIN, 18);
//		dialog_14 = new Font("Jetbrains Mono", Font.PLAIN, 14);
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
			stat_font = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/UnifontExMono.ttf");
			dialog_14 = Font.createFont(Font.TRUETYPE_FONT, is);
		}catch(FontFormatException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
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
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		if(gp.gameState == gp.playState) {
			//play stuff
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogState) {
			drawDialog();
		}
		
		messageCounter++;
		if(messageCounter > 120) {
			messageCounter = 0;
			messageOn = false;
		}
		
		if(messageOn == true) {
			g2.setFont(dialog_14);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
			g2.drawString(message, 50, gp.tileSize*11);
		}
		
	}
	public void drawTitleScreen() {
		//TITLE
		String title = "Liebestraum";
		int x = getXForText(title);
		int y = gp.screenHeight/3;
		
		g2.setFont(source_80B);
	
		//SHADOW
		g2.setColor(Color.red);
		g2.drawString(title, x+3, y+3);
		//MAIN TEXT
		g2.setColor(Color.white);
		g2.drawString(title, x, y);
	}
	
	public void drawDialog() {
		int x = gp.tileSize*2;
		int y = gp.tileSize*8;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.screenHeight - (gp.tileSize*10);
		
		drawDialogSubWindow(x, y, width, height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		
		g2.setFont(stat_font);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16));
		
		for(String line : currentDialog.split("\n")) {
			g2.drawString(line, x, y);
			y += 20;
		}
	}
	
	public void drawDialogSubWindow(int x, int y, int width, int height) {
		Color c =new Color(0, 0, 0, 200);
		g2.setColor(c);		
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
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
