import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse } from 'src/app/models/authResponse.interface';
import { Register } from 'src/app/models/register.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/components/auth/services/auth.service';
import { SessionService } from 'src/app/components/auth/services/session.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {

    public onError = false;

    public form = this.fb.group({
        name: [
            '',
            [
                Validators.required,
                Validators.min(3),
                Validators.max(20)
            ]
        ],
        email: [
            '',
            [
                Validators.required,
                Validators.email
            ]
        ],
        password: [
            '',
            [
                Validators.required,
                Validators.min(8),
                Validators.max(40),
                Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')
            ]
        ]
    });

    constructor(private fb: FormBuilder,
        private authService: AuthService,
        private sessionService: SessionService,
        private router: Router) { }

    public submit(): void {
        const register = this.form.value as Register;
        this.authService.register(register).subscribe({
            next: (response: AuthResponse) => {
                localStorage.setItem('token', response.token);
                this.authService.me().subscribe((user: User) => {
                    this.sessionService.logIn(user);
                    this.router.navigate(['/post-list'])
                });
            },
            error: _ => this.onError = true,
        });
    }

}
