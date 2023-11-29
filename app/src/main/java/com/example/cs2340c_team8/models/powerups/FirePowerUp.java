package com.example.cs2340c_team8.models.powerups;

import android.content.Context;

import com.example.cs2340c_team8.models.enums.PowerUpType;
import com.example.cs2340c_team8.models.interfaces.PowerUp;

import java.util.concurrent.TimeUnit;

public class FirePowerUp extends BasePowerUp implements PowerUp  {
    private final PowerUpType powerUpType = PowerUpType.FIRE;

    public FirePowerUp(Context context, int startX, int startY) {
        super(context, startX, startY);
    }

    @Override
    public PowerUpType getType() {
        return powerUpType;
    }

    @Override
    public int getEffect() {
        return 500;
    }

    @Override
    public long getDuration() {
        return 5;
    }

    @Override
    public void attackPlayer() {
        player.addPowerUp(this);
        Thread timeThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                com.example.cs2340c_team8.views.GameView.setFireballRange(160);
            }
            com.example.cs2340c_team8.views.GameView.setFireballRange(160);
        });
        com.example.cs2340c_team8.views.GameView.setFireballRange(this.getEffect());
        com.example.cs2340c_team8.views.GameView.removePowerUp(this.context);
        timeThread.start();
    }
}
