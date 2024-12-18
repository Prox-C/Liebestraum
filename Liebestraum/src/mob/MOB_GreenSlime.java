package mob;

import java.util.Random;
import entity.Entity;
import main.GamePanel;

public class MOB_GreenSlime extends Entity {
    GamePanel gp;
    private boolean isChasingPlayer = false;
    private int chaseCounter = 0;

    public MOB_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Green Slime";
        speed = 1;
        type = 2;
        mobID = 0;
        maxHealth = 5;
        life = maxHealth;
        solidArea.x = 9;
        solidArea.y = 8;
        solidArea.width = 28;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/mob/greenslime-3", gp.tileSize, gp.tileSize);
        up2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
        down1 = setup("/mob/greenslime-3", gp.tileSize, gp.tileSize);
        down2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
        right1 = setup("/mob/greenslime-1", gp.tileSize, gp.tileSize);
        right2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
        left1 = setup("/mob/greenslime-1", gp.tileSize, gp.tileSize);
        left2 = setup("/mob/greenslime-2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        // Handle chasing logic
        if (isChasingPlayer) {
            chaseCounter--;
            if (chaseCounter <= 0) {
                isChasingPlayer = false; // Stop chasing after timeout
            } else {
                chasePlayer();
                return; // Skip random movement while chasing
            }
        }

        // Random movement logic
        if (actionLockCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "right";
            } else {
                direction = "left";
            }
            actionLockCounter = 0;
        }
    }

    public void flea() {
        isChasingPlayer = true; // Start chasing the player
        chaseCounter = 300;    // Chase for 300 frames (~5 seconds)
    }

    // Method to calculate and set the direction towards the player
    public void chasePlayer() {
        int mobX = this.worldX;
        int mobY = this.worldY;
        int playerX = gp.player.worldX;
        int playerY = gp.player.worldY;

        int xDifference = playerX - mobX;
        int yDifference = playerY - mobY;

        if (Math.abs(xDifference) > Math.abs(yDifference)) {
            if (xDifference > 0) {
                direction = "right";
            } else {
                direction = "left";
            }
        } else {
            if (yDifference > 0) {
                direction = "down";
            } else {
                direction = "up";
            }
        }
    }
}
