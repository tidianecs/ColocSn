import { Component } from '@angular/core';
import { AuthService } from './Core/Auth/Auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FrontColocSn';
  constructor(public auth: AuthService) {}

  login()    { this.auth.login('/home'); }
  logout()   { this.auth.logout(); }
  register() { this.auth.register('/home'); }
}
