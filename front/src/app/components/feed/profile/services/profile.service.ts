import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Topic } from "../../../../models/topic.interface";
import { User } from "../../../../models/user.interface";

@Injectable({
    providedIn: 'root'
})
export class ProfileService {

    private pathService = 'api';

    constructor(private httpClient: HttpClient) { }

    public getSubscriptions(id: string): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${this.pathService}/user/${id}/topics`);
    }

    public updateUser(id: string, user: User) {
        return this.httpClient.put(`${this.pathService}/user/${id}`, user);
    }

    public unSubscribe(id: string, userId: string) {
        return this.httpClient.delete(`${this.pathService}/topic/${id}/subscribe/${userId}`);
    }

}