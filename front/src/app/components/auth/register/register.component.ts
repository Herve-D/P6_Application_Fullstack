import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse } from 'src/app/models/authResponse.interface';
import { Register } from 'src/app/models/register.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {

    public onError = false;

    public form = this.fb.group({
        name: [''],
        email: [''],
        password: ['']
    });

    constructor(private fb: FormBuilder,
        private authService: AuthService,
        private sessionService: SessionService,
        private router: Router) { }

    public submit(): void {
        const register = this.form.value as Register;
        this.authService.register(register).subscribe(
            (response: AuthResponse) => {
                localStorage.setItem('token', response.token);
                this.authService.me().subscribe((user: User) => {
                    this.sessionService.logIn(user);
                    this.router.navigate(['/post-list'])
                });
            },
            error => this.onError = true
        );
    }

}
