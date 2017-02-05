package com.slandshow.projectspace.model.world_creater;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.slandshow.projectspace.game_constants.Constants;
import com.slandshow.projectspace.model.GameCar;
import com.slandshow.projectspace.model.GameMap;
import com.slandshow.projectspace.model.LightCreater;
import com.slandshow.projectspace.view.GameCamera;
import com.slandshow.projectspace.view.GameRender;

/**
 * Created by Admin on 04.02.2017.
 */

public class WorldCreater {


    public enum WorldState {
        LOADED, UNLOADED, NONE
    }

    private LightCreater lightCreater;
    private WorldState currentState;
    private GameCar gameCar;
    private FixtureDef wheelFixtureDef;
    private GameCamera gameCamera;
    private World world;
    private GameRender gameRender;
    private GameMap gameMap;

    // Физика Box2D движка
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;


    public WorldCreater() {

        // Основа для всей игры
        world = new World(new Vector2(0, -9.81f), true); // Основа для физики Box2D
        gameCamera = new GameCamera(Gdx.graphics.getWidth() / Constants.PPM, Gdx.graphics.getHeight() / Constants.PPM); // Создание камеры
        gameRender = new GameRender(this); // Основа для рендеринга всей игры

        // Создание оболочек для тел:
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        gameMap = new GameMap(bodyDef, fixtureDef, this);

        // Создаем машину
        wheelFixtureDef = new FixtureDef();
        fixtureDef.density = 5;
        fixtureDef.friction = .4f;
        fixtureDef.restitution = .3f;

        wheelFixtureDef.density = fixtureDef.density * 1.5f;
        wheelFixtureDef.friction = 50;
        wheelFixtureDef.restitution = .4f;

        gameCar = new GameCar(world, fixtureDef, wheelFixtureDef, 0, 3, 3, 1.25f);

        // Создаем свет от фар
        lightCreater = new LightCreater(world, gameCar.getChassis());
        lightCreater.createConeLight();

        currentState = WorldState.LOADED;

    }


    public void dispose() {
        world.dispose();
    }

    public LightCreater getLightCreater() {
        return lightCreater;
    }

    public GameCar getGameCar() {
        return gameCar;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public GameRender getGameRender() {
        return gameRender;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public World getWorld() {
        return world;
    }
}
