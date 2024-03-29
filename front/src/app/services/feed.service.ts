import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "../models/topic.interface";

@Injectable({
    providedIn: 'root'
})
export class FeedService {

    private pathService = 'api/topic';

    constructor(private httpClient: HttpClient) { }

    public all(): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(this.pathService);
    }

}