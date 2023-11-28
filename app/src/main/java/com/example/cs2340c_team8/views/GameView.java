package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
// This method sets View object to 800x800 pixels, do not change it!
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;
import com.example.cs2340c_team8.models.levels.Level;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.powerups.BasePowerUp;
import com.example.cs2340c_team8.models.powerups.FirePowerUp;
import com.example.cs2340c_team8.models.powerups.IcePowerUp;
import com.example.cs2340c_team8.models.powerups.StarPowerUp;

public class GameView extends View {
    private Level level;
    private int currentMap = 1;
    private static Bitmap map;
    private Bitmap goomba;
    private Bitmap koopaTroopa;
    private Bitmap bulletBill;
    private Bitmap shell;
    private Bitmap playerBitmap;
    private static Bitmap powerUpBitmap;
    private Goomba firstGoomba;
    private KoopaTroopa firstKoopaTroopa;
    private BulletBill firstBulletBill;
    private PiranhaPlant firstShell;
    private BasePowerUp powerUp;
    private static Player player;
    private static float scale;
    private static int tileColor1 = Color.rgb(87, 140, 170);
    private static int tileColor2 = Color.rgb(107, 68, 150);
    private static int tileColor3 = Color.rgb(255, 119, 0);
    private int calls = 0;
    private boolean gameCompleted;
    private static boolean levelChanged;
    private double scalar = 0.5;
    private int keyOneX = (int) (176 * (scalar));
    private int keyOneY = (int) (464 * (scalar));
    private int keyTwoX = (int) (320 * (scalar));
    private int keyTwoY = (int) (32 * (scalar));
    private int keyThreeX = (int) (400 * (scalar));
    private int keyThreeY = (int) (384 * (scalar));
    private Bitmap fireball = BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
    public static boolean isFireballThrown = false;
    private static int fireballX;
    private static int fireballY;
    private static int fireballRange = 160;
    public static boolean firstGoombaExists;
    public static boolean firstShellExists;
    public static boolean firstBulletBillExists;
    public static boolean firstKoopaTroopaExists;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameCompleted = false;
    }

    public static void removePowerUp(Context context) {
        powerUpBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.empty);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentMap == 1 && calls == 0) {
            createMapOne();
            calls++;
        } else if (currentMap == 2 && calls == 0) {
            createMapTwo();
            calls++;
        } else if (currentMap == 3 && calls == 0) {
            createMapThree();
            calls++;
        }

        if (currentMap == 1) {
            canvas.drawBitmap(map, scaleMap(), null);
            if (firstGoombaExists) {
                canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
            } else {
                firstGoomba.setStartX(0);
                firstGoomba.setStartY(500);
            }
            if (firstKoopaTroopaExists) {
                canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
            } else {
                firstKoopaTroopa.setStartX(700);
                firstKoopaTroopa.setStartY(0);
            }
            if (firstBulletBillExists) {
                canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
            } else {
                firstBulletBill.setStartX(800);
                firstBulletBill.setStartY(0);
            }
            if (firstShellExists) {
                canvas.drawBitmap(shell, scaleShell(firstShell), null);
            } else {
                firstShell.setStartX(0);
                firstShell.setStartY(400);
            }
            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
            canvas.drawBitmap(powerUpBitmap, scalePowerup(powerUp),null);
            firstGoomba.moveEnemy();
            firstKoopaTroopa.moveEnemy();
            firstBulletBill.moveEnemy();
            firstShell.moveEnemy();
            powerUp.moveEnemy();
        } else if (currentMap == 2) {
            canvas.drawBitmap(map, scaleMap(), null);
            if (firstGoombaExists) {
                canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
            } else {
                firstGoomba.setStartX(0);
                firstGoomba.setStartY(500);
            }
            if (firstKoopaTroopaExists) {
                canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
            } else {
                firstKoopaTroopa.setStartX(700);
                firstKoopaTroopa.setStartY(0);
            }
            if (firstBulletBillExists) {
                canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
            } else {
                firstBulletBill.setStartX(800);
                firstBulletBill.setStartY(0);
            }
            if (firstShellExists) {
                canvas.drawBitmap(shell, scaleShell(firstShell), null);
            } else {
                firstShell.setStartX(0);
                firstShell.setStartY(400);
            }
            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
            canvas.drawBitmap(powerUpBitmap, scalePowerup(powerUp),null);
            firstGoomba.moveEnemy();
            firstKoopaTroopa.moveEnemy();
            firstBulletBill.moveEnemy();
            firstShell.moveEnemy();
            powerUp.moveEnemy();
        } else if (currentMap == 3) {
            canvas.drawBitmap(map, scaleMap(), null);
            if (firstGoombaExists) {
                canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
            } else {
                firstGoomba.setStartX(0);
                firstGoomba.setStartY(500);
            }
            if (firstKoopaTroopaExists) {
                canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
            } else {
                firstKoopaTroopa.setStartX(700);
                firstKoopaTroopa.setStartY(0);
            }
            if (firstBulletBillExists) {
                canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
            } else {
                firstBulletBill.setStartX(800);
                firstBulletBill.setStartY(0);
            }
            if (firstShellExists) {
                canvas.drawBitmap(shell, scaleShell(firstShell), null);
            } else {
                firstShell.setStartX(0);
                firstShell.setStartY(400);
            }
            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
            canvas.drawBitmap(powerUpBitmap, scalePowerup(powerUp),null);
            firstGoomba.moveEnemy();
            firstKoopaTroopa.moveEnemy();
            firstBulletBill.moveEnemy();
            firstShell.moveEnemy();
            powerUp.moveEnemy();
        }

        if (isFireballThrown) {
            if (fireballX < player.getStartX() + fireballRange) {
                canvas.drawBitmap(fireball, scaleFireball(fireballX, fireballY), null);
                fireballX++;
            } else {
                isFireballThrown = false;
            }
        }

        if (isLevelOver(player.getStartX(), player.getStartY())) {
            GameConfig.incrementLevel();
            levelChanged = true;

            if (currentMap == 3) {
                gameCompleted = true;
            }
            currentMap++;
            calls = 0;
        }
        invalidate();
    }

    public void createMapThree() {
        firstBulletBillExists = true;
        firstKoopaTroopaExists = true;
        firstShellExists = true;
        firstGoombaExists = true;
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map3);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap =
                BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());
        powerUpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ice_power);

        firstGoomba = new Goomba(goomba, 550, 48, true, 1);
        firstKoopaTroopa =
                new KoopaTroopa(koopaTroopa, 700, 60, true, 1);
        firstBulletBill =
                new BulletBill(bulletBill, 550, 100, 200, 1);
        firstShell = new PiranhaPlant(920, 60, 100, 1);
        powerUp = new IcePowerUp(this.getContext(),300, 60);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
        player.addObserver(powerUp);
    }

    public void createMapTwo() {
        firstBulletBillExists = true;
        firstKoopaTroopaExists = true;
        firstShellExists = true;
        firstGoombaExists = true;
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map2);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap =
                BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());
        powerUpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fire_power);

        firstGoomba = new Goomba(goomba, 500, 80, true, 1);
        firstKoopaTroopa = new KoopaTroopa(koopaTroopa, 400, 90, true, 1);
        firstBulletBill = new BulletBill(bulletBill, 550, 610, 200, 1);
        firstShell = new PiranhaPlant(650, 650, 1200, 1);
        powerUp = new FirePowerUp(this.getContext(),200, 90);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
        player.addObserver(powerUp);
    }

    public void createMapOne() {
        firstBulletBillExists = true;
        firstKoopaTroopaExists = true;
        firstShellExists = true;
        firstGoombaExists = true;
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap =
                BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());
        powerUpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star_powerup);

        firstGoomba = new Goomba(goomba, 550, 48, true, 1);
        firstKoopaTroopa = new KoopaTroopa(koopaTroopa, 800, 48, true, 1);
        firstBulletBill = new BulletBill(bulletBill, 550, 550, 50, 1);
        firstShell = new PiranhaPlant(650, 650, 700, 1);
        powerUp = new StarPowerUp(this.getContext(),650, 550);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
        player.addObserver(powerUp);
    }

    public static boolean isLevelOver(int playerX, int playerY) {
        int mapLeftX = 0;
        int mapLeftY = 0;
        int mapLength = 85;
        int mapHeight = 85;
        int playerWidth = 16;
        int playerHeight = 16;

        return (playerX + playerWidth > mapLeftX)
                && (playerY + playerHeight > mapLeftY)
                && (playerX < mapLeftX + mapLength)
                && (playerY < mapLeftY + mapHeight);
    }

    public static boolean isValidMoveX(double actuatorX) {
        if (actuatorX > 0) {
            int xth = player.getStartX() + 48;
            for (int y = player.getStartY(); y < player.getStartY() + 16; y++) {
                if (map.getPixel(xth, y) != tileColor1 && map.getPixel(xth, y)
                        != tileColor2 && map.getPixel(xth, y) != tileColor3) {
                    return false;
                }
            }
            return true;
        } else if (actuatorX < 0) {
            int xth = player.getStartX() - 1;
            for (int y = player.getStartY(); y < player.getStartY() + 16; y++) {
                if (map.getPixel(xth, y) != tileColor1 && map.getPixel(xth, y)
                        != tileColor2 && map.getPixel(xth, y) != tileColor3) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidMoveY(double actuatorY) {
        if (actuatorY > 0) {
            int yth = player.getStartY() + 48;
            for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
                if (map.getPixel(x, yth) != tileColor1 && map.getPixel(x, yth)
                        != tileColor2 && map.getPixel(x, yth) != tileColor3) {
                    return false;
                }
            }
            return true;
        } else if (actuatorY < 0) {
            int yth = player.getStartY() - 1;
            for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
                if (map.getPixel(x, yth) != tileColor1 && map.getPixel(x, yth)
                        != tileColor2 && map.getPixel(x, yth) != tileColor3) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Matrix scaleMap() {
        this.scale = Math.min((float) getWidth() / map.getWidth(),
                (float) getHeight() / map.getHeight());
        Matrix mapMatrix = new Matrix();
        mapMatrix.setScale(scale, scale);
        return mapMatrix;
    }

    public Matrix scaleGoomba(Goomba test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public Matrix scalePlayer(Player test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public Matrix scaleKoopa(KoopaTroopa test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public Matrix scaleBullet(BulletBill test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public Matrix scaleShell(PiranhaPlant test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public Matrix scaleFireball(int x, int y) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(x * scale, y * scale);
        return enemyMatrix;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = resolveSize(800, widthMeasureSpec);
        int height = resolveSize(800, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


    public static boolean bulletBillKB() {
        int yth = player.getStartY() - 1;
        for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
            if (map.getPixel(x, yth) != tileColor1 && map.getPixel(x, yth)
                    != tileColor2 && map.getPixel(x, yth) != tileColor3) {
                return false;
            }
        }
        return true;
    }
    public static void setFireballPosition() {
        fireballX = player.getStartX() + 60;
        fireballY = player.getStartY();
    }

    public static int getFireballX() {
        return fireballX;
    }

    public static int getFireballY() {
        return fireballY;
    }

    public static void setFireballRange(int effect) {
        fireballRange = effect;
    }
    public boolean isGameCompleted() {
        return gameCompleted;
    }

    public static boolean isLevelChanged() {
        return levelChanged;
    }

    public static void setLevelChanged(boolean levelChanged1) {
        levelChanged = levelChanged1;
    }

    public Matrix scalePowerup(BasePowerUp test) {
        Matrix powerMatrix = new Matrix();
        powerMatrix.setScale(scale, scale);
        powerMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return powerMatrix;
    }
}
