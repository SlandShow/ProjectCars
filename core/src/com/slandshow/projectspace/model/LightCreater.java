package com.slandshow.projectspace.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;


public class LightCreater {

    private PointLight light;
    private RayHandler rayHandler;
    private ConeLight coneLight;
    private Body body;

    public LightCreater(World world, Body body) {
        this.body = body;
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(1.5f);
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public void setAmbientToRay(float ambientLight) {
        rayHandler.setAmbientLight(ambientLight);
    }

    public void createPointLight() {
        light = new PointLight(rayHandler, 100, Color.WHITE, 5f, 10, 10);
        light.attachToBody(body);
        light.setXray(false);
    }

    public void createConeLight() {
        coneLight = new ConeLight(rayHandler, 120, Color.WHITE, 15f, 10, 10, body.getAngle(), 15);
        coneLight.setSoftnessLength(0);
        coneLight.attachToBody(body);
        coneLight.setXray(false);
    }

    public void renderPointLight(float dt, OrthographicCamera camera, float x, float y) {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
        light.setXray(false);
        light.setPosition(x, y);
    }

    public void renderConeLight(float dt, OrthographicCamera camera, float x, float y) {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
        coneLight.setPosition(x, y);
    }
}
