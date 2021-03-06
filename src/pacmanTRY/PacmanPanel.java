package pacmanTRY;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 *
 * Baßak Þevval Ekici
 * 
 */
public class PacmanPanel extends JPanel{
  
  final int width = 900,height = 900;
  Pacman pacman;
  Ghost ghost;
  int ghostX;
  int ghostY;
  Timer timer;
  ImageIcon pacmanImg;
  ImageIcon ghostImg;
  ImageIcon background;
  boolean inGame= true;
  
  public PacmanPanel(){
    ghost = new Ghost();
    pacman = new Pacman();
    addKeyListener(new PacmanAdapter());
    setBackground(Color.white);
    setFocusable(true);
    setPreferredSize(new Dimension(width, height));  
    pacmanImg = new ImageIcon("pacmanTRY/pacman.png");
    background = new ImageIcon("pacmanTRY/background.png");
    ghostImg = new ImageIcon("pacmanTRY/ghost"+((int)(Math.random() * 5)+1)+".png");   
    timer = new Timer(100, new GhostListener() );
    timer.start();
  }
     
  public void locateGhost(){
     ghostImg = new ImageIcon("pacmanTRY/ghost"+((int)(Math.random() * 5)+1)+".png");
     int rand1 = ((int)(Math.random() * 50)*10);
      ghost.ghostX = rand1;  
      int rand2 = ((int)(Math.random() * 50)*10);
      ghost.ghostY = rand2;
  }
  public void paintComponent(Graphics g) { 
    super.paintComponent(g);
    g.drawImage(background.getImage(), 0, 0, null);  
    if (inGame) {  
      g.drawImage(ghostImg.getImage(), ghost.ghostX, ghost.ghostY, this);
      g.drawImage(pacmanImg.getImage(), pacman.pacmanX, pacman.pacmanY, this);  
      } 
  else {  
    g.setColor(Color.black);
    g.drawString("Game Over", width / 2, height / 2);
    }        
  }
    
  public void checkGhost(){
    if ((pacman.pacmanX == ghost.ghostX) && (pacman.pacmanY == ghost.ghostY)) {  
      locateGhost();     
    }
  }
  

  
  public void checkWalls(){
    
    if (pacman.pacmanY >= height){
      inGame = false;
    }
    if (pacman.pacmanY < 0){
      inGame = false;
    }
    if (pacman.pacmanX >= width){
      inGame = false;
    }
    if (pacman.pacmanX < 0){
      inGame = false;
    }
    if(!inGame){
      timer.stop();
    }
  }
  
  
  class GhostListener implements ActionListener{ 
    
    public void actionPerformed(ActionEvent e){
      
      if (inGame) {   
        checkGhost();
        checkWalls();
        pacman.move();
      }
      
      repaint();
    }
  }
  
  
  class PacmanAdapter extends KeyAdapter {
    
    public void keyPressed(KeyEvent e){
      
      int key = e.getKeyCode();
      
      if (key == KeyEvent.VK_LEFT){
        pacman.left = true;
        pacman.up = false;
        pacman.down = false;  
        pacman.right = false;
      } 
      if (key == KeyEvent.VK_RIGHT){
        pacman.right = true;
        pacman.up = false;
        pacman.down = false;
        pacman.left = false;
      }
      
      if (key == KeyEvent.VK_UP){
        pacman.up = true;
        pacman.right = false;
        pacman.left = false;
        pacman.down = false;
      }
      if (key == KeyEvent.VK_DOWN){
        pacman.down = true;
        pacman.right = false;
        pacman.left = false;
        pacman.up = false;
      }
    }
  }
}
