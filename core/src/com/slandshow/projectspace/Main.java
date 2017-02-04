package com.slandshow.projectspace;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.slandshow.projectspace.view.GameMenu;
import com.slandshow.projectspace.view.GameScreen;


public class Main extends Game {

    public static final float PPM = 32; // 1 пиксель - 1 метр


    private Screen gameScreen;
    private GameMenu menu;


    @Override
    public void create() {
        gameScreen = new GameScreen();
        menu = new GameMenu();
        setScreen(gameScreen);
    }

    @Override
    public void render() {
        super.render();
    }
}
