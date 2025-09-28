import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthService } from './Core/Auth/Auth.service';
import { AuthInterceptor } from './Core/Auth/Auth.interceptor';
import { HomePageComponent } from './Layouts/home-page/home-page.component';
import { NavbarComponent } from './Layouts/navbar/navbar.component';

export function kcFactory(auth: AuthService) {
  return () => auth.init(); // <- ta méthode d'init keycloak-js
}

@NgModule({
  declarations: [AppComponent, HomePageComponent, NavbarComponent],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule],
  providers: [
    { provide: APP_INITIALIZER, useFactory: kcFactory, deps: [AuthService], multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
