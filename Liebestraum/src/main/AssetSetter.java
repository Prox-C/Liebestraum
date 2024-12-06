package main;

import entity.NPC_Kulot;
import mob.MOB_GreenSlime;
import object.OBJ_Boulder;
//import object.OBJ_Chest;
//import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Pickaxe;
import object.OBJ_Sign;
import object.OBJ_SpeedPotion;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 39 * gp.tileSize;
		gp.obj[0].worldY = 41 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Boulder(gp);
		gp.obj[1].worldX = 38 * gp.tileSize;
		gp.obj[1].worldY = 9 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Pickaxe(gp);
		gp.obj[2].worldX = 19 * gp.tileSize;
		gp.obj[2].worldY = 32 * gp.tileSize;
		
//		gp.obj[3] = new OBJ_SpeedPotion(gp);
//		gp.obj[3].worldX = 20 * gp.tileSize;
//		gp.obj[3].worldY = 10 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Sign(gp);
		gp.obj[3].worldX = 23 * gp.tileSize;
		gp.obj[3].worldY = 18 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Kulot(gp);
		gp.npc[0].worldX = gp.tileSize * 12;
		gp.npc[0].worldY = gp.tileSize * 13;
	}
	
	public void setMob() {
//		gp.mob[0] = new MOB_GreenSlime(gp);
//		gp.mob[0].worldX = gp.tileSize * 8;
//		gp.mob[0].worldY = gp.tileSize * 11;
//		
//		gp.mob[1] = new MOB_GreenSlime(gp);
//		gp.mob[1].worldX = gp.tileSize * 18;
//		gp.mob[1].worldY = gp.tileSize * 24;
	}
}

