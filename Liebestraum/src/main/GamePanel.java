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

	//ENTITY
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity mob[] = new Entity[20];
	public ArrayList<Entity> entityList = new ArrayList<>();
	
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogState = 3;
	
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
		playMusic(6);
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
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			//MOB
			for(int i = 0; i < mob.length; i++) {
				if(mob[i] != null) {
					if(mob[i].alive == true && mob[i].dying == false) {
						mob[i].update();
					}
					if(mob[i].alive == false) {
						mob[i] = null;
					}
				}
			}
		}
		if(gameState == pauseState) {
			
		}
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
					for(int i = 0; i < npc.length; i++) {
						if(npc[i] != null) {
							entityList.add(npc[i]);
						}
					}
					for(int i = 0; i < obj.length; i++) {
						if(obj[i] != null) {
							entityList.add(obj[i]);
						}
					}
					for(int i = 0; i < mob.length; i++) {
						if(mob[i] != null) {
							entityList.add(mob[i]);
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
