package com.example.cs2340c_team8.models.levels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.BlueShell;
import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level1 extends Level {
    private ArrayList<Enemy> enemies;
    private ArrayList<Bitmap> enemyBitmaps;

    public Level1(View view, int spawnX, int spawnY) {
        super(view, R.drawable.map1, spawnX, spawnY);

        createEnemyEntities();
        for (Enemy enemy : enemies) {
            PLAYER.addObserver(enemy);
        }
    }

    public void onLevelCompleted() {

    }

    public void createEnemyEntities() {
        Bitmap goombaBitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.goomba);
        Bitmap koopaTroopaBitmap =
                BitmapFactory.decodeResource(view.getResources(), R.drawable.koopa_troopa);
        Bitmap bulletBillBitmap =
                BitmapFactory.decodeResource(view.getResources(), R.drawable.bullet_bill);

        enemyBitmaps.add(goombaBitmap);
        enemyBitmaps.add(koopaTroopaBitmap);
        enemyBitmaps.add(bulletBillBitmap);
        enemyBitmaps.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.shell));

        Goomba goomba =
                new Goomba(goombaBitmap, 550, 48, true, 1);
        KoopaTroopa koopaTroopa =
                new KoopaTroopa(koopaTroopaBitmap, 800, 48, true, 1);
        BulletBill bulletBill =
                new BulletBill(bulletBillBitmap, 550, 550, 50, 1);
        BlueShell shell =
                new BlueShell(650, 650, 700, 1);

        enemies = new ArrayList<>();
        enemies.add(goomba);
        enemies.add(koopaTroopa);
        enemies.add(bulletBill);
        enemies.add(shell);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Bitmap> getEnemyBitmaps() {
        return enemyBitmaps;
    }
}
