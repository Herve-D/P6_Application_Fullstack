import { RouterModule, Routes } from "@angular/router";
import { PostListComponent } from "./post/list/post-list.component";
import { TopicComponent } from "./topic/topic.component";
import { ProfileComponent } from "./profile/profile.component";
import { NgModule } from "@angular/core";
import { PostDetailComponent } from "./post/detail/post-detail.component";
import { PostCreateComponent } from "./post/create/post-create.component";

const routes: Routes = [
    { path: 'post-list', title: 'Post', component: PostListComponent },
    { path: 'post-detail/:id', title: 'Detail', component: PostDetailComponent },
    { path: 'post-create', title: 'Create', component: PostCreateComponent },
    { path: 'topic', title: 'Topic', component: TopicComponent },
    { path: 'profile', title: 'Profile', component: ProfileComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FeedRoutingModule { }