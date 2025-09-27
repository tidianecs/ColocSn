import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { AuthService } from './Auth.service';
import { environment } from '../../../environments/environment';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // n'ajoute le token que pour ton API (évite les assets, etc.)
    const isApi = req.url.startsWith(environment.apiBase.replace('/api/v1',''));
    if (!isApi) return next.handle(req);

    return from(this.auth.getValidToken()).pipe(
      switchMap(token => {
        if (token) {
          const cloned = req.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
          return next.handle(cloned);
        }
        // pas authentifié -> passe la requête telle quelle (utile si endpoints publics)
        return next.handle(req);
      })
    );
  }
}
