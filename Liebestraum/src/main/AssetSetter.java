package main;

import entity.NML_Duck;
import entity.NPC_Kulot;
import entity.NPC_Leo;
import mob.MOB_GreenSlime;
import object.OBJ_Tree;
import object.OBJ_Void;
import object.OBJ_Axe;
//import object.OBJ_Chest;
//import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Pickaxe;
import object.OBJ_Sign;
//import object.OBJ_SpeedPotion;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int mapNum = 0;
		
		gp.obj[mapNum][0] = new OBJ_Key(gp);
		gp.obj[mapNum][0].worldX = 39 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 41 * gp.tileSize;
		
		gp.obj[mapNum][1] = new OBJ_Tree(gp);
		gp.obj[mapNum][1].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][1].worldY = 9 * gp.tileSize;
		
		gp.obj[mapNum][2] = new OBJ_Axe(gp);
		gp.obj[mapNum][2].worldX = 19 * gp.tileSize;
		gp.obj[mapNum][2].worldY = 32 * gp.tileSize;
		
		gp.obj[mapNum][3] = new OBJ_Tree(gp);
		gp.obj[mapNum][3].worldX = 33 * gp.tileSize;
		gp.obj[mapNum][3].worldY = 10 * gp.tileSize;
		
		gp.obj[mapNum][4] = new OBJ_Tree(gp);
		gp.obj[mapNum][4].worldX = 39 * gp.tileSize;
		gp.obj[mapNum][4].worldY = 10 * gp.tileSize;
		
		gp.obj[mapNum][5] = new OBJ_Tree(gp);
		gp.obj[mapNum][5].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][5].worldY = 14 * gp.tileSize;
		
//		gp.obj[3] = new OBJ_SpeedPotion(gp);
//		gp.obj[3].worldX = 20 * gp.tileSize;
//		gp.obj[3].worldY = 10 * gp.tileSize;
		
		gp.obj[mapNum][6] = new OBJ_Sign(gp);
		gp.obj[mapNum][6].worldX = 23 * gp.tileSize;
		gp.obj[mapNum][6].worldY = 18 * gp.tileSize;
		
		gp.obj[mapNum][7] = new OBJ_Void(gp);
		gp.obj[mapNum][7].worldX = 42 * gp.tileSize;
		gp.obj[mapNum][7].worldY = 25 * gp.tileSize;
		
		gp.obj[mapNum][8] = new OBJ_Void(gp);
		gp.obj[mapNum][8].worldX = 42 * gp.tileSize;
		gp.obj[mapNum][8].worldY = 26 * gp.tileSize;
		
		gp.obj[mapNum][9] = new OBJ_Void(gp);
		gp.obj[mapNum][9].worldX = 42 * gp.tileSize;
		gp.obj[mapNum][9].worldY = 27 * gp.tileSize;
		
		mapNum++;
	}
	
	public void setNPC() {
		int mapNum = 0;

		gp.npc[mapNum][0] = new NPC_Kulot(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 14;
		gp.npc[mapNum][0].worldY = gp.tileSize * 13;
		
		gp.npc[mapNum][1] = new NML_Duck(gp);
		gp.npc[mapNum][1].worldX = gp.tileSize * 10;
		gp.npc[mapNum][1].worldY = gp.tileSize * 11;
		
		gp.npc[mapNum][2] = new NML_Duck(gp);
		gp.npc[mapNum][2].worldX = gp.tileSize * 14;
		gp.npc[mapNum][2].worldY = gp.tileSize * 15;
		
		mapNum++;
		
		gp.npc[mapNum][0] = new NPC_Leo(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 35;
		gp.npc[mapNum][0].worldY = gp.tileSize * 5;
	}
	
	public void setMob() {
		int mapNum = 0;

		gp.mob[mapNum][0] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][0].worldX = gp.tileSize * 21;
		gp.mob[mapNum][0].worldY = gp.tileSize * 14;
//		
//		gp.mob[1] = new MOB_GreenSlime(gp);
//		gp.mob[1].worldX = gp.tileSize * 18;
//		gp.mob[1].worldY = gp.tileSize * 24;
	}
}

