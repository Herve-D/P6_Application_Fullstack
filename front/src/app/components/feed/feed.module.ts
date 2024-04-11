import { NgModule } from "@angular/core";
import { PostListComponent } from "./post/list/post-list.component";
import { TopicComponent } from "./topic/topic.component";
import { ProfileComponent } from "./profile/profile.component";
import { FeedRoutingModule } from "./feed-routing.module";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PostDetailComponent } from "./post/detail/post-detail.component";
import { PostCreateComponent } from "./post/create/post-create.component";

const materialModules = [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule
]

@NgModule({
    declarations: [
        PostListComponent,
        PostDetailComponent,
        PostCreateComponent,
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