import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "../../../../models/topic.interface";

@Injectable({
    providedIn: 'root'
})
export class TopicService {

    private pathService = 'api';

    constructor(private httpClient: HttpClient) { }

    public getTopics(): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${this.pathService}/topic`);
    }

    public subscribe(id: string, userId: string) {
        return this.httpClient.post(`${this.pathService}/topic/${id}/subscribe/${userId}`, null);
    }

}