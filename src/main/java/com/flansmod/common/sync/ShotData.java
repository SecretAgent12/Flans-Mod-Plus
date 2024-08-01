package com.flansmod.common.sync;

import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.raytracing.BulletHit;

public class ShotData {
    public BulletHit hit;
    public EntityBullet bullet;


    public ShotData(BulletHit hit, EntityBullet bullet) {
        this.hit = hit;
        this.bullet = bullet;
    }
}
