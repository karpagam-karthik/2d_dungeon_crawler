package com.example.cs2340c_team8.models.levels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;
import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level3 extends Level {
    private ArrayList<Enemy> enemies;
    private ArrayList<Bitmap> enemyBitmaps;
    public Level3(View view, int spawnX, int spawnY) {
        super(view, R.drawable.map3, spawnX, spawnY);
    }

    @Override
    void onLevelCompleted() {

    }
    /*HEADS UP:
    Locations of where the enemies go may be at a wall. I wasn't able to propagate to the
    proceeding maps so I couldn't verify that these location where the enemies were placed are
    valid. For now I kinda just eyeballed it. This can be fixed tho just by changing the startX
    and startY params once someone is able to beat the level and go to the next map.
     */
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
                new Goomba(goombaBitmap, 200, 48, true, 1);
        KoopaTroopa koopaTroopa =
                new KoopaTroopa(koopaTroopaBitmap, 950, 100, true, 1);
        BulletBill bulletBill =
                new BulletBill(bulletBillBitmap, 550, 800, 100, 1);
        PiranhaPlant shell =
                new PiranhaPlant(650, 650, 700, 1);

        enemies = new ArrayList<>();
        enemies.add(goomba);
        enemies.add(koopaTroopa);
        enemies.add(bulletBill);
        enemies.add(shell);
    }

    @Override
    ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    @Override
    ArrayList<Bitmap> getEnemyBitmaps() {
        return this.enemyBitmaps;
    }
}