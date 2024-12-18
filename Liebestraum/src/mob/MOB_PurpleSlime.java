package mob;

import java.util.Random;
import entity.Entity;
import main.GamePanel;

public class MOB_PurpleSlime extends Entity {
    GamePanel gp;
    private boolean isChasingPlayer = false;
    private int chaseCounter = 0;
    private final int CHASE_RADIUS = 100; // Distance in pixels to start chasing the player

    public MOB_PurpleSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Purple Slime";
        speed = 1;
        type = 2;
        mobID = 2;
        maxHealth = 6;
        life = maxHealth;
        damage = 3;
        solidArea.x = 9;
        solidArea.y = 8;
        solidArea.width = 28;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/mob/violet-3", gp.tileSize, gp.tileSize);
        up2 = setup("/mob/violet-2", gp.tileSize, gp.tileSize);
        down1 = setup("/mob/violet-3", gp.tileSize, gp.tileSize);
        down2 = setup("/mob/violet-2", gp.tileSize, gp.tileSize);
        right1 = setup("/mob/violet-1", gp.tileSize, gp.tileSize);
        right2 = setup("/mob/violet-2", gp.tileSize, gp.tileSize);
        left1 = setup("/mob/violet-1", gp.tileSize, gp.tileSize);
        left2 = setup("/mob/violet-2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        // Check if the player is within the chase radius
        int mobX = this.worldX + solidArea.x + solidArea.width / 2;
        int mobY = this.worldY + solidArea.y + solidArea.height / 2;
        int playerX = gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width / 2;
        int playerY = gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height / 2;

        int distanceToPlayer = (int) Math.sqrt(Math.pow(playerX - mobX, 2) + Math.pow(playerY - mobY, 2));

        if (distanceToPlayer <= CHASE_RADIUS) {
            isChasingPlayer = true;
            chaseCounter = 300; // Reset chase timer
        }

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
