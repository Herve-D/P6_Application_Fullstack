import { Component, OnInit } from '@angular/core';
import { FeedService } from 'src/app/services/feed.service';

@Component({
    selector: 'app-topic',
    templateUrl: './topic.component.html',
    styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit {

    public topics$ = this.feedService.getTopics();

    constructor(private feedService: FeedService) { }

    ngOnInit(): void { }

}
