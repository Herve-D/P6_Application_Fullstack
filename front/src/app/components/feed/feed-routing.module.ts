import { RouterModule, Routes } from "@angular/router";
import { PostComponent } from "./post/post.component";
import { TopicComponent } from "./topic/topic.component";
import { ProfileComponent } from "./profile/profile.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
    { path: 'post', title: 'Post', component: PostComponent },
    { path: 'topic', title: 'Topic', component: TopicComponent },
    { path: 'profile', title: 'Profile', component: ProfileComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FeedRoutingModule { }