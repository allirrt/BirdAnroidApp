package com.irrt.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyBirdGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] bird;
	int birdStateFlag = 0;
	float flyHeight;
	float fallingSpeed = 0;//скорость падения
    int gameStateFlag = 0;
    Texture topTube;
    Texture bottomTube;
    int spaceBetweenTube = 500; //величина пространчтва между трубами
    Random random;
    int tubeSpeed = 5;
    int tubeNumbers = 5;
    float tubeX[] = new float[tubeNumbers];
    float tubeShift[] = new float[tubeNumbers];//сдвиг труб
    float distanceBetweenTubes;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture[2];
        bird[0] = new Texture("bird_wings_up.png");
        bird[1] = new Texture("bird_wings_down.png");
        topTube = new Texture("top_tube.png");
        bottomTube = new Texture("bottom_tube.png");
        random = new Random();

        distanceBetweenTubes = Gdx.graphics.getWidth();
        for (int i = 0; i < tubeNumbers; i++){
            tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + i * distanceBetweenTubes;
       tubeShift[i] =  (random.nextFloat() - 0.5f) *
               (Gdx.graphics.getHeight() - spaceBetweenTube - 200);//двигаем трубы вверх и вниз рандомно


        }

        flyHeight =  Gdx.graphics.getHeight()/2;//высота полета
	}

	@Override
	public void render () {
        batch.begin();//запускаем игру
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//рисуем background
	    if (Gdx.input.justTouched()){ //когда произошло прикосновение
        gameStateFlag = 1;
        }
	    if (gameStateFlag == 1){//игра запустится ести пользователь прикоснулся к экрану

            if (Gdx.input.justTouched()){ //когда произошло прикосновение
                fallingSpeed = -30;
                }
            if (flyHeight > 0 || fallingSpeed < 0){//чтобы птичка не улетала выше горизонта
                fallingSpeed++;
                flyHeight -= fallingSpeed;
            }
           }else {
            if (Gdx.input.justTouched()) { //когда произошло прикосновение
                gameStateFlag = 1;
            }
        }
        for (int i = 0; i < tubeNumbers; i++) {
            if (tubeX[i] < - topTube.getWidth()){
                tubeX[i] = tubeNumbers * distanceBetweenTubes;
            } else {
                tubeX[i] -= tubeSpeed;
            }
            //рисуем трубы
            batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 +
                    spaceBetweenTube / 2 + tubeShift[i]);//рисуем верхнюю трубу(координаты)
            batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 -
                    spaceBetweenTube / 2 - bottomTube.getHeight() + tubeShift[i]);//рисуем нижнюю трубу(координаты)
        }
      if (birdStateFlag == 0){
          birdStateFlag = 1;
      }else {
          birdStateFlag= 0;
      }



   batch.draw(bird[birdStateFlag], Gdx.graphics.getWidth()/2 - bird[birdStateFlag].getWidth()/2,
           flyHeight);//рисуем птичку
   batch.end();
	}
	

}
