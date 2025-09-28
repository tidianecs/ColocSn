import { Component } from '@angular/core';
import { AuthService } from 'src/app/Core/Auth/Auth.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
    constructor(private auth: AuthService) {}
    login(){ this.auth.login('/home'); }
    register(){ this.auth.register('/home'); }
}
