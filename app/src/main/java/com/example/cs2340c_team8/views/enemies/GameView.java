package com.example.cs2340c_team8.views.enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;
import com.example.cs2340c_team8.models.levels.Level;

public class GameView extends View {
    private Level level;
    private int currentMap = 1;
    private static Bitmap map;
    private Bitmap goomba;
    private Bitmap koopaTroopa;
    private Bitmap bulletBill;
    private Bitmap shell;
    private Bitmap playerBitmap;
    private Goomba firstGoomba;
    private KoopaTroopa firstKoopaTroopa;
    private BulletBill firstBulletBill;
    private PiranhaPlant firstShell;
    private static Player player;
    private static float scale;
    private static int tileColor1 = Color.rgb(87, 140, 170);
    private static int tileColor2 = Color.rgb(107, 68, 150);
    private static int tileColor3 = Color.rgb(255, 119, 0);
    private int calls = 0;
    private boolean gameCompleted;
    private static boolean levelChanged;
    private int scalar = 11111111;
    private int keyOneX = 176 * (scalar);
    private int keyOneY = 464 * (scalar);
    private int keyTwoX = 320 * (scalar);
    private int keyTwoY = 32 * (scalar);
    private int keyThreeX = 400 * (scalar);
    private int keyThreeY = 384 * (scalar);

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameCompleted = false;
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
            canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
            canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
            canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
            canvas.drawBitmap(shell, scaleShell(firstShell), null);
            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
            firstGoomba.moveEnemy();
            firstKoopaTroopa.moveEnemy();
            firstBulletBill.moveEnemy();
            firstShell.moveEnemy();
        } else if (currentMap == 2) {
            canvas.drawBitmap(map, scaleMap(), null);
            canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
            canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
            canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
            canvas.drawBitmap(shell, scaleShell(firstShell), null);
            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
            firstGoomba.moveEnemy();
            firstKoopaTroopa.moveEnemy();
            firstBulletBill.moveEnemy();
            firstShell.moveEnemy();
        } else if (currentMap == 3) {
            canvas.drawBitmap(map, scaleMap(), null);
            canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
            canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
            canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
            canvas.drawBitmap(shell, scaleShell(firstShell), null);
            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
            firstGoomba.moveEnemy();
            firstKoopaTroopa.moveEnemy();
            firstBulletBill.moveEnemy();
            firstShell.moveEnemy();
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
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map3);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap =
                BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

        firstGoomba = new Goomba(goomba, 550, 48, true, 1);
        firstKoopaTroopa =
                new KoopaTroopa(koopaTroopa, 700, 60, true, 1);
        firstBulletBill =
                new BulletBill(bulletBill, 550, 100, 200, 1);
        firstShell = new PiranhaPlant(920, 60, 100, 1);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
    }

    public void createMapTwo() {
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map2);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap =
                BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

        firstGoomba = new Goomba(goomba, 500, 80, true, 1);
        firstKoopaTroopa = new KoopaTroopa(koopaTroopa, 400, 90, true, 1);
        firstBulletBill = new BulletBill(bulletBill, 550, 610, 200, 1);
        firstShell = new PiranhaPlant(650, 650, 1200, 1);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
    }

    public void createMapOne() {
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap =
                BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

        firstGoomba = new Goomba(goomba, 550, 48, true, 1);
        firstKoopaTroopa = new KoopaTroopa(koopaTroopa, 800, 48, true, 1);
        firstBulletBill = new BulletBill(bulletBill, 550, 550, 50, 1);
        firstShell = new PiranhaPlant(650, 650, 700, 1);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
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

    public static void throwFireball() {
        int fireballX = player.getStartX() + 60;
        int fireballY = player.getStartY();
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
}
