package com.slandshow.projectspace.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.slandshow.projectspace.model.world_creater.WorldCreater;


import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private CircleShape shape;
    private ChainShape groundShape;
    private List<ChainShape> groundList;
    private WorldCreater worldCreater;

    public GameMap(BodyDef bodyDef, FixtureDef fixtureDef, WorldCreater worldCreater) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
        groundList = new ArrayList<ChainShape>();
        this.worldCreater = worldCreater;

        // Создание карты
        init();
    }


    private void init() {

        for (int i = 0; i < 5; i++) {
            groundList.add(new ChainShape());
        }

        // 1 часть карты
        groundShape = groundList.get(0);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-50, 0), new Vector2(50, 0)});
        fixtureDef.shape = groundShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;
        worldCreater.getWorld().createBody(bodyDef).createFixture(fixtureDef);
        groundShape.dispose();

        // 2 часть карты
        groundShape = groundList.get(1);
        bodyDef.position.set(-90, -70);
        groundShape.createChain(new Vector2[]{new Vector2(50, 0), new Vector2(-50, 30)});
        fixtureDef.shape = groundShape;
        fixtureDef.friction = .9f;
        fixtureDef.restitution = 0;
        worldCreater.getWorld().createBody(bodyDef).createFixture(fixtureDef);
        groundShape.dispose();

    }


}
