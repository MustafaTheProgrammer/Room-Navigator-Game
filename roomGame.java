import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

class roomGame extends JFrame implements KeyListener{
    public static int width = 10;
    public static int height = 10;
    public static char[][] pixels = new char[width][height];
    public static int[] playerPos = {1, -1};
    public static int[] startPos = {1, -1};
    public static ArrayList<Integer> entities = new ArrayList<Integer>();
    public static int level = 1;
    public static int deaths = 0;
    public static float timer = 0.0f;
    public static boolean levelTransition = false;
    public static boolean runGame = true;
    boolean leftKey;
    boolean upKey;
    boolean rightKey;
    boolean downKey;

    public roomGame(){
        this.setTitle("Room Game");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        gameLoop();
    }

    private void gameLoop(){
        //level 1
        addWalls();
        addEntity(2, 8, -8);
        
        while (runGame){

            System.out.print("\033[H\033[2J");
            System.out.flush();

            //reset buffer
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    pixels[i][j] = '.';
                }
            }

            //player
            if (leftKey){
                playerPos[0]--;
            }
            if (rightKey){
                playerPos[0]++;
            }
            if (downKey){
                playerPos[1]--;
            }
            if (upKey){
                playerPos[1]++;
            }

            //entities
            for (int i = 0; i < entities.size(); i += 3){
                if (entities.get(i) == 0){
                    if (entities.get(i+1) == playerPos[0] && entities.get(i+2) == playerPos[1]){
                        if (leftKey == true){
                            playerPos[0]++;
                        }
                        else if (rightKey == true){
                            playerPos[0]--;
                        }
                        else if (downKey == true){
                            playerPos[1]++;
                        }
                        else if (upKey == true){
                            playerPos[1]--;
                        }
                    }
                    setPixel(entities.get(i+1), entities.get(i+2), '#');
                }
                else if (entities.get(i) == 1){
                    if (entities.get(i+1) == playerPos[0] && entities.get(i+2) == playerPos[1]){
                        playerPos[0] = startPos[0];
                        playerPos[1] = startPos[1];
                        deaths++;
                    }
                    setPixel(entities.get(i+1), entities.get(i+2), 'X');
                }
                else if (entities.get(i) == 2){
                    if (entities.get(i+1) == playerPos[0] && entities.get(i+2) == playerPos[1]){
                        playerPos[0] = startPos[0];
                        playerPos[1] = startPos[1];
                        level++;
                        levelTransition = true;
                        
                    }
                    setPixel(entities.get(i+1), entities.get(i+2), '$');
                }
            }

            if (levelTransition){
                for (int i = entities.size()-1; i >= 0; i--){
                    entities.remove(i);
                }
                addWalls();
                //loading levels
                if (level == 2){
                    addEntity(0, 1, -4);
                    addEntity(0, 2, -4);
                    addEntity(0, 3, -4);
                    addEntity(0, 4, -4);
                    addEntity(0, 1, -5);
                    addEntity(0, 2, -5);
                    addEntity(0, 3, -5);
                    addEntity(0, 4, -5);
                    addEntity(2, 2, -7);
                    setStartPos(1, -1);
                }
                if (level == 3){
                    addEntity(0, 1, -3);
                    addEntity(0, 2, -3);
                    addEntity(0, 3, -3);
                    addEntity(0, 4, -3);
                    addEntity(0, 5, -3);
                    addEntity(0, 4, -6);
                    addEntity(0, 5, -6);
                    addEntity(0, 6, -6);
                    addEntity(0, 7, -6);
                    addEntity(0, 8, -6);
                    addEntity(2, 8, -8);
                    setStartPos(1, -1);
                }
                if (level == 4){
                    addEntity(0, 2, -4);
                    addEntity(0, 4, -4);
                    addEntity(0, 5, -4);
                    addEntity(0, 6, -4);
                    addEntity(0, 2, -5);
                    addEntity(0, 6, -5);
                    addEntity(0, 2, -6);
                    addEntity(0, 6, -6);
                    addEntity(0, 2, -7);
                    addEntity(0, 6, -7);
                    addEntity(0, 1, -7);
                    addEntity(0, 6, -8);
                    addEntity(2, 1, -8);
                    setStartPos(8, -8);
                }
                if (level == 5){
                    addEntity(0, 2, -1);
                    addEntity(0, 4, -1);
                    addEntity(0, 6, -1);
                    addEntity(0, 2, -2);
                    addEntity(0, 4, -2);
                    addEntity(0, 6, -2);
                    addEntity(0, 8, -2);
                    addEntity(0, 6, -3);
                    addEntity(0, 8, -3);
                    addEntity(0, 1, -5);
                    addEntity(0, 2, -5);
                    addEntity(0, 4, -5);
                    addEntity(0, 6, -6);
                    addEntity(0, 7, -6);
                    addEntity(0, 8, -6);
                    addEntity(0, 1, -7);
                    addEntity(0, 2, -7);
                    addEntity(2, 1, -6);
                    setStartPos(8, -1);
                }
                if (level == 6){
                    addEntity(0, 3, -3);
                    addEntity(0, 3, -4);
                    addEntity(0, 3, -5);
                    addEntity(0, 3, -6);
                    addEntity(0, 6, -1);
                    addEntity(0, 6, -2);
                    addEntity(0, 6, -3);
                    addEntity(0, 6, -6);
                    addEntity(0, 6, -7);
                    addEntity(0, 6, -8);
                    addEntity(2, 8, -4);
                    setStartPos(1, -4);
                }
                if (level == 7){
                    addEntity(0, 2, -2);
                    addEntity(0, 2, -3);
                    addEntity(0, 2, -4);
                    addEntity(0, 2, -5);
                    addEntity(0, 2, -6);
                    addEntity(0, 2, -7);
                    addEntity(0, 2, -8);
                    addEntity(0, 4, -1);
                    addEntity(0, 4, -2);
                    addEntity(0, 4, -3);
                    addEntity(0, 4, -4);
                    addEntity(0, 4, -5);
                    addEntity(0, 4, -6);
                    addEntity(0, 4, -7);
                    addEntity(0, 6, -2);
                    addEntity(0, 6, -3);
                    addEntity(0, 6, -4);
                    addEntity(0, 6, -5);
                    addEntity(0, 6, -6);
                    addEntity(0, 6, -7);
                    addEntity(0, 6, -8);
                    addEntity(2, 8, -8);
                    setStartPos(1, -8);
                }
                if (level == 8){
                    addEntity(0, 2, -2);
                    addEntity(0, 4, -2);
                    addEntity(0, 6, -2);
                    addEntity(0, 8, -2);
                    addEntity(0, 1, -4);
                    addEntity(0, 3, -4);
                    addEntity(0, 5, -4);
                    addEntity(0, 7, -4);
                    addEntity(0, 2, -6);
                    addEntity(0, 4, -6);
                    addEntity(0, 6, -6);
                    addEntity(0, 8, -6);
                    addEntity(0, 1, -8);
                    addEntity(0, 3, -8);
                    addEntity(0, 5, -8);
                    addEntity(0, 7, -8);
                    addEntity(2, 2, -8);
                    setStartPos(4, -1);
                }
                if (level == 9){
                    addEntity(1, 4, -2);
                    addEntity(1, 4, -3);
                    addEntity(1, 4, -4);
                    addEntity(1, 4, -5);
                    addEntity(1, 4, -6);
                    addEntity(1, 4, -7);
                    addEntity(2, 8, -4);
                    setStartPos(1, -4);
                }
                if (level == 10){
                    addEntity(1, 2, -3);
                    addEntity(1, 2, -4);
                    addEntity(1, 3, -6);
                    addEntity(1, 3, -7);
                    addEntity(1, 5, -2);
                    addEntity(1, 5, -3);
                    addEntity(1, 5, -7);
                    addEntity(1, 5, -8);
                    addEntity(1, 7, -1);
                    addEntity(1, 7, -2);
                    addEntity(1, 7, -5);
                    addEntity(1, 7, -6);
                    addEntity(2, 8, -5);
                    setStartPos(1, -4);
                }
                if (level == 11){
                    addEntity(1, 1, -2);
                    addEntity(1, 2, -2);
                    addEntity(1, 3, -2);
                    addEntity(1, 4, -2);
                    addEntity(1, 5, -2);
                    addEntity(1, 6, -2);
                    addEntity(1, 7, -2);
                    addEntity(1, 7, -3);
                    addEntity(1, 7, -4);
                    addEntity(1, 7, -5);
                    addEntity(1, 7, -6);
                    addEntity(1, 7, -7);
                    addEntity(1, 6, -7);
                    addEntity(1, 5, -7);
                    addEntity(1, 4, -7);
                    addEntity(1, 3, -7);
                    addEntity(1, 2, -7);
                    addEntity(1, 2, -6);
                    addEntity(1, 2, -5);
                    addEntity(1, 2, -4);
                    addEntity(1, 3, -4);
                    addEntity(1, 4, -4);
                    addEntity(1, 5, -4);
                    addEntity(1, 5, -5);
                    addEntity(2, 4, -5);
                    setStartPos(1, -1);
                }
                if (level == 12){
                    addEntity(1, 2, -2);
                    addEntity(1, 2, -3);
                    addEntity(1, 2, -4);
                    addEntity(1, 2, -5);
                    addEntity(1, 2, -6);
                    addEntity(1, 2, -7);
                    addEntity(1, 2, -8);
                    addEntity(1, 4, -1);
                    addEntity(1, 4, -2);
                    addEntity(1, 4, -3);
                    addEntity(1, 4, -4);
                    addEntity(1, 4, -5);
                    addEntity(1, 4, -6);
                    addEntity(1, 4, -7);
                    addEntity(1, 6, -2);
                    addEntity(1, 6, -3);
                    addEntity(1, 6, -4);
                    addEntity(1, 6, -5);
                    addEntity(1, 6, -6);
                    addEntity(1, 6, -7);
                    addEntity(1, 6, -8);
                    addEntity(2, 8, -8);
                    setStartPos(1, -8);
                }
                if (level == 13){
                    addEntity(1, 2, -2);
                    addEntity(1, 4, -2);
                    addEntity(1, 6, -2);
                    addEntity(1, 8, -2);
                    addEntity(1, 1, -4);
                    addEntity(1, 3, -4);
                    addEntity(1, 5, -4);
                    addEntity(1, 7, -4);
                    addEntity(1, 2, -6);
                    addEntity(1, 4, -6);
                    addEntity(1, 6, -6);
                    addEntity(1, 8, -6);
                    addEntity(1, 1, -8);
                    addEntity(1, 3, -8);
                    addEntity(1, 5, -8);
                    addEntity(1, 7, -8);
                    addEntity(2, 6, -8);
                    setStartPos(4, -1);
                }
                if (level == 14){
                    addEntity(0, 1, -1);
                    addEntity(0, 1, -2);
                    addEntity(0, 1, -3);
                    addEntity(0, 1, -4);
                    addEntity(0, 1, -5);
                    addEntity(0, 1, -6);
                    addEntity(0, 2, -1);
                    addEntity(0, 8, -1);
                    addEntity(0, 8, -2);
                    addEntity(0, 8, -3);
                    addEntity(0, 8, -4);
                    addEntity(0, 8, -5);
                    addEntity(0, 8, -6);
                    addEntity(0, 7, -1);
                    addEntity(0, 4, -3);
                    addEntity(0, 4, -4);
                    addEntity(0, 4, -5);
                    addEntity(0, 4, -6);
                    addEntity(0, 4, -7);
                    addEntity(0, 4, -8);
                    addEntity(0, 5, -3);
                    addEntity(0, 5, -4);
                    addEntity(0, 5, -5);
                    addEntity(0, 5, -6);
                    addEntity(0, 5, -7);
                    addEntity(0, 5, -8);
                    addEntity(2, 2, -7);
                    addEntity(1, 7, -7);
                    setStartPos(4, -1);
                }
                if (level == 15){
                    addEntity(1, 1, -8);
                    addEntity(1, 2, -8);
                    addEntity(1, 3, -8);
                    addEntity(1, 4, -8);
                    addEntity(1, 5, -8);
                    addEntity(1, 6, -8);
                    addEntity(1, 7, -8);
                    addEntity(1, 8, -8);
                    addEntity(1, 7, -7);
                    addEntity(1, 8, -7);
                    addEntity(1, 1, -6);
                    addEntity(1, 2, -6);
                    addEntity(1, 3, -6);
                    addEntity(1, 4, -6);
                    addEntity(1, 8, -6);
                    addEntity(1, 1, -5);
                    addEntity(1, 2, -5);
                    addEntity(1, 3, -5);
                    addEntity(1, 4, -5);
                    addEntity(1, 5, -5);
                    addEntity(1, 8, -5);
                    addEntity(1, 1, -4);
                    addEntity(1, 2, -4);
                    addEntity(1, 3, -4);
                    addEntity(1, 4, -4);
                    addEntity(1, 5, -4);
                    addEntity(1, 8, -4);
                    addEntity(1, 1, -3);
                    addEntity(1, 2, -3);
                    addEntity(1, 3, -3);
                    addEntity(1, 4, -3);
                    addEntity(1, 8, -3);
                    addEntity(1, 7, -2);
                    addEntity(1, 8, -2);
                    addEntity(1, 1, -1);
                    addEntity(1, 2, -1);
                    addEntity(1, 3, -1);
                    addEntity(1, 4, -1);
                    addEntity(1, 5, -1);
                    addEntity(1, 6, -1);
                    addEntity(1, 7, -1);
                    addEntity(1, 8, -1);
                    addEntity(2, 1, -2);
                    setStartPos(1, -7);
                }
                if (level == 16){
                    addEntity(1, 1, -2);
                    addEntity(1, 2, -2);
                    addEntity(1, 3, -2);
                    addEntity(1, 4, -2);
                    addEntity(1, 5, -2);
                    addEntity(1, 6, -2);
                    addEntity(1, 7, -2);
                    addEntity(1, 7, -3);
                    addEntity(1, 7, -4);
                    addEntity(1, 7, -5);
                    addEntity(1, 7, -6);
                    addEntity(1, 7, -7);
                    addEntity(1, 5, -8);
                    addEntity(1, 5, -7);
                    addEntity(1, 5, -6);
                    addEntity(1, 5, -5);
                    addEntity(1, 5, -4);
                    addEntity(1, 3, -3);
                    addEntity(1, 3, -4);
                    addEntity(1, 3, -5);
                    addEntity(1, 3, -6);
                    addEntity(1, 3, -7);
                    addEntity(1, 1, -3);
                    addEntity(1, 1, -4);
                    addEntity(1, 1, -5);
                    addEntity(1, 1, -6);
                    addEntity(1, 1, -7);
                    addEntity(1, 1, -8);
                    addEntity(2, 2, -3);
                    setStartPos(1, -1);
                }
                if (level == 17){
                    addEntity(0, 1, -1);
                    addEntity(0, 8, -1);
                    addEntity(0, 1, -8);
                    addEntity(0, 8, -8);
                    addEntity(2, 2, -1);
                    addEntity(1, 3, -1);
                    addEntity(1, 4, -1);
                    addEntity(1, 5, -1);
                    addEntity(1, 6, -1);
                    addEntity(1, 7, -1);
                    addEntity(1, 8, -2);
                    addEntity(1, 8, -3);
                    addEntity(1, 8, -4);
                    addEntity(1, 8, -5);
                    addEntity(1, 8, -6);
                    addEntity(1, 8, -7);
                    addEntity(2, 7, -8);
                    addEntity(1, 6, -8);
                    addEntity(1, 5, -8);
                    addEntity(1, 4, -8);
                    addEntity(1, 3, -8);
                    addEntity(1, 2, -8);
                    addEntity(1, 1, -7);
                    addEntity(1, 1, -6);
                    addEntity(1, 1, -5);
                    addEntity(1, 1, -4);
                    addEntity(1, 1, -3);
                    addEntity(1, 1, -2);
                    setStartPos(4, -5);
                }
                if (level == 18){
                    addEntity(1, 1, -1);
                    addEntity(1, 5, -1);
                    addEntity(2, 8, -1);
                    addEntity(1, 3, -2);
                    addEntity(1, 7, -2);
                    addEntity(1, 1, -3);
                    addEntity(1, 5, -3);
                    addEntity(1, 8, -3);
                    addEntity(1, 6, -4);
                    addEntity(1, 8, -4);
                    addEntity(1, 2, -5);
                    addEntity(1, 4, -5);
                    addEntity(1, 1, -6);
                    addEntity(1, 7, -6);
                    addEntity(1, 3, -7);
                    addEntity(1, 2, -8);
                    addEntity(1, 5, -8);
                    addEntity(1, 7, -8);
                    setStartPos(1, -8);
                }
                if (level == 19){
                    addEntity(1, 2, -2);
                    addEntity(1, 3, -2);
                    addEntity(1, 4, -2);
                    addEntity(1, 5, -2);
                    addEntity(1, 6, -2);
                    addEntity(1, 7, -2);
                    addEntity(1, 2, -3);
                    addEntity(1, 4, -3);
                    addEntity(1, 7, -3);
                    addEntity(1, 2, -4);
                    addEntity(1, 4, -4);
                    addEntity(1, 2, -5);
                    addEntity(1, 4, -5);
                    addEntity(1, 7, -5);
                    addEntity(1, 2, -6);
                    addEntity(1, 7, -6);
                    addEntity(1, 2, -7);
                    addEntity(1, 3, -7);
                    addEntity(1, 4, -7);
                    addEntity(1, 5, -7);
                    addEntity(1, 6, -7);
                    addEntity(1, 7, -7);
                    addEntity(2, 3, -3);
                    setStartPos(1, -5);
                }
                if (level == 20){
                    addEntity(1, 3, -2);
                    addEntity(1, 3, -3);
                    addEntity(1, 3, -4);
                    addEntity(1, 6, -2);
                    addEntity(2, 6, -3);
                    addEntity(1, 6, -4);
                    addEntity(1, 2, -6);
                    addEntity(1, 3, -6);
                    addEntity(1, 4, -6);
                    addEntity(1, 5, -6);
                    addEntity(1, 6, -6);
                    addEntity(1, 7, -6);
                    addEntity(1, 3, -7);
                    addEntity(1, 4, -7);
                    addEntity(1, 5, -7);
                    addEntity(1, 6, -7);
                    setStartPos(1, -5);
                }
                if (level == 21){
                    addEntity(1, 1, -2);
                    addEntity(1, 2, -2);
                    addEntity(1, 3, -2);
                    addEntity(1, 4, -2);
                    addEntity(1, 5, -2);
                    addEntity(1, 7, -2);
                    addEntity(1, 8, -2);
                    addEntity(1, 2, -4);
                    addEntity(1, 3, -4);
                    addEntity(1, 4, -4);
                    addEntity(1, 5, -4);
                    addEntity(1, 6, -4);
                    addEntity(1, 7, -4);
                    addEntity(1, 8, -4);
                    addEntity(1, 1, -6);
                    addEntity(1, 2, -6);
                    addEntity(1, 3, -6);
                    addEntity(1, 5, -6);
                    addEntity(1, 6, -6);
                    addEntity(1, 7, -6);
                    addEntity(1, 8, -6);
                    addEntity(1, 1, -8);
                    addEntity(1, 2, -8);
                    addEntity(1, 3, -8);
                    addEntity(1, 4, -8);
                    addEntity(1, 6, -8);
                    addEntity(1, 7, -8);
                    addEntity(1, 8, -8);
                    addEntity(2, 5, -8);
                    setStartPos(4, -1);
                }
                //end game
                if (level == 22){
                    runGame = false;
                }
                playerPos[0] = startPos[0];
                playerPos[1] = startPos[1];
                levelTransition = false;
            }

            //render player
            setPixel(playerPos[0], playerPos[1], '@');
            
            //render
            System.out.println("Level: " + level + "\nDeaths: " + deaths + "\nTime: " + Math.floor(timer));
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    System.out.print(pixels[i][j]);
                }
                System.out.println();
            }

            //delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            timer += 0.1;
        }
        //end screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("You won! Congrats!");
        System.out.println("Your time was " + timer + " seconds.");
        System.out.println("You had " + deaths + " deaths.");
    }

    public static void setPixel(int x, int y, char sym){
        pixels[Math.abs(y)][x] = sym;
    }

    public static void addWalls(){
        addEntity(0, 0, 0);
        addEntity(0, 1, 0);
        addEntity(0, 2, 0);
        addEntity(0, 3, 0);
        addEntity(0, 4, 0);
        addEntity(0, 5, 0);
        addEntity(0, 6, 0);
        addEntity(0, 7, 0);
        addEntity(0, 8, 0);
        addEntity(0, 9, 0);
        addEntity(0, 0, -1);
        addEntity(0, 9, -1);
        addEntity(0, 0, -2);
        addEntity(0, 9, -2);
        addEntity(0, 0, -3);
        addEntity(0, 9, -3);
        addEntity(0, 0, -4);
        addEntity(0, 9, -4);
        addEntity(0, 0, -5);
        addEntity(0, 9, -5);
        addEntity(0, 0, -6);
        addEntity(0, 9, -6);
        addEntity(0, 0, -7);
        addEntity(0, 9, -7);
        addEntity(0, 0, -8);
        addEntity(0, 9, -8);
        addEntity(0, 0, -9);
        addEntity(0, 1, -9);
        addEntity(0, 2, -9);
        addEntity(0, 3, -9);
        addEntity(0, 4, -9);
        addEntity(0, 5, -9);
        addEntity(0, 6, -9);
        addEntity(0, 7, -9);
        addEntity(0, 8, -9);
        addEntity(0, 9, -9);
    }

    public static void setStartPos(int x, int y){
        startPos[0] = x;
        startPos[1] = y;
    }
    
    public static void addEntity(int id, int x, int y){
        entities.add(id);
        entities.add(x);
        entities.add(y);
    }

    public void keyTyped(KeyEvent e){
        //nothing!
    }
    
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case 37: leftKey = true;
            break;
            case 38: upKey = true;
            break;
            case 39: rightKey = true;
            break;
            case 40: downKey = true;
            break;
        }
    }
    
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case 37: leftKey = false;
            break;
            case 38: upKey = false;
            break;
            case 39: rightKey = false;
            break;
            case 40: downKey = false;
            break;
        }
    }

    public static void main(String[] args){
        new roomGame();
    }
}