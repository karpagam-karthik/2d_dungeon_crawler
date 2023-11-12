package com.example.cs2340c_team8.views.enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

public class GameView extends View {
    private int currentMap;
    private Bitmap map;
    private Bitmap goomba;
    private Bitmap koopaTroopa;
    private Bitmap bulletBill;
    private Bitmap shell;
    private Bitmap playerBitmap;
    private Goomba firstGoomba;
    private Goomba secondGoomba;
    private KoopaTroopa firstKoopaTroopa;
    private BulletBill firstBulletBill;
    private PiranhaPlant firstShell;
    private Player player;
    private float scale;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        currentMap = 1;

        map = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
        goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
        koopaTroopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_troopa);
        bulletBill = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        shell = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
        playerBitmap = BitmapFactory.decodeResource(getResources(), GameConfig.fetchCharacterSprite());

        firstGoomba = new Goomba(goomba, 550, 48, true, 1);
        secondGoomba = new Goomba(goomba, 600, 48, true, 1);
        firstKoopaTroopa = new KoopaTroopa(koopaTroopa, 800, 48, true, 1);
        firstBulletBill = new BulletBill(bulletBill, 550, 550, 200,1);
        firstShell = new PiranhaPlant(650, 650, 1200, 1);

        player = Player.getInstance();
        player.setStartX(100);
        player.setStartY(100);

        player.addObserver(firstGoomba);
        player.addObserver(secondGoomba);
        player.addObserver(firstKoopaTroopa);
        player.addObserver(firstBulletBill);
        player.addObserver(firstShell);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(map, scaleMap(), null);
        canvas.drawBitmap(goomba, scaleGoomba(firstGoomba), null);
        canvas.drawBitmap(goomba, scaleGoomba(secondGoomba), null);
        canvas.drawBitmap(koopaTroopa, scaleKoopa(firstKoopaTroopa), null);
        canvas.drawBitmap(bulletBill, scaleBullet(firstBulletBill), null);
        canvas.drawBitmap(shell, scaleShell(firstShell), null);
        canvas.drawBitmap(playerBitmap, scalePlayer(player), null);
        firstGoomba.moveEnemy();
        secondGoomba.moveEnemy();
        firstKoopaTroopa.moveEnemy();
        firstBulletBill.moveEnemy();
        firstShell.moveEnemy();

        invalidate();
    }

    public Matrix scalePlayer(Player test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public Matrix scaleMap() {
        this.scale = Math.min((float) getWidth() / map.getWidth(), (float) getHeight() / map.getHeight());
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
}
