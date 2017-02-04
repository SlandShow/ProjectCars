package com.slandshow.projectspace.control;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.slandshow.projectspace.Main;
import com.slandshow.projectspace.view.GameScreen;

import sun.rmi.runtime.Log;

public class PlayerInputController implements InputProcessor {

    private GameScreen screen;
    private final float maxZoomScrolled = 6f; // Минимальный скролинг камеры
    private final float minZoomScrolled = 0.3f; // Максимальный скроллинг камеры

    public PlayerInputController(GameScreen screen) {
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            // прыжок
            case Input.Keys.W:
                //GameScreen.MOVEMENT.y = GameScreen.SPEED;
                GameScreen.car.getLeftAxis().enableMotor(true);
                GameScreen.car.getLeftAxis().setMotorSpeed(-GameScreen.car.getMotorSpeed());
                break;
            // лево
            case Input.Keys.A:
                //GameScreen.MOVEMENT.x = -GameScreen.SPEED;
                break;
            // право
            case Input.Keys.D:
                // GameScreen.MOVEMENT.x = GameScreen.SPEED;
                break;
            // назад
            case Input.Keys.S:
                GameScreen.car.getLeftAxis().enableMotor(true);
                GameScreen.car.getLeftAxis().setMotorSpeed(GameScreen.car.getMotorSpeed());
                break;

        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            // прыжок
            case Input.Keys.W:
                GameScreen.MOVEMENT.y = 0;
                GameScreen.car.getLeftAxis().enableMotor(false);

                break;
            // лево
            case Input.Keys.A:
                GameScreen.MOVEMENT.x = 0;
                break;
            // право
            case Input.Keys.D:
                GameScreen.MOVEMENT.x = 0;
                break;
            // назад
            case Input.Keys.S:
                GameScreen.car.getLeftAxis().enableMotor(false);

                break;

        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (screen.getGameCam().zoom >= (minZoomScrolled - amount / Main.PPM) && screen.getGameCam().zoom <= (maxZoomScrolled - amount / Main.PPM))
            screen.getGameCam().zoom += amount / Main.PPM;
        return true;
    }
}
