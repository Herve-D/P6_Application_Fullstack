import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostRequest } from 'src/app/models/postRequest.interface';
import { Topic } from 'src/app/models/topic.interface';
import { FeedService } from 'src/app/services/feed.service';

@Component({
    selector: 'app-post-create',
    templateUrl: './post-create.component.html',
    styleUrls: ['./post-create.component.scss'],
})
export class PostCreateComponent {

    public onError = false;
    public topics$: Observable<Topic[]> = this.feedService.getTopics();
    public form = this.fb.group({
        topic: ['', [Validators.required]],
        title: ['', [Validators.required]],
        content: ['', [Validators.required]]
    });

    constructor(private fb: FormBuilder,
        private feedService: FeedService,
        private router: Router) { }

    public submit(): void {
        if (this.form.valid) {
            const post = this.form.value as PostRequest;
            this.feedService.createPost(post).subscribe(
                () => {
                    this.router.navigate(['/post-list'])
                },
                error => this.onError = true
            );
        }
    }

}
