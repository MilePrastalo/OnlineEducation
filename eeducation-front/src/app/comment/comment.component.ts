import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../model/Comment';
import {ImageDialogComponent} from '../image-dialog/image-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {CommentService} from '../service/comment.service';
import {AddComment} from '../model/AddComment';
import {Lesson} from '../model/Lesson';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit {

  @Input() comment: Comment;
  @Input() lesson: Lesson;
  replyText = '';

  constructor(public dialog: MatDialog, private commentService: CommentService) {
  }

  ngOnInit(): void {
    console.log(this.comment.user);
  }

  reply() {
    const dialogRef = this.dialog.open(ImageDialogComponent, {
      width: '250px',
      data: {text: this.replyText}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const addComment = new AddComment(result, this.lesson.id, this.comment.id);
        this.commentService.addComment(addComment).subscribe(
          response => {
            this.comment.replies.push(response);
          }
        );
      }
    });
  }
}
