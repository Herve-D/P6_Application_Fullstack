import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/services/feed.service';

@Component({
    selector: 'app-post-list',
    templateUrl: './post-list.component.html',
    styleUrls: ['./post-list.component.scss'],
})
export class PostListComponent implements OnInit {

    public posts$ = this.feedService.getPosts();

    constructor(private feedService: FeedService) { }

    ngOnInit(): void { }

}
