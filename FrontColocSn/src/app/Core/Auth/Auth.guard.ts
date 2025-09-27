import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { AuthService } from './Auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService) {}
  async canActivate(): Promise<boolean> {
    const logged = await this.auth.isLoggedIn();
    if (!logged) {
      await this.auth.login(window.location.href); // redirige vers Keycloak puis revient
    }
    return true;
  }
}
