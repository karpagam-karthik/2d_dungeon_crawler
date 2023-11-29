package com.example.cs2340c_team8.models.powerups;

import android.content.Context;

import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.BlueShell;
import com.example.cs2340c_team8.models.enums.PowerUpType;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.interfaces.PowerUp;

import java.util.concurrent.TimeUnit;

public class IcePowerUp extends BasePowerUp implements PowerUp {
    private final PowerUpType powerUpType = PowerUpType.ICE;

    public IcePowerUp(Context context, int startX, int startY) {
        super(context, startX, startY);
    }

    @Override
    public PowerUpType getType() {
        return powerUpType;
    }

    @Override
    public int getEffect() {
        return 0;
    }

    @Override
    public long getDuration() {
        return 0;
    }
    @Override
    public void attackPlayer() {
        player.addPowerUp(this);
        Thread timeThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                for (PlayerObserver enemy : this.player.getObservers()) {
                    if(enemy.getClass() == BulletBill.class ) {
                        ((BulletBill) enemy).fastMovespeed();
                    } else if (enemy.getClass() == Goomba.class) {
                        ((Goomba) enemy).fastMovespeed();
                    } else if (enemy.getClass() == KoopaTroopa.class) {
                        ((KoopaTroopa) enemy).fastMovespeed();
                    } else if (enemy.getClass() == BlueShell.class) {
                        ((BlueShell) enemy).fastMovespeed();
                    }
                }
            }
            for (PlayerObserver enemy : this.player.getObservers()) {
                if(enemy.getClass() == BulletBill.class ) {
                    ((BulletBill) enemy).fastMovespeed();
                } else if (enemy.getClass() == Goomba.class) {
                    ((Goomba) enemy).fastMovespeed();
                } else if (enemy.getClass() == KoopaTroopa.class) {
                    ((KoopaTroopa) enemy).fastMovespeed();
                } else if (enemy.getClass() == BlueShell.class) {
                    ((BlueShell) enemy).fastMovespeed();
                }
            }
        });
        for (PlayerObserver enemy : this.player.getObservers()) {
            if(enemy.getClass() == BulletBill.class ) {
                ((BulletBill) enemy).slowMovespeed();
            } else if (enemy.getClass() == Goomba.class) {
                ((Goomba) enemy).slowMovespeed();
            } else if (enemy.getClass() == KoopaTroopa.class) {
                ((KoopaTroopa) enemy).slowMovespeed();
            } else if (enemy.getClass() == BlueShell.class) {
                ((BlueShell) enemy).slowMovespeed();
            }
        }
        com.example.cs2340c_team8.views.GameView.removePowerUp(this.context);
        timeThread.start();
    }
}
