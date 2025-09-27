import Keycloak, { KeycloakInstance, KeycloakInitOptions } from 'keycloak-js';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private kc!: KeycloakInstance;

  // --- INITIALISATION AU DEMARRAGE ---
  async init(): Promise<boolean> {
    this.kc = new Keycloak({
      url: `${environment.keycloak.url}`,
      realm: environment.keycloak.realm,
      clientId: environment.keycloak.clientId
    });

    const options: KeycloakInitOptions = {
      onLoad: 'check-sso',                 // ne force pas, mais lit la session si elle existe
      pkceMethod: 'S256',
      silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
      checkLoginIframe: true
    };

    const authenticated = await this.kc.init(options);

    // auto-refresh simple
    // refresh 20s avant l'expiration
    setInterval(() => {
      this.kc.updateToken(20).catch(() => {
        // si refresh échoue, on peut rediriger vers login
        console.warn('Token refresh failed');
      });
    }, 10000);

    return authenticated;
  }

  // --- APIS AUTH ---
  login(redirectPath: string = '/home') {
    const redirectUri = window.location.origin + redirectPath;
    return this.kc.login({ redirectUri });
  }


  logout() {
    return this.kc.logout({ redirectUri: window.location.origin });
  }

  register(redirectPath: string = '/home') {
    const redirectUri = window.location.origin + redirectPath;
    return this.kc.register({ redirectUri });
  }

  account() {
    return this.kc.accountManagement();
  }

  // --- ETAT / INFOS ---
  async isLoggedIn(): Promise<boolean> {
    return !!this.kc?.token && !this.isTokenExpired();
  }

  getSubject(): string | undefined {
    return this.kc?.tokenParsed?.sub as string | undefined;
  }

  getUsername(): string | undefined {
    return (this.kc?.tokenParsed?.['preferred_username'] as string) || undefined;
  }

  // --- TOKEN ---
  private isTokenExpired(): boolean {
    if (!this.kc?.tokenParsed?.exp) return true;
    const expiresIn = this.kc.tokenParsed.exp * 1000 - new Date().getTime();
    return expiresIn <= 0;
  }

  /**
   * Retourne un token valide (refresh si < minValidity s)
   */
  async getValidToken(minValiditySeconds = 20): Promise<string | undefined> {
    if (!this.kc) return undefined;
    try {
      await this.kc.updateToken(minValiditySeconds);
      return this.kc.token || undefined;
    } catch {
      return undefined;
    }
  }
}
