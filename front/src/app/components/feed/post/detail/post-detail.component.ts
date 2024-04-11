import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/services/feed.service';

@Component({
    selector: 'app-post-detail',
    templateUrl: './post-detail.component.html',
    styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit {

    public posts$ = this.feedService.getPosts();

    constructor(private feedService: FeedService) { }

    ngOnInit(): void { }

}
