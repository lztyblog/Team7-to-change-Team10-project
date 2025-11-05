package io.github.team10.escapefromuni;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class EventGreggs extends Event {

    private Texture greggsTexture;
    private Sprite greggsSprite;

    private boolean used = false;

    public EventGreggs(Player player, EscapeGame game)
    {
        super(EventType.POSITIVE, player, game);
        greggsTexture = new Texture("GreggsSausageRoll.png");
    }

    @Override
    public void startEvent() {
        if (eventFinished) return;

        greggsSprite = new Sprite(greggsTexture);
        greggsSprite.setSize(1f, 1f);
        greggsSprite.setPosition(7.5f, 4f);
    }

    @Override
    public void endEvent() {
        if (!eventFinished && used)
        {
            eventFinished = true;
            greggsTexture.dispose();
        }
    }

    @Override
    public void update(float delta) {
        if (!used)
        {
            float playerDist = getPlayerGreggsDist();
            if (playerDist < 1f)
            {
                pickupGreggs();
            }
        }
    }

    private float getPlayerGreggsDist()
    {
        Vector2 playerPos = player.getCenter();
        float greggsX = greggsSprite.getX() + greggsSprite.getWidth() / 2f;
        float greggsY = greggsSprite.getY() + greggsSprite.getHeight() / 2f;
        Vector2 greggsCenter = new Vector2(greggsX, greggsY);
        return greggsCenter.dst(playerPos);
    }

    private void pickupGreggs()
    {
        used = true;
        player.increaseSpeed(2f);
    }

    @Override
    public void draw() {
        if (!used)
        {
            greggsSprite.draw(game.batch);
        }
    }

    @Override
    public void drawUI() {}
    
}
 