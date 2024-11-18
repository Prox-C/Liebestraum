package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/worldmap01.txt");
	}
	
	public void loadMap(String filePath) {
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
					
					mapTileNum[col][row] = num;
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
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/Grass_Middle.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/Path_Middle.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/Water_Middle.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/bush.png"));
			tile[3].collision = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_nw.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_n.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_ne.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_w.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_e.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_sw.png"));
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_s.png"));
			
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_se.png"));
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_c_nw.png"));
			
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_c_ne.png"));
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_c_sw.png"));
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/water_c_se.png"));
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_nw.png"));
			
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_n.png"));
			
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_ne.png"));
			
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_w.png"));
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_e.png"));
			
			tile[21] = new Tile();
			tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_sw.png"));
			
			tile[22] = new Tile();
			tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_s.png"));
			
			tile[23] = new Tile();
			tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_se.png"));
			
			tile[24] = new Tile();
			tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_c_nw.png"));
			
			tile[25] = new Tile();
			tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_c_ne.png"));
			
			tile[26] = new Tile();
			tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_c_sw.png"));
			
			tile[27] = new Tile();
			tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/path_c_se.png"));
			
			tile[28] = new Tile();
			tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_nw.png"));
			
			tile[29] = new Tile();
			tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_n.png"));
			
			tile[30] = new Tile();
			tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_ne.png"));
			
			tile[31] = new Tile();
			tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_w.png"));
			
			tile[32] = new Tile();
			tile[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_e.png"));
			
			tile[33] = new Tile();
			tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_sw.png"));
			
			tile[34] = new Tile();
			tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_s.png"));
			
			tile[35] = new Tile();
			tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_se.png"));
			
			tile[36] = new Tile();
			tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_c_nw.png"));
			
			tile[37] = new Tile();
			tile[37].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_c_ne.png"));
			
			tile[38] = new Tile();
			tile[38].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_c_sw.png"));
			
			tile[39] = new Tile();
			tile[39].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cliff/cliff_c_se.png"));
			
			tile[40] = new Tile();
			tile[40].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/grass2.png"));
			
			tile[41] = new Tile();
			tile[41].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/grass3.png"));
			
			tile[42] = new Tile();
			tile[42].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/small_rock.png"));
			
			tile[43] = new Tile();
			tile[43].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/flower.png"));
			
			tile[44] = new Tile();
			tile[44].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/rose.png"));
			
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum  = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			 
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;

			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}
	}
}


