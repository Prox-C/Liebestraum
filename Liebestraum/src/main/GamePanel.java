package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16: default size hit map tiles ngan player/npcs
	final int scale = 3; // para dako kitaon it usa ka map tile ha aton mga screens
	
	public final int tileSize = originalTileSize * scale; //48 tiles (16x3)
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	// Thus, in terms of pixels, The resolution is 768(48x16) by 576(48x12)
	
	//WORLD SETTINGS
	public int maxWorldCol;
	public int maxWorldRow;
	public final int maxMap = 10;
	public int currentMap = 0; // default map

	//FPS
	int FPS = 60;
	
	//SYSTEM 
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();	
	Sound sfx = new Sound();
	public 	CollisionChecker collChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	public int musicPlaying = 0;

	//ENTITY
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][10];
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity mob[][]= new Entity[maxMap][20];
	public ArrayList<Entity> entityList = new ArrayList<>();
	
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogState = 3;
	public final int gameOverState = 4;
	public final int optionState = 5;
	public final int transitionState = 6;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // all drawings from this component will be done in an offscreen painting buffer
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		gameState = titleState;
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMob();
		playMusic(12);
		musicPlaying = 12;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime; 
		
		
		while (gameThread != null) {
//			System.out.println("Game running");
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime; 
			
			if (delta >= 1) {
				//update info
				update();
				
				//draw updated info into screen
				repaint();
				
				delta--;
			}
			
		}
	}
	
	public void update() {
		
		if(gameState == playState) {
			//PLAYER
			player.update();
			
			//NPC
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			//MOB
			for(int i = 0; i < mob[1].length; i++) {
				if(mob[currentMap][i] != null) {
					if(mob[currentMap][i].alive == true && mob[currentMap][i].dying == false) {
						mob[currentMap][i].update();
					}
					if(mob[currentMap][i].alive == false) {
						mob[currentMap][i] = null;
					}
				}
			}
		}
		if(gameState == pauseState) {
			
		}
	}
	
	public void retry() {
		player.setDefaultPositions();
		player.restoreHealth();
		player.blueSlimesKilled = 0;
		aSetter.setNPC();
		aSetter.setMob();
	}
	
	public void restart() {
		currentMap = 0;
		player.setDefaultValues();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMob();
		eHandler.resetEvents();
		
		//PLAYER INVENTORY
		player.armed = false;
		player.silver_keys = 0;
		player.golden_keys = 0;
		player.pickaxeDurability = 0;
		player.axeDurability = 0;
		player.fishrodDurability = 0;
		player.shovelDurability = 0;
		
		//MISIONS
		player.blueSlimesKilled = 0;
		player.greenSlimesKilled = 0;
		player.purpleSlimesKilled = 0;
		player.slimeQuest = false;
		
		player.stage = 0;
		player.questDone = false;
	}
	
	public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g); 
		Graphics2D g2 = (Graphics2D)g;
		
		//TITLE SCREEN
				if(gameState == titleState) {
					
					ui.draw(g2);
				}
				else {
					
					//TILE
					tileM.draw(g2);
					
					//ADDS ENTITIES TO LIST
					entityList.add(player);
					for(int i = 0; i < npc[1].length; i++) {
						if(npc[currentMap][i] != null) {
							entityList.add(npc[currentMap][i]);
						}
					}
					for(int i = 0; i < obj[1].length; i++) {
						if(obj[currentMap][i] != null) {
							entityList.add(obj[currentMap][i]);
						}
					}
					for(int i = 0; i < mob[1].length; i++) {
						if(mob[currentMap][i] != null) {
							entityList.add(mob[currentMap][i]);
						}
					}
					
					//SORT
					Collections.sort(entityList, new Comparator<Entity>() {

						@Override
						public int compare(Entity e1, Entity e2) {
							int result = Integer.compare(e1.worldY, e2.worldY);
							return result;
						}
						
					});
					
					//DRAW ENTITIES
					for(int i = 0; i < entityList.size(); i++) {
						entityList.get(i).draw(g2);
					}
					//CLEAR LIST
					entityList.clear();
					//UI
					ui.draw(g2);
				}
				g2.dispose(); // release system resources
			}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic(int i) {
		music.stop();
	}
	public void playSE(int i) {
		sfx.setFile(i);
		sfx.play();
	}
}
