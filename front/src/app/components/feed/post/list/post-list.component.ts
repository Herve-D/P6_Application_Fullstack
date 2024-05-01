import { Component, OnInit } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { PostService } from 'src/app/components/feed/post/services/post.service';
import { Post } from 'src/app/models/post.interface';

@Component({
    selector: 'app-post-list',
    templateUrl: './post-list.component.html',
    styleUrls: ['./post-list.component.scss'],
})
export class PostListComponent implements OnInit {

    public posts$!: Observable<Post[]>;
    public isDescending = true;

    constructor(private postService: PostService) { }

    ngOnInit(): void {
        this.postService.getSubscriptionPosts().subscribe((posts: Post[]) => {
            posts.sort((a, b) => new Date(b.created_at).getTime() - new Date(a.created_at).getTime());
            this.posts$ = of(posts);
        })
    }

    public sortPosts(): void {
        this.posts$ = this.posts$.pipe(
            map((posts: Post[]) => {
                return posts.sort((a, b) => {
                    const order = new Date(a.created_at).getTime() - new Date(b.created_at).getTime();
                    return this.isDescending ? -order : order;
                });
            })
        );
        this.isDescending = !this.isDescending;
    }

}
