package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
   public void draw(Graphics2D g2) {
    this.g2 = g2;

    if (gp.gameState == gp.playState) {
        playTime += (double) 1 / 60; // Update time only in play mode
    }

    if (gp.gameState == gp.pauseState) {
        drawpauseScreen();
    }

    if (gameFinished) {  // Ensure this block is inside the draw method
        String text;
        int textLength;
        int x;
        int y;

        // "YOU FOUND THE TREASURE!" - Golden Yellow
        g2.setFont(new Font("Georgia", Font.BOLD, 40));
        g2.setColor(new Color(255, 215, 0)); // Golden Yellow
        text = "You Found The Treasure!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        // "YOUR TIME IS: xx.xx" - White with Black Shadow
        g2.setFont(new Font("Arial", Font.PLAIN, 30));

        // Draw Black Shadow
        g2.setColor(Color.BLACK);
        text = "Your Time is: " + dFormat.format(playTime) + "!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 + (gp.tileSize * 4);
        g2.drawString(text, x + 2, y + 2); // Offset for shadow

        // Draw White Foreground
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // "$CONGRATS$!" - Neon Green or Bright Cyan
        g2.setFont(new Font("Impact", Font.BOLD, 80));
        g2.setColor(new Color(57, 255, 20)); // Neon Green
        // g2.setColor(new Color(0, 255, 255)); // Bright Cyan (Uncomment if you prefer this)

        text = "$CONGRATS$!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = (gp.screenWidth - textLength) / 2;
        y = gp.screenHeight / 2 + (gp.tileSize * 2);
        g2.drawString(text, x, y);
        
        gp.gameThread = null;
        
    } else {
        g2.setFont(arial_40);
        g2.setColor(Color.orange);
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 50);

        // Display time but do not update when paused
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

        // MESSAGE
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}

    public void drawpauseScreen(){
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "paused";
        int x = getXforCenteredText(text);
        
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
