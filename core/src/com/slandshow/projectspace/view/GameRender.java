package com.slandshow.projectspace.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.slandshow.projectspace.game_constants.Constants;
import com.slandshow.projectspace.model.world_creater.WorldCreater;

/**
 * Created by Admin on 04.02.2017.
 */

public class GameRender {

    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private WorldCreater worldCreater;

    public GameRender(WorldCreater worldCreater) {
        batch = new SpriteBatch();
        debugRenderer = new Box2DDebugRenderer();
        this.worldCreater = worldCreater;
    }


    public void update(float dt) {

    }

    public void render(float dt) {

        // Очистка
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Система Frames per second
        worldCreater.getWorld().step(Constants.TIMESTEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATION);

        // Обновление камеры
    }


    public SpriteBatch getBatch() {
        return batch;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }
}
