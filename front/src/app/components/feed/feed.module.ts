import { NgModule } from "@angular/core";
import { PostComponent } from "./post/post.component";
import { TopicComponent } from "./topic/topic.component";
import { ProfileComponent } from "./profile/profile.component";
import { FeedRoutingModule } from "./feed-routing.module";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";

const materialModules = [
    MatCardModule,
]

@NgModule({
    declarations: [
        PostComponent,
        TopicComponent,
        ProfileComponent
    ],
    imports: [
        FeedRoutingModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ...materialModules
    ]
})
export class FeedModule { }