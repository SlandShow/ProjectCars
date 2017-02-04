package com.slandshow.projectspace.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.slandshow.projectspace.Main;

public class GameCar {

    private Body chassis, legtWheel, rightWheel;
    private WheelJoint leftAxis, rightAxis;
    private float motorSpeed;
    private Sprite wheelSprite, carSprite;
    private Texture wheelTexture, carTexture;


    public GameCar(World world,
                   FixtureDef chassisFixtureDef,
                   FixtureDef wheelFixtureDef,
                   float x, float y,
                   float width, float height) {

        // Задаем скорость
        motorSpeed = 50;

        // Создание тела (машина) с помощью Box2D
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 50);

        PolygonShape chassisShape = new PolygonShape();
        chassisShape.set(new float[]{
                        -width / 2, -height / 2,
                        width / 2, -height / 2,
                        width / 2 * .4f, height / 2,
                        -width / 2 * .8f, height / 2 * .8f
                }
        );

        chassisFixtureDef.shape = chassisShape;

        chassis = world.createBody(bodyDef);
        chassis.createFixture(chassisFixtureDef);

        // левое колесо
        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(height / 3f);
        wheelFixtureDef.shape = wheelShape;
        legtWheel = world.createBody(bodyDef);
        legtWheel.createFixture(wheelFixtureDef);

        // правое колесо
        rightWheel = world.createBody(bodyDef);
        rightWheel.createFixture(wheelFixtureDef);

        // Левая связка
        WheelJointDef axisDef = new WheelJointDef();
        axisDef.bodyA = chassis;
        axisDef.bodyB = legtWheel;
        axisDef.localAxisA.set(Vector2.Y); // 0, 1, так как это ость OY

        axisDef.localAnchorA.set(-width / 2 * .8f + wheelShape.getRadius() / 2, -height / 2 * 1.25f);
        axisDef.frequencyHz = 4.5f;
        axisDef.maxMotorTorque = chassisFixtureDef.density * 20;
        leftAxis = (WheelJoint) world.createJoint(axisDef);

        // Правая связка
        axisDef.bodyB = rightWheel;
        axisDef.localAnchorA.x *= -1;
        rightAxis = (WheelJoint) world.createJoint(axisDef);

        // Загрузка текстур
        wheelSprite = new Sprite(new Texture(Gdx.files.internal("car_textures/wheels/wheel.png")));
        wheelSprite.setSize(wheelSprite.getWidth() / (Main.PPM * Main.PPM) + .16f, wheelSprite.getHeight() / (Main.PPM * Main.PPM) + .16f); // Делаем нормальный размер
        wheelSprite.setOrigin(wheelSprite.getWidth() / 2, wheelSprite.getHeight() / 2);
    }

    public void render(float dt, SpriteBatch batch) {
        // Левое колесо
        wheelSprite.setPosition(legtWheel.getPosition().x - wheelSprite.getWidth() / 2, legtWheel.getPosition().y - wheelSprite.getHeight() / 2);
        wheelSprite.setRotation(legtWheel.getAngle() * MathUtils.radiansToDegrees);
        wheelSprite.draw(batch);

        // Правое колесо
        wheelSprite.setPosition(rightWheel.getPosition().x - wheelSprite.getWidth() / 2, rightWheel.getPosition().y - wheelSprite.getHeight() / 2);
        wheelSprite.setRotation(rightWheel.getAngle() * MathUtils.radiansToDegrees);
        wheelSprite.draw(batch);
    }

    public Body getChassis() {
        return chassis;
    }

    public Body getLegtWheel() {
        return legtWheel;
    }

    public Body getRightWheel() {
        return rightWheel;
    }

    public WheelJoint getLeftAxis() {
        return leftAxis;
    }

    public WheelJoint getRightAxis() {
        return rightAxis;
    }

    public float getMotorSpeed() {
        return motorSpeed;
    }
}
