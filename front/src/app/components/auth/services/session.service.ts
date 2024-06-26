import { Injectable } from "@angular/core";
import { User } from "../../../models/user.interface";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class SessionService {
    public isLogged = false;
    public user: User | undefined;

    private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

    public logIn(user: User): void {
        this.user = user;
        this.isLogged = true;
        this.next();
    }

    public logOut(): void {
        localStorage.removeItem('token');
        this.user = undefined;
        this.isLogged = false;
        this.next();
    }

    private next(): void {
        this.isLoggedSubject.next(this.isLogged);
    }
}