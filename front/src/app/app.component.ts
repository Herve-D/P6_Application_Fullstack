import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'MDD';

  constructor(private router: Router) { }

  public isHome(): boolean {
    return this.router.url.valueOf() === '/';
  }

  public isAuth(): boolean {
    return this.router.url.startsWith('/login') || this.router.url.startsWith('/register');
  }

}
