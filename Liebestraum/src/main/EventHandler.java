package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.height = 2;
		eventRect.width = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;	
	}
	
	public void checkEvent() {
		if(hit(14, 19, "down") == true) {damageTile(gp.dialogState);}
		if(hit(18, 27, "down") == true) {healingWell(gp.dialogState);}

	}
	

	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection)) {
				hit = true;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void damageTile(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialog = "Roses remind me of you, Ligaya. ";
		gp.player.life -= 1;
	}
	public void healingWell(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogState;
			gp.ui.currentDialog = "Recovering HP . . .";
			gp.player.life = gp.player.maxHealth;
		}
	}
}
