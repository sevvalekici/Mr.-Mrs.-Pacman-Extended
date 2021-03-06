package GameLogic.UpdateManager;

import GameLogic.Enums.Movement;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import java.util.List;
import java.util.Random;

import static GameLogic.Constants.COUNTDOWN;
import static GameLogic.Constants.SCATTER;

/**
 * Created by talhaseker on 5.11.2017.
 */
public class GhostController {

    private List<Ghost> ghosts;
    private Pacman pm;

    public GhostController(List<Ghost> ghosts, Pacman pm){
        this.ghosts = ghosts;
        this.pm = pm;
    }

    public void move(){
        for (Ghost ghost:ghosts) {
            specificMove(ghost);
        }
    }

    private void specificMove(Ghost ghost){
        switch (ghost.getGhostType()){
            case PINKY:
                pinkyMove(ghost);
                break;
            case CLYDE:
                clydeMove(ghost);
                break;
            case BLINKY:
                blinkyMove(ghost);
                break;
            case INKY:
                inkyMove(ghost);
                break;
            default:
                break;
        }

    }

    private void inkyMove(Ghost ghost){
        int cageTimer = 0;
        boolean MovementUP = false;
        int mod = 7;
        int deathTimer = 40;
        int pacLives = 5;

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            pacLives = pm.getLivesLeft();
            cageTimer = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimer > 0){
            if(cageTimer%mod==0){
                if(MovementUP){
                    ghost.curMovement = Movement.UP;
                }
                else{
                    ghost.curMovement = Movement.DOWN;
                }
                MovementUP = !MovementUP;
            }
            cageTimer --;
            if(cageTimer <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY > 250 && curY <= 308) && (curX >= 231 && curX <= 368)) {
                cageTimer = deathTimer;
            }
            int targetX = 250, targetY = 350;
            if(ghost.isScattered()){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
            } else if(!ghost.isAttacking){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
                ghost.countdownTimer --;
            } else {

                targetX = pm.getXpos();
                targetY = pm.getYpos();
                ghost.countdownTimer --;
            }

            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }
            tryToMove(ghost, curX, curY, targetX, targetY);

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private void pinkyMove(Ghost ghost){
        int cageTimer = 0;
        boolean MovementUP = false;
        int mod = 7;
        int minDistance = 100;
        int deathTimer = 40;
        int pacLives = 5;

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            pacLives = pm.getLivesLeft();
            cageTimer = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimer > 0){
            if(cageTimer%mod==0){
                if(MovementUP){
                    ghost.curMovement = Movement.UP;
                }
                else{
                    ghost.curMovement = Movement.DOWN;
                }
                MovementUP = !MovementUP;
            }
            cageTimer --;
            if(cageTimer <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            // check to see if in center (just spawned)
            if ((curY > 250 && curY <= 308) && (curX >= 231 && curX <= 368)) {
                cageTimer = deathTimer;
            }

            int targetX = 250, targetY = 350;
            if(ghost.isScattered()){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
            }  else if(!ghost.isAttacking){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
                ghost.countdownTimer --;
            }else {
                targetX = pm.getXpos();
                targetY = pm.getYpos();
                ghost.countdownTimer --;
            }
            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }
            // if pinky is within a set distance from pacman
            if(getDistanceToPacman(curX, curY, targetX, targetY) < minDistance){
                // it tries to go the same Movement as pacman
                try{
                    ghost.curMovement = pm.curMovement;
                }
                // just incase something goes wrong, it sets the Movement as the last Movement
                catch(NullPointerException NPE){
                    ghost.curMovement = ghost.lastMovement;
                }
                // and if it can't go that Movement, it'll just move according to the standard
                // ai and try to eat pacman
                if(!moveIsAllowed(ghost.curMovement)){
                    tryToMove(ghost, curX, curY, targetX, targetY);
                }
            }
            else{
                tryToMove(ghost, curX, curY, targetX, targetY);
            }

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private void blinkyMove(Ghost ghost){
        int cageTimer = 0;
        boolean MovementUP = false;
        int mod = 7;
        int deathTimer = 40;
        int pacLives = 5;

        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            pacLives = pm.getLivesLeft();
            cageTimer = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimer > 0){
            if(cageTimer%mod==0){
                if(MovementUP){
                    ghost.curMovement = Movement.UP;
                }
                else{
                    ghost.curMovement = Movement.DOWN;
                }
                MovementUP = !MovementUP;
            }
            cageTimer --;
            if(cageTimer <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY > 249 && curY <= 310) && (curX >= 230 && curX <= 370)) {
                cageTimer = deathTimer;
            }
            int targetX = 250, targetY = 350;
            if(ghost.isScattered()){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
            } else if(!ghost.isAttacking){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
                ghost.countdownTimer --;
            }else {
                targetX = pm.getXpos();
                targetY = pm.getYpos();
                ghost.countdownTimer --;
            }

            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }

            tryToMove(ghost, curX, curY, targetX, targetY);

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private void clydeMove(Ghost ghost){

        int cageTimer = 0;
        boolean MovementUP = false;
        int mod = 7;
        int deathTimer = 40;
        int minDistance = 100;
        int pacLives = 5;
        Random numGen = new Random();
        boolean moveOpposite = false;
        int oppCounter = 0;


        int curX = ghost.getXpos();
        int curY = ghost.getYpos();

        if(pm.getLivesLeft() != pacLives){
            pacLives = pm.getLivesLeft();
            cageTimer = COUNTDOWN;
            ghost.isAttacking = false;
            ghost.countdownTimer = SCATTER;
        }

        if(cageTimer > 0){
            if(cageTimer%mod==0){
                if(MovementUP){
                    ghost.curMovement = Movement.UP;
                }
                else{
                    ghost.curMovement = Movement.DOWN;
                }
                MovementUP = !MovementUP;
            }
            cageTimer --;
            if(cageTimer <= 0){
                ghost.lastMovement = Movement.LEFT;
                ghost.setXpos(ghost.getInitialOutOfCagePos().x);
                ghost.setYpos(ghost.getInitialOutOfCagePos().y);
            }
        } else {
            if ((curY > 249 && curY <= 310) && (curX >= 230 && curX <= 370)) {
                cageTimer = deathTimer;
            }
            int targetX = 250, targetY = 350;
            if(ghost.isScattered()){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
            }  else if(!ghost.isAttacking){
                targetX = 558 - pm.getXpos();
                targetY = 551 - pm.getYpos();
                ghost.countdownTimer --;
            }else {
                targetX = pm.getXpos();
                targetY = pm.getYpos();
                if (getDistanceToPacman(curX, curY, targetX, targetY) > minDistance){
                    try{
                        ghost.curMovement = pm.curMovement;
                    }
                    catch(NullPointerException NPE){
                        ghost.curMovement = ghost.lastMovement;
                    }
                    if (moveOpposite) {
                        if (oppCounter++ == 50) {
                            moveOpposite = false;
                            oppCounter = 0;
                        }
                        targetX = 558 - targetX;
                        targetY = 551 - targetY;
                    } else {
                        if (numGen.nextInt(40) == 1) { // 1 in 40 chance
                            moveOpposite = true;
                        }
                        if(ghost.curMovement == Movement.UP){
                            targetY -= minDistance/2;
                        }
                        else if(ghost.curMovement == Movement.DOWN){
                            targetY += minDistance/2;
                        }
                        else if(ghost.curMovement == Movement.LEFT){
                            targetX -= minDistance/2;
                        }
                        else{
                            targetX += minDistance/2;
                        }
                    }
                }
                ghost.countdownTimer --;
            }
            if(ghost.countdownTimer <= 0){
                ghost.flipAttack();
            }
            tryToMove(ghost, curX, curY, targetX, targetY);

        }
        ghost.lastMovement = ghost.curMovement;
    }

    private double getDistanceToPacman(int Gx, int Gy, int Px, int Py){
        double distance = 0;
        distance = Math.sqrt(Math.pow((Px - Gx), 2) + Math.pow((Py - Gy), 2));
        return distance;
    }

    private void tryToMove (Ghost ghost, int curX, int curY, int targetX, int targetY){
        int horizontalDifference = curX - targetX;
        int verticalDifference = curY - targetY;
        Movement preferredHorizontal = horizontalDifference > 0 ? Movement.LEFT : Movement.RIGHT;
        Movement preferredVertical = verticalDifference > 0 ? Movement.UP : Movement.DOWN;
        boolean verticalMoreImportant = Math.abs(verticalDifference) > Math.abs(horizontalDifference);
        if (verticalMoreImportant)
            ghost.curMovement = preferredVertical;
        else
            ghost.curMovement = preferredHorizontal;
        if (!moveIsAllowed(ghost.curMovement)) {
            if (verticalMoreImportant) {
                if (ghost.lastMovement == Movement.LEFT || ghost.lastMovement == Movement.RIGHT) {
                    ghost.curMovement = ghost.lastMovement;
                    if (!moveIsAllowed(ghost.curMovement))
                        ghost.curMovement = ghost.curMovement == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                } else {
                    ghost.curMovement = preferredHorizontal;
                    if (!moveIsAllowed(ghost.curMovement)) {
                        ghost.curMovement = preferredHorizontal == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                        if (!moveIsAllowed(ghost.curMovement))
                            ghost.curMovement = preferredVertical == Movement.UP ? Movement.DOWN : Movement.UP;
                    }
                }
            } else {
                if (ghost.lastMovement == Movement.UP || ghost.lastMovement == Movement.DOWN) {
                    ghost.curMovement = ghost.lastMovement;
                    if (!moveIsAllowed(ghost.curMovement))
                        ghost.curMovement = ghost.curMovement == Movement.UP ? Movement.DOWN : Movement.UP;
                } else {
                    ghost.curMovement = preferredVertical;
                    if (!moveIsAllowed(ghost.curMovement)) {
                        ghost.curMovement = preferredVertical == Movement.UP ? Movement.DOWN : Movement.UP;
                        if (!moveIsAllowed(ghost.curMovement))
                            ghost.curMovement = preferredHorizontal == Movement.LEFT ? Movement.RIGHT : Movement.LEFT;
                    }
                }
            }
        }
    }

    private boolean moveIsAllowed(Movement curMovement){
        return true;
    }
}
