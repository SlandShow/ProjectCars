package com.slandshow.projectspace.model;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.slandshow.projectspace.Main;

/**
 * Created by Admin on 08.01.2017.
 */
public class TiledMapDrawer {

    // VARS:
    private TiledMap gameMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TmxMapLoader mapLoader;
    private World world;
    private MapLayer collisiomObjectLayer;
    private MapObjects objects;


    public TiledMapDrawer(World world) {

        this.world = world;


        // Загрузка карты
        mapLoader = new TmxMapLoader();
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        gameMap = mapLoader.load("maps/map.tmx");

        // Для рендеринга
        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap, 1 / Main.PPM);
        tiledMapRenderer.getBatch().disableBlending();

        // Создание тайлов (?)
        collisiomObjectLayer = gameMap.getLayers().get("12");
        objects = collisiomObjectLayer.getObjects();

        


    }
}
