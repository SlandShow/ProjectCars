package com.slandshow.projectspace.model.scenes;

import com.badlogic.gdx.Screen;
import com.slandshow.projectspace.model.world_creater.WorldCreater;

/**
 * Created by Admin on 04.02.2017.
 */

public class FirstLvl implements Screen {

    private WorldCreater worldCreater;

    public FirstLvl(WorldCreater worldCreater) {
        this.worldCreater = worldCreater;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        worldCreater.getGameRender().render(delta);
    }

    @Override
    public void resize(int width, int height) {
        worldCreater.getGameCamera().resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        worldCreater.dispose();
        worldCreater.getGameRender().dispose();
    }
}
