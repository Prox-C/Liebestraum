package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed; 
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) { // NEW GAME
					gp.gameState = gp.playState;
					gp.stopMusic(gp.musicPlaying);
					gp.playMusic(13);
					gp.musicPlaying = 13;
					gp.restart();
				}
				if(gp.ui.commandNum == 1) { // QUIT
					System.exit(0);
				}
				
			}
		}
		
		//PLAY STATE
		else if(gp.gameState == gp.playState) {
			
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if (code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.optionState;
			}
			
		} 
		
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}

			
		}
		//DIALOG STATE
		else if(gp.gameState == gp.dialogState) {
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		//GAME OVER STATE
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		
		//OPTION STATE
		else if(gp.gameState == gp.optionState) {
			optionState(code);
		}
		
	}
	
	public void optionState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 4;
		}
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
				}
				if(gp.ui.commandNum == 1 && gp.sfx.volumeScale > 0) {
					gp.sfx.volumeScale--;
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
				}
				if(gp.ui.commandNum == 1 && gp.sfx.volumeScale < 5) {
					gp.sfx.volumeScale++;
				}
			}
		}
	}
	
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			//play sound
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			//play sound
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) { //RETRY
				gp.gameState = gp.playState;
				gp.retry();
				gp.stopMusic(gp.musicPlaying);
				if(gp.player.stage == 2) {
					gp.playMusic(14);
					gp.musicPlaying = 14;
				}
				if(gp.player.stage == 3) {
					gp.playMusic(6);
					gp.musicPlaying = 6;
				}
				if(gp.player.stage == 4) {
					gp.playMusic(6);
					gp.musicPlaying = 6;
				}
			}
			else if(gp.ui.commandNum == 1) {//EXIT
				gp.gameState = gp.titleState;
				gp.restart();
				gp.playMusic(12);
				gp.musicPlaying = 12;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		 
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

	
}
