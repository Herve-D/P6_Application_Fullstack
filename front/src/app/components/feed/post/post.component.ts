import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/services/feed.service';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnInit {

    public topics$ = this.feedService.all();

    constructor(private feedService: FeedService) { }

    ngOnInit(): void { }

}
