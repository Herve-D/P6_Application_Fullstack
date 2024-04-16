import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "../models/topic.interface";
import { Post } from "../models/post.interface";
import { User } from "../models/user.interface";
import { PostRequest } from "../models/postRequest.interface";

@Injectable({
    providedIn: 'root'
})
export class FeedService {

    private pathService = 'api';

    constructor(private httpClient: HttpClient) { }

    public createPost(post: PostRequest) {
        return this.httpClient.post(`${this.pathService}/post`, post);
    }

    public getPosts(): Observable<Post[]> {
        return this.httpClient.get<Post[]>(`${this.pathService}/post`);
    }

    public getTopics(): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${this.pathService}/topic`);
    }

    public getSubscriptions(id: string): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${this.pathService}/user/${id}/topics`);
    }

    public updateUser(id: string, user: User) {
        return this.httpClient.put(`${this.pathService}/user/${id}`, user);
    }

    public subscribe(id: string, userId: string) {
        return this.httpClient.post(`${this.pathService}/topic/${id}/subscribe/${userId}`, null);
    }

    public unSubscribe(id: string, userId: string) {
        return this.httpClient.delete(`${this.pathService}/topic/${id}/subscribe/${userId}`);
    }

}