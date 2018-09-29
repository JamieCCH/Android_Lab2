// [Game2014][Lab 2]
// [Name]Jamie Ching-chun Huang
// [ID] 101088322

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;

	private int size = 10;

	private Texture[] myTexture1 = new Texture[size];
	private Texture[] myTexture2 = new Texture[size];

	private int[] imgX = new int[size];
	private int[] imgY = new int[size];

	private int[] img2X = new int[size];
	private int[] img2Y = new int[size];

	private int bgColorIndex = 0;

	private   Color[] bgColorArray =  {
			Color.SALMON,
			Color.SKY,
			Color.OLIVE,
			Color.MAROON,
			Color.NAVY,
			Color.CORAL,
			Color.BLUE,
			Color.YELLOW,
			Color.CHARTREUSE,
			Color.CYAN,
			Color.GREEN,
			Color.ORANGE,
			Color.PURPLE,
			Color.LIGHT_GRAY,
			Color.LIME,
			Color.PINK,
			Color.GOLD,
			Color.FIREBRICK,
	};


	private  Color[] colorArray =  {
			new Color(226/255f, 226/255f, 115/255f,0.65f),
			new Color(84/255f, 152/255f, 15/255f, 0.87f),
			new Color(152/255f, 15/255f, 84/255f,0.9f),
			new Color(5/255f, 27/255f, 152/255f,0.7f),
			new Color(183/255f, 14/255f, 144/255f,0.83f),
			new Color(65/255, 69/255, 97/255f,0.61f),
	};

	@Override
	public void create () {
		batch = new SpriteBatch();
		setTextures();
	}


	private void setTextures(){

		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();

		//instantiate 2 array of 10 new Texture images
		for(int i=0;i<size;i++) {
			myTexture1[i] = new Texture("water.jpg");
			myTexture2[i] = new Texture("GreenCarpet.jpg");

			imgX[i] = MathUtils.random(screenWidth);
			imgY[i] = MathUtils.random(screenHeight);
			img2X[i] = MathUtils.random(screenWidth);
			img2Y[i] = MathUtils.random(screenHeight);

			Gdx.app.log("myTexture2["+i+"].(x,y) ", ""+img2X[i]+"," +img2Y[i]);
		}
	}


	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		//print out the width and height of the device
		Gdx.app.log("Device size ", String.valueOf(width) + "x" + String.valueOf(width));
	}

	private void setBgColor(int bgColorIndex){
		Gdx.gl.glClearColor(bgColorArray[bgColorIndex].r,bgColorArray[bgColorIndex].g, bgColorArray[bgColorIndex].b,bgColorArray[bgColorIndex].a);
	}

	@Override
	public void render () {

		setBgColor(bgColorIndex);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		for(int i=0;i<myTexture1.length;i++){
			//draw the first array
			batch.draw(myTexture1[i], imgX[i] , imgY[i] );

			//draw the second array and log the position
			batch.draw(myTexture2[i], img2X[i] , img2Y[i] );
//			Gdx.app.log("myTexture2["+i+"].(x,y) ", ""+img2X[i]+"," +img2Y[i]);
		}

		batch.end();
	}

	@Override
	public void pause() {
		int range = colorArray.length-1;
		int bgColRange = bgColorArray.length-1;
		int index = MathUtils.random(range);
		int bgIndex = MathUtils.random(bgColRange);

		//change background color randomly from colorArray
		Gdx.gl.glClearColor( bgColorArray[bgIndex].r, bgColorArray[bgIndex].g, bgColorArray[bgIndex].b, bgColorArray[bgIndex].a);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		//set SpriteBatch randomly to a new color from batchColor
		batch.setColor(colorArray[index]);

	}

	@Override
	public void resume() {

		//reset background color
		int bgColRange = bgColorArray.length-1;
		bgColorIndex = MathUtils.random(bgColRange);

		//reset SpriteBatch color already set in pause()

		//reset images position
		setTextures();
	}


	@Override
	public void dispose () {

		//dispose all the images
		batch.dispose();
		for(int i=0;i<size;i++){
			myTexture1[i].dispose();
			myTexture2[i].dispose();
		}
	}
}
