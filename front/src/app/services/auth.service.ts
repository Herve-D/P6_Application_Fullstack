import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Register } from "../models/register.interface";
import { Observable } from "rxjs";
import { AuthResponse } from "../models/authResponse.interface";
import { Login } from "../models/login.interface";
import { User } from "../models/user.interface";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private pathService = 'api';

    constructor(private httpClient: HttpClient) { }

    public register(register: Register): Observable<AuthResponse> {
        return this.httpClient.post<AuthResponse>(`${this.pathService}/auth/register`, register);
    }

    public login(login: Login): Observable<AuthResponse> {
        return this.httpClient.post<AuthResponse>(`${this.pathService}/auth/login`, login);
    }

    public me(): Observable<User> {
        return this.httpClient.get<User>(`${this.pathService}/user/me`);
    }
}