package main;

import entity.NPC_Kulot;
import object.OBJ_Boulder;
//import object.OBJ_Chest;
//import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Pickaxe;
import object.OBJ_SpeedPotion;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 21 * gp.tileSize;
		gp.obj[0].worldY = 21 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Boulder(gp);
		gp.obj[1].worldX = 26 * gp.tileSize;
		gp.obj[1].worldY = 19 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Pickaxe(gp);
		gp.obj[2].worldX = 13 * gp.tileSize;
		gp.obj[2].worldY = 27 * gp.tileSize;
		
		gp.obj[3] = new OBJ_SpeedPotion(gp);
		gp.obj[3].worldX = 20 * gp.tileSize;
		gp.obj[3].worldY = 10 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Key(gp);
		gp.obj[4].worldX = 9 * gp.tileSize;
		gp.obj[4].worldY = 9 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Kulot(gp);
		gp.npc[0].worldX = gp.tileSize * 8;
		gp.npc[0].worldY = gp.tileSize * 11;
	}
}

