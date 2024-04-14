import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse } from 'src/app/models/authResponse.interface';
import { Login } from 'src/app/models/login.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {

    public onError = false;

    form = this.fb.group({
        email: [''],
        password: ['']
    });

    constructor(private fb: FormBuilder,
        private authService: AuthService,
        private sessionService: SessionService,
        private router: Router) { }

    public submit(): void {
        const login = this.form.value as Login;
        this.authService.login(login).subscribe(
            (response: AuthResponse) => {
                localStorage.setItem('token', response.token);
                this.authService.me().subscribe((user: User) => {
                    this.sessionService.logIn(user);
                    this.router.navigate(['/post-list']);
                });
            },
            error => this.onError = true
        );
    }

}
