package main;

import entity.NML_Duck;
import entity.NPC_Fabron;
import entity.NPC_Khrono;
import entity.NPC_Kulot;
import entity.NPC_Leo;
import entity.NPC_Ligaya;
import entity.NPC_Yuji;
import mob.MOB_BlueSlime;
import mob.MOB_GreenSlime;
import mob.MOB_PurpleSlime;
import object.OBJ_Tree;
import object.OBJ_Void;
import object.OBJ_Boulder;
import object.OBJ_Chest;
import object.OBJ_GoldKey;
import object.OBJ_HealPotion;
import object.OBJ_Key;
import object.OBJ_Sign;
import object.OBJ_Sword;
import object.OBJ_SpeedPotion;
import object.OBJ_StrBuff;

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
		
		gp.obj[mapNum][2] = new OBJ_Chest(gp);
		gp.obj[mapNum][2].worldX = 19 * gp.tileSize;
		gp.obj[mapNum][2].worldY = 32 * gp.tileSize;
		gp.obj[mapNum][2].name = "Axe";
		
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
		//MAP 2
		gp.obj[mapNum][0] = new OBJ_Tree(gp);
		gp.obj[mapNum][0].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 11 * gp.tileSize;
		
		gp.obj[mapNum][1] = new OBJ_Sword(gp);
		gp.obj[mapNum][1].worldX = 17 * gp.tileSize;
		gp.obj[mapNum][1].worldY = 11 * gp.tileSize;
		
		gp.obj[mapNum][2] = new OBJ_Chest(gp);
		gp.obj[mapNum][2].worldX = 21 * gp.tileSize;
		gp.obj[mapNum][2].worldY = 37 * gp.tileSize;
		gp.obj[mapNum][2].name = "Pickaxe";
		
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
		
		gp.obj[mapNum][6] = new OBJ_Void(gp);
		gp.obj[mapNum][6].worldX = 34 * gp.tileSize;
		gp.obj[mapNum][6].worldY = 24 * gp.tileSize;
		gp.obj[mapNum][6].name = "Void3";
		
		gp.obj[mapNum][7] = new OBJ_Key(gp);
		gp.obj[mapNum][7].worldX = 37 * gp.tileSize;
		gp.obj[mapNum][7].worldY = 40 * gp.tileSize;
		
		mapNum++;
		//MAP 3
		gp.obj[mapNum][0] = new OBJ_Key(gp);
		gp.obj[mapNum][0].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 9 * gp.tileSize;
		
		gp.obj[mapNum][1] = new OBJ_Chest(gp);
		gp.obj[mapNum][1].worldX = 14 * gp.tileSize;
		gp.obj[mapNum][1].worldY = 23 * gp.tileSize;
		gp.obj[mapNum][1].name = "Fishrod";
		
		gp.obj[mapNum][2] = new OBJ_GoldKey(gp);
		gp.obj[mapNum][2].worldX = 32 * gp.tileSize;
		gp.obj[mapNum][2].worldY = 27 * gp.tileSize;

		gp.obj[mapNum][3] = new OBJ_Chest(gp);
		gp.obj[mapNum][3].worldX = 34 * gp.tileSize;
		gp.obj[mapNum][3].worldY = 30 * gp.tileSize;
		gp.obj[mapNum][3].name = "Shovel";
		
		gp.obj[mapNum][4] = new OBJ_Void(gp);
		gp.obj[mapNum][4].worldX = 10 * gp.tileSize;
		gp.obj[mapNum][4].worldY = 11 * gp.tileSize;
		gp.obj[mapNum][4].name = "Void4";
		
		gp.obj[mapNum][5] = new OBJ_Key(gp);
		gp.obj[mapNum][5].worldX = 8 * gp.tileSize;
		gp.obj[mapNum][5].worldY = 10 * gp.tileSize;
		
		gp.obj[mapNum][6] = new OBJ_HealPotion(gp);
		gp.obj[mapNum][6].worldX = 25 * gp.tileSize;
		gp.obj[mapNum][6].worldY = 16 * gp.tileSize;
		
		gp.obj[mapNum][7] = new OBJ_StrBuff(gp);
		gp.obj[mapNum][7].worldX = 10 * gp.tileSize;
		gp.obj[mapNum][7].worldY = 8 * gp.tileSize;
		
		gp.obj[mapNum][8] = new OBJ_Tree(gp);
		gp.obj[mapNum][8].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][8].worldY = 8 * gp.tileSize;
		
		//TRIAL CHAMBER Ground Floor
		mapNum++;
		gp.obj[mapNum][0] = new OBJ_Chest(gp);
		gp.obj[mapNum][0].worldX = 13 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 4 * gp.tileSize;
		gp.obj[mapNum][0].name = "Life";
		
		//TRIAL CHAMBER 1st Floor
		mapNum++;
		gp.obj[mapNum][0] = new OBJ_HealPotion(gp);
		gp.obj[mapNum][0].worldX = 10 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 7 * gp.tileSize;
		
		//TRIAL CHAMBER 2nd Floor
		mapNum++;
		gp.obj[mapNum][0] = new OBJ_HealPotion(gp);
		gp.obj[mapNum][0].worldX = 10 * gp.tileSize;
		gp.obj[mapNum][0].worldY = 7 * gp.tileSize;
				
		
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
		
		mapNum++;
		mapNum++;
		//TRIAL CHAMBER
		gp.npc[mapNum][0] = new NPC_Fabron(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 10;
		gp.npc[mapNum][0].worldY = gp.tileSize * 7;
		
		mapNum = 7;
		gp.npc[mapNum][0] = new NPC_Ligaya(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 10;
		gp.npc[mapNum][0].worldY = gp.tileSize * 7;
		
	}
	
	public void setMob() {
		int mapNum = 2;
		int i = 0;
		
		//RAVALON
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 26;
		gp.mob[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 28;
		gp.mob[mapNum][i].worldY = gp.tileSize * 13;
		i++;
		
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 37;
		gp.mob[mapNum][i].worldY = gp.tileSize * 11;
		i++;
		
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 34;
		gp.mob[mapNum][i].worldY = gp.tileSize * 14;
		i++;
		
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 30;
		gp.mob[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 40;
		gp.mob[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		
		gp.mob[mapNum][i] = new MOB_BlueSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 35;
		gp.mob[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		
		mapNum++;
		//BEACH
		i = 0;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 39;
		gp.mob[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 36;
		gp.mob[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 38;
		gp.mob[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 25;
		gp.mob[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 31;
		gp.mob[mapNum][i].worldY = gp.tileSize * 30;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 29;
		gp.mob[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		mapNum++;
		
		//LEVEL 1
		mapNum++;
		i = 0;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 7;
		gp.mob[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 13;
		gp.mob[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 10;
		gp.mob[mapNum][i].worldY = gp.tileSize * 10;
		i++;
		
		gp.mob[mapNum][i] = new MOB_GreenSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 10;
		gp.mob[mapNum][i].worldY = gp.tileSize * 4;
		i++;
		
		//LEVEL 2
		mapNum++;
		i = 0;
		
		gp.mob[mapNum][i] = new MOB_PurpleSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 7;
		gp.mob[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		
		gp.mob[mapNum][i] = new MOB_PurpleSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 13;
		gp.mob[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		
		gp.mob[mapNum][i] = new MOB_PurpleSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 10;
		gp.mob[mapNum][i].worldY = gp.tileSize * 10;
		i++;
		
		gp.mob[mapNum][i] = new MOB_PurpleSlime(gp);
		gp.mob[mapNum][i].worldX = gp.tileSize * 10;
		gp.mob[mapNum][i].worldY = gp.tileSize * 4;
	}
}

