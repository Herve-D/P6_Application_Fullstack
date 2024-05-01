import { NgModule } from "@angular/core";
import { PostListComponent } from "./post/list/post-list.component";
import { TopicComponent } from "./topic/topic.component";
import { ProfileComponent } from "./profile/profile.component";
import { FeedRoutingModule } from "./feed-routing.module";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PostDetailComponent } from "./post/detail/post-detail.component";
import { PostCreateComponent } from "./post/create/post-create.component";

const materialModules = [
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatSelectModule,
    MatSnackBarModule,
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