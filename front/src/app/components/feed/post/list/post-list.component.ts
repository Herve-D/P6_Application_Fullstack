import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/components/feed/post/services/post.service';

@Component({
    selector: 'app-post-list',
    templateUrl: './post-list.component.html',
    styleUrls: ['./post-list.component.scss'],
})
export class PostListComponent implements OnInit {

    public posts$ = this.feedService.getPosts();

    constructor(private feedService: PostService) { }

    ngOnInit(): void { }

}
