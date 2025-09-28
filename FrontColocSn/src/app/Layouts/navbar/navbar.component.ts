import { Component } from '@angular/core';
import { AuthService } from 'src/app/Core/Auth/Auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
    title = 'FrontColocSn';
    constructor(public auth: AuthService) {}
  
    login()    { this.auth.login('/home'); }
    logout()   { this.auth.logout(); }
    register() { this.auth.register('/home'); }
}
