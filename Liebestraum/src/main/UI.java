package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;


import object.OBJ_Heart;
import entity.Entity;

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
	int subState = 0;
	public Entity npc;
	int  charIndex = 0;
	String combinedText = "";

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
		
		Entity heart = new OBJ_Heart(gp);
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
		
		if(gp.gameState == gp.playState || gp.gameState == gp.optionState) {
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
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		
		messageCounter++;
		if(messageCounter > 140) {
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
	public void drawGameOverScreen() {
	    // Background overlay
	    g2.setColor(new Color(0, 0, 0, 150));
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    String text = "G A M E  O V E R  :(";
	    int x, y;

	    g2.setFont(option_font);
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 44));

	    // Shadow
	    g2.setColor(Color.BLACK);
	    x = getXForText(text);
	    y = gp.tileSize * 4;
	    g2.drawString(text, x, y);

	    // Main text
	    g2.setColor(Color.white);
	    g2.drawString(text, x - 4, y - 4);

	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26));

	    // Retry
	    text = "RETRY";
	    x = getXForText(text);
	    y += gp.tileSize * 4; // Offset below the "GAME OVER" text
	    g2.drawString(text, x, y);
	    if (commandNum == 0) {
	        g2.setColor(Color.white);
	        g2.drawString("[", x - g2.getFontMetrics().stringWidth(">") - 10, y);
	        g2.drawString("]", x + g2.getFontMetrics().stringWidth(text) + 10, y);
	    }

	    // Back to Main Menu
	    text = "MAIN MENU";
	    x = getXForText(text);
	    y += gp.tileSize; // Offset below the "RETRY" text
	    g2.drawString(text, x, y);
	    if (commandNum == 1) {
	        g2.setColor(Color.white);
	        g2.drawString("[", x - g2.getFontMetrics().stringWidth(">") - 10, y);
	        g2.drawString("]", x + g2.getFontMetrics().stringWidth(text) + 10, y);
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
	    int x = gp.screenWidth / 2;
	    int y = gp.screenHeight / 10;

	    // Load and display animated GIF
	    ImageIcon gifIcon = new ImageIcon(getClass().getResource("/ui/cover3.gif"));
	    Image gifImage = gifIcon.getImage();

	    // Draw the background GIF (stretch it to fit screen size)
	    g2.drawImage(gifImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

	    x = 512 - gp.screenWidth / 2;
	    Image logoImage = new ImageIcon(getClass().getResource("/ui/logo3.png")).getImage();
	    g2.drawImage(logoImage, x, y, 512, 72, null);
	    
	    // ------------------------------------------- MENU ------------------------------------
	    // NEW GAME
	    String text = "New Game";
	    y = gp.screenHeight / 2 - 50; // Y-coordinate stays the same

	    g2.setFont(option_font);
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 22f));

	    // Measure the width of the text
	    FontMetrics metrics = g2.getFontMetrics(g2.getFont());
	    int textWidth = metrics.stringWidth(text);

	    // Calculate x to center-align the text
	    x = (gp.screenWidth - textWidth) / 2;

	    // Draw the outline (stroke) for the text
	    g2.setColor(new Color(67, 56, 120));
	    g2.setStroke(new BasicStroke(4));
	    g2.drawString(text, x - 2, y - 2);
	    g2.drawString(text, x + 2, y - 2);
	    g2.drawString(text, x - 2, y + 2);
	    g2.drawString(text, x + 2, y + 2);

	    // Draw the main text
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);

	    // Selector arrows for New Game
	    if (commandNum == 0) {
	        g2.setColor(Color.white);
	        g2.drawString("[", x - metrics.stringWidth("[") - 10, y);
	        g2.drawString("]", x + textWidth + 10, y);
	    }

//	    // Load Game
//	    text = "Load Game";
//	    y = gp.screenHeight / 2;
//	    textWidth = metrics.stringWidth(text);
//	    x = (gp.screenWidth - textWidth) / 2;

//	    // Outline
//	    g2.setColor(new Color(67, 56, 120));
//	    g2.setStroke(new BasicStroke(4));
//	    g2.drawString(text, x - 2, y - 2);
//	    g2.drawString(text, x + 2, y - 2);
//	    g2.drawString(text, x - 2, y + 2);
//	    g2.drawString(text, x + 2, y + 2);
//
//	    // Main text
//	    g2.setColor(Color.white);
//	    g2.drawString(text, x, y);

//	    // Selector arrows for Load Game
//	    if (commandNum == 1) {
//	        g2.setColor(Color.white);
//	        g2.drawString("[", x - metrics.stringWidth("[") - 10, y);
//	        g2.drawString("]", x + textWidth + 10, y);
//	    }

	    // Exit
	    text = "Exit";
	    y = gp.screenHeight / 2;
	    textWidth = metrics.stringWidth(text);
	    x = (gp.screenWidth - textWidth) / 2;

	    // Outline
	    g2.setColor(new Color(67, 56, 120));
	    g2.setStroke(new BasicStroke(4));
	    g2.drawString(text, x - 2, y - 2);
	    g2.drawString(text, x + 2, y - 2);
	    g2.drawString(text, x - 2, y + 2);
	    g2.drawString(text, x + 2, y + 2);

	    // Main text
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);

	    // Selector arrows for Exit
	    if (commandNum == 1) {
	        g2.setColor(Color.white);
	        g2.drawString("[", x - metrics.stringWidth("[") - 10, y);
	        g2.drawString("]", x + textWidth + 10, y);
	    }
	}

	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(stat_font);
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));
	    
	    //SUB WINDOW
	    int frameX = gp.tileSize*5;
	    int frameY = gp.tileSize;
	    int frameWidth = gp.tileSize*7;
	    int frameHeight = gp.tileSize*8;
	    
	    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
	    
	    switch(subState) {
	    case 0: options_top(frameX, frameY); break;
	    case 1: options_control(frameX, frameY); break;
	    }
	    gp.keyH.enterPressed = false;  
	}
	
	 public void options_top(int frameX, int frameY) {
	    	int textX;
	    	int textY;
	    	
	    	//TITLE
	    	String text = "Options";
	    	textX = getXForText(text);
	    	textY = frameY + gp.tileSize;
	    	g2.setFont(option_font);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
	    	g2.drawString(text, textX+15, textY);
	    	
	    	g2.setFont(dialog_14);
			
			textX = frameX + gp.tileSize;

	    	//MUSIC
	    	text = "Music Volume";
	    	textY += gp.tileSize;
	    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
	    	g2.drawString(text, textX, textY);
	    	if(commandNum == 0) {
	    		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
	    		g2.drawString(">", textX-20, textY);
	    	}
	    	
	    	//SFX
	    	text = "SFX Volume";
	    	textY += gp.tileSize;
	    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
	    	g2.drawString(text, textX, textY);
	    	if(commandNum == 1) {
	    		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
	    		g2.drawString(">", textX-20, textY);
	    	}
	    	
	    	//CONTROLS
	    	text = "Controls";
	    	textY += gp.tileSize;
	    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
	    	g2.drawString(text, textX, textY);
	    	if(commandNum == 2) {
	    		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
	    		g2.drawString(">", textX-20, textY);
	    		if(gp.keyH.enterPressed) {
	    			subState = 1;
	    			commandNum = 0;
	    		}
	    	}
	    	
	    	//MENU
	    	text = "Return to Menu";
	    	textY += gp.tileSize;
	    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
	    	g2.drawString(text, textX, textY);
	    	if(commandNum == 3) {
	    		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
	    		g2.drawString(">", textX-20, textY);
	    		if(gp.keyH.enterPressed) {
	    			gp.stopMusic(0);
	    			gp.playMusic(6);
	    			gp.gameState = gp.titleState;
	    		}
	    	}
	    	
	    	//BACK
	    	text = "Close(ESC)";
	    	textY += gp.tileSize*2;
	    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
	    	g2.drawString(text, textX, textY);
	    	if(commandNum == 4) {
	    		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
	    		g2.drawString(">", textX-20, textY);
	    		if(gp.keyH.enterPressed) {
	    			gp.gameState = gp.playState;
	    		}
	    	}
	    	
			textX = frameX + gp.tileSize + 120;
	    	textY = frameY + gp.tileSize + 30;
	    	g2.setStroke(new BasicStroke(1));
	    	
	    	//MUSIC BAR
	    	g2.drawRoundRect(textX+30, textY, 100, 24, 15, 15);
	    	int volumeWidth = 20 * gp.music.volumeScale;
	    	g2.fillRoundRect(textX+30, textY, volumeWidth, 24, 15, 15);
	    	
	    	//SOUND BAR
	    	textY += gp.tileSize;
	    	g2.drawRoundRect(textX+30, textY, 100, 24, 15, 15);
	    	volumeWidth = 20 * gp.sfx.volumeScale;
	    	g2.fillRoundRect(textX+30, textY, volumeWidth, 24, 15, 15);
	    	
	    }
	 
	 public void options_control(int frameX, int frameY) {
		 
		 int textX, textY;
		 
		//TITLE
	    	String text = "Controls";
	    	textX = getXForText(text);
	    	textY = frameY + gp.tileSize;
	    	g2.setFont(option_font);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
	    	g2.drawString(text, textX+12, textY);
	    	
	    	g2.setFont(dialog_14);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));

			textX = frameX + gp.tileSize;
			textY += gp.tileSize;
			
			g2.drawString("Movement", textX, textY);
			g2.drawString("(WASD)", textX + 180, textY); textY += gp.tileSize;
			
			g2.drawString("Interact/Attack", textX, textY);
			g2.drawString("(ENTER)", textX + 180, textY); textY += gp.tileSize;
			
			g2.drawString("Proceed", textX, textY);
			g2.drawString("(ENTER)", textX + 180, textY); textY += gp.tileSize;

			g2.drawString("Pause", textX, textY);
			g2.drawString("(P)", textX + 180, textY); textY += gp.tileSize;

			g2.drawString("Options", textX, textY);
			g2.drawString("(ESC)", textX + 180, textY); textY += gp.tileSize;
			
			//BACK
			g2.drawString("Back", textX, textY);
			if(commandNum == 0) {
	    		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
				g2.drawString(">", textX -25, textY);
				if(gp.keyH.enterPressed) {
					subState = 0;
					commandNum = 2;
				}
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
		
		if(npc.dialog[npc.dialogSet][npc.dialogIndex] != null) {
			//currentDialog = npc.dialog[npc.dialogSet][npc.dialogIndex];
			
			char charachters[] = npc.dialog[npc.dialogSet][npc.dialogIndex].toCharArray();
			if(charIndex < charachters.length) {
				String s = String.valueOf(charachters[charIndex]);
				combinedText = combinedText + s;
				currentDialog = combinedText;
				charIndex++;
			}
			if(gp.keyH.enterPressed) {
				charIndex = 0;
				combinedText = "";
				if(gp.gameState == gp.dialogState) {
					npc.dialogIndex++;
					gp.keyH.enterPressed = false;
				}
			}
			
		}
		else {
			npc.dialogIndex = 0;
			
			if(gp.gameState == gp.dialogState) {
				gp.gameState = gp.playState;
			}
		}
		
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
	
	public void drawSubWindow(int x, int y, int width, int height) {
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
		
		g2.setFont(option_font);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 44));
		g2.drawString(text, x, y);
	}
	
	public int getXForText(String text) {
		int length =  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
