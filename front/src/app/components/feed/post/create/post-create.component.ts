import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostRequest } from 'src/app/models/postRequest.interface';
import { Topic } from 'src/app/models/topic.interface';
import { PostService } from 'src/app/components/feed/post/services/post.service';
import { TopicService } from '../../topic/services/topic.service';

@Component({
    selector: 'app-post-create',
    templateUrl: './post-create.component.html',
    styleUrls: ['./post-create.component.scss'],
})
export class PostCreateComponent {

    public onError = false;
    public topics$: Observable<Topic[]> = this.topicService.getTopics();
    public form = this.fb.group({
        topic: ['', [Validators.required]],
        title: ['', [Validators.required]],
        content: ['', [Validators.required]]
    });

    constructor(private fb: FormBuilder,
        private postService: PostService,
        private topicService: TopicService,
        private router: Router) { }

    public submit(): void {
        if (this.form.valid) {
            const post = this.form.value as PostRequest;
            this.postService.createPost(post).subscribe(
                () => {
                    this.router.navigate(['/post-list'])
                },
                error => this.onError = true
            );
        }
    }

}
