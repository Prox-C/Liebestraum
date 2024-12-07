package main;

import java.awt.Color;
import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0; 
		int row = 0;
		while(map < gp.maxMap &&  col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}	 
		
			
	}

	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if("any".equals(reqDirection) || gp.player.direction.contentEquals(reqDirection)) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
					
					
					
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
			}
		return hit;
	}
	
	public void damageTile(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialog = "Roses remind me of you, Ligaya. ";
		gp.player.life -= 1;
//		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	public void spawned(int map, int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialog = "*You woke up in an unfamiliar place and you don't remember\nanything.*";
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	public void healingWell(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.playSE(10);
			gp.gameState = gp.dialogState;
			gp.ui.currentDialog = "Recovering HP . . .";
			gp.player.life = gp.player.maxHealth;
			gp.player.attackCancelled = true;
			canTouchEvent = false;
		}
	}
	
	public void checkEvent() {
		
		//CHECK IF PLAYER IS AWAY FROM EVENT
		
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			if(hit(0, 14, 19, "down") == true) {damageTile(gp.dialogState);}
			else if(hit(0, 18, 27, "down") == true) {healingWell(gp.dialogState);}
			else if(hit(0, 31, 9, "up") == true) {
				changeMap(1, 31, 8);
				gp.ui.displayMessage("Leo's Lodge", Color.WHITE);
				}
			else if(hit(1, 31, 9, "down") == true) {
				changeMap(0, 31, 10);
				gp.ui.displayMessage("Memory Lane", Color.WHITE);
				}
			else if(hit(0, 19, 14, "any") == true) {spawned(0, 19, 14, gp.dialogState);}
		}
	}
	
	
	public void changeMap(int map, int col, int row) {
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
	}
	
	
}
