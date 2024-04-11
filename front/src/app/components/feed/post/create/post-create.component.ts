import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/services/feed.service';

@Component({
    selector: 'app-post-create',
    templateUrl: './post-create.component.html',
    styleUrls: ['./post-create.component.scss'],
})
export class PostCreateComponent implements OnInit {

    public posts$ = this.feedService.getPosts();

    constructor(private feedService: FeedService) { }

    ngOnInit(): void { }

}
