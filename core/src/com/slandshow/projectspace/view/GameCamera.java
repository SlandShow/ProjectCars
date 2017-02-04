package com.slandshow.projectspace.view;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Admin on 04.02.2017.
 */

public class GameCamera {

    private OrthographicCamera gameCam;

    public GameCamera(float x, float y) {
        gameCam = new OrthographicCamera(x, y);
    }

    public void update(float dt) {
        gameCam.update();
    }

    public void render(float dt) {

    }

    public void setGameCam(OrthographicCamera gameCam) {
        this.gameCam = gameCam;
    }

    public OrthographicCamera getGameCam() {
        return gameCam;
    }
}
