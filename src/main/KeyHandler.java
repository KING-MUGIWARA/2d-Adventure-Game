package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //DEBUGG
    boolean checkDrawTime = false;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        }

    @Override
    public void keyPressed(KeyEvent e) {
       int code = e.getKeyCode(); //returns the number of the key that was pressed
        if (gp.gameState == gp.playState) { // Only allow movement in playState
       if (code == KeyEvent.VK_W) upPressed = true;
       if (code == KeyEvent.VK_S) downPressed = true;
       if (code == KeyEvent.VK_A) leftPressed = true;
       if (code == KeyEvent.VK_D) rightPressed = true;
   }

   if (code == KeyEvent.VK_P) {    
    if (gp.gameState == gp.playState) {
        gp.gameState = gp.pauseState;
        gp.music.pause(); // Pause the music when pausing
    } 
    else if (gp.gameState == gp.pauseState) {
        gp.gameState = gp.playState;
        gp.music.resume(); // Resume the music when unpaused
    }
}

       //DEBUGG
       
       if (code == KeyEvent.VK_T){    
           if(checkDrawTime == false){
               checkDrawTime = true;
           }
           else if(checkDrawTime == true){
               checkDrawTime = false;
       }
       
    }
  }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
       if (code == KeyEvent.VK_W){ 
          upPressed = false; 
       }
       if (code == KeyEvent.VK_S){    
           downPressed = false;
       }
       if (code == KeyEvent.VK_A){    
           leftPressed = false;
       }
       if (code == KeyEvent.VK_D){    
           rightPressed = false;
       }
    }
    
}
