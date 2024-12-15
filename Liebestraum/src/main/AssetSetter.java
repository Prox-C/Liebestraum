package main;

import entity.NML_Duck;
import entity.NPC_Khrono;
import entity.NPC_Kulot;
import entity.NPC_Leo;
import entity.NPC_Yuji;
import mob.MOB_GreenSlime;
import object.OBJ_Tree;
import object.OBJ_Void;
import object.OBJ_Axe;
import object.OBJ_Boulder;
import object.OBJ_Key;
import object.OBJ_Pickaxe;
import object.OBJ_Sign;
import object.OBJ_Sword;
import object.OBJ_SpeedPotion;

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
		
		gp.obj[mapNum][6] = new OBJ_Sign(gp);
		gp.obj[mapNum][6].worldX = 23 * gp.tileSize;
		gp.obj[mapNum][6].worldY = 18 * gp.tileSize;
		
		gp.obj[mapNum][7] = new OBJ_Void(gp);
		gp.obj[mapNum][7].worldX = 41 * gp.tileSize;
		gp.obj[mapNum][7].worldY = 25 * gp.tileSize;
		
		gp.obj[mapNum][8] = new OBJ_Void(gp);
		gp.obj[mapNum][8].worldX = 41 * gp.tileSize;
		gp.obj[mapNum][8].worldY = 26 * gp.tileSize;
		
		gp.obj[mapNum][9] = new OBJ_Void(gp);
		gp.obj[mapNum][9].worldX = 41 * gp.tileSize;
		gp.obj[mapNum][9].worldY = 27 * gp.tileSize;
		
		mapNum++;
		
		mapNum++;
		
		gp.obj[mapNum][0] = new OBJ_Tree(gp);
		gp.obj[mapNum][0].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 11 * gp.tileSize;
		
		gp.obj[mapNum][1] = new OBJ_Sword(gp);
		gp.obj[mapNum][1].worldX = 17 * gp.tileSize;
		gp.obj[mapNum][1].worldY = 11 * gp.tileSize;
		
		gp.obj[mapNum][2] = new OBJ_Pickaxe(gp);
		gp.obj[mapNum][2].worldX = 21 * gp.tileSize;
		gp.obj[mapNum][2].worldY = 37 * gp.tileSize;
		
		gp.obj[mapNum][3] = new OBJ_SpeedPotion(gp);
		gp.obj[mapNum][3].worldX = 17 * gp.tileSize;
		gp.obj[mapNum][3].worldY = 9 * gp.tileSize;
		
		gp.obj[mapNum][4] = new OBJ_Boulder(gp);
		gp.obj[mapNum][4].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][4].worldY = 17 * gp.tileSize;
		
		gp.obj[mapNum][5] = new OBJ_Void(gp);
		gp.obj[mapNum][5].worldX = 22 * gp.tileSize;
		gp.obj[mapNum][5].worldY = 15 * gp.tileSize;
		gp.obj[mapNum][5].name = "Void2";
		
		gp.obj[mapNum][5] = new OBJ_Void(gp);
		gp.obj[mapNum][5].worldX = 35 * gp.tileSize;
		gp.obj[mapNum][5].worldY = 24 * gp.tileSize;
		gp.obj[mapNum][5].name = "Void3";
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
		
		mapNum++;
		
		gp.npc[mapNum][0] = new NPC_Khrono(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 39;
		gp.npc[mapNum][0].worldY = gp.tileSize * 34;
		
		gp.npc[mapNum][1] = new NPC_Yuji(gp);
		gp.npc[mapNum][1].worldX = gp.tileSize * 14;
		gp.npc[mapNum][1].worldY = gp.tileSize * 26;
	}
	
	public void setMob() {
		int mapNum = 2;
		int i = 0;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 26;
		gp.mob[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 28;
		gp.mob[mapNum][i].worldY = gp.tileSize * 13;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 37;
		gp.mob[mapNum][i].worldY = gp.tileSize * 11;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 34;
		gp.mob[mapNum][i].worldY = gp.tileSize * 14;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 30;
		gp.mob[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 40;
		gp.mob[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 35;
		gp.mob[mapNum][i].worldY = gp.tileSize * 20;
		i++;
	}
}

