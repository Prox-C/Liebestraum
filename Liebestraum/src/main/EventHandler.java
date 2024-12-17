package main;

import java.awt.Color;

import entity.Entity;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventMaster; 
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	int tempMap, tempCol, tempRow;
	String nextMapName = "";
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventMaster = new Entity(gp);
		
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
		
		setDialog();	
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
	
	public void spawned(int map, int col, int row, int gameState) {
		gp.gameState = gameState;
		eventMaster.startDialog(eventMaster, 0);
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void relapse(int map, int col, int row, int gameState) {
		gp.ui.displayMessage("Press [enter] to investigate.", Color.WHITE);
		if(gp.keyH.enterPressed) {
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			eventMaster.startDialog(eventMaster, 1);
			eventRect[map][col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
	public void healingWell(int gameState) {
		gp.ui.displayMessage("Press [enter] to drink.", Color.WHITE);
		if(gp.keyH.enterPressed == true) {
			gp.playSE(10);
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			eventMaster.startDialog(eventMaster, 2);
			gp.player.life = gp.player.maxHealth;
			canTouchEvent = false;
		}
	}
	
	public void investigate(int map, int col, int row, int gameState) {
		gp.ui.displayMessage("Press [enter] to investigate.", Color.WHITE);
		if(gp.keyH.enterPressed) {
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			eventMaster.startDialog(eventMaster, 3);
			eventRect[map][col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
	public void weaponize(int map, int col, int row, int gameState) {
		gp.gameState = gameState;
		eventMaster.startDialog(eventMaster, 4);
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void speedBuff(int map, int col, int row, int gameState) {
		gp.gameState = gameState;
		eventMaster.startDialog(eventMaster, 5);
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void sandWorm(int map, int col, int row, int gameState) {
		gp.ui.displayMessage("Press [enter] to investigate.", Color.WHITE);
		if(gp.keyH.enterPressed) {
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			gp.player.life -= 2;
			eventMaster.startDialog(eventMaster, 6);
			eventRect[map][col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
	public void sandKey(int map, int col, int row, int gameState) {
		gp.ui.displayMessage("Press [enter] to investigate.", Color.WHITE);
		if(gp.keyH.enterPressed) {
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			gp.player.silver_keys++;
			eventMaster.startDialog(eventMaster, 7);
			eventRect[map][col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
	public void sandLife(int map, int col, int row, int gameState) {
		gp.ui.displayMessage("Press [enter] to investigate.", Color.WHITE);
		if(gp.keyH.enterPressed) {
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			gp.player.maxHealth += 2;
			gp.player.life += 2;
			eventMaster.startDialog(eventMaster, 8);
			eventRect[map][col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
	public void prepare4combat(int map, int col, int row, int gameState) {
		gp.gameState = gameState;
		eventMaster.startDialog(eventMaster, 9);
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void ravalonCleared(int gameState) {
		gp.gameState = gameState;
		eventMaster.startDialog(eventMaster, 10);
		canTouchEvent = false;
		gp.player.slimeQuest = false;
	}
	
	public void fish(int map, int col, int row) {
		if(gp.player.fishrodDurability > 0) {
			gp.ui.displayMessage("Press [enter] to fish.", Color.WHITE);
			if(gp.keyH.enterPressed) {
	 			gp.player.attackCancelled = true;
				gp.player.golden_keys++;
				gp.ui.displayMessage("Trial key acquired. ("+ gp.player.golden_keys + "/3)", Color.GREEN);
				gp.playSE(4);
				eventRect[map][col][row].eventDone = true;
				canTouchEvent = false;
			}
		}
	}
	
	public void treasure(int map, int col, int row) {
		if(gp.player.shovelDurability > 0) {
			gp.ui.displayMessage("Press [enter] to dig.", Color.WHITE);
			if(gp.keyH.enterPressed) {
	 			gp.player.attackCancelled = true;
				gp.player.golden_keys++;
				gp.ui.displayMessage("Trial key acquired. ("+ gp.player.golden_keys + "/3)", Color.GREEN);
				gp.playSE(4);
				eventRect[map][col][row].eventDone = true;
				canTouchEvent = false;
			}
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
			if(hit(0, 19, 14, "any") == true) {spawned(0, 19, 14, gp.dialogState);}
			
			//QUEST 1
			else if(hit(0, 40, 9, "any") == true) {investigate(0, 40, 9, gp.dialogState);}
			else if(hit(0, 38, 12, "any") == true) {investigate(0, 38, 12, gp.dialogState);}

			else if(hit(0, 37, 16, "any") == true) {
				relapse(0, 22, 24, gp.dialogState);
				gp.player.questDone = true;
			}
			
			//QUEST 2
			else if(hit(2, 10, 22, "any") == true) {sandWorm(2, 10, 22, gp.dialogState);}
			else if(hit(2, 10, 33, "any") == true) {sandKey(2, 10, 33, gp.dialogState);}
			else if(hit(2, 21, 26, "any") == true) {investigate(2, 21, 26, gp.dialogState);}
			else if(hit(2, 14, 40, "any") == true) {sandLife(2, 14, 40, gp.dialogState);}
			
			//QUEST 3
			else if(hit(2, 24, 15, "right") == true) {prepare4combat(2, 24, 15, gp.dialogState);}
			else if(gp.player.stage == 3 && gp.player.slimeQuest == true) {ravalonCleared(gp.dialogState);}
			else if(hit(3, 26, 9, "left") == true) {fish(3, 26, 9);}
			else if(hit(3, 14, 39, "any") == true) {treasure(3, 14, 39);}



			
			//INTO LEO'S LODGE
			else if(hit(0, 31, 9, "up") == true) {changeMap(1, 31, 8, "Leo's Lodge");}
			//INTO THE OUTSKIRTS
			else if(hit(1, 31, 9, "down") == true) {changeMap(0, 31, 10, "The Outskirts");}
			else if(hit(2, 8, 26, "left") == true||hit(2, 8, 25, "left") == true||hit(2, 8, 27, "left") == true) {
				changeMap(0, 40, 26, "The Outskirts");
			}
			//INTO RAVALON
			else if(hit(0, 41, 26, "right") == true||hit(0, 41, 25, "right") == true||hit(0, 41, 27, "right") == true) {
				changeMap(2, 10, 26, "Ravalon");
			}
			else if(hit(3, 33, 6, "up") == true||(hit(3, 34, 6, "up")) == true||(hit(3, 35, 6, "up")) == true) {
				changeMap(2, 34, 41, "Ravalon");
			}
			//INTO BEACH
			else if(hit(2, 34, 43, "down") == true||hit(2, 33, 43, "down") == true||hit(2, 35, 43, "down") == true) {
				changeMap(3, 34, 7, "Crescent Beach");
			}
			else if(hit(4, 10, 11, "down") == true) {changeMap(3, 10, 12, "Crescent Beach");}
			
			//INTO TRIAL CHAMBER
			else if(hit(3, 10, 11, "up") == true) {changeMap(4, 10, 9, "Trial Chamber");}
			else if(hit(5, 7, 4, "any") == true) {changeMap(4, 7, 4, "Trial Chamber - Level 1");}
			//Level 1
			else if(hit(4, 7, 4, "any") == true) {changeMap(5, 7, 4, "Trial Chamber - Level 1");}
			else if(hit(5, 7, 4, "any") == true) {changeMap(5, 7, 4, "Trial Chamber");}
			else if(hit(6, 7, 10, "any") == true) {changeMap(5, 7, 10, "Trial Chamber - Level 1");}
			//Level 2
			else if(hit(5, 7, 10, "any") == true) {changeMap(6, 7, 10, "Trial Chamber - Level 2");}


			
			//HEAL
			else if(hit(2, 39, 29, "up") == true) {healingWell(gp.dialogState);}
			
			//WEAPONIZE
			else if(hit(2, 17, 11, "down") == true) {weaponize(2, 17, 11, gp.dialogState);}
			
			//SPEED BUFF
			else if(hit(2, 17, 9, "left") == true) {speedBuff(2, 17, 9, gp.dialogState); gp.player.speed++;}
		}
	}
	
	
	public void changeMap(int map, int col, int row, String mapName) {		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		nextMapName = mapName;
		canTouchEvent = false;
	}
	
	public void setDialog() {
		//SPAWNED
		eventMaster.dialog[0][0] = "( You've awoken in an unfamiliar place, clueless of everything. )";
		eventMaster.dialog[0][1] = "( Confused, you pressed onwards in search for answers. )";
		
		//RELAPSE
		eventMaster.dialog[1][0] = "[ SYSTEM ] Investigating . . .";
		eventMaster.dialog[1][1] = "( As you admire the roses' beauty, the familiar name of\nLigaya suddenly crosses your mind. )";
		eventMaster.dialog[1][2] = "( Fragments of your memory has returned. Little of which\nhowever, is that of Ligaya. )";	
		eventMaster.dialog[1][3] = "( Uncertain, you rush back to the lodge to ask Leo for\nenlightenment. )";		
		eventMaster.dialog[1][4] = "[ SYSTEM ] Quest: Return to the lodge and talk to Leo.";


		//HEALING WELL
		eventMaster.dialog[2][0] = "( You drank water from the well of life. )";
		eventMaster.dialog[2][1] = "[ SYSTEM ] HP is fully recovered! )";

		//INVESTIGATE
		eventMaster.dialog[3][0] = "[ SYSTEM ] Investigating . . .";
		eventMaster.dialog[3][1] = "[ SYSTEM ] Nothing found.";
		
		//WEAPONIZE
		eventMaster.dialog[4][0] = "( X-Calibur has accepted you as it's wielder. Armed with\nit's power, you continued your journey. )";
		eventMaster.dialog[4][1] = "[ SYSTEM ] You are now armed. Press enter to attack.";

		//SPEED BUFF
		eventMaster.dialog[5][0] = "( You drank a bottle of swiftness. )";
		eventMaster.dialog[5][1] = "[ SYSTEM ] Movement speed permanently increased.";
		
		//SAND WORMS
		eventMaster.dialog[6][0] = "[ SYSTEM ] Investigating . . .";
		eventMaster.dialog[6][1] = "[ SYSTEM ] You have been poisoined by a sand worm. ";
		
		//SAND KEY
		eventMaster.dialog[7][0] = "[ SYSTEM ] Investigating . . .";
		eventMaster.dialog[7][1] = "[ SYSTEM ] You have found a key.";
		
		//SAND LIFE
		eventMaster.dialog[8][0] = "[ SYSTEM ] Investigating . . .";
		eventMaster.dialog[8][1] = "( You have found an essence of life. )";
		eventMaster.dialog[8][2] = "[ SYSTEM ] Max HP permanently increased.";
		
		//KILL THE SLIMES
		eventMaster.dialog[9][0] = "( !!! )";
		eventMaster.dialog[9][1] = "( You have encountered hostile monsters. )";
		eventMaster.dialog[9][2] = "[ SYSTEM ] Quest: Defeat 7 Biohazard Slimes.";
		
		//SLIMES CLEARED
		eventMaster.dialog[10][0] = "( You defeated the Biohazard Slimes. )";
		eventMaster.dialog[10][1] = "[ SYSTEM ] Quest: Find and talk to Khrono.";
	}
	
	
}
