package GameLogic.ScreenItems;

import GameLogic.Enums.Movement;
import GameLogic.ScreenItems.PacmanObject;
/**
     * @return x-axis velocity of movable pacman object
     */
public class MovingObject extends PacmanObject {
    //Variables
    private double velocityX, velocityY;

    public MovingObject(){
        super();
        velocityX = 0.0;
        velocityY = 0.0;
    }
/**
     * @return x-axis velocity of movable pacman object
     */
    /** Sets the velocity to the amount given by parameters.
     * @param velocityX velocity of x-axis
     * @param velocityY velocity of y-axis
     */
    public void setVelocity(int velocityX, int velocityY) {
        if (velocityX > 0 && velocityY > 0){
            this.velocityX = velocityX;
            this.velocityY = velocityY;
        }
        //Else display error message
    }

    /**
     * @return x-axis velocity of movable pacman object
     */
    public double getVelocityX() {
        return this.velocityX;
    }

    /**
     * @return y-axis velocity of movable pacman object
     */
    public double getVelocityY() {
        return this.velocityY;
    }

    // move is called each time a arrow key is
    // pressed, pacman cannot move to 2 different
    // ways at the same time (etc. up and left is forbidden)
    /** Manages pacman objects position. It's called each time user presses an arrow button
     * @param movement Movement enumeration, indicating the direction Pacman will move
     */
    public void move(Movement movement) {
        if (movement == Movement.DOWN) {
            this.changePosition(0, 20);
        }
        else if (movement == Movement.UP) {
            this.changePosition(0, -20);
        }
        else if (movement == Movement.LEFT) {
            this.changePosition(20, 0);
        }
        else if (movement == Movement.RIGHT) {
            this.changePosition(-20, 0);
        }
    }
}
