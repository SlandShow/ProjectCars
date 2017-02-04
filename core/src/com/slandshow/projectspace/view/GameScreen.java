package com.slandshow.projectspace.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.slandshow.projectspace.Main;
import com.slandshow.projectspace.control.PlayerInputController;
import com.slandshow.projectspace.model.GameCar;
import com.slandshow.projectspace.model.LightCreater;


/**
 * Created by Admin on 08.01.2017.
 */
public class GameScreen implements Screen {

    // VARS:
    private SpriteBatch batch;
    private Texture texture;
    private OrthographicCamera gameCam;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    // КОНСТАНТЫ:
    public static final float TIMESTEP = 1 / 60f;
    public static final int VELOCITY_ITERATIONS = 8, POSITION_ITERATION = 3;
    public static final float SPEED = 500;
    public static Vector2 MOVEMENT = new Vector2();

    // Физика Box2D движка
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private CircleShape shape;
    private Body body;
    private Body box;
    private ChainShape groundShape;
    private ChainShape groundShape2;
    private PolygonShape boxShape;
    private Array<Body> bodies;
    private Mesh mesh;


    // Спрайты и текстуры
    private Sprite playerSprite;
    private Sprite background;

    public static GameCar car;
    private FixtureDef wheelFixtureDef;
    private LightCreater light;

    public GameScreen() {
        batch = new SpriteBatch();
        bodies = new Array<Body>();
    }

    public Body getBoxBody() {
        return box;
    }

    public OrthographicCamera getGameCam() {
        return gameCam;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        gameCam = new OrthographicCamera(Gdx.graphics.getWidth() / Main.PPM, Gdx.graphics.getHeight() / Main.PPM);

        // Создание хэндлера
        Gdx.input.setInputProcessor(new PlayerInputController(this));

        // Создание тел:
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        // Создаем машину
        wheelFixtureDef = new FixtureDef();
        fixtureDef.density = 5;
        fixtureDef.friction = .4f;
        fixtureDef.restitution = .3f;

        wheelFixtureDef.density = fixtureDef.density * 1.5f;
        wheelFixtureDef.friction = 50;
        wheelFixtureDef.restitution = .4f;

        car = new GameCar(world, fixtureDef, wheelFixtureDef, 0, 3, 3, 1.25f);

        /*
        // Прямоугольник
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(2.25f, 10);

        boxShape = new PolygonShape();
        boxShape.setAsBox(.5f, 1);

        fixtureDef.shape = boxShape;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 5;

        box = world.createBody(bodyDef);
        box.createFixture(fixtureDef);

        boxShape.dispose();

        // Задаем текстуру нашей фикстуре
        playerSprite = new Sprite(new Texture("sprites/player.png"));
        playerSprite.setSize(playerSprite.getWidth() / Main.PPM, playerSprite.getHeight() / Main.PPM);
        playerSprite.setOrigin(playerSprite.getWidth() / 2, playerSprite.getHeight() / 2);
        box.setUserData(playerSprite);

        // Окружность
        shape = new CircleShape();
        shape.setPosition(new Vector2(0, -1.0f));
        shape.setRadius(.5f);


        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f; // вес
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .75f;

        box.createFixture(fixtureDef);

        shape.dispose();
     */

        // Земля
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-50, 0), new Vector2(50, 0)});

        fixtureDef.shape = groundShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape.dispose();

        bodyDef.position.set(-90, -70);
        groundShape2 = new ChainShape();
        groundShape2.createChain(new Vector2[]{new Vector2(50, 0), new Vector2(-50, 30)});

        fixtureDef.shape = groundShape2;
        fixtureDef.friction = .9f;
        fixtureDef.restitution = 0;

        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape2.dispose();

        // Создание света
        light = new LightCreater(world, car.getChassis());
        light.createConeLight();

        // Создание заднего фона
        background = new Sprite(new Texture("background/sky.png"));
        background.setPosition(-background.getWidth() / 2, 0);
    }

    @Override
    public void render(float delta) {

        // Очистка
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATION);

//        box.applyForceToCenter(MOVEMENT, true);

        // Обновление камеры
        gameCam.position.set(car.getChassis().getPosition().x, car.getChassis().getPosition().y, 0);
        gameCam.update();

        // для рендеринга, который не связан с движком
        // рендеринг текстур, спрайтов и т.п.
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();

        // Рисуем задний фон
       // background.draw(batch);

        // Рисуем текстуры машины                           
        car.render(delta, batch);

        world.getBodies(bodies); // Движок изменяет нашу коллекцию, постоянно пополняя ее новыми объектами типа Body
        for (Body body : bodies) {
            if (body.getUserData() != null && body.getUserData() instanceof Sprite) {
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(batch);
            }
        }
        batch.end();

        // Рендеринг фикстур Box2D движка
        debugRenderer.render(world, gameCam.combined);

        // Рендеринг света
        light.renderConeLight(delta, gameCam, car.getChassis().getPosition().x, car.getChassis().getPosition().y);

    }

    @Override
    public void resize(int width, int height) {
        gameCam.viewportWidth = width / Main.PPM;
        gameCam.viewportHeight = height / Main.PPM;
        gameCam.update();
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
        world.dispose();
        batch.dispose();
        debugRenderer.dispose();
        playerSprite.getTexture().dispose();
        light.getRayHandler().dispose();
    }
}
