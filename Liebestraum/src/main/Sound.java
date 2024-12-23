package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	
	int volumeScale = 3;
	float volume; 
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sfx/zen_garden_theme.wav");
		soundURL[1] = getClass().getResource("/sfx/buff.wav");
		soundURL[2] = getClass().getResource("/sfx/key.wav");
		soundURL[3] = getClass().getResource("/sfx/obstacle-2.wav");
		soundURL[4] = getClass().getResource("/sfx/equipment.wav");
		soundURL[5] = getClass().getResource("/sfx/mission_accomplished.wav");
		soundURL[6] = getClass().getResource("/sfx/waterfall.wav");
		soundURL[7] = getClass().getResource("/sfx/attack.wav");
		soundURL[8] = getClass().getResource("/sfx/mob_hit.wav");
		soundURL[9] = getClass().getResource("/sfx/player_hurt.wav");
		soundURL[10] = getClass().getResource("/sfx/heal.wav");
		soundURL[11] = getClass().getResource("/sfx/game_over.wav");
		
		soundURL[12] = getClass().getResource("/sfx/liebestraum.wav");
		soundURL[13] = getClass().getResource("/sfx/flower-waltz.wav");
		soundURL[14] = getClass().getResource("/sfx/blackkeys.wav");
		soundURL[15] = getClass().getResource("/sfx/torrent.wav");

	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void checkVolume() {
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		
		fc.setValue(volume);
	}
}

