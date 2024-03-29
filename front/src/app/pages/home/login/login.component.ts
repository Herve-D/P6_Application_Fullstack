import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse } from 'src/app/models/authResponse.interface';
import { Login } from 'src/app/models/login.interface';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {

    form = this.fb.group({
        email: [''],
        password: ['']
    });

    constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

    public submit(): void {
        const login = this.form.value as Login;
        this.authService.login(login).subscribe(
            (response: AuthResponse) => {
                localStorage.setItem('token', response.token);
                this.router.navigate(['/topic']);
            }
        );
    }

}
