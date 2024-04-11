import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "../models/topic.interface";
import { Post } from "../models/post.interface";
import { User } from "../models/user.interface";

@Injectable({
    providedIn: 'root'
})
export class FeedService {

    private pathService = 'api';

    constructor(private httpClient: HttpClient) { }

    public getTopics(): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${this.pathService}/topic`);
    }

    public getSubscriptions(id: string): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${this.pathService}/user/${id}/topics`);
    }

    public getPosts(): Observable<Post[]> {
        return this.httpClient.get<Post[]>(`${this.pathService}/post`);
    }

    public updateUser(id: string, user: User) {
        return this.httpClient.put(`${this.pathService}/user/${id}`, user);
    }

}