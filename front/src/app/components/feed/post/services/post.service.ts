import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Post } from "../../../../models/post.interface";
import { PostRequest } from "../../../../models/postRequest.interface";
import { CommentRequest } from "../../../../models/commentRequest.interface";

@Injectable({
    providedIn: 'root'
})
export class PostService {

    private pathService = 'api';

    constructor(private httpClient: HttpClient) { }

    public createPost(post: PostRequest) {
        return this.httpClient.post(`${this.pathService}/post`, post);
    }

    public getPostById(id: string): Observable<Post> {
        return this.httpClient.get<Post>(`${this.pathService}/post/${id}`);
    }

    public getPosts(): Observable<Post[]> {
        return this.httpClient.get<Post[]>(`${this.pathService}/post`);
    }

    public createCommment(comment: CommentRequest) {
        return this.httpClient.post(`${this.pathService}/comment`, comment);
    }

}