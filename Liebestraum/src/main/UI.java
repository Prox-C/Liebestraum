package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

import object.OBJ_Heart;
import object.SuperObject;

import java.awt.image.BufferedImage;
//
//import object.OBJ_Key;
//import object.OBJ_Pickaxe;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font stat_font, source_80B, dialog_14, title_font, option_font;

	BufferedImage heart_full, heart_half, heart_empty;
	public boolean messageOn = false;
	private Color messageColor = Color.white; // Default color
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public String currentDialog = "";
	public int commandNum = 0;

	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
			stat_font = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/UnifontExMono.ttf");
			dialog_14 = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/Crang.ttf");
			title_font = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/Retro Gaming.ttf");
			option_font = Font.createFont(Font.TRUETYPE_FONT, is);
		}catch(FontFormatException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		source_80B = new Font("Source Pro", Font.BOLD, 40);
		
		//CREATE HUD OBJECT
		
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_empty = heart.image3;
		
		
	}
	
	public void displayMessage(String text, Color color) {
		
		message = text;
		messageColor = color;
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
			drawPlayerLife();
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
		}
		if(gp.gameState == gp.dialogState) {
			drawDialog();
			drawPlayerLife();
		}
		
		messageCounter++;
		if(messageCounter > 120) {
			messageCounter = 0;
			messageOn = false;
		}
		
		 if (messageOn == true) {
		        g2.setFont(stat_font);
		        g2.setColor(new Color(0, 0, 0, 192)); // Black with 75% opacity
		        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16f));
		     

		        // Draw the background rectangle
		        int rectX = 40;
		        int rectY = gp.tileSize * 11 - 20; // Position slightly above the text baseline
		        int rectWidth = g2.getFontMetrics().stringWidth(message) + 20; // Add padding
		        int rectHeight = 30; // Fixed height for the rectangle

		        g2.fillRoundRect(rectX, rectY, rectWidth, rectHeight, 15, 15); // Rounded corners

		        // Draw the message text
		        g2.setColor(messageColor); // Set text color
		        g2.drawString(message, rectX + 10, gp.tileSize * 11);
		}
		
	}
	
	public void drawPlayerLife() {
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		
		while(i < gp.player.maxHealth/2) {
			g2.drawImage(heart_empty, x, y, null);
			i++;
			x += 40;
		}
		
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += 40;
		}
		
		
	}
	public void drawTitleScreen() {
	    // TITLE
	    String title = "Liebestraum";
	    int x = 230;
	    int y = gp.screenHeight / 6;

	    // Load and display animated GIF
	    ImageIcon gifIcon = new ImageIcon(getClass().getResource("/gifs/cover.gif"));
	    Image gifImage = gifIcon.getImage();

	    // Draw the background GIF (stretch it to fit screen size)
	    g2.drawImage(gifImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

	    // Set the font size and style
	    g2.setFont(title_font);
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40f));

	    // STROKE SETTINGS (outline)
	    g2.setColor(new Color(67, 56, 120));  // Color of the stroke
	    g2.setStroke(new BasicStroke(6));  // Set stroke width

	    // Draw the stroke by slightly off setting the position
	    g2.drawString(title, x - 2, y - 2);  // Top-left stroke
	    g2.drawString(title, x + 2, y - 2);  // Top-right stroke
	    g2.drawString(title, x - 2, y + 2);  // Bottom-left stroke
	    g2.drawString(title, x + 2, y + 2);  // Bottom-right stroke

	    // Create the gradient (from left to right)
	    GradientPaint gradient = new GradientPaint(
	            x, y, new Color(155, 193, 255), // Start point (gold)
	            x + 150, y, new Color(203, 157, 240) // End point (orange)
	    );

	    // Apply the gradient to the text
	    g2.setPaint(gradient);

	    // MAIN TEXT (gradient color)
	    g2.drawString(title, x, y);
	    
	    // ------------------------------------------- MENU ------------------------------------
	    // NEW GAME
	    
	    String text = "New Game";
	    x = 50;
	    y = gp.screenHeight/2-50;
	    
	    g2.setFont(option_font);
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 22f));
	    
	    g2.setColor(new Color(67, 56, 120));  
	    g2.setStroke(new BasicStroke(4)); 

	    g2.drawString(text, x - 2, y - 2); 
	    g2.drawString(text, x + 2, y - 2);  
	    g2.drawString(text, x - 2, y + 2);  
	    g2.drawString(text, x + 2, y + 2); 
	    
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    if(commandNum == 0) {
	    	  g2.setColor(new Color(67, 56, 120));
	    	g2.drawString("<", 195, y);
	    }
	    
	    // LOAD GAME
	    
	    text = "Load Game";
	    x = 50;
	    y = gp.screenHeight/2;
	    
	    g2.setColor(new Color(67, 56, 120));  // Color of the stroke
	    g2.setStroke(new BasicStroke(4));  // Set stroke width

	    g2.drawString(text, x - 2, y - 2);  
	    g2.drawString(text, x + 2, y - 2);  
	    g2.drawString(text, x - 2, y + 2);  
	    g2.drawString(text, x + 2, y + 2); 
	    
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    if(commandNum == 1) {
	    	  g2.setColor(new Color(67, 56, 120));
	    	g2.drawString("<", 210, y);
	    }
	    
	    text = "Exit";
	    x = 50;
	    y = gp.screenHeight/2 + 50;
	    
	    g2.setColor(new Color(67, 56, 120));  // Color of the stroke
	    g2.setStroke(new BasicStroke(4));  // Set stroke width

	    g2.drawString(text, x - 2, y - 2);  
	    g2.drawString(text, x + 2, y - 2);  
	    g2.drawString(text, x - 2, y + 2);  
	    g2.drawString(text, x + 2, y + 2); 
	    
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    if(commandNum == 2) {
	    	  g2.setColor(new Color(67, 56, 120));
	    	g2.drawString("<", 118, y);
	    }
	   
	    
	  
	   
	    
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
