import {Component, OnInit} from '@angular/core';
import {LessonService} from '../service/lesson.service';
import {Lesson} from '../model/Lesson';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AddComment} from '../model/AddComment';
import {CommentService} from '../service/comment.service';

@Component({
  selector: 'app-lesson',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.scss']
})
export class LessonComponent implements OnInit {
  lesson: Lesson;
  lessonId: number;
  lessonContent: HTMLElement;
  addCommentForm: FormGroup;

  constructor(private lessonService: LessonService,
              private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private commentService: CommentService) {
  }

  get commentText() {
    return this.addCommentForm.controls.commentText.value as string;
  }

  ngOnInit(): void {
    this.addCommentForm = this.formBuilder.group({
      commentText: ['', [
        Validators.required
      ]]
    });
    this.lessonContent = document.getElementById('lessonContent') as HTMLElement;
    this.lessonId = Number(this.route.snapshot.paramMap.get('id'));
    this.lessonService.getLesson(this.lessonId).subscribe(
      response => {
        this.lesson = response;
        console.log(this.lesson);
        this.lessonContent.innerHTML = '';
        const split = this.lesson.lessonContent.split('\n');
        for (const part of split) {
          this.lessonContent.innerHTML = this.lessonContent.innerHTML + '<br/>' + part;
        }
      }
    );
  }

  delete() {
    this.lessonService.deleteLesson(this.lessonId).subscribe(
      response => {
        this.router.navigateByUrl('course/' + this.lesson.courseId);
      }
    );
  }

  onCommentSubmit() {
    console.log(this.commentText);
    const addComment = new AddComment(this.commentText, this.lesson.id, null);
    this.commentService.addComment(addComment).subscribe(
      response => {
        this.lesson.comments.push(response);
      }
    );
  }

  edit() {
    this.router.navigateByUrl('edit-lesson/' + this.lessonId);
  }

}
