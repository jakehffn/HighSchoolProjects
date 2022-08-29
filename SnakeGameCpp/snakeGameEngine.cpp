#include "snakeGameEngine.h"
#include <ctime>
#include <iostream>
#include <vector>
#include <SDL2/SDL.h>
#include <SDL2/SDL_ttf.h>
#include <fstream>

SnakeEngine::SnakeEngine(Window &window) :
	GameEngine(window) {

	srand(time(0));

	this->fps = 20;
	this->dirLock = false;
	this->isRunning = false;
}

void SnakeEngine::snakeInit() {

	const int startingPieces = 4;

	for (int xx = 0; xx < startingPieces; xx++) {
		this->rects.push_back(createSnakePiece(400 + 1, 200 + 10 * xx + 1));
	}

	this->dir = UP;

	setAppleLoc();
	getHighScore();

	if (!this->isRunning) {

		this->isRunning = true;
		gameLoop();
	}
}

void SnakeEngine::setAppleLoc() {

	this->appleLocation[0] = (rand() % this->width) / 10 * 10 + 1;
	this->appleLocation[1] = (rand() % this->height) / 10 * 10 + 1;

	for (Rect r : this->rects) {

		if (r.xPos == this->appleLocation[0] && r.yPos == this->appleLocation[1]) {
			setAppleLoc();
		}
	}
}

Rect SnakeEngine::createSnakePiece(int xPos, int yPos) {

	RGB rgb{ 212, 190, 152, 255 };

	return Rect{ 8, 8, xPos, yPos, 1, rgb };
}

void SnakeEngine::drawSnake() {

	for (Rect r : this->rects) {
		drawRect(r);
	}
}

void SnakeEngine::setHighScore() {

	std::ofstream temp;
	temp.open("temp.txt");

  	if (temp.is_open()) {

		temp << std::to_string(this->score);
		temp.close();

	} else {
		std::cout << "Failed to open temp file";
	}

	remove("highScore.txt");
	rename("temp.txt", "highScore.txt");
}

void SnakeEngine::getHighScore() {

	std::string score = "0";
	std::ifstream scoreFile;
	scoreFile.open("highScore.txt");

	if (scoreFile.is_open()) {
		getline(scoreFile, score);
	} else {
		std::cout << "Unable to open file" << std::endl;
	}

	this->highScore = std::stoi(score);
}

void SnakeEngine::gameLogic() {

	int dx;
	int dy;
	Rect front = this->rects.front();

	switch (this->dir) {

		case UP:
			dx = 0;
			dy = -10;
			break;

		case DOWN:
			dx = 0;
			dy = 10;
			break;

		case LEFT:
			dx = -10;
			dy = 0;
			break;

		case RIGHT:
			dx = 10;
			dy = 0;
			break;

		default:
			dx = 0;
			dy = -10;
			break;
	}

	int newX, newY;
	newX = front.xPos + dx;
	newY = front.yPos + dy;

	bool gOver = false;

	for (Rect r : this->rects) {

		if ((newX == r.xPos && newY == r.yPos)) {
			gOver = true;
		}
	}

	if (newX >= this->width || newY >= this->height || newX < 0 || newY < 0) {
		gOver = true;
	}

	if (newX == this->appleLocation[0] && newY == this->appleLocation[1]) {

		setAppleLoc();
		this->score++;

	} else {
		this->rects.erase(this->rects.end() - 1);
	}

	if (!gOver) {

		this->rects.insert(this->rects.begin(), createSnakePiece(newX, newY));

		int highScore = (this->score > this->highScore) ? this->score : this->highScore;
		drawScore(this->score, highScore);
		drawSnake();

		Rect r = createSnakePiece(this->appleLocation[0], this->appleLocation[1]);

		r.rgb.r = 234;
		r.rgb.g = 105;
		r.rgb.b = 98;

		drawRect(r);

	} else {
		gameOver();
	}
	
	this->dirLock = false;
}

void SnakeEngine::gameOver() {

	if (this->score > this->highScore) {

		setHighScore();
		getHighScore();
	}

	this->rects.clear();
	this->dirLock = false;
	this->score = 0;

	snakeInit();
}

void SnakeEngine::pollEvents(SDL_Event &event) {

	switch (event.type) {
	
		case SDL_QUIT:

			this->closed = true;
			break;

		case SDL_KEYDOWN:

			switch (event.key.keysym.sym) {

				case SDLK_ESCAPE:

					this->closed = true;
					break;

				case SDLK_w:
				case SDLK_UP:

					if (this->dir != DOWN && !this->dirLock) {
						this->dir = UP;
						this->dirLock = true;
					}

					break;

				case SDLK_s:
				case SDLK_DOWN:

					if (this->dir != UP && !this->dirLock) {
						this->dir = DOWN;
						this->dirLock = true;
					}

					break;

				case SDLK_a:
				case SDLK_LEFT:

					if (this->dir != RIGHT && !this->dirLock) {
						this->dir = LEFT;
						this->dirLock = true;
					}

					break;

				case SDLK_d:
				case SDLK_RIGHT:

					if (this->dir != LEFT && !this->dirLock) {
						this->dir = RIGHT;
						this->dirLock = true;
					}

					break;
			}

			break;

		default:
			break;
	}
}