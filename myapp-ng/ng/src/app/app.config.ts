import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter, withRouterConfig, withInMemoryScrolling } from '@angular/router';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
	provideRouter(routes, withInMemoryScrolling(), withRouterConfig({
	  onSameUrlNavigation: 'reload'
	}))

  ]
};
