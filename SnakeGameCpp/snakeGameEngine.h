#pragma once

#include "gameEngine.h"
#include "visualStructs.h"
#include <vector>

class SnakeEngine : public GameEngine {
	
public:

	SnakeEngine(Window &window);
	void snakeInit();

private:

	virtual void pollEvents(SDL_Event &event);
	void gameLogic();
	void drawSnake();

	void setHighScore();
	void getHighScore();

	void setAppleLoc();
	void gameOver();

	Rect createSnakePiece(int, int);

	enum DIRECTION {
		LEFT,
		RIGHT,
		UP,
		DOWN
	};

private:

	DIRECTION dir;

	bool dirLock;
	bool isRunning;

	int score = 0;
	int highScore = 0;
	int oldHighScore;
	int appleLocation[2];

	std::vector<Rect> rects;
};