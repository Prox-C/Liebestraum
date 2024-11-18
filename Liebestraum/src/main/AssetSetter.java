package main;

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
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 24 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Boulder();
		gp.obj[1].worldX = 15 * gp.tileSize;
		gp.obj[1].worldY = 19 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Pickaxe();
		gp.obj[2].worldX = 20 * gp.tileSize;
		gp.obj[2].worldY = 25 * gp.tileSize;
		
		gp.obj[3] = new OBJ_SpeedPotion();
		gp.obj[3].worldX = 30 * gp.tileSize;
		gp.obj[3].worldY = 30 * gp.tileSize;
	}
}

