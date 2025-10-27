package io.github.team10.escapefromuni;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * This class will deal with the 'main' game logic.
 * 
 * For example, creating the player, calling initialiseMap() on the RoomManager.
 * Will handle rendering of game textures using the SpriteBatch stored in EscapeGame. 
 */
public class GameScreen extends ScreenAdapter {
    final EscapeGame game;
    Texture backgroundTexture;

    public GameScreen(final EscapeGame game)
    {
        this.game = game;
        backgroundTexture = new Texture("TempBackground.png");
    }

    @Override
    public void render(float delta) {
		update(delta);
		draw();
	}

    private void update(float delta)
    {

    }

    private void draw()
    {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
		game.batch.begin();

        float worldWidth = game.viewport.getWorldWidth();
		float worldHeight = game.viewport.getWorldHeight();
        game.batch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }
}
