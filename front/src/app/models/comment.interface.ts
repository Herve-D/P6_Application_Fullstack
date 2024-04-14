import { User } from "./user.interface";

export interface Comment {
    id: number;
    content: string;
    user: User;
}