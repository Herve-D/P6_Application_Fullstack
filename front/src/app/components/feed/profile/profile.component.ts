import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/models/topic.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/components/auth/services/auth.service';
import { SessionService } from 'src/app/components/auth/services/session.service';
import { ProfileService } from './services/profile.service';

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
            name: [user.name],
            email: [user.email]
        });
    }

    private fetchSubscriptions(): void {
        this.subscriptions$ = this.profileService.getSubscriptions(this.user.id.toString());
    }

    public submit(user: User): void {
        const updateUser: User = {
            ...user,
            name: this.userForm.value.name,
            email: this.userForm.value.email
        };
        this.profileService.updateUser(user.id.toString(), updateUser).subscribe((user: User) => {
            this.user = user;
            this.snackInfo('Mise à jour effectuée !')
        },
            error => this.onError = true);
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
