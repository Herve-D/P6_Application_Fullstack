import { Comment } from "./comment.interface";
import { Topic } from "./topic.interface";
import { User } from "./user.interface";

export interface Post {
    id: number;
    title: string;
    content: string;
    created_at: Date;
    user: User;
    topic: Topic;
    comments: Comment[];
}