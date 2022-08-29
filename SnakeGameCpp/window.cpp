#include "window.h"
#include <iostream>
#include <string>

Window::Window(const std::string &title, int width, int height) :
	title(title), width(width), height(height) {

	this->closed = !init();
}

Window::~Window() {

	SDL_DestroyRenderer(this->renderer);
	SDL_DestroyWindow(this->windowSDL);
	SDL_Quit();
}


bool Window::init() {

	if (SDL_Init(SDL_INIT_VIDEO) != 0) {

		std::cerr << "Failed to initialize SDL \n";
		return 0;
	}

	if (TTF_Init() != 0){

		std::cerr << "Failed to initialize SDL_ttf \n";
		return 0;
	}

	this->windowSDL = SDL_CreateWindow(
		this->title.c_str(),
		SDL_WINDOWPOS_CENTERED,
		SDL_WINDOWPOS_CENTERED,
		this->width, this->height,
		0
	);

	this->font = TTF_OpenFont("arial.ttf", 20);
	this->fontSmaller = TTF_OpenFont("arial.ttf", 12);

	if(!this->font) {
    	printf("TTF_OpenFont: %s\n", TTF_GetError());
	}

	if (this->windowSDL == nullptr) {

		std::cerr << "Failed to create window\n";
		return 0;
	}

	this->renderer = SDL_CreateRenderer(this->windowSDL, -1, SDL_RENDERER_ACCELERATED | SDL_RENDERER_PRESENTVSYNC);
	
	if (this->renderer == nullptr) {

		std::cerr << "Failed to create renderer\n";
		return 0;
	}

	return true;
}

void Window::drawRect(int w, int h, int x, int y, int r, int g, int b, int a) {

	SDL_Rect rect;

	rect.w = w;
	rect.h = h;
	rect.x = x;
	rect.y = y;

	SDL_SetRenderDrawColor(this->renderer, r, g, b, a);
	SDL_RenderFillRect(this->renderer, &rect);
}

void Window::drawRect(Rect r) {
	Window::drawRect(r.width, r.height, r.xPos, r.yPos, r.rgb.r, r.rgb.g, r.rgb.b, r.rgb.a);
}

void Window::drawScore(int score, int highScore) {
	
	std::string s = std::to_string(score);
	char const *p = s.c_str();

	std::string sh = std::to_string(highScore);
	std::string highScoreString = "High Score: ";
	char const *ph = highScoreString.append(sh).c_str();

	SDL_Color color = { 212, 190, 152 };
	SDL_Surface * surface = TTF_RenderText_Solid(this->font, p, color);
	SDL_Texture * texture = SDL_CreateTextureFromSurface(this->renderer, surface);

	int texW = 0;
	int texH = 0;
	SDL_QueryTexture(texture, NULL, NULL, &texW, &texH);
	SDL_Rect dstrect = { 50, 50, texW, texH };

	SDL_RenderCopy(this->renderer, texture, NULL, &dstrect);

	color = { 212, 190, 152 };
	surface = TTF_RenderText_Solid(this->fontSmaller, ph, color);
	texture = SDL_CreateTextureFromSurface(this->renderer, surface);

	
	SDL_QueryTexture(texture, NULL, NULL, &texW, &texH);
	dstrect = { 50, 70, texW, texH };

	SDL_RenderCopy(this->renderer, texture, NULL, &dstrect);
}

void Window::pollEvents(SDL_Event &event) {

	switch (event.type) {

		case SDL_QUIT:
		
			this->closed = true;
			break;

		case SDL_KEYDOWN:
				
			switch (event.key.keysym.sym) {

			case SDLK_ESCAPE:

				this->closed = true;
				break;
			}
			
			break;

		case SDL_MOUSEMOTION:
			break;

		case SDL_MOUSEBUTTONDOWN:
			break;

		case SDL_MOUSEBUTTONUP:
			break;

		default:
			break;
	}
	
}

void Window::clear() const {

	SDL_RenderPresent(this->renderer);
	SDL_SetRenderDrawColor(this->renderer, 41, 40, 40, 255);
	SDL_RenderClear(this->renderer);
}
