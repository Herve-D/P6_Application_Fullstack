import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CommentRequest } from 'src/app/models/commentRequest.interface';
import { Post } from 'src/app/models/post.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/components/auth/services/auth.service';
import { PostService } from 'src/app/components/feed/post/services/post.service';

@Component({
    selector: 'app-post-detail',
    templateUrl: './post-detail.component.html',
    styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit {

    public post: Post | undefined;
    public postId: string;
    public commentForm!: FormGroup;

    constructor(private route: ActivatedRoute,
        private feedService: PostService,
        private authService: AuthService,
        private fb: FormBuilder) {
        this.postId = this.route.snapshot.paramMap.get('id')!;
    }

    ngOnInit(): void {
        this.fetchPost();
        this.initForm();
    }

    public back(): void {
        window.history.back();
    }

    private fetchPost(): void {
        this.feedService.getPostById(this.postId).subscribe((post: Post) => {
            this.post = post;
        })
    }

    private initForm(): void {
        this.commentForm = this.fb.group({
            content: ['', [Validators.required]]
        });
    }

    private clearForm(): void {
        this.commentForm.reset();
        Object.keys(this.commentForm.controls).forEach((key) => {
            this.commentForm.controls[key].setErrors(null);
        })
    }

    public submit(): void {
        if (this.commentForm.valid) {
            this.authService.me().subscribe((user: User) => {
                const comment = this.commentForm.value as CommentRequest;
                comment.postId = +this.postId;
                comment.userId = user.id;
                this.feedService.createCommment(comment).subscribe(
                    () => {
                        this.fetchPost();
                        this.clearForm();
                    }
                )
            })
        }
    }

}
