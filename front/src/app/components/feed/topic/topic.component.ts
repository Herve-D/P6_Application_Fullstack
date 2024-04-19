import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/models/topic.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/components/auth/services/auth.service';
import { TopicService } from './services/topic.service';

@Component({
    selector: 'app-topic',
    templateUrl: './topic.component.html',
    styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit {

    public topics$!: Observable<Topic[]>;
    private user!: User;

    constructor(private topicService: TopicService,
        private authService: AuthService,
        private matSnack: MatSnackBar,) { }

    ngOnInit(): void {
        this.topics$ = this.topicService.getTopics();
        this.fetchUser();
    }

    private fetchUser(): void {
        this.authService.me().subscribe((user: User) => {
            this.user = user;
        })
    }

    public isSubscribed(id: number): boolean {
        return this.user.topics.some(topic => topic.id === id);
    }

    public subscribe(id: number): void {
        this.topicService.subscribe(id.toString(), this.user.id.toString()).subscribe(() => {
            this.fetchUser();
            this.snackInfo('Abonnement ajouté !')
        });
    }

    private snackInfo(message: string): void {
        this.matSnack.open(message, 'Close', { duration: 3000 });
    }

}
