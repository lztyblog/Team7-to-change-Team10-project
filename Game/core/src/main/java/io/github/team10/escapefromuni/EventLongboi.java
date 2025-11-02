package io.github.team10.escapefromuni;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class EventLongboi extends Event {

    private boolean hidden = true;
    private final Texture longboiHiddenTexture;
    private final Texture longboiTexture;
    private Sprite longboiSprite;

    public EventLongboi(Player player, EscapeGame game)
    {
        super(EventType.HIDDEN, player, game);
        longboiTexture = new Texture("Longboi.png");
        longboiHiddenTexture = new Texture("LongboiShadow.png");

    }

    @Override
    public void startEvent()
    {
        // Check that the event has not finished (has not been activated previously).
        if (eventFinished) return;

        longboiSprite = new Sprite(longboiHiddenTexture);
        longboiSprite.setSize(1f, 2f);
        longboiSprite.setPosition(8f, 4.5f);
    }

    @Override
    public void endEvent()
    {
        if (!eventFinished && !hidden){
            eventFinished = true;
            longboiHiddenTexture.dispose();
            longboiTexture.dispose();
        }
    }

    @Override
    public void update(float delta)
    {
        // Check every frame if player is close enough to reveal.
        if (hidden)
        {
            float playerDist = getPlayerLongboiDist();
            if (playerDist < 3f)
            {
                reveal();
            }
        }
    }

    /**
     * Reveal the hidden event - longboi will appear.
     */
    private void reveal()
    {
        hidden = false;
        longboiSprite = new Sprite(longboiTexture);
        longboiSprite.setSize(1f, 2f);
        longboiSprite.setPosition(8f, 4.5f);
        // TODO: Show text/speech bubble.
    }

    private float getPlayerLongboiDist()
    {
        Vector2 playerPos = player.getCenter();
        float longboiX = longboiSprite.getX() + longboiSprite.getWidth() / 2f;
        float longboiY = longboiSprite.getY() + longboiSprite.getHeight() / 2f;
        Vector2 longboiCenter = new Vector2(longboiX, longboiY);
        return longboiCenter.dst(playerPos);
    }

    @Override
    public void draw()
    {
        if (eventFinished) return;
        longboiSprite.draw(game.batch);
    }
}
