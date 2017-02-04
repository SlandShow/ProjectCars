package com.slandshow.projectspace.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Admin on 09.01.2017.
 */
public class GameMenu implements Screen {

    private Stage stage;
    private Table table;
    private TextButton buttonExit, buttonPlay, buttonSettings;
    private Label heading;
    private Skin skin;
    private BitmapFont white, black;
    private TextureAtlas atlas;

    public GameMenu() {

    }


    @Override
    public void show() {
        stage = new Stage();
        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(atlas);
        table = new Table(skin);

        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Создание фонта
        black = new BitmapFont(Gdx.files.internal("game_fonts/black16.fnt"), false);
        white = new BitmapFont(Gdx.files.internal("game_fonts/white16.fnt"), false);

        // Создание кнопки
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        buttonExit = new TextButton("Exit", textButtonStyle);
        buttonExit.pad(20);

        Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.WHITE);
        heading = new Label("Project Space", headingStyle);
        heading.setFontScale(2);

        // Закрепляем кнопки на экран
        table.add(heading);
        table.row();
        table.add(buttonExit);
        table.debug();
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        // Очистка
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
    }
}
