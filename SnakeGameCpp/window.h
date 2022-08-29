#pragma once

#include <string>
#include <SDL2/SDL.h>
#include <SDL2/SDL_ttf.h>
#include "visualStructs.h"

class Window {
public:
	Window(const std::string &title, int width, int height);
	~Window();

	virtual void pollEvents(SDL_Event &event);
	void clear() const;

	inline bool isClosed() const { return this->closed; }

	void drawRect(int w, int h, int x, int y, int r, int g, int b, int a);
	void drawRect(Rect r);
	void drawScore(int score, int highScore);

private:
	bool init();

	std::string title;
	SDL_Window* windowSDL = nullptr;

protected:
	SDL_Renderer* renderer = nullptr;
	TTF_Font* font = nullptr;
	TTF_Font* fontSmaller = nullptr;

	bool closed = false;

	int width = 800;
	int height = 600;
};
