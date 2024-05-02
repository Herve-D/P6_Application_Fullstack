import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'MDD';
  @ViewChild('sidenav') sidenav!: MatSidenav;

  constructor(private router: Router) { }

  public isHome(): boolean {
    return this.router.url.valueOf() === '/';
  }

  public isAuth(): boolean {
    return this.router.url.startsWith('/login') || this.router.url.startsWith('/register');
  }

  public openSideBar() {
    this.sidenav.toggle();
  }

  public closeSideBar() {
    this.sidenav.close();
  }

}
