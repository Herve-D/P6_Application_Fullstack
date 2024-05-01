import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/models/topic.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/components/auth/services/auth.service';
import { SessionService } from 'src/app/components/auth/services/session.service';
import { ProfileService } from './services/profile.service';
import { PASSWORD_PATTERN } from 'src/app/constants/password.validator';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {

    public user!: User;
    public userForm!: FormGroup;
    public subscriptions$!: Observable<Topic[]>;
    public onError = false;

    constructor(private authService: AuthService,
        private profileService: ProfileService,
        private fb: FormBuilder,
        private matSnack: MatSnackBar,
        private sessionService: SessionService,
        private router: Router) { }

    ngOnInit(): void {
        this.authService.me().subscribe((user: User) => {
            this.user = user;
            this.initForm(user);
            this.fetchSubscriptions();
        });
    }

    private initForm(user: User): void {
        this.userForm = this.fb.group({
            name: [user.name,
            [
                Validators.required,
                Validators.min(3),
                Validators.max(20)
            ]
            ],
            email: [user.email,
            [
                Validators.required,
                Validators.email
            ]
            ],
            password: [user.password,
            [
                Validators.required,
                Validators.min(8),
                Validators.pattern(PASSWORD_PATTERN)
            ]
            ]
        });
    }

    private fetchSubscriptions(): void {
        this.subscriptions$ = this.profileService.getSubscriptions(this.user.id.toString());
    }

    public submit(user: User): void {
        const updateUser: User = {
            ...user,
            name: this.userForm.value.name,
            email: this.userForm.value.email,
            password: this.userForm.value.password ? this.userForm.value.password : user.password
        };
        this.profileService.updateUser(user.id.toString(), updateUser).subscribe({
            next: (user: User) => {
                this.user = user;
                this.snackInfo('Mise à jour effectuée !')
            },
            error: _ => this.onError = true,
        });
    }

    public unSubscribe(id: number): void {
        this.profileService.unSubscribe(id.toString(), this.user.id.toString()).subscribe(() => {
            this.fetchSubscriptions();
            this.snackInfo('Abonnement supprimé !')
        });
    }

    private snackInfo(message: string): void {
        this.matSnack.open(message, 'Close', { duration: 3000 });
    }

    public logout(): void {
        this.sessionService.logOut();
        this.router.navigate(['']);
    }

}
