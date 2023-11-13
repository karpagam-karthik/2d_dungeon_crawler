package com.example.cs2340c_team8.views.enemies;

import static java.lang.System.currentTimeMillis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.Enemy;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;
import com.example.cs2340c_team8.models.levels.Level;
import com.example.cs2340c_team8.models.levels.Level1;
import com.example.cs2340c_team8.models.levels.Level2;
import com.example.cs2340c_team8.models.levels.Level3;
import com.example.cs2340c_team8.viewModels.LeaderboardViewModel;
import com.example.cs2340c_team8.views.activities.DungeonActivity;
import com.example.cs2340c_team8.views.activities.LeaderboardActivity;

import java.util.ArrayList;

public class GameView extends View {
    private Level level;
    private int currentMap = 1;
    private static Bitmap map1;
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
    private float scale;
    private static int tileColor1 = Color.rgb(87, 140, 170);
    private static int tileColor2 = Color.rgb(107, 68, 150);
    private static int tileColor3 = Color.rgb(255, 119, 0);
    private int calls = 0;
    private boolean gameCompleted;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameCompleted = false;
//        level = new Level1(this, 100, 100);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
////        if (level.getClass() == Level1.class && calls == 0) {
////            createMapOne();
////            calls++;
////        } else if (level.getClass() == Level2.class && calls == 0) {
////            createMapTwo();
////            calls++;
////        } else if (level.getClass() == Level3.class && calls == 0) {
////            createMapThree();
////            calls++;
////        }
//
//        if (currentMap == 1) {
//            ArrayList<Enemy> enemies = level.get
//            canvas.drawBitmap(map1, scaleMap(), null);
//            canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
//            canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
//            canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
//            canvas.drawBitmap(shell, scaleShell(firstShell), null);
//            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
//            firstGoomba.moveEnemy();
//            firstKoopaTroopa.moveEnemy();
//            firstBulletBill.moveEnemy();
//            firstShell.moveEnemy();
//        } else if (currentMap == 2) {
//            canvas.drawBitmap(map1, scaleMap(), null);
//            canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
//            canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
//            canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
//            canvas.drawBitmap(shell, scaleShell(firstShell), null);
//            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
//            firstGoomba.moveEnemy();
//            firstKoopaTroopa.moveEnemy();
//            firstBulletBill.moveEnemy();
//            firstShell.moveEnemy();
//        } else if (currentMap == 3) {
//            canvas.drawBitmap(map1, scaleMap(), null);
//            canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
//            canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
//            canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
//            canvas.drawBitmap(shell, scaleShell(firstShell), null);
//            canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
//            firstGoomba.moveEnemy();
//            firstKoopaTroopa.moveEnemy();
//            firstBulletBill.moveEnemy();
//            firstShell.moveEnemy();
//        }
//
//        if (isLevelOver(player.getStartX(), player.getStartY())) {
//            if (currentMap == 3) {
//                gameCompleted = true;
//            }
//            switch (level.getClass()) {
//                case Level1.class:
//                    level = new Level2(this, 100, 100);
//                case Level2.class:
//                    level = new Level3(this, 100, 100);
//                default:
////                    LeaderboardViewModel.addNewScore(GameConfig.username, score, currentTimeMillis() - startTime);
////
////                    Intent end = new Intent(DungeonActivity.this, LeaderboardActivity.class);
////                    end.putExtra("score", Integer.parseInt(((String) scoreTextView.getText())
////                            .split(" ")[1]));
////                    end.putExtra("time", timeElapsedTextView.getText());
////                    end.putExtra("keys", "3 of 3");
////                    end.putExtra("success", true);
////                    startActivity(end);
////                    finish();
//            }
//            currentMap++;
////            calls = 0;
//        }
//        invalidate();
//    }

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
            canvas.drawBitmap(map1, scaleMap(), null);
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
            canvas.drawBitmap(map1, scaleMap(), null);
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
            canvas.drawBitmap(map1, scaleMap(), null);
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
            if (currentMap == 3) {
                gameCompleted = true;
            }
            currentMap++;
            calls = 0;
        }
        invalidate();
    }

    public void createMapThree() {
        map1 = BitmapFactory.decodeResource(getResources(), R.drawable.map3);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap = BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

        firstGoomba = new Goomba(goomba, 550, 48, true, 1);
        firstKoopaTroopa = new KoopaTroopa(koopaTroopa, 700, 60, true, 1);
        firstBulletBill = new BulletBill(bulletBill, 550, 100, 200, 1);
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
        map1 = BitmapFactory.decodeResource(getResources(), R.drawable.map2);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap = BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

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
        map1 = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap = BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

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

        return (playerX + playerWidth > mapLeftX) &&
                (playerY + playerHeight > mapLeftY) &&
                (playerX < mapLeftX + mapLength) &&
                (playerY < mapLeftY + mapHeight);
    }

    public static boolean isValidMoveX(double actuatorX) {
        if (actuatorX > 0) {
            int xth = player.getStartX() + 16;
            for (int y = player.getStartY(); y < player.getStartY() + 16; y++) {
                if (map1.getPixel(xth, y) != tileColor1 && map1.getPixel(xth, y) != tileColor2 && map1.getPixel(xth, y) != tileColor3) {
                    return false;
                }
            }
            return true;
        } else if (actuatorX < 0) {
            int xth = player.getStartX() - 1;
            for (int y = player.getStartY(); y < player.getStartY() + 16; y++) {
                if (map1.getPixel(xth, y) != tileColor1 && map1.getPixel(xth, y) != tileColor2 && map1.getPixel(xth, y) != tileColor3) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidMoveY(double actuatorY) {
        if (actuatorY > 0) {
            int yth = player.getStartY() + 16;
            for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
                if (map1.getPixel(x, yth) != tileColor1 && map1.getPixel(x, yth) != tileColor2 && map1.getPixel(x, yth) != tileColor3) {
                    return false;
                }
            }
            return true;
        } else if (actuatorY < 0) {
            int yth = player.getStartY() - 1;
            for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
                if (map1.getPixel(x, yth) != tileColor1 && map1.getPixel(x, yth) != tileColor2 && map1.getPixel(x, yth) != tileColor3) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Matrix scaleMap() {
        this.scale = Math.min((float) getWidth() / map1.getWidth(), (float) getHeight() / map1.getHeight());
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

    public boolean isGameCompleted() {
        return gameCompleted;
    }
}
