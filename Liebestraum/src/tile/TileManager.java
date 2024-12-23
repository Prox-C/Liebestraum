package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	
	ArrayList<String> fileNames= new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		//READ TILE DATA
		InputStream is = getClass().getResourceAsStream("/maps/memorylane_data.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
		} catch(IOException e){
			e.printStackTrace();
		}	
		
		tile = new Tile[fileNames.size()];
		getTileImage();

		
		is = getClass().getResourceAsStream("/maps/memorylane.txt");
		br = new BufferedReader(new InputStreamReader(is));
		
		try {
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
			
			br.close();

;		}catch(IOException e) {
			System.out.println("Exception");
		}
		
		loadMap("/maps/memorylane.txt", 0);
		loadMap("/maps/cleric_house.txt", 1);
		loadMap("/maps/ravalon.txt", 2);
		loadMap("/maps/beach.txt", 3);
		loadMap("/maps/trail_chamber.txt", 4);
		loadMap("/maps/level1.txt", 5);
		loadMap("/maps/level2.txt", 6);
		loadMap("/maps/level3.txt", 7);
	}
	
	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br =  new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch(IOException e) {
			
		}
	}
	
	public void getTileImage() {
		for(int i = 0; i < fileNames.size(); i++) {
			String fileName;
			boolean collision;  
			
			//GET FILE NAME
			fileName = fileNames.get(i);
			
			if(collisionStatus.get(i).equals("true")) {
				collision = true;
			}
			else {
				collision = false;
			}
			
			setup(i, fileName, collision);
		}
	}
	
	public void setup(int i, String tileCode, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[i] = new Tile();
			tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + tileCode));
			tile[i].image = uTool.scaleImage(tile[i].image, gp.tileSize, gp.tileSize);
			tile[i].collision = collision;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum  = mapTileNum[gp.currentMap][worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			 
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;

			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}
	}
}


